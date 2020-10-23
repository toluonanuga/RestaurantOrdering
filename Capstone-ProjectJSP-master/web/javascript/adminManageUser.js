
var Http = new XMLHttpRequest();
var ActorList;
var test;
function recieveActorList()
{
    console.log("Recieved actor list");
    var url = '/CAPSTONE/EditActorServlet';
    Http.open("GET", url, true);
    Http.addEventListener("load", loadActor);
    Http.send();
}
function loadActor()
{
    console.log("Attempting to load list");
    if (Http.readyState == 4 && Http.status == 200)
    {
        ActorList = JSON.parse(Http.response);
    }

}
function searchUserByEmail(userEmail)
{
    document.getElementById("editAccountPlace").innerHTML = " ";
    for (var i = 0; i < ActorList.length; i++)
    {

        if (ActorList[i].email.includes(userEmail))
        {
            document.getElementById("editAccountPlace").innerHTML += 
                    "<tr id=" + "'userId" + ActorList[i].id + "'" + ">" +
                    "<td>" + ActorList[i].email + "</td>" +
                    "<td>" + ActorList[i].firstName + "</td>" +
                    "<td>" + ActorList[i].lastName + "</td>" +
                    /*
                    "<td>" + actorList[i].password + "</td>" +
                    "<td>" + actorList[i].street + "</td>" +
                    "<td>" + actorList[i].houseNumber + "</td>" +
                    "<td>" + actorList[i].unitNumber + "</td>" +
                    "<td>" + actorList[i].city + "</td>" +
                    "<td>" + actorList[i].provice + "</td>" +
                    "<td>" + actorList[i].postalCode + "</td>" +
                    "<td>" + actorList[i].phoneNumber + "</td>" +
                    "<td>" + actorList[i].role + "</td>" +
                    "<td>" + actorList[i].state + "</td>" +
                    */
                    "<td>" + "<button type='button' " + "class='btn btn-info btn-lg' data-toggle='modal' data-target='#actorModal"+ActorList[i].id+"'>Edit" + "</button>" + "</td>" +
                    "<td>" + "<button type='button' class='btn btn-danger' onclick='"+"deleteActor("+ActorList[i].itemId+")'>Delete" + "</button>" + "</td>" +
                    "</tr>";

        }
    }
}

function editActor(id, firstName, lastName, street, houseNumber, 
                    unitNumber, city, postal, province, phoneNumber, email, password, role)
{
    var Http1 = new XMLHttpRequest();
     var actor;
     for(var i in ActorList)
     {
        if(ActorList[i].id == id.value)
        {
            ActorList[i].firstName = firstName.value;
            ActorList[i].lastName = lastName.value;
            ActorList[i].street = street.value;
            ActorList[i].houseNumber = houseNumber.value;
            ActorList[i].unitNumber = unitNumber.value;
            ActorList[i].city = city.value;
            ActorList[i].postalCode = postal.value;
            ActorList[i].province = province.value;
            ActorList[i].phoneNumber = phoneNumber.value;
            ActorList[i].email = email.value;
            ActorList[i].password = password.value;
            ActorList[i].role = role.value;
            actor = ActorList[i];
            console.log(actor);
            
        }
     }
   
     actor = JSON.stringify(actor);
     var url = '/CAPSTONE/EditActorServlet?action=1&sendActor='+actor;
       Http1.open("POST",url,true);
       Http1.send(); 
       recieveActorList();
       searchUserByEmail("");
       
}


function deleteActor(id)
{
       var Http2 = new XMLHttpRequest();
       var url = '/CAPSTONE/EditActorServlet?action=2&deleteActor='+id;
       Http2.open("POST",url,true);
       Http2.send(); 
       recieveActorList();
       searchUserByEmail("");
}


