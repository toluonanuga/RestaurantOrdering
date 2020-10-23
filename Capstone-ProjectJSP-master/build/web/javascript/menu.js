const Http = new XMLHttpRequest();

function AddFavoriteItem(button, itemId)
{
    if (button.checked == true)
    {
        console.log("step 1")
        var url = '/CAPSTONE/addFav?itemId=' + itemId + '&action=1';
        console.log(url);
        Http.open("GET", url);
        Http.send();
        console.log("Is sended" + itemId);

    } else
    {
        console.log("step 1")
        var url = '/CAPSTONE/addFav?itemId=' + itemId + '&action=0';
        console.log(url);
        Http.open("GET", url);
        Http.send();
        console.log("Is sended" + itemId);
    }

}


function AddToOrder(itemId, price, divname)
{
    var qty = document.getElementById("qty"+itemId).value;
    var name = document.getElementById(divname).innerHTML;
//    console.log("qty: " + qty);

    
    var cart = localStorage.getItem("items");
    var cartObj = JSON.parse(cart);

    if (cartObj === null)
    {
        cartObj = [];
    }


    let latestItem =
            {
                id: itemId,
                name: name,
                quantity: qty,
                price: price
            };
            
   for(var i = 0; i < cartObj.length; i++)
   {
       if(cartObj[i].name === latestItem.name)
       {
          cartObj.splice(i, 1);
           
       }
   }
  
    cartObj.push(latestItem);     
    localStorage.setItem("items", JSON.stringify(cartObj));
}


