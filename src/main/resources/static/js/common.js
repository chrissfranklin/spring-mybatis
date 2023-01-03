// common.js
//
// Common (global) functions used across all simplerclient pages and views
//

// Fetch the requestParamValue from url
function getRequestParamValue(requestParamName)
{
    if(requestParamName=(new RegExp('[?&]'+encodeURIComponent(requestParamName)+'=([^&]*)')).exec(location.search)) {
        return decodeURIComponent(requestParamName[1]);
    }
}

// Original generic excel download implementation
function fetchExcelByJson(xLicense, reportType, reportName, fileName, practiceName, dateDescription, jsonData)
{
    const url = "/api/download/excel";
    console.log("fetchExcel() - reportName: " + reportName + ", url: " + url);
    var request = new XMLHttpRequest();
    request.open("POST", url, true);
    request.setRequestHeader('x-license',       xLicense);
    request.setRequestHeader('fileName',        fileName);
    request.setRequestHeader('reportType',      reportType);
    request.setRequestHeader('reportName',      reportName);
    request.setRequestHeader('practiceName',    practiceName);
    request.setRequestHeader('dateDescription', dateDescription);
    request.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    request.responseType = "blob";
    request.onload = function() {
        saveData(fileName, this.response);
    };
    console.log("fetchExcel() - before send(blob)");
    startSpinner();
    request.send(jsonData);
    console.log("fetchExcel() - after send(blob)");
    stopSpinner();
}


// Called by fetchExcelByJson() and all js files
function saveData(myFileName, rezultData)
{
    console.log("saveData(): rezultData: "        + rezultData);
    //var contentType = "application/vnd.ms-excel";
    var contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    var blob = new Blob([rezultData], {type: contentType} );
    console.log("saveData(): blob: " + blob);
    try {
        var link              = document.createElement('a');
        link.href             = window.URL.createObjectURL(blob);
        link.download         = myFileName;
        link.style.visibility = 'hidden';
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    }
    catch(exc) {
        console.log("saveData() - Blob method failed with the following exception" + exc);
    }
}

// Creates a comma-separated list of supervisingPhysicianKeys
// Only currently used by utilization queries
function getSupervisingPhysicianKeys(utilizationLevelData)
{
    let rezult = '';
    utilizationLevelData.content.forEach(function(utilizationObject) {
        rezult += utilizationObject.supervisingPhysicianKey + "x";
    });
    // remove the last char if ","
    rezult = rezult.slice(0, -1);
    console.log("supervisingPhysicianKeys: " + rezult);
    return rezult;
}


// Helper to set the view name in both localStorage and visible div page widget
// This function should be called at the top every time a page view changes, ie inventoryMain, inventoryLevel1, etc
function setCurrentViewName(currentViewName)
{
    localStorage.setItem("currentView", currentViewName);
    let myViewDiv = document.getElementById("viewDiv");
    if(myViewDiv) {
        myViewDiv.innerHTML = currentViewName;
    }
}

// Helper to get currentViewName from localStorage
function getCurrentViewName()
{
    return localStorage.getItem("currentView");
}

// set the previousView in localStorage
function setPreviousViewName(viewName)
{
    localStorage.setItem("previousView", viewName);
}

// get the previousView from localStorage
function getPreviousViewName()
{
    return localStorage.getItem("previousView");
}


// set previous and current view in localStorage
// Called by every onLoad() method and also by every submit button that changes the dataset/view
function setMyView(myCurrentViewName)
{
    let previousViewName  = getCurrentViewName();
    setPreviousViewName(previousViewName); // set prev = current
    setCurrentViewName(myCurrentViewName); // set current = given view
    console.log("Updated views to...");
    console.log("  previousView: " + previousViewName);
    console.log("  currentView:  " + myCurrentViewName);
    // clear inputs if previousView is not empty previousView != currentView OR currentViewNameOld is empty
    if( ( !previousViewName ||
            getPreviousViewName() != myCurrentViewName ) &&
        myCurrentViewName != "index" &&
        myCurrentViewName != "list"  &&
        myCurrentViewName != "main"
    ) {
        console.log("previousView != currentView. Clearing inputs...");
        resetFormInputs(); // resets totalElements,pageNumber,sortDirection,sortColumn
    }
}


function startSpinner()
{
    // turn on the spinner while the data loads...
    console.log("turning ON ***spinner***");
    // clear dataDiv
    document.getElementById("dataDiv").innerHTML     = "";
    // hide the dataHolderDiv (containing data and metadata divs) before we start the spinner
    document.getElementById("dataDiv").style = "display: none";
    if(document.getElementById("metaDataDivWrapper")) {
        document.getElementById("metaDataDivWrapper").style = "display: none";
    }
    document.getElementById("dataSpinnerDiv").style.display = "block";
    //$('body').addClass('busy');
}

function stopSpinner()
{
    console.log("turning OFF ***spinner***");
    document.getElementById("dataSpinnerDiv").style.display = "none";
    document.getElementById("dataDiv").style = "display: block";
    if(document.getElementById("metaDataDivWrapper")) {
        document.getElementById("metaDataDivWrapper").style = "display: block";
    }
    //visibility: visible|hidden
    //$('body').removeClass('busy');
}

// Helper for printing Pagination Scoreboard: 'page 1 of 5 (24 total)'
function getPaginationScoreboard(pageData)
{
    // show currentPage, totalPages, totalElements
    var pageSize      = parseInt(pageData.size);
    var pageNumber    = parseInt(pageData.number);
    pageNumber++;
    var totalPages    = parseInt(pageData.totalPages);
    var totalElements = parseInt(pageData.totalElements);
    return pageNumber + " of " + totalPages + " (" + totalElements + " rows)";
}


// Helper function for inventory.html page to set totalElements input to empty
// This is called when (for example) the startDate or endDate changes (can no longer use cache so set totalElements to empty)
function resetFormInputs() {
    // some pages that use JPA dont track totalElements
    //if (document.body.contains("totalElements")) {
    if(document.getElementById("totalElements")) {
        document.getElementById("totalElements").value = "";
    }

    if(document.getElementById("pageNumber")) {
        document.getElementById("pageNumber").value = "";
    }

    if(document.getElementById("sortColumn")) {
        document.getElementById("sortColumn").value = "";
    }

    if(document.getElementById("sortDirection")) {
        document.getElementById("sortDirection").value = "";
    }
}


// Helper for pagination menu shown below resulset
function getPaginationMenuDiv(pageData, functionName)
{
    var rezult        = "";
    var pageSize      = parseInt(pageData.size);
    var pageNumber    = parseInt(pageData.number);
    pageNumber++;
    var totalPages    = parseInt(pageData.totalPages);
    var totalElements = parseInt(pageData.totalElements);
    var prevPageNumber = pageNumber - 1;
    var nextPageNumber = pageNumber + 1;
    console.log("inside getPaginationMenu()...");
    console.log("prevPageNumber: " + prevPageNumber);
    console.log("nextPageNumber: " + nextPageNumber);
    if(totalElements > 0) {
        let arrowLeftIcon  = "<img src=\"/img/arrow-left-circle.svg\" alt=\"Bootstrap\" width=\"32\" height=\"32\">";
        let arrowRightIcon = "<img src=\"/img/arrow-right-circle.svg\" alt=\"Bootstrap\" width=\"32\" height=\"32\">";
        rezult += "<div id=\"pageMenuDiv\" class=\"w-25 p-3\">";
        rezult += "<ul class=\"pagination\">";
        // calculate the link to previous page
        if(pageNumber > 1) {
            rezult += "<li><a href=\"#\" onClick=\"" + functionName + "(" + prevPageNumber + ")\">" + arrowLeftIcon + "</a></li>&nbsp;";
        }
        else {
            rezult += "<li><a href=\"#\">&nbsp;&nbsp;</a></li>";
        }
        // calculate display for current page
        rezult += "<li><a href=\"#\">" + getPaginationScoreboard(pageData) + "</a></li>";

        // calculate the link to next page
        if(pageNumber < totalPages) {
            rezult += "&nbsp;<li><a href=\"#\" onClick=\"" + functionName + "(" + nextPageNumber + ")\">" + arrowRightIcon + "</a></li>";
        }
        else {
            rezult += "<li><a href=\"#\">&nbsp;&nbsp;</a></li>";
        }
        rezult += "</ul>";
        rezult += "</div>";
        console.log("at end of getPaginationMenu()... rezult: \n" + rezult);
    }
    else {
        console.log("WARNING: Could not generate Pagination Menu - totalElements is empty");
    }
    return rezult;
}
