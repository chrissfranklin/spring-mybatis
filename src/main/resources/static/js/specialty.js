// specialty.js

// Called by specialty.html body onload listener
function myBodyOnloadFunction(obj)
{
  console.log("TOP: myBodyOnloadFunction()...");
  setMyView("specialtyMain"); // defined in common.js
}

//
// specialty
//
async function fetchSpecialty()
{
  console.log("top of fetchSpecialty()...");
  setMyView("specialtyDetail");
  // set local variables
  var xLicense = localStorage.getItem("xLicense");
  var parentCustomerNumber = localStorage.getItem("parentCustomerNumber");

  // Call specialty service and send x-license header and parentCustomerNumber in url
  var url = "/api/specialty/" + parentCustomerNumber;
  console.log("fetchSpecialty() - url: " + url);
  let dataIn;
  var myHeaders = new Headers();
  myHeaders.append('x-license', xLicense);
  var request = new Request( url, { method: 'GET', body: dataIn, headers: myHeaders } );
  console.log("before specialty fetch...");
  startSpinner();
  const response = await fetch(request);
  console.log("after specialty page fetch");
  const specialtyPageData = await response.json();
  console.log("got back specialtyPageData object");
  console.log("specialtyPageData: " + JSON.stringify(specialtyPageData));
  // start: output
  let output = "";
  output += "<table class=\"table table-bordered table-sm\">";
  output += "<tr>";
  output += "<td><a href=\"#\" onClick=\"fetchSpecialtySortColumn('specialty', 'ASC');\">specialty</a></td>";
  output += "</tr>";

  //specialtyPageData.forEach(function(specialtyObject) {
  //specialtyPageData.forEach((specialtyObject) => {
  specialtyPageData.forEach(function(specialtyObject) {
    output += "<tr>";
    output += "<td>" + specialtyObject.specialty + "</td>";
    output += "</tr>";
  });
  output += "</table>";

  // turn off the spinner now that data has loaded...
  stopSpinner();
  document.getElementById("dataDiv").innerHTML = output;
  localStorage.setItem("specialtyPageData", specialtyPageData);
  // Enable div showing Resultset data and metadata
  document.getElementById("dataHolderDiv").style = "display: block";
}
