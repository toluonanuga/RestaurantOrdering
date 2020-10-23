<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" type="text/css" href="css/menu.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
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
                    <a class="nav-item nav-link" href="menu">Menu</a>
                    <a class="nav-item nav-link" href="home">Main Page</a>
                    <a class="nav-item nav-link" href="DeliveryChoice">Checkout</a>  
                    <c:choose>
                        <c:when test="${empty sessionScope.actorEmail}">
                        <a class="nav-item nav-link" href="login">Login</a>
                        </c:when>
                        <c:otherwise>
                        <a class="nav-item nav-link" href="favourites">History/Favourites</a>
                        <a class="nav-item nav-link" href="edit">Edit Account</a>
                        <a class="nav-item nav-link" href="Logout">Logout</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>

        <div class="container">
            <div class="row">
                <h1>Favourites</h1>
                <div class="card-columns">
                    <c:forEach items="${favouriteList}" var="item" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${item.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${item.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${item.itemName}</h5>
                                        <p class="card-text">$${item.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            

                        <div class="modal fade" id="myModal${item.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${item.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${item.description}</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <h1>Order History</h1>
            <hr/>
            <table>
                <c:forEach items="${orderedItemList}" var="order" varStatus="status">
                    <tr>
                        <c:forEach items="${order.itemList}" var="items">

                        <div class="card" style="max-width: 1000px;  max-height:100px;" data-toggle="modal" data-target="#myModal${items.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${items.imagePath}" class="card-img" alt="..." style="width:95px;height:95px;">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${items.itemName}</h5>
                                        <p class="card-text">$${items.price}</p>
                                        <p hidden>${items.quantity}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            

                        <div class="modal fade" id="myModal${items.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${items.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${items.description}</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${status.last}">
                        <div class="card" style="max-width: 1000px;  max-height:70px;">
                            <div class="row no-gutters">
                                <div class="card-body">
                                    <h4 class="card-text" align="right">$${order.totalPrice} <input type="submit" value="Reorder" onClick="AddToOrder(${order.orderID})"/></h4>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    </tr>
                </c:forEach>
            </table>
            <hr/>
        </div>

        <div hidden id ="reorders">
            ${orderList}
        </div>

        <!-- Always jQuery first, then Popper.js, then Bootstrap JS, then our custom JS files. -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="javascript/home.js"></script>
        <script src="javascript/reorder.js"></script>
    </body>
</html>