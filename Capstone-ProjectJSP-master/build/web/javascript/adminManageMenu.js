

var Http = new XMLHttpRequest();
var ItemList;
function recieveItemList()
{
       var url = '/CAPSTONE/SendItemServlet?action=1';
       Http.open("GET",url,true);
       Http.addEventListener("load", loadItem);
       Http.send();  
}
function loadItem()
{
    if(Http.readyState == 4 && Http.status == 200)
    {
      ItemList = JSON.parse(Http.response);
      console.log("Hi");
    }
}
function searchItemByName(inputItemName)
{
    document.getElementById("menuPlace").innerHTML = " ";
    for(var i = 0; i < ItemList.length; i++ )
    {

     if(ItemList[i].itemName.includes(inputItemName))
     {
         console.log(ItemList[i].itemName);
         document.getElementById("menuPlace").innerHTML += "<tr id="+"'menuItem"+ItemList[i].itemId+"'" + ">"+
                                                          "<td>"+ItemList[i].itemName+"</td>"+
                                                          "<td>"+ItemList[i].price+"</td>"+
                                                          "<td>"+ItemList[i].description+"</td>"+
                                                          "<td>"+ItemList[i].category+"</td>"+
                                                          "<td>"+ItemList[i].imagePath+"</td>"+
                                                          "<td>"+"<button type='button' "+ "class='btn btn-info btn-lg' data-toggle='modal' data-target='"+"#myModal"+ItemList[i].itemId+"'"+  ">Edit"+"</button>" +"</td>"+
                                                          "<td>"+"<button type='button' class='btn btn-danger' onclick='"+"deleteItem("+ItemList[i].itemId+")'>Delete"+"</button>" +"</td>"+ 
                                                          "</tr>";
                                                  
     }
    }
}
function editItem(id,name,price,description,category,filepath)
{
    var Http1 = new XMLHttpRequest();
     var item;
     for(var i in ItemList)
     {
        if(ItemList[i].itemId == id.value)
        {
            ItemList[i].itemName = name.value;
            ItemList[i].price = price.value;
            ItemList[i].description = description.value;
            ItemList[i].category = category.value;
            ItemList[i].filepath = filepath.value;
            item = ItemList[i];
            console.log(item);
            
        }
     }
   
     item = JSON.stringify(item);
     var url = '/CAPSTONE/SendItemServlet?action=2&sendItem='+item;
       Http1.open("POST",url,true);
       Http1.send(); 
       recieveItemList();
       searchItemByName("");
       
 }
 
 function deleteItem(id)
 {
       var Http2 = new XMLHttpRequest();
       var url = '/CAPSTONE/SendItemServlet?action=3&deleteItem='+id;
       Http2.open("POST",url,true);
       Http2.send(); 
       recieveItemList();
       searchItemByName("");
 }

 function addItem()
 {
   var form = document.getElementById("addForm");   
   form.submit();
 }
 

 


