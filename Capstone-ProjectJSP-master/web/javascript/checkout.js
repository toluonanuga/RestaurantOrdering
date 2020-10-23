
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var cart = localStorage.getItem("items");
var cartObj = JSON.parse(cart);
var total = parseFloat(0);
console.log(cartObj);

function Table()
{
    var table = document.getElementById('treciept');
    var objprice = parseFloat(0);

    table.innerHTML += "<tr><td>Item</td><td>Quantity</td><td>Price</td><td>Delete</td></tr>";

    for (var obj in cartObj)
    {
        objprice = (cartObj[obj].price * cartObj[obj].quantity);
        var pennies = objprice * 100;
        objprice = Math.ceil(pennies) / 100;
        
        table.innerHTML += "<tr><td>" + cartObj[obj].name + "</td><td>" + cartObj[obj].quantity + "</td>\n\
        <td>" + objprice + "</td><td> <input type=\"button\" value=\"delete\" onclick=\"removeItem(this)\"\>  </td></tr>";

        total = parseFloat(total) + objprice;
        console.log("total is: " + total);
        //itemlist is the HTML the servlet reads off of to figure out what is in the order. 
        document.getElementById("item_list").value += cartObj[obj].id + "," + cartObj[obj].quantity + ",";
    }
    document.getElementById("total_price").value = total;
    table.innerHTML += "<tr><td colspan=\"2\"><h4 id=\"subtotal\"></h4></td></tr>";
    document.getElementById("subtotal").innerHTML = "Subtotal:  $" + total.toFixed(2);   

}
window.onload = Table;


function sendOrder()
{
    alert("no functionality for sending yet.");
}

//Delete function has been modified from https://stackoverflow.com/questions/20312095/delete-table-row-using-javascript
function removeItem(item) {
    try {
        var table = document.getElementById('treciept');
        var rowCount = table.rows.length;

        for (var i = 0; i < rowCount; i++) {
            var row = table.rows[i];

            if (row === item.parentNode.parentNode) {
                if (rowCount <= 1) {
                    alert("Cannot delete all the rows.");
                    break;
                }
                table.deleteRow(i);
                rowCount--;
                cartObj.splice(i - 1, 1);
                i--;
                alert(cartObj.length);
                document.getElementById("item_list").value = "";
                total = 0;
                for (var obj in cartObj)
                {
                    objprice = (cartObj[obj].price * cartObj[obj].quantity);
                    var pennies = objprice * 100;
                    objprice = Math.ceil(pennies) / 100;

                    document.getElementById("item_list").value += cartObj[obj].id + "," + cartObj[obj].quantity + ",";
                    total = parseFloat(total) + objprice;
                }
                localStorage.setItem("items", JSON.stringify(cartObj));
                document.getElementById("total_price").value = total;
                document.getElementById("subtotal").innerHTML = "Subtotal:  $" + total.toFixed(2);   
            }
        }
    } catch (e) {
        alert(e);
    }
}
