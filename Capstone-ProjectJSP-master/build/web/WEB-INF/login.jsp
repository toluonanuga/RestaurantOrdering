<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>

        <nav class="navbar fixed-top navbar-expand-lg navbar-light ">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-brand nav-link" href="home">
                        <img src="css/tg.png" width="30" height="30" alt="">Tandoori Grill
                    </a>
                    <a class="nav-item nav-link" href="menu">Menu</a>
                    <a class="nav-item nav-link" href="home">Main Page</a>
                    <a class="nav-item nav-link" href="checkout">Checkout</a>
                    <a class="nav-item nav-link" href="login">Login</a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="card" >
                <div class="card-body"> 
                    <div class ="form">
                    <form action="login" method="POST">
                        <table >
                            <tr>
                                <td>
                                    Email:
                                </td>
                                <td>
                                    <input type="text" name="email" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Password: 
                                </td>
                                <td>
                                    <input type="password" name="password" />
                                </td>
                            </tr>
                        </table>
                        <input id="login_button"  type="submit" value="Login" /> 
                        <h5>${login_message}</h5>
                        <div id="menu_link-div">
                            <a href="menu" class="text-center" ><p class="text-dark">Click here to make an order without account.</p></a> 
                            
                            <a href="registry" class="text-center"><p class="text-dark">Create an account.</p>
                        </div>
                           
                        
                    </form>    
                </div>
            </div>
        </div>
            
        </div> 
        

        <!-- Always jQuery first, then Popper.js, then Bootstrap JS, then our custom JS files. -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="javascript/home.js"></script>
    </body>
</html>
