<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" type="text/css" href="css/checkout.css"">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Checkout</title>
    </head>
    <body>
        <nav class="navbar fixed-top navbar-expand-lg navbar-light ">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" name="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-brand nav-link" href="home">
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
                            <a class="nav-item nav-link" href="edit">Edit Account</a>
                            <a class="nav-item nav-link" href="Logout">Logout</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>

        <div align="center">
            <h3>Order Review</h3>
            <div class="row justify-content-center">
                <div class="col-auto">
                    <form action="OrderConfirmation" method="POST">
                        <table class="table">
                            <tr>
                                <td >Items</td>
                                <th>Quantity</th>
                                <th>Price</th>
                            </tr>

                            <c:forEach items="${cartItems}" var="currItem" varStatus="status">
                                <tr>
                                    <td>
                                        ${currItem.itemName}
                                    </td>
                                    <td>
                                        ${currItem.quantity}
                                    </td>
                                    <td>
                                        ${currItem.price}
                                    </td>
                                </tr>
                            </c:forEach>

                                <tr>
                                    <td colspan="3">
                                      ${total}
                                    </td>
                                </tr>                                
                                
                            <tr>
                                <td colspan="3">
                                    <h3>Personal Information</h3>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><p>${deliveryName}</p><td>
                            <tr>
                                ${deliveryAdd}
                            <tr>
                                <td><input type="submit" name="PayAtDoor" value="Pay in Person"/></td>
                                <td><input type="submit" name="PayPal" value="PayPal" /></td>
                            </tr>
                        </table>

                    </form>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="javascript/home.js"></script>

    </body>
</html>