var orderList;
var Http = new XMLHttpRequest();
function recieveOrderList()
{
    var url = '/CAPSTONE/ManagerOrderServlet';
    Http.open("GET", url, true);
    Http.addEventListener("load", loadList);
    Http.send();
}
function loadList()
{
    if(Http.readyState == 4 && Http.status == 200)
    {
        orderList = JSON.parse(Http.response);
        console.log(orderList);
    }
}
function searchOrderByEmail(inputCusEmail)
{
    document.getElementById("orderSpace").innerHTML = " ";
    for(var i = 0; i < orderList.length; i++ )
    {

     if(orderList[i].customerEmail.includes(inputCusEmail))
     {
         var startdate = new Date(orderList[i].orderDate);
         var duedate = new Date(orderList[i].dueDate);
         document.getElementById("orderSpace").innerHTML += "<tr id="+"'orderId"+orderList[i].orderID+"'" + ">"+
                                                          "<td>"+orderList[i].orderID+"</td>"+
                                                          "<td>"+orderList[i].customerEmail+"</td>"+
                                                          "<td>"+orderList[i].orderStatus+"</td>"+
                                                          "<td>"+orderList[i].paymentMethod+"</td>"+
                                                          "<td>"+startdate+"</td>"+
                                                          "<td>"+duedate+"</td>"+
                                                          "<td>"+"<button type='button' "+"data-toggle='modal' data-target='#myModalOrder"+orderList[i].orderID+"'" +"class='btn btn-info btn-lg'>View"+"</button>" +"</td>"+
                                                          "</tr>";
                                                  
     }
    }
}


