 var http = new XMLHttpRequest();
function printReport( option, filename)
{
  
    if(option == 1)
    {
        var url = "/CAPSTONE/PrintOrderServlet?action=printAll&filename=" + filename; 
         http.open("POST", url, true);
         http.addEventListener("load", takeResponseFromPrintOrder);
         http.send();    
    }
    else if(option == 2)
    {
         var url = "/CAPSTONE/PrintOrderServlet?action=printMonth&filename=" + filename; 
         http.open("POST", url, true);
         http.addEventListener("load", takeResponseFromPrintOrder);
         http.send();  
    }
    else if(option == 3)
    {
         var url = "/CAPSTONE/PrintOrderServlet?action=printWeek&filename=" + filename; 
         http.open("POST", url, true);
         http.addEventListener("load", takeResponseFromPrintOrder);
         http.send();  
    }
   
}

function takeResponseFromPrintOrder()
{
    var check;
    if (http.readyState == 4 && http.status == 200)
    {
        check = JSON.parse(http.response);
        console.log(check);
        if(check == true)
        {
            document.getElementById("reportMessage").innerHTML ="Print Report completed";
        }
        else 
        {
            document.getElementById("reportMessage").innerHTML = "fail to print report";
        }
    }
}
