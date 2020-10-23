var Http = new XMLHttpRequest();
function restoreDatabase(inputFileName)
{
   var restoreFileName = document.getElementById(inputFileName).innerText;
   var url = "admin?action=restore&filename="+restoreFileName;
   Http.open("POST",url,true);
   Http.addEventListener("load", handleResponse);
   Http.send();
   
}
function handleResponse()
{
    var response
    if (Http.readyState == 4 && Http.status == 200)
    {
        response = JSON.parse(Http.response);
        console.log(response);
    }
    if(response == true)
    {
        document.getElementById("backUpRestoreStatus").innerHTML = "Successfully restore database";
    }
    else
    {
        document.getElementById("backUpRestoreStatus").innerHTML = "Fail restore database, please contact the development team";
    }
}


