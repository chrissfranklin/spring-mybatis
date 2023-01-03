package org.example.rest.preference.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import org.example.rest.preference.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@AllArgsConstructor
@RequestMapping("/api")
public class PreferenceController
{
  @Autowired
  PreferenceService preferenceService;

  //
  // FETCH
  //
  // Fetch a List of PreferenceEntity objects for the given userId and practiceId
  // Used to fetch a list of all the preferences for the drop-down in UI for all reports across the site
  @RequestMapping(value =
  {
    "/preference/fetch/{userId}/{practiceId}"
  }, method = RequestMethod.GET)
  public ResponseEntity<String> getPreferenceEntityListByUserIdAndPracticeId
  (
    HttpServletRequest request,
    @PathVariable("userId")     int userId,
    @PathVariable("practiceId") int practiceId
  )
  {
    log.trace("calling PreferenceService.findPreferenceEntitySetByUserIdAndPracticeId()...");
    return this.preferenceService.findPreferenceEntityListByUserIdAndPracticeId(request, userId, practiceId);
  }


/*
  // Fetch a List of PreferenceEntity objects by preferenceSetId and reportName
  @RequestMapping(value =
  {
    "/preferenceitem/fetch/{preferenceSetId}"
  }, method = RequestMethod.GET)
  public ResponseEntity<String> getPreferenceEntityListByPreferenceSetId
  (
    HttpServletRequest request,
    @PathVariable("preferenceSetId") int preferenceEntitySetId,
    @PathVariable("reportName")      String reportName
  )
  {
    log.trace("calling PreferenceService.findByPreferenceSetId()...");
    return this.preferenceService.findPreferenceEntityListByPreferenceSetId(request, preferenceEntitySetId);
  }


  // Fetch a List of PreferenceEntity objects by userId, practiceId, reportName
  @RequestMapping(value =
  {
    "/preferenceitem/fetch/{userId}/{practiceId}/{preferenceSetId}/{reportName}"
  }, method = RequestMethod.GET)
  public ResponseEntity<String> getPreferenceEntityListByUserIdAndPracticeIdAndReportName
  (
    HttpServletRequest request,
    @PathVariable("userId")     int userId,
    @PathVariable("practiceId") int practiceId,
    @PathVariable("reportName") String reportName
  )
  {
    log.trace("calling PreferenceService.findAllPreferenceByUserId()...");
    return this.preferenceService.findPreferenceEntityListByUserIdAndPracticeIdAndReportName(request, userId, practiceId, reportName);
  }


  @RequestMapping(value =
    {
      "/preferenceitem/fetch/all"
    }, method = RequestMethod.GET)
  public ResponseEntity<String> getAll(HttpServletRequest request)
  {
    log.trace("calling preferenceService.findAll()...");
    return this.preferenceService.findAllPreferences(request);
  }
*/

  //
  // SAVE
  //
/*
  // save PreferenceEntitySet value
  @RequestMapping(value =
  {
    "/preference/save/{userId}/{practiceId}/{preferenceName}"
  }, method = RequestMethod.GET)
  public ResponseEntity<String> savePreference
  (
    HttpServletRequest request,
    @PathVariable("userId")         int userId,
    @PathVariable("practiceId")     int practiceId,
    @PathVariable("preferenceName") String preferenceName
  )
  {
    log.trace("calling PreferenceService.savePreferenceSet()...");
    return this.preferenceService.savePreference(request, userId, practiceId,  preferenceName);
  }
*/


  // save PreferenceEntity value
  @RequestMapping(value =
  {
    "/preference/save/{userId}/{practiceId}/{preferenceName}/{reportName}/{itemName}/{itemValue}"
  }, method = RequestMethod.GET)
  public ResponseEntity<String> savePreference
  (
    HttpServletRequest request,
    @PathVariable("userId")         int userId,
    @PathVariable("practiceId")     int practiceId,
    @PathVariable("preferenceName") String preferenceName,
    @PathVariable("reportName")     String reportName,
    @PathVariable("itemName")       String itemName,
    @PathVariable("itemValue")      String itemValue
  )
  {
    log.trace("calling PreferenceService.savePreference()...");
    return this.preferenceService.savePreference(request, userId, practiceId, preferenceName, reportName, itemName, itemValue);
  }


}
