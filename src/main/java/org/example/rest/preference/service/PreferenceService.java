package org.example.rest.preference.service;

import org.example.common.service.BaseService;
import org.example.rest.preference.entity.PreferenceEntity;
import org.example.rest.preference.entity.PreferenceItemEntity;
import org.example.rest.preference.mapper.PreferenceEntityMapper;
import org.example.rest.preference.mapper.PreferenceItemEntityMapper;
import org.example.util.MyTimer;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@PropertySource("classpath:application.properties")
@Transactional
@Slf4j
@AllArgsConstructor
public class PreferenceService extends BaseService
{

  public static final String SPLUNK_EVENT_SQL_PREFERENCESET_QUERY = "SPLUNK_EVENT_SQL_PREFERENCESET_QUERY";
  public static final String SPLUNK_EVENT_SQL_PREFERENCES_QUERY = "SPLUNK_EVENT_SQL_PREFERENCES_QUERY";

  public static final String SPLUNK_EVENT_SQL_PREFERENCES_CHECK_QUERY = "SPLUNK_EVENT_SQL_PREFERENCES_CHECK_QUERY";

  @Autowired
  private final PreferenceEntityMapper preferenceEntityMapper;

  @Autowired
  private final PreferenceItemEntityMapper preferenceItemEntityMapper;

  //
  // FIND
  //

  public ResponseEntity<String> findPreferenceEntityListByUserIdAndPracticeId(HttpServletRequest request, int userId, int practiceId)
  {
    log.trace("TOP: PreferenceService.findPreferenceEntityListByUserIdAndPracticeId()...");
    log.trace("userId: " + userId);
    log.trace("practiceId: " + practiceId);
    MyTimer t = new MyTimer();
    t.start();
    List<PreferenceEntity> preferenceEntityList = this.preferenceEntityMapper.selectPreferenceEntityListByUserIdAndPracticeId(userId, practiceId);
    long elapsedMillis = t.stop();
    log.debug(SPLUNK_EVENT_SQL_PREFERENCESET_QUERY + ": PreferenceRepository.findPreferenceEntityListByUserIdAndPracticeId(). userId: " +
      userId + ". MyTimer.elapsedMillis: " + elapsedMillis);
    // TODO: handle case where preferenceEntity is empty i.e. create new
    if(preferenceEntityList == null || preferenceEntityList.size() < 1) {
      log.error("No PreferenceEntityList values found for userId: " + userId);
    } else {
      log.trace("preferenceEntityList size: : " + preferenceEntityList.size());
      // fetch related PreferenceItemEntity records
      for(PreferenceEntity preferenceEntity : preferenceEntityList) {
        List<PreferenceItemEntity> preferenceItemEntityList = preferenceItemEntityMapper.selectPreferenceItemEntityListByPreferenceId(preferenceEntity.getId());
        preferenceEntity.setPreferenceItemEntityList(preferenceItemEntityList);
      }
    }
    log.trace("PreferenceService.findPreferenceEntityListByUserIdAndPracticeId(): preferenceEntityList: " + preferenceEntityList.toString());
    return this.packageResponse(request, serialize(preferenceEntityList));
  }


  //
  // SAVE
  //


  public ResponseEntity<String> savePreference(HttpServletRequest request, int userId, int practiceId, String preferenceName, String reportName, String itemName, String itemValue)
  {
    String rezult = "";
    // first determine if the preferenceEntity object already exists in db
    PreferenceEntity preferenceEntity = preferenceEntityMapper.selectPreferenceEntityByUserIdAndPracticeIdAndPreferenceName(userId, practiceId, preferenceName);
    if(preferenceEntity == null) {
      log.trace("existing preferenceEntity is empty - will proceed with insert...");
      preferenceEntityMapper.insertPreference(userId, practiceId, preferenceName);
      // get the preferenceId value of the record we just inserted
      preferenceEntity = preferenceEntityMapper.selectPreferenceEntityByUserIdAndPracticeIdAndPreferenceName(userId, practiceId, preferenceName);
      log.trace("successfully inserted new preferenceEntity record and got back preferenceId: " + preferenceEntity.getId());
      //rezult = "insert success";
    } else {
      log.trace("preferenceEntity record already exists - will not insert new");
    }
    // next determine if preferenceItemEntity object with same itemName already exists in db
    PreferenceItemEntity preferenceItemEntity = preferenceItemEntityMapper.selectPreferenceItemEntityByPreferenceIdAndReportNameAndItemName(preferenceEntity.getId(), reportName, itemName);
    if(preferenceItemEntity == null) {
      log.trace("existing preferenceItemEntity is empty - inserting new item...");
      preferenceItemEntityMapper.insertPreferenceItem(preferenceEntity.getId(), reportName, itemName, itemValue);
      log.trace("successfully inserted new preferenceItemEntity record");
    } else {
      log.trace("existing preferenceItemEntity is not empty - checking itemValue...");
      if(preferenceItemEntity.getItemValue() == itemValue) {
        log.trace("itemValues match - will not update...");
      } else {
        log.trace("itemValues do not match - updating itemValue...");
        preferenceItemEntityMapper.updatePreferenceItem(preferenceEntity.getId(), reportName, itemName, itemValue);
        log.trace("successfully updated preferenceItemEntity with preferenceId: " + preferenceEntity.getId());
      }
    }
    return this.packageResponse(request, serialize(rezult));
  }

}