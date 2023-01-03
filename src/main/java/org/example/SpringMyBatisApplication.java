package org.example;

import lombok.extern.slf4j.Slf4j;
//import lombok.extern.slf4j.XSlf4j;
import org.apache.ibatis.type.MappedTypes;
import org.example.rest.preference.entity.PreferenceEntity;
import org.example.rest.preference.entity.PreferenceItemEntity;
import org.example.rest.preference.mapper.PreferenceEntityMapper;
import org.example.rest.preference.mapper.PreferenceItemEntityMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@MapperScan("org.example.rest.preference.mapper")
@MappedTypes({PreferenceEntity.class})
@Slf4j
public class SpringMyBatisApplication implements CommandLineRunner
{
  @Autowired
  PreferenceEntityMapper preferenceEntityMapper;

  @Autowired
  PreferenceItemEntityMapper preferenceItemEntityMapper;

  public static void main(String[] args)
  {
    log.trace("before SpringApplication.main()...");
    SpringApplication.run(SpringMyBatisApplication.class, args);
    log.trace("... after SpringApplication.main()");
  }

  @Override
  public void run(String... args) throws Exception
  {
    log.trace("before SpringApplication.run()...");


    int userId            = 1;
    int practiceId        = 123456789;
    String preferenceName = "chris pref 1";
    String reportName     = "report1";

    // 1: Get all Preferences for given user and practice
    List<PreferenceEntity> preferenceEntityList = preferenceEntityMapper.selectPreferenceEntityListByUserIdAndPracticeId(userId, practiceId);
    log.trace("1: PreferenceEntity List: " + preferenceEntityList);


    // 2: Get all Preferences and Values for given preference name (preferenceId)
    //PreferenceEntity preferenceEntity = preferenceMapper.getPreferenceEntityByPreferenceId(int preferenceId);
    PreferenceEntity preferenceEntity = preferenceEntityList.get(0);
    int preferenceId = preferenceEntity.getId();
    List<PreferenceItemEntity> preferenceItemEntityList = preferenceItemEntityMapper.selectPreferenceItemEntityListByPreferenceIdAndReportName(preferenceId, reportName);
    preferenceEntity.setPreferenceItemEntityList(preferenceItemEntityList);
    log.trace("2: PreferenceEntity List for preferenceId: " + preferenceId + " and reportName: " + reportName + ": " + preferenceEntityList);




    // 3: Save a new Preference record
    String preferenceName2 = "new pref";
    preferenceEntityMapper.insertPreference(userId, practiceId, preferenceName2);
    preferenceEntity = preferenceEntityMapper.selectPreferenceEntityByUserIdAndPracticeIdAndPreferenceName(userId, practiceId, preferenceName2);
    log.trace("inserted new preferenceEntity: " + preferenceEntity);
    reportName = "new report";

    // 4: Save some PreferenceItemEntity records
    preferenceItemEntityMapper.insertPreferenceItem(preferenceEntity.getId(), reportName, "newWidget1", "newWidgetValue1");
    preferenceItemEntityMapper.insertPreferenceItem(preferenceEntity.getId(), reportName, "newWidget2", "newWidgetValue2");

    preferenceItemEntityList = preferenceItemEntityMapper.selectPreferenceItemEntityListByPreferenceIdAndReportName( preferenceEntity.getId(), reportName );
    log.trace("PreferenceEntityItemEntity List: " + preferenceItemEntityList);



    /*
    //PreferenceEntity preferenceEntity = preferenceMapper.getPreferenceEntityByUserIdAndPracticeIdAndPreferenceName(userId, practiceId, preferenceName);
    //log.trace("PreferenceEntity: " + preferenceEntity);

    //preferenceEntity.setPreferenceItemEntityList(preferenceItemEntityList);
    log.trace("ALL LOADED:::: ");
    //log.trace("PreferenceEntity: " + preferenceEntity);
    */
    log.trace("... after SpringApplication.run()");
  }
}
