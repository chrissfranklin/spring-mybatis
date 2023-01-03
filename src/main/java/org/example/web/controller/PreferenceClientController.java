package org.example.web.controller;

import org.example.rest.preference.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

//@Profile("!prod")
@Controller
@CrossOrigin
@Slf4j
public class PreferenceClientController
{
  private static final String EMPTY = "";

  @Autowired
  PreferenceService preferenceService;

  @Autowired
  Environment environment;

  // simplerclient
  // index.html - simple landing page with links. UI routes from here to /list
  @RequestMapping(value="/")
  public String index(Model model)
    throws Exception
  {
    return "index";
  }

  // main.html - main landing page
  @RequestMapping(value="/main")
  public String main(Model model)
    throws Exception
  {
    return "main";
  }


  // report1.html - report1 page
  @RequestMapping(value="/report1")
  public String report1(Model model)
    throws Exception
  {
    return "report1";
  }

  // report1.html - report2 page
  @RequestMapping(value="/report2")
  public String report2(Model model)
    throws Exception
  {
    return "report2";
  }


  // list.html - show list of all permissionIds fetched from db via ajax call
  @RequestMapping(value="/list")
  public String list(Model model)
    throws Exception
  {
    /*
    // Add the list of permissions to model
    model.addAttribute("permissionEntityList", this.preferenceService.findAll());
    //
    // Add spring profiles (local, dev, qa, prod) to model
    String springProfiles = Arrays.toString( this.environment.getActiveProfiles() );
    model.addAttribute("springProfiles", springProfiles);

    // Add localhost URLs
    String localUiUrl  = "http://localhost:4200";
    String localApiUrl = "http://localhost:8080";
    model.addAttribute("localUiUrl",  localUiUrl);
    model.addAttribute("localApiUrl", localApiUrl);

    // Add UI and API URLs to model
    String uiUrl       = EMPTY;
    String apiUrl      = EMPTY;
    if(springProfiles.contains("local")) {
      uiUrl  = localUiUrl;
      apiUrl = localApiUrl;
    }
    else if(springProfiles.contains("dev")) {
      uiUrl  = "https://lynxanalyticsui-dev.dev.aks.west.us.mckesson.com";   //"https://ui.lynxanalytics-dv.mcksh.io";
      apiUrl = "https://lynxanalyticsrest-dev.dev.aks.west.us.mckesson.com"; //"https://api.lynxanalytics-dv.mcksh.io";
    }
    else if(springProfiles.contains("qa")) {
      uiUrl   = "https://lynxanalyticsui-qa.dev.aks.west.us.mckesson.com";   //"https://ui.lynxanalytics-qa.mcksh.io";
      apiUrl  = "https://lynxanalyticsrest-qa.dev.aks.west.us.mckesson.com"; //"https://api.lynxanalytics-qa.mcksh.io";
    }
    else if(springProfiles.contains("prod")) {
      uiUrl   = "https://lynxanalyticsui-prod.aks.west.us.mckesson.com";   //"https://ui.lynxanalytics-pd.mcksh.io/";
      apiUrl  = "https://lynxanalyticsrest-prod.aks.west.us.mckesson.com"; //"https://api.lynxanalytics-pd.mcksh.io";
    }
    model.addAttribute("uiUrl", uiUrl);
    model.addAttribute("apiUrl", apiUrl);

    // Show urls
    log.debug("SimplerClientController - springProfiles: " + springProfiles);
    log.debug("SimplerClientController - localUiUrl:  " + localUiUrl);
    log.debug("SimplerClientController - localApiUrl: " + localApiUrl);
    log.debug("SimplerClientController - uiUrl:  " + uiUrl);
    log.debug("SimplerClientController - apiUrl: " + apiUrl);
     */
    return "list";
  }

}
