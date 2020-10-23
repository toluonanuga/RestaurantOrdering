var counter = 0;

var currentOrders = [];

let timerId = setTimeout(function tick() {
 
 refreshOrders();
  timerId = setTimeout(tick, 5000); // (*)
}, 5000);
function startTimer() {
  
}

//setInterval(refreshOrders(), 1000);
    
function refreshOrders() {
       $(document).ready(function() {
            $.get("Employee?action=refresh", function(response) {
                
                currentOrders = [];
                for (var i = 0; i < response.length; i++)
                {
                    currentOrders.push(response[i]);
                    console.log(currentOrders);
                    
                    var orderExists = document.getElementById(response[i].orderID);
                    
                    if (orderExists === null)
                    {
                        let container = document.getElementById("cardcontainer");
                        let cardDiv = createCardDiv(response[i].orderID);
                        let cardRow = createCardRow();
                        let cardCol = createCardCol();
                        let cardBody = createCardBody();
                        attachToCardBody(cardBody, response[i].orderID, response[i].deliveryInfo.deliveryStreet, response[i].totalPrice, response[i].orderStatus);

                        cardCol.appendChild(cardBody);
                        cardRow.appendChild(cardBody);
                        cardDiv.appendChild(cardRow);

                        let modalFade = createModalFade(response[i].orderID);
                        let modalDialogue = createModalDialog();
                        let modalContent = createModalContent();
                        let modalHeader = createModalHeader();

                        let modalBody = createModalBody(
                        response[i].deliveryInfo.recieverFirstname, 
                        response[i].deliveryInfo.recieverLastname, 
                        response[i].deliveryInfo.building,
                        response[i].deliveryInfo.unit,
                        response[i].deliveryInfo.deliveryStreet,
                        response[i].deliveryInfo.deliveryCity,
                        response[i].deliveryInfo.deliveryPostalCode,
                        response[i].deliveryInfo.deliveryNote);

                        let modalFooter = createModalFooter(response[i].orderStatus, response[i].orderID, response[i].customerID);


                        modalContent.appendChild(modalHeader);
                        modalContent.appendChild(modalBody);
                        modalContent.appendChild(modalFooter);
                        modalDialogue.appendChild(modalContent);
                        modalFade.appendChild(modalDialogue);

                        container.appendChild(cardDiv);
                        container.appendChild(modalFade);

                    }else
                    {
                        var statusChecker = document.getElementById("footer" + response[i].orderID).firstChild;
                        
                        if (response[i].orderStatus === "RECEIVED")
                        {
                            updateStatusReceived(statusChecker, response[i].orderID);
                            let cardUpdater = document.getElementById("status" + response[i].orderID);
                            cardUpdater.innerHTML = "<b>Status: </b>" + response[i].orderStatus + "<br>";
                            
                        }else if (response[i].orderStatus === "CONFIRMED")
                        {
                            updateStatusConfirmed(statusChecker, response[i].orderID);
                            let cardUpdater = document.getElementById("status" + response[i].orderID);
                            cardUpdater.innerHTML = "<b>Status: </b>" + response[i].orderStatus + "<br>";
                        }
                    }
                }
                
                
            });
        });
}

function createCardDiv(orderID)
{
    let card = document.createElement("div");
    card.classList.add("card");
    card.id = orderID;
    card.style = "max-width: 200px; max-height: 200px;";
    card.dataset.toggle = "modal";
    card.dataset.target = "#myModal" + orderID;
    return card;
}

function createCardRow()
{
    let cardRow = document.createElement("div");
    cardRow.classList.add("row");
    cardRow.classList.add("no-gutters");
    return cardRow;
}

function createCardCol()
{
    let cardCol = document.createElement("div");
    cardCol.classList.add("col-md-25");
    return cardCol;
}

function createCardBody()
{
    let cardBody = document.createElement("div");
    cardBody.classList.add("card-body");
    return cardBody;
}

function attachToCardBody(cardBody, orderID, address, totalPrice, status)
{
    let span1 = document.createElement("span");
    span1.classList.add("card-text");
    span1.innerHTML = "<b>Order: </b>" + (orderID) + "<br>";
    cardBody.appendChild(span1);
    counter = counter + 1;
    
    let span2 = document.createElement("span");
    span2.classList.add("card-text");
    
    let currTime = new Date().toTimeString().replace(/.*(\d{2}:\d{2}:\d{2}).*/, "$1");
    
    span2.innerHTML = "<b>Received: </b>" + currTime + "<br>";
    cardBody.appendChild(span2);
    
    let span3 = document.createElement("span");
    span3.classList.add("card-text");
    span3.innerHTML = "<b>Address: </b>" + address + "<br>";
    cardBody.appendChild(span3);
    
    let span4 = document.createElement("span");
    span4.classList.add("card-text");
    span4.innerHTML = "<b>Price: </b>" + totalPrice + "<br>";
    cardBody.appendChild(span4);
    
    let span5 = document.createElement("span");
    span5.classList.add("card-text");
    span5.id = "status" + orderID;
    span5.innerHTML = "<b>Status: </b>" + status + "<br>";
    cardBody.appendChild(span5);   
}

function createModalFade(orderID)
{
    let modalFade = document.createElement("div");
    modalFade.classList.add("modal");
    modalFade.classList.add("fade");
    modalFade.id = "myModal" + orderID;
    modalFade.setAttribute("role", "dialog");
    modalFade.style = "margin-left: -20%";
    
    return modalFade;
}

function createModalDialog()
{
    let modalDialogue = document.createElement("div");
    modalDialogue.classList.add("modal-dialog");
    
    return modalDialogue;
}

function createModalContent()
{
    let modalContent = document.createElement("div");
    modalContent.classList.add("modal-content");
    
    return modalContent;
}

function createModalHeader()
{
    let modalHeader = document.createElement("div");
    modalHeader.classList.add("modal-header");
    
    let modalTitle = document.createElement("h4");
    modalTitle.classList.add("modal-title");
    modalTitle.innerHTML = "Order Information";
    
    modalHeader.appendChild(modalTitle);
    
    return modalHeader;
    
}

function createModalBody(firstName, lastName, building, unit, address, city, postal, note)
{
    let modalBody = document.createElement("div");
    modalBody.classList.add("modal-body");
    
    let span1 = document.createElement("span");
    span1.innerHTML = "<b>First Name: </b>" + firstName + "   ";
    modalBody.appendChild(span1);
    
    let span2 = document.createElement("span");
    span2.innerHTML = "<b>Last Name: </b>" + lastName + "<br>";
    modalBody.appendChild(span2);
    
    let span3 = document.createElement("span");
    span3.innerHTML = "<b>Building no.: </b>" + building + "   ";
    modalBody.appendChild(span3);
    
    let span4 = document.createElement("span");
    span4.innerHTML = "<b>Unit no.: </b>" + unit + "<br>";
    modalBody.appendChild(span4);
    
    let span5 = document.createElement("span");
    span5.innerHTML = "<b>Address: </b>" + address + "<br>";
    modalBody.appendChild(span5);
    
    let span6 = document.createElement("span");
    span6.innerHTML = "<b>City: </b>" + city + "   ";
    modalBody.appendChild(span6);
    
    let span7 = document.createElement("span");
    span7.innerHTML = "<b>Postal Code: </b>" + postal + "<br>";
    modalBody.appendChild(span7);
    
    let span8 = document.createElement("span");
    span8.innerHTML = "<b>Note: </b><br>";
    modalBody.appendChild(span8);
    
    let p = document.createElement("p");
    p.innerHTML = note;
    modalBody.appendChild(p);
    
    
    return modalBody;
}

function updateStatusReceived(button, orderID)
{
    button.value = "CONFIRM";
    button.style = "color: white; background-color: orange;";
    button.innerHTML = "CONFIRM";
    button.setAttribute("onclick", "confirmOrder(" + orderID + ")");
}

function updateStatusConfirmed(button, orderID)
{
    button.value = "COMPLETE";
    button.style = "color: black; background-color: green";
    button.innerHTML = "COMPLETE";
    button.setAttribute("onclick", "completeOrder(" + orderID + ")");
}

function confirmOrder(orderID)
{
    
    $(document).ready(function() {
            $.post("Employee",
            {
               status: "CONFIRMED",
               orderID: orderID
            },
            function(response) {
                $('.modal').modal('hide') // closes all active pop ups.
                $('.modal-backdrop').remove() // Removes the stupid gray background.
            });
        });
    
}

function completeOrder(orderID)
{
    $(document).ready(function() {
            $.post("Employee",
            {
               status: "COMPLETE",
               orderID: orderID
            },
            function(response) {
                $('.modal').modal('hide') // closes all active pop ups.
                $('.modal-backdrop').remove() // Removes the stupid gray background.
                deleteCard(orderID);
            });
        });
}

function deleteCard(orderID)
{
    let card = document.getElementById(orderID);
    let modal = document.getElementById("myModal" + orderID);
    
    card.parentNode.removeChild(card);
    modal.parentNode.removeChild(modal);
}

function voidOrder()
{

    let orderID = document.getElementById("pushVoidID").value;
    let managerEmail = document.getElementById("managerEmail").value;
    let managerPassword = document.getElementById("managerPass").value;
    let managerReason = document.getElementById("managerReason").value;
    let customerID = document.getElementById("pushVoidCusID").value;
            
        $(document).ready(function() {
            $.post("Employee",
            {
               status: "VOID",
               orderID: orderID,
               managerEmail: managerEmail,
               managerPassword: managerPassword,
               managerReason: managerReason,
               customerID: customerID
            },
            function(response) {
                $('.modal').modal('hide') // closes all active pop ups.
                $('.modal-backdrop').remove() // Removes the stupid gray background.
                deleteCard(orderID);
            });
        });
    
}

function prepVoidOrder(orderID, cusID)
{
    let valPusher = document.getElementById("pushVoidID");
    valPusher.value = orderID;
    let valPusher1 = document.getElementById("pushVoidCusID");
    valPusher1.value = cusID;
}

function showItems(orderID)
{
    for (var i = 0; i < currentOrders.length; i++)
    {
        if (orderID === currentOrders[i].orderID)
        {
            let itemList = document.getElementById("itemList");
            
            while (itemList.firstChild) 
            {
                itemList.removeChild(itemList.lastChild);
            }
            
            for (var j = 0; j < currentOrders[i].itemList.length; j++)
            {
                let currItem = document.createElement("ul");
                currItem.innerHTML = currentOrders[i].itemList[j].itemName;
                
                itemList.appendChild(currItem);
            }
            return;
        }
    }
}

function createModalFooter(status, orderID, cusId)
{
    let modalFooter = document.createElement("div");
    modalFooter.classList.add("modal-footer");
    modalFooter.id = "footer" + orderID;
    
    
    let statusButton = document.createElement("Button");
    statusButton.classList.add("btn");
    statusButton.classList.add("btn-default");
    statusButton.dataset.dismiss = "modal";
    statusButton.type = "button";
    if (status === "RECEIVED")
    {
        statusButton.value = "CONFIRM";
        statusButton.style = "color: white; background-color: orange;";
        statusButton.innerHTML = "CONFIRM";
        statusButton.setAttribute("onclick", "confirmOrder(" + orderID + ")");
    }else if (status === "CONFIRMED")
    {
        statusButton.value = "COMPLETE";
        statusButton.style = "color: black; background-color: green";
        statusButton.innerHTML = "COMPLETE";
        statusButton.setAttribute("onclick", "completeOrder(" + orderID + ")");
    }
    
    modalFooter.appendChild(statusButton);
    
    
    let voidButton = document.createElement("Button");
    voidButton.classList.add("btn");
    voidButton.classList.add("btn-default");
    voidButton.dataset.toggle = "modal";
    voidButton.dataset.target = "#credentialModal";
    voidButton.setAttribute("onclick", "prepVoidOrder(" + orderID + " , " +cusId + ")");
    voidButton.type = "button";
    voidButton.style = "color: white; background-color: red;";
    voidButton.innerHTML = "VOID";
    
    modalFooter.appendChild(voidButton);
    
    
    
    let showItemsButton = document.createElement("Button");
    showItemsButton.classList.add("btn");
    showItemsButton.classList.add("btn-default");
    showItemsButton.dataset.toggle = "modal";
    showItemsButton.dataset.target = "#itemModal";
    showItemsButton.setAttribute("onclick", "showItems(" + orderID + ")");
    showItemsButton.value = "SHOW ITEMS";
    showItemsButton.style = "color: white; background-color: black";
    showItemsButton.innerHTML = "SHOW ITEMS";
    
    modalFooter.appendChild(showItemsButton);
    
    return modalFooter;
}