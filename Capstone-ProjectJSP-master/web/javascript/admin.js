/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var search = document.getElementsByName("search");
var input, filter, a, i, txtValue;
var account = document.getElementsByName("account");

function changeRole(){
    
    var role = document.getElementById("role");
    if (role !== null){    
        document.getElementById("role").setAttribute("account.role", "role");
    }
    
}

function changeActive(curr){
    if (curr !== null){
        if (curr === "active"){
            document.getElementById("search").setAttribute("account.role", "Inactive");
        }
        else{
            document.getElementById("search").setAttribute("account.role", "Active");
        }
        
        
    }
}

function searchBy(){
    filter = search.value.toUpperCase();
    
    for (i = 0; i < search.length; i++) {
    a = search[i].getElementsByName("account")[0];
    txtValue = a.textContent || a.innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      search[i].style.display = "";
    } else {
      search[i].style.display = "none";
    }
  }
    
}
