var HttpBackUpRequest = new XMLHttpRequest();
var HttpBackUpRequest1 = new XMLHttpRequest();
var backupFileList;
var check;
function createBackUpFile(inputFileName)
{
    var url = "admin?action=createBackup&bkfilename=" + inputFileName;
    HttpBackUpRequest1.open("POST", url, true);
    HttpBackUpRequest1.addEventListener("load", this.getResponse);
    HttpBackUpRequest1.send();
}
function getFileList()
{
    var url = "admin?action=getAllRestoreFile";
    HttpBackUpRequest.open("POST", url, true);
    HttpBackUpRequest.addEventListener("load", loadlist);
    HttpBackUpRequest.send();
}
function loadlist()
{
    if (HttpBackUpRequest.readyState == 4 && HttpBackUpRequest.status == 200)
    {
        backupFileList = JSON.parse(HttpBackUpRequest.response)
        console.log(backupFileList);
    }
}
function getResponse()
{
    if (HttpBackUpRequest1.readyState == 4 && HttpBackUpRequest1.status == 200)
    {
         check = JSON.parse(HttpBackUpRequest1.response)
    }
     diplay();
}
function diplay()
{
    getFileList();
    if (check == true)
    {
        document.getElementById("backUpRestoreStatus").innerHTML = "Successfully backup database";
       
         document.getElementById("restoreSpace").innerHTML = " ";
        for (var i = 0; i < backupFileList.length; i++)
        {
      
            document.getElementById("restoreSpace").innerHTML += "<tr id=" + "'menuItem" + backupFileList[i].itemId + "'" + ">" +
                    "<td id=" + "'file_" + backupFileList[i] + "' " + ">" + backupFileList[i] + "</td>" +
                    "<td>" + "<button type='button' " + "onclick='restoreDatabase" + "(file_" + "backupFileList[i]" + ")' " + "class='btn btn-info btn-lg'>Restore</button>" + "</td>" +
                    "</tr>";
        }

    } else
    {
        document.getElementById("backUpRestoreStatus").innerHTML = "Fail backup database, please contact the development team";
    }
}

