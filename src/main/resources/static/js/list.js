// list.js

// Called by list.html body onload listener
function myBodyOnloadFunction(obj)
{
  console.log("TOP: myBodyOnloadFunction()...");
  setMyView("listMain"); // defined in common.js
}

//
// preference
//
async function fetchPreference()
{
  console.log("top of fetchPreference()...");
  setMyView("preferenceDetail");
  // set local variables
  var url = "/api/preference/fetch/all";
  console.log("fetchPreference() - url: " + url);
  let dataIn;
  var myHeaders = new Headers();
  //myHeaders.append('x-license', xLicense);
  var request = new Request( url, { method: 'GET', body: dataIn, headers: myHeaders } );
  console.log("before preference fetch...");
  startSpinner();
  const response = await fetch(request);
  console.log("after preference page fetch");
  const preferencePageData = await response.json();
  console.log("got back preferencePageData object");
  console.log("preferencePageData: " + JSON.stringify(preferencePageData));
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

  preferencePageData.forEach(function(preferenceObject) {
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
  localStorage.setItem("preferencePageData", preferencePageData);
  // Enable div showing Resultset data and metadata
  document.getElementById("dataHolderDiv").style = "display: block";
}
