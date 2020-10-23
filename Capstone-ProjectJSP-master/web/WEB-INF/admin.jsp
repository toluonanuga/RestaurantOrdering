<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <!--        <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
                 Bootstrap CSS 
        
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        
                <link rel="stylesheet" type="text/css" href="css/manager.css">
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">-->       

        <link href="css/manager.css" type="text/css" rel="stylesheet" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Manager Page</title>
    </head>
    <body>

        <nav class="navbar fixed-top navbar-expand-lg navbar-light ">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-brand nav-link" href="#">
                        <img src="css/tg.png" width="30" height="30" alt="">Tandoori Grill
                    </a>
                    <a class="nav-item nav-link" href="Logout">Logout</a>
                </div>
            </div>
        </nav>

        <div class="container" style="background-color: white;">

            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a data-toggle="tab" class="nav-link" onclick="recieveItemList()" href="#menu1">Edit Menu</a></li>
                <li class="nav-item">
                    <a data-toggle="tab" class="nav-link" href="#menu6">Add Item</a></li>
                <li class="nav-item">
                    <a data-toggle="tab" class="nav-link" onclick="recieveActorList()" href="#menu2">Edit Account</a></li>
                <li class="nav-item">
                    <a data-toggle="tab" class="nav-link" href="#menu3">Print Reports</a></li>
                <li class="nav-item">
                    <a data-toggle="tab" class="nav-link" onclick="recieveOrderList()" href="#menu4">Search Past Orders</a></li>
                <li class="nav-item">
                    <a data-toggle="tab" class="nav-link" onclick="getFileList()" href="#menu5">Backup and Restore</a></li>
            </ul>

            <div class="tab-content">
                <div id="menu1" class="tab-pane fade">
                    <h3>Edit Menu</h3>

                    <div class="row">
                        <form class="form-inline">
                            <label for="searchMenu" class="col-sm-2 col-form-label">Search Item:</label>
                            <input type="text" class="form-control mb-2 mr-sm-2" id="searchMenuBar" placeholder="Search by name">
                            <button type="button" class="btn btn-primary mb-2" onclick="searchItemByName(searchMenuBar.value)">Search</button>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Description</th>
                                        <th>Category</th>
                                        <th>Image file path</th>
                                        <th>Edit</th>
                                        <th>Delete</th>
                                    </tr>
                                </thead>
                                <tbody id="menuPlace">
                                    <c:forEach  items="${ItemMenu}" var="item" >         
                                        <tr id="menuItem(${item.itemId})">

                                            <td>${item.itemName}</td>
                                            <td>${item.price}</td>
                                            <td>${item.description}</td>
                                            <td>${item.category}</td>
                                            <td>${item.imagePath}</td>
                                            <td><button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal${item.itemId}">Edit</button></td>
                                            <td><button type="button" class="btn btn-danger btn-lg" onclick="deleteItem(${item.itemId})">Delete</button></td>
                                            <!-- Modal content-->
                                    <div class="modal fade" id="myModal${item.itemId}" role="dialog">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">

                                                    <h4 class="modal-title">Edit ${item.itemName}</h4>
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <label for="editItemId" class="col-sm-2 col-form-label">Id: </label>
                                                        <input type="text" name="editItemId" id="editItemId${item.itemId}" class="form-control" value="${item.itemId}" disabled>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editItemName" class="col-sm-2 col-form-label">Name:</label>
                                                        <input type="text" name="editItemName" id="editItemName${item.itemId}" class="form-control" value="${item.itemName}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editItemPrice" class="col-sm-2 col-form-label">Price:</label>
                                                        <input type="text" name="editItemPrice" id="editItemPrice${item.itemId}" class="form-control" value="${item.price}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editItemDescription" class="col-sm-2 col-form-label">Description:</label>
                                                        <input type="text" name="editItemDescription" id="editItemDescription${item.itemId}" class="form-control" value="${item.description}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editItemCategory" class="col-sm-2 col-form-label">Category:</label>
                                                        <input type="text" name="editItemCategory" id="editItemCategory${item.itemId}" class="form-control" value="${item.category}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editItemFilePath" class="col-sm-2 col-form-label">Image File Path:</label>
                                                        <input type="text" name="editItemFilePath" id="editItemFilePath${item.itemId}" class="form-control" value="${item.imagePath}">
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-primary" onclick="editItem(editItemId${item.itemId}, editItemName${item.itemId}, editItemPrice${item.itemId}, editItemDescription${item.itemId}, editItemCategory${item.itemId}, editItemFilePath${item.itemId})" >Save</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </form>


                    </div>


                </div>
                <div id="menu2" class="tab-pane fade">
                    <h3>Edit Account</h3>
                    
                    <div class="row">
                        <form class="form-inline">
                            <label for="searchActor" class="col-sm-2 col-form-label">Search Actors:</label>
                            <input type="text" class="form-control mb-2 mr-sm-2" id="searchActorBar" placeholder="Search by Email">
                            <button type="button" class="btn btn-primary mb-2" onclick="searchUserByEmail(searchActorBar.value)">Search</button>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Email</th>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Edit</th>
                                        <th>Delete</th>
                                    </tr>
                                </thead>
                                <tbody id="editAccountPlace">
                                    <c:forEach  items="${account_edit}" var="actor" >         
                                        <tr id="userId(${actor.id})">

                                            <td>${actor.email}</td>
                                            <td>${actor.firstName}</td>
                                            <td>${actor.lastName}</td>
                                            <td><button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#actorModal${actor.id}">Edit</button></td>
                                            <td><button type="button" class="btn btn-danger btn-lg" onclick="deleteActor(${actor.id})">Delete</button></td>
                                            
                                    <div class="modal fade" id="actorModal${actor.id}" role="dialog">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">

                                                    <h4 class="modal-title">Edit ${actor.firstName} ${actor.lastName}</h4>
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <label for="editActorId" class="col-sm-2 col-form-label">Id: </label>
                                                        <input type="text" name="editActorId" id="editActorId${actor.id}" class="form-control" value="${actor.id}" disabled>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editFirstName" class="col-sm-2 col-form-label">First Name:</label>
                                                        <input type="text" name="editFirstName" id="editActorFName${actor.id}" class="form-control" value="${actor.firstName}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editLastName" class="col-sm-2 col-form-label">Last Name:</label>
                                                        <input type="text" name="editLastName" id="editActorLName${actor.id}" class="form-control" value="${actor.lastName}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editStreet" class="col-sm-2 col-form-label">Street:</label>
                                                        <input type="text" name="editStreet" id="editStreet${actor.id}" class="form-control" value="${actor.street}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editHouseNumber" class="col-sm-2 col-form-label">House Num:</label>
                                                        <input type="text" name="editHouseNumber" id="editHouseNumber${actor.id}" class="form-control" value="${actor.houseNumber}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editUnitNumber" class="col-sm-2 col-form-label">Unit Num:</label>
                                                        <input type="text" name="editUnitNumber" id="editUnitNumber${actor.id}" class="form-control" value="${actor.unitNumber}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editCity" class="col-sm-2 col-form-label">City:</label>
                                                        <input type="text" name="editCity" id="editCity${actor.id}" class="form-control" value="${actor.city}" readonly>
                                                    </div>
                                                    
                                                    <div class="form-group">
                                                        <label for="editProvince" class="col-sm-2 col-form-label">Province:</label>
                                                        <input type="text" name="editProvince" id="editProvince${actor.id}" class="form-control" value="${actor.province}" readonly>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editPostal" class="col-sm-2 col-form-label">Postal:</label>
                                                        <input type="text" name="editPostal" id="editPostal${actor.id}" class="form-control" value="${actor.postalCode}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editPhoneNumber" class="col-sm-2 col-form-label">Phone:</label>
                                                        <input type="text" name="editPhoneNumber" id="editPhoneNumber${actor.id}" class="form-control" value="${actor.phoneNumber}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editEmail" class="col-sm-2 col-form-label">Email:</label>
                                                        <input type="text" name="editEmail" id="editEmail${actor.id}" class="form-control" value="${actor.email}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editPassword" class="col-sm-2 col-form-label">Password:</label>
                                                        <input type="text" name="editPassword" id="editPassword${actor.id}" class="form-control" value="${actor.password}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="editRole" class="col-sm-2 col-form-label">Role:</label>
                                                        <select class="form-control" id="editRole${actor.id}" name="editRole">
                                                            <option value="${actor.role}" selected disabled hidden>${actor.role}</option>
                                                            <option value="C">C ustomer</option>
                                                            <option value="E">E mployee</option>
                                                            <option value="M">M anager</option>
                                                            <option value="A">A dmin</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-primary" onclick="editActor(editActorId${actor.id}, editActorFName${actor.id}, editActorLName${actor.id}, editStreet${actor.id}, editHouseNumber${actor.id}, editUnitNumber${actor.id}, editCity${actor.id}, editPostal${actor.id}, editProvince${actor.id}, editPhoneNumber${actor.id}, editEmail${actor.id}, editPassword${actor.id}, editRole${actor.id})" >Save</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </form>
                    </div>


                </div>
                <div id="menu6" class="tab-pane fade">
                    <h3>Add item to Menu</h3>
                    <table class="table">
                        <tbody>
                        <form action="admin?action=addItem" method="POST" enctype="multipart/form-data" id="addForm">
                            <tr>
                                <td>
                                    <label for="addItemName">Item name</label>
                                </td>
                                <td>
                                    <input type="text" id="addItemName" name="addItemName" placeholder="Input Name"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="addItemPrice">Price</label>
                                </td>
                                <td>
                                    <input type="text" id="addItemPrice" name="addItemPrice" placeholder="Input price">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="addItemDes">Description</label>
                                </td>
                                <td>
                                    <input type="text" id="addItemDes" name="addItemDes" placeholder="Input Description">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="addItemCate">Category</label>
                                </td>
                                <td>
                                    <select id="addItemCate" name="addItemCate">
                                        <option value="appetizers">Appetizers</option>
                                        <option value="chefs">Chefs Special</option>
                                        <option value="breads">Breads</option>
                                        <option value="biryani">Biryani</option>
                                        <option value="rice">Rice</option>
                                        <option value="kadahi">Kadahi</option>
                                        <option value="beef curry">Beef Curry</option>
                                        <option value="lamb_goat">Lamb/Goat</option>
                                        <option value="favorites">Favorites</option>
                                        <option value="veg">Vegetable</option>
                                        <option value="indo_chinese">Indonesian Chinese</option>
                                        <option value="sides">Sides</option>
                                        <option value="chicken">Chicken</option>
                                        <option value="drinks">Drinks</option>
                                        <option value="desert">Desserts</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="addItemImage">Image File</label>
                                </td>
                                <td>
                                    <input type="file" id="addItemImage" name="addItemImage"> <span>374x374 image, .jpeg .jpg .png format</span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button type="button" onclick="addItem()" >Add</input>
                                </td>
                            </tr>
                        </form>
                        <p>${addItemStatus}</p>
                        </tbody>
                    </table>
                </div>
                <div id="menu3" class="tab-pane fade">
                    <h3>Print Reports</h3>
                    <div class="row">
                        <form class="form-inline">
                            <label for="printReportbar" class="col-sm-2 col-form-label" >Input File Name</label>
                            <input type="text" class="form-control mb-2 mr-sm-2" id="printReportbar" name="search" placeholder="filenam without .csv" >
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Type Of Report</th>
                                        <th>Print</th>
                                    </tr>
                                </thead> 
                                <tbody>
                                    <tr>
                                        <td>All Orders</td>
                                        <td><button type="button" class="btn btn-primary mb-2" onclick="printReport(1, printReportbar.value)">Print</button></td>
                                    </tr>
                                    <tr>
                                        <td>All Orders In Last Month</td>
                                        <td><button type="button" class="btn btn-primary mb-2" onclick="printReport(2, printReportbar.value)">Print</button></td>
                                    </tr>
                                    <tr>
                                        <td>All Orders In Last Week</td>
                                        <td><button type="button" class="btn btn-primary mb-2" onclick="printReport(3, printReportbar.value)">Print</button></td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                        <h4 id="reportMessage"></h4>
                    </div>
                </div>
                <div id="menu4" class="tab-pane fade">
                    <h3>Search Past Orders</h3>
                    <div class="row">
                        <form class="form-inline">
                            <label for="searchOrder" class="col-sm-2 col-form-label">Search Order:</label>
                            <input type="text" class="form-control mb-2 mr-sm-2" id="searchOrder" name="search" placeholder="Search orders by email" >
                            <button type="button" class="btn btn-primary mb-2" onclick="searchOrderByEmail(searchOrder.value)">Search</button>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Order Id</th>
                                        <th>Customer Email</th>
                                        <th>Order Status</th>
                                        <th>Payment Method</th>
                                        <th>Start date</th>
                                        <th>Due date</th>
                                        <th>View detail</th>
                                    </tr>
                                </thead> 
                                <tbody id="orderSpace">
                                    <c:forEach items="${orderList}" var="order">
                                        <tr>
                                            <td>${order.orderID}</td>
                                            <td>${order.customerEmail}</td>
                                            <td>${order.orderStatus}</td>
                                            <td>${order.paymentMethod}</td>
                                            <td><fmt:formatDate type="both" value="${order.orderDate}"/></td>
                                            <td><fmt:formatDate type="both" value="${order.dueDate}"/></td>
                                            <td><button class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModalOrder${order.orderID}" type="button">View</button> </td>
                                    <div class="modal fade" id="myModalOrder${order.orderID}" role="dialog">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">

                                                    <h4 class="modal-title">Order Id ${order.orderID}</h4>
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                </div>
                                                <div class="modal-body">
                                                    <c:forEach items="${order.itemList}" var="orderItem">
                                                        <div class="form-group row">
                                                            <label for="orderprice${order.orderID}_${orderItem.itemId}" class="col-sm-2 col-form-label">${orderItem.itemName}</label>
                                                            <div class="col-sm-10">
                                                                <h2><input type="text" readonly class="form-control-plaintext" id="orderprice${order.orderID}_${orderItem.itemId}" value="$${orderItem.price}"></h2>
                                                            </div>      
                                                        </div>   
                                                    </c:forEach> 
                                                </div>
                                                <div class="modal-footer">
                                                    <h2>Total:</h2>
                                                    <h2>$${order.totalPrice}</h2>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
                <div id="menu5" class="tab-pane fade">
                    <h3>Backup and Restore Database</h3>
                    <div class="row"> 
                        <form class="form-inline">
                            <label for="createBackup" class="col-sm-2 col-form-label">Input Backup file name:</label>
                            <input type="text" class="form-control mb-2 mr-sm-2" id="createBackup" placeholder="Backup file name">
                            <button type="button" class="btn btn-primary mb-2" onclick="createBackUpFile(createBackup.value)">Create</button>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Restore File</th>
                                        <th>Restore</th>
                                    </tr>
                                </thead>
                                <tbody id="restoreSpace">
                                    <c:forEach items="${restoreFileList}" var="restoreFile">
                                        <tr>
                                            <td id="file_${restoreFile}">${restoreFile}</td>
                                            <td><button type="button"  class="btn btn-info btn-lg" onclick="restoreDatabase('file_${restoreFile}')">Restore</button></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </form>
                        <p id="backUpRestoreStatus"></p>
                        <h4>${main_message}</h4>
                    </div>
                </div>



            </div>


            <!-- Always jQuery first, then Popper.js, then Bootstrap JS, then our custom JS files. -->
            <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

            <script src="javascript/adminManageMenu.js"></script>
            <script src="javascript/adminManageUser.js"></script>
            <script src="javascript/adminManageOrder.js"></script>
            <script src="javascript/adminRestore.js"></script>
            <script src="javascript/adminBackup.js"></script>
            <script src="javascript/printReportFile.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>