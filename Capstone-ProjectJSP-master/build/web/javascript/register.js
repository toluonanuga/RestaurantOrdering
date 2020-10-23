/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function autoWrite(button) {

    if (button.checked == true)
    {

        document.getElementById("deliveryEmail").value = document.getElementById("recieverEmail").value;
        document.getElementById("deliverybuilding").value = document.getElementById("building").value;
        document.getElementById("deliveryPhonenumber").value = document.getElementById("recieverPhonenumber").value;
        document.getElementById("deliveryunit").value = document.getElementById("unit").value;
        document.getElementById("deliveryStreet").value = document.getElementById("recieverStreet").value;
        document.getElementById("deliveryPostalCode").value = document.getElementById("recieverPostalCode").value;
    } else {

        document.getElementById("deliveryEmail").value = "";
        document.getElementById("deliverybuilding").value = "";
        document.getElementById("deliveryunit").value = "";
        document.getElementById("deliveryStreet").value = "";
        document.getElementById("deliveryPostalCode").value = "";
        document.getElementById("deliveryPhonenumber").value = "";
    }
}



function checkDuplicate()
{
var password_field = document.getElementById("password");
var confirmPass_field = document.getElementById("confirmPass");
    if (password_field.value != confirmPass_field.value)
    {
        confirmPass_field.setCustomValidity("Must match with password");
    }
    else
    {
        confirmPass_field.setCustomValidity("");
    }
}

