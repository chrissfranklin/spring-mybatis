package org.example.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;
//import oracle.jdbc.oracore.Util;

@PropertySource("classpath:application.properties")
@Slf4j
public class BaseService
{


  //@Value("${query.page}")
  private String defaultPageSize;

/*
  //@Value("${license.expiry}")
  String expiration;

  private static final String HDR_LICENSE_NAME = "X-License";
  // private static final String TEST_LICENSE = "XX-0000008015-27346-1521077688";
  // private static final String TEST_LICENSE = "XX-0000008015|0000008015|0000008015-27346-0000008015-1521077688";

  private static final String HDR_PID = "pid";
  private static final String HDR_UID = "uid";


  // License Inner Class
  protected static final class License
  {
    //String customerNumber;
    String customerNumberPipeString;
    String parentCustomerNumber;
    long   customerId;
    long   expiry;

    // 1-arg constructor
    public License(String fromHeader)
    {
      log.trace("BaseService.License(1 arg constructor) - fromHeader: " + fromHeader);
      if (fromHeader != null && fromHeader.length() > 0) {
        String[] parts = fromHeader.split("-");
        if (parts.length == 5) {
          this.customerNumberPipeString = parts[1];
          //this.parentCustomerNumber     = Long.parseLong(parts[2]);
          this.parentCustomerNumber     = parts[2];
          this.customerId               = Long.parseLong(parts[3]);
          this.expiry                   = Long.parseLong(parts[4]);
        }
      }
      log.trace("End of BaseService.License() constructor....");
    }

    // 4-arg constructor
    //public License(String customerNumber, long customerId, long when)
    public License(String customerNumberPipeString, String parentCustomerNumber, long customerId, long when)
    {
      log.trace("BaseService.License(4 arg constructor) - "         +
          "  customerNumberPipeString: " + customerNumberPipeString +
          ", parentCustomerNumber:     " + parentCustomerNumber     + 
          ", customerId:               " + customerId               + 
          ", when:                     " + when);
      this.customerNumberPipeString = customerNumberPipeString;
      this.parentCustomerNumber     = parentCustomerNumber;
      this.customerId               = customerId;
      this.expiry                   = when;
    }

    // Note: TODO: deprecate this method - rename to getLicenseString()
    // @deprecated - see getLicenseString() below
    public String header()
    {
      log.trace("BaseService.License.header()...");
      return "XX-" + this.customerNumberPipeString + "-" + this.parentCustomerNumber + "-" + this.customerId + "-" + this.expiry;
    }

    public String getLicenseString()
    {
      log.trace("BaseService.License.getLicenseString()...");
      return this.header();
    }

    // Return a List of Strings of customerNumbers
    public List<String> getSiteCustomerNumberList()
    {
      String[] siteCustomerNumberArray = this.customerNumberPipeString.split("\\|");
      List<String> siteCustomerNumberList = Arrays.stream(siteCustomerNumberArray).collect(Collectors.toList());
      return siteCustomerNumberList;
    }
    

    public String getParentCustomerNumberAsString()
    {
      Long myLong = new Long(this.parentCustomerNumber);
      return myLong.toString();
    }

    public String toString()
    {
      return "License.toString(): " +
             "  customerNumberPipeString: " + customerNumberPipeString +
             ", parentCustomerNumber: "     + parentCustomerNumber +
             ", customerId: "               + customerId +
             ", expiry: "                   + (new Date(expiry * 1000));
    }

  } // end License Inner Class


  
  // alias for License.getSiteCustomerNumberList()
  public List<String> getSiteCustomerNumberList(HttpServletRequest request)
  {
    log.trace("BaseService.getSiteCustomerNumberList() - Calling BaseService.getExistingLicense()...");
    return this.getExistingLicense(request).getSiteCustomerNumberList();
  }

  
  // Get the xLicense String from header and validate it by comparing License.expiry vs currentTime
  // Called by AnalyticsAspect.validateUser()
  protected boolean licenseExpired(HttpServletRequest request)
  {
    log.trace("===============================================TOP: BaseService.licenseExpired()...");
    Long expiry = this.getExistingLicense(request).expiry;    
    Date d1 = new Date(expiry * 1000);
    LocalDate expiryTime = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    Date currentDate = new Date();
    LocalDate currentTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();    
    boolean rezult = currentTime.compareTo(expiryTime) > 0;
    log.trace("comparing currentTime '" + currentTime + "' to expiryTime '" + expiryTime + "'... rezult = '" + rezult + "'");
    return rezult;
  }  
  

  // Get xLicense String from request header, then create new License object with it
  // Note: This method only called within BaseService (private)
  protected License getExistingLicense(HttpServletRequest request)
  {
    String licenseString = request.getHeader(HDR_LICENSE_NAME);
    log.trace("BaseService.getExistingLicense(): licenseString: " + licenseString);        
    License license = null;
    if(licenseString != null) {
      license = new License(licenseString);
    }
    //log.trace("BaseService.getExistingLicense()... after creating license: " + license.toString());
    return license; 
  }

  // Note: This method only called within BaseService (private)
  //private License getNewLicense(String customerNumber, long customerId)
  private License getNewLicense(String customerNumberListString, String parentCustomerNumber, long customerId)
  {
    long now = System.currentTimeMillis() / 1000;
    long then = now + Integer.valueOf(expiration) * 60;
    log.trace("BaseService.getNewLicense() - " +
      "  customerNumberListString: " + customerNumberListString +
      ", parentCustomerNumber: "     + parentCustomerNumber +
      ", customerId: "               + customerId +
      ", then: "                     + then);
    return new License(customerNumberListString, parentCustomerNumber, customerId, then);  // TODO: rename 'then' to a dateNamedVariable
  }

*/


  protected ResponseEntity<String> packageResponse(HttpServletRequest request, String resultString)
  {
    HttpHeaders headers = new HttpHeaders();
    return new ResponseEntity<String>(resultString, headers, HttpStatus.OK); // empty headers for now
  }



/*
  // Note: this method called by many *Service subclasses (protected)
  protected ResponseEntity<String> packageResponse(HttpServletRequest request, String resultString)
  {
    log.trace("BaseService.packageResponse() - before calling this.getExistingLicense()... resultString: " + resultString);
    License existingLicense = this.getExistingLicense(request);
    if (existingLicense != null) {
      log.trace("in BaseService.packageResponse() - about to calling packageInitialResponse()...");
      return this.packageInitialResponse(existingLicense.customerNumberPipeString,
                                         existingLicense.parentCustomerNumber,
                                         existingLicense.customerId,
                                         resultString);
    }
    else {
      log.debug("Could not get license from request via getExistingLicense() so will call packageAuthorizedResponse()...");
      return this.packageAuthorizedResponse(Long.toString(this.getPermissionId(request)), resultString);
    }
  }
*/

/*

  // Note: this method only called by packageResponse() method above (private)
  // and does not include x-license header
  private ResponseEntity<String> packageAuthorizedResponse(String pid, String uid)
  {
    log.trace("===============================================TOP: BaseService.packageAuthorizedResponse()...");
    HttpHeaders headers = new HttpHeaders();
    try {
      headers.set("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, x-license, pid, uid");
      headers.set("Access-Control-Allow-Credentials", "true");
      headers.set("Access-Control-Expose-Headers", "x-license, uid, pid"); // TODO: synchronize x-license name here
      headers.set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
      headers.add(HDR_PID, pid);
      headers.add(HDR_UID, uid);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    // here we intentionally return a response with empty content but header content shown above
    return new ResponseEntity<String>(null, headers, HttpStatus.OK);
  }

  // protected ResponseEntity<String> packageInitialResponse(String customerNumber, Long customerId, String resultStr)
  // The customerNumber String param in this method is actually a comma-separated list of customerNumbers representing
  // customer 'sites' or locations
  // protected ResponseEntity<String> packageInitialResponse(String customerNumber, Long customerId, Long
  // parentCustomerNumber, String resultStr)
  //protected ResponseEntity<String> packageInitialResponse(String customerNumber, Long customerId, String resultStr)
  protected ResponseEntity<String> packageInitialResponse(String customerNumberListString, String parentCustomerNumber, Long parentCustomerId, String resultStr)
  {
    log.trace("===============================================TOP: BaseService.packageInitialResponse()...");
    log.trace("BaseService.packageInitialResponse() - " +
      "customerNumberListString: " + customerNumberListString +
      ", parentCustomerNumber: "   + parentCustomerNumber     +
      ", parentCustomerId: "       + parentCustomerId         +
      ", resultStr: "              + resultStr);
    // log.trace("BaseService.packageInitialResponse() - customerNumber: " + customerNumber + ", customerId: " +
    // customerId +
    // ", parentCustomerNumber: " + parentCustomerNumber + ", resultStr: " + resultStr);
    HttpHeaders headers = new HttpHeaders();
    try {
      License next = this.getNewLicense(customerNumberListString, parentCustomerNumber, parentCustomerId);
      // headers.set("Access-Control-Allow-Origin", "*");
      headers.set("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, x-license, uid, pid"); // X-License or x-license?
      headers.set("Access-Control-Allow-Credentials", "true");
      headers.set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
      headers.set("Access-Control-Expose-Headers", "x-license, uid, pid"); // X-License or x-license?
      headers.set("Access-Control-Max-Age", "1209600");
      headers.add("x-license", next.getLicenseString()); // add the X-License to the header
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return new ResponseEntity<String>(resultStr, headers, HttpStatus.OK);
  }

*/

/*

  // TODO: fix this - this and getCustomerNumber() method below have same implementation
  //protected String getParentCustomerNumber(HttpServletRequest request)
  //protected Long getParentCustomerNumber(HttpServletRequest request)
  protected String getParentCustomerNumber(HttpServletRequest request)
  {
    log.trace("BaseService.getParentCustomerNumber() - Calling BaseService.getExistingLicense()...");
    License license = this.getExistingLicense(request);
    return this.getParentCustomerNumber(license);
  }

  //protected Long getParentCustomerNumber(License license)
  //{
  //  //String parentCustomerNumber = (license == null ? null : license.customerNumber);
  //  Long parentCustomerNumber = (license == null ? null : license.parentCustomerNumber);
  //  log.trace("BaseService.getParentCustomerNumber(License) - Got parentCustomerNumber: '" + parentCustomerNumber + "' from existing license");
  //  return parentCustomerNumber;
  //}
  
  protected String getParentCustomerNumber(License license)
  {
    String parentCustomerNumber = (license == null ? null : license.parentCustomerNumber);
    log.trace("BaseService.getParentCustomerNumber(License) - Got parentCustomerNumber: '" + parentCustomerNumber + "' from existing license");
    return parentCustomerNumber;
  }  
  
  //protected String getCustomerNumber(HttpServletRequest request)
  protected String getCustomerNumberListString(HttpServletRequest request)
  {
    log.trace("BaseService.getCustomerNumberListString(HttpServletRequest) - getting customerNumberListString from existing license in request...");
    License license = this.getExistingLicense(request);
    return this.getCustomerNumberListString(license);
  }

  // Get the customerNumberListString from the license in the header
  //protected String getCustomerNumber(HttpHeaders headers)
  protected String getCustomerNumberListString(HttpHeaders headers)
  {
    log.trace("BaseService.getCustomerNumberListString(HttpHeaders) - getting from existing license in headers...");
    License license = new License(headers.getFirst(HDR_LICENSE_NAME)); // get the first header by name
    return this.getCustomerNumberListString(license);
  }

  protected String getCustomerNumberListString(License license)
  {
    log.trace("BaseService.getCustomerNumberListString(License) - getting from given license ...");
    // Get the list of customerNumbers separated by pipe char as seen in the license
    String customerNumberPipeString = (license == null ? null : license.customerNumberPipeString);
    // Now convert the pipe chars to commas for db IN clause
    return MyUtil.pipeStringToCommaString(customerNumberPipeString); // replace pipes with commas
  }

  public String[] getCustomerNumberArray(License license)
  {
    log.trace("BaseService.getCustomerNumberArray(License) - getting from given license ...");
    // Get the list of customerNumbers separated by pipe char as seen in the license
    String customerNumberPipeString = (license == null ? null : license.customerNumberPipeString);
    return MyUtil.getArrayFromPipeString(customerNumberPipeString);
  }

  // Get the permissionId ('pid') from request header
  protected Long getPermissionId(HttpServletRequest request)
  {
    String pid = request.getHeader(HDR_PID);
    log.trace("BaseService.getPermissionId() - Got permissionId '" + pid + "' (aka pid) from request header.");
    return pid == null ? 0l : Long.parseLong(pid);
  }

  // Get the userId ('uid')from request header
  protected Long getUserId(HttpServletRequest request)
  {
    String uid = request.getHeader(HDR_UID);
    log.trace("BaseService.getUserId() - Got userId '" + uid + "' (aka uid) from request header.");
    return uid == null ? 0l : Long.parseLong(uid);
  }

  // Get the xLicense from request header, then get customerId from
  // xLicense as license.customerId
  protected long getCustomerId(HttpServletRequest request)
  {    
    License license = this.getExistingLicense(request);
    log.trace("BaseService.getCustomerId() - Got customerId '" + license.customerId + "' from license in request header.");
    return license == null ? null : license.customerId;
  }
  */


  protected String serialize(Object obj)
  {
    log.trace("BaseService.serialize() - serialize this object: " + obj);
    if(obj == null) log.warn("object is null. No data returned by query");
    ObjectMapper mapper = new ObjectMapper();
    // mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
    String ret = null;
    try {
      ret = mapper.writeValueAsString(obj);
    }
    catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return ret;
  }

  protected <T> Stream<T> streamByType(Collection<T> collection, boolean serial)
  {
    if (serial)
      return collection.stream();
    else
      return collection.parallelStream();
  }

  protected Optional<PageRequest> getPaging(Optional<Integer> pageNumber, Optional<Integer> pageSize)
  {
    if (pageNumber.isPresent() || pageSize.isPresent())
      return Optional.of(PageRequest.of( pageNumber.orElse(1) - 1, pageSize.orElse(Integer.valueOf(defaultPageSize) ) ) );
    else
      return Optional.empty();
  }  
  
} // end BaseService class
