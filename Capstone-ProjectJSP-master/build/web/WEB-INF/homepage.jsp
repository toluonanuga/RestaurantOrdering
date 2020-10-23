<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" type="text/css" href="css/home.css">
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
        <div class="content" id="content">
        <h1>Welcome to Tandoori Grill</h1>
        <h4>
           Tandoori Grill has been reviewed as one of the best Indian restaurants in Calgary. We strive to bring you the very authentic and tasty 
           delicacies from the Indian subcontinent. We provide a classic combination of indisputable food taste, unique experience and tradition that 
           holds worldwide recognition. In order to create a healthier and lighter meal, we have modified traditional Indian recipes to eliminate excess 
           fat, cholesterol and calories without sacrificing the taste and flavor.Did you watch those mouth watering delicacies. Yeah, they were prepared 
           right in our kitchen with love that is to expose the real taste that is in India. Enjoy Indian authentic food with your loved ones at the best 
           Indian restaurant in Calgary. 
        </h4>  
        </div>

        <!-- Always jQuery first, then Popper.js, then Bootstrap JS, then our custom JS files. -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="javascript/home.js"></script>

    </body>
</html>
