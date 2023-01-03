// report1.js

// Called by list.html body onload listener
function myBodyOnloadFunction(obj)
{
  console.log("TOP: myBodyOnloadFunction()...");
  setMyView("report1Main"); // defined in common.js
}

//
// report1
//
async function fetchReport1Preferences()
{
    console.log("top of fetchReport1Preferences()...");
    setMyView("report1Detail");

    var userId     = 1;
    var practiceId = 123456789;
    var reportName = "report1";

    var url = "/api/preference/fetch/" + userId + "/" + practiceId;
    console.log("fetchReport1Preferences() - url: " + url);
    let dataIn;
    var myHeaders = new Headers();
    var request = new Request( url, { method: 'GET', body: dataIn, headers: myHeaders } );
    console.log("before report1Preferences fetch...");
    startSpinner();
    const response = await fetch(request);
    console.log("after report1Preferences fetch");
    const report1PreferencesPageData = await response.json();
    console.log("got back report1PreferencesPageData object");
    console.log("report1PreferencesPageData: " + JSON.stringify(report1PreferencesPageData));
    // start: output
    let output = "";
    output += "<table class=\"table table-bordered table-sm\">";
    output += "<tr>";
    output += "<td>id</td>";
    output += "<td>userId</td>";
    output += "<td>practiceId</td>";
    output += "<td>preferenceName</td>";
    output += "<td>id</td>";
    output += "<td>reportName</td>";
    output += "<td>itemName</td>";
    output += "<td>itemValue</td>";
    output += "</tr>";
    report1PreferencesPageData.forEach(function(preferenceObject) {
      preferenceObject.preferenceItemEntityList.forEach(function (preferenceItemObject) {
        output += "<tr>";
        output += "<td>" + preferenceObject.id              + "</td>";
        output += "<td>" + preferenceObject.userId          + "</td>";
        output += "<td>" + preferenceObject.practiceId      + "</td>";
        output += "<td>" + preferenceObject.preferenceName  + "</td>";
        output += "<td>" + preferenceItemObject.id          + "</td>";
        output += "<td>" + preferenceItemObject.reportName  + "</td>";
        output += "<td>" + preferenceItemObject.itemName    + "</td>";
        output += "<td>" + preferenceItemObject.itemValue   + "</td>";
        output += "</tr>";
      });
    });
    output += "</table>";

    // turn off the spinner now that data has loaded...
    stopSpinner();
    document.getElementById("dataDiv").innerHTML = output;
    localStorage.setItem("report1PreferencesPageData", report1PreferencesPageData);
    // Enable div showing Resultset data and metadata
    document.getElementById("dataHolderDiv").style = "display: block";
}


async function saveReport1Preferences(colorValue)
{
    console.log("top of saveReport1Preferences()...");
    var userId          = 1;
    var practiceId      = 123456789;
    var preferenceName  = "chris pref 1"
    var reportName      = "report1";
    var itemName        = "color";
    var itemValue       = colorValue;
    var url = "/api/preference/save/" + userId + "/" + practiceId + "/" + preferenceName + "/" + reportName + "/" + itemName + "/" + itemValue;
    console.log("saveReport1Preferences() - url: " + url);
    //
    let dataIn;
    var myHeaders = new Headers();
    var request = new Request( url, { method: 'GET', body: dataIn, headers: myHeaders } );
    console.log("before saveReport1Preferences save...");
    startSpinner();
    const response = await fetch(request);
    console.log("after saveReport1Preferences save");
    const report1SavePreferencesPageData = await response.json();
    console.log("got back report1PreferencesSavePageData object");
    console.log("report1SavePreferencesPageData: " + JSON.stringify(report1SavePreferencesPageData));

    // turn off the spinner now that data has loaded...
    stopSpinner();
    //document.getElementById("dataDiv").innerHTML = output;
    //localStorage.setItem("report1PreferencesPageData", report1PreferencesPageData);
    // Enable div showing Resultset data and metadata
    //document.getElementById("dataHolderDiv").style = "display: block";
    //

}