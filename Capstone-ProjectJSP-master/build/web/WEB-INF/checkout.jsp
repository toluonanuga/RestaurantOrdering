<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" type="text/css" href="css/checkout.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
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
                        <a class="nav-item nav-link" href="favourites">History/Favourites</a>
                        <a class="nav-item nav-link" href="edit">Edit Account</a>
                        <a class="nav-item nav-link" href="Logout">Logout</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>
        <div class="container-fluid"> <!-- the overarching div for both the reciept and the form. !-->
            <div class="row">
                <div class="col-lg bg-light">     <!-- form div !-->
                    <span style="white-space:nowrap"> <!--forces the labels to not wrap with spaces !-->
                        <h4> Delivery Information </h4>
                        <form action = "checkout" method="POST">
                            <div class="form-group row">
                                <label for="recieverFirstname" class="col-sm-2 col-form-label">First Name:</label>
                                <div class="col-sm-4">
                                    <input type="recieverFirstname" class="form-control" name="recieverFirstname" placeholder="First Name" value="${deliveryFname}" required>
                                </div>
                                <label for="recieverLastname" class="col-sm-2 col-form-label">Last Name:</label>
                                <div class="col-sm-4">
                                    <input type="recieverLastname" class="form-control" name="recieverLastname" placeholder="Last Name" value="${deliveryLname}" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="recieverEmail" class="col-sm-2 col-form-label">Email:</label>
                                <div class="col-sm-4">
                                    <input type="recieverEmail" class="form-control" name="recieverEmail" placeholder="Email" value="${deliveryEmail}" required>
                                </div>                                
                                <label for="recieverPhonenumber" class="col-sm-2 col-form-label">Phone:</label>
                                <div class="col-sm-4">
                                    <input type="recieverPhonenumber" class="form-control" name="recieverPhonenumber" placeholder="123-123-1234" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" value="${deliveryPhone}" title="123-123-1234" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="building" class="col-sm-2 col-form-label">Building:</label>
                                <div class="col-sm-4">
                                    <input type="building" class="form-control" name="building" placeholder="Building no." value="${deliveryBuilding}" required>
                                </div>
                                <label for="unit" class="col-sm-2 col-form-label">Unit:</label>
                                <div class="col-sm-4">
                                    <input type="unit" class="form-control" name="building" placeholder="Unit (If applicable)" value="${deliveryUnit}">
                                </div>                                
                            </div>

                            <div class="form-group row">
                                <label for="deliveryStreet" class="col-sm-2 col-form-label">Street:</label>
                                <div class="col-sm-4">
                                    <input type="deliveryStreet" class="form-control" name="deliveryStreet" placeholder="Street"  value="${deliveryStreet}" required>
                                </div>
                                <label for="deliveryPostalCode" class="col-sm-2 col-form-label">Postal Code:</label>
                                <div class="col-sm-4">
                                    <input type="deliveryPostalCode" class="form-control" name="deliveryPostalCode" placeholder="Postal Code" value="${deliveryPostal}" required>
                                </div>                                
                            </div>

                            <div class="form-group row">
                                <label for="notes" class="col-sm-2 col-form-label">Special <br> Requests?</label>
                                <div class="col-sm-4">
                                    <textarea class="form-control" name="notes" rows="3" value="${deliveryNote}"></textarea>
                                </div>                                
                            </div>  
                            <div class="form-group row">
                                <div class="col-sm-10 offset-sm-2">
                                        <input type="hidden" name="item_list" id="item_list">
                                        <input type="hidden" name="total_price" id="total_price">
                                    <button type="submit" class="btn btn-primary">Next</button>
                                </div>
                            </div>
                        </form>

                    </span>
                </div>
                <div class="col-lg bg-light">
                    <table class="table" id="treciept"></table> 
                </div>
            </div>
        </div>  
        <!-- Always jQuery first, then Popper.js, then Bootstrap JS, then our custom JS files. -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="javascript/checkout.js"></script>
    </body>
</html>
