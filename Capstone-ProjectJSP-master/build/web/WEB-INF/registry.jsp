<%-- 
    Document   : register
    Created on : Feb 20, 2020, 1:59:23 PM
    Author     : 777568
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" type="text/css" href="css/register.css"">
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

        <p class="text-lg-center"><h1>REGISTER</h1></p>

    <div class="container-fluid"> <!-- the overarching div for both the reciept and the form. !-->



        <div class="row">

            <div class="col-lg bg-light">     <!-- form div !-->
                <span style="white-space:nowrap"> <!--forces the labels to not wrap with spaces !-->
                    <form action="registry" method="POST">

                        <h2>Customer Information</h2>
                        <div class="form-group row">
                            <label for="recieverFirstname" class="col-sm-2 col-form-label">First Name:</label>
                            <div class="col-sm-4">
                                <input type="text" name="recieverFirstname" class="form-control" id="recieverFirstname" placeholder="First Name">
                            </div>
                            <label for="recieverLastname" class="col-sm-2 col-form-label">Last Name:</label>
                            <div class="col-sm-4">
                                <input type="text" name="recieverLastname" class="form-control" id="recieverLastname" placeholder="Last Name">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="recieverEmail" class="col-sm-2 col-form-label">Email:</label>
                            <div class="col-sm-4">
                                <input type="email" name="recieverEmail" class="form-control" id="recieverEmail" placeholder="Email">
                            </div>                                
                            <label for="recieverPhonenumber" class="col-sm-2 col-form-label">Phone:</label>
                            <div class="col-sm-4">
                                <input type="tel" class="form-control" name="recieverPhonenumber" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" id="recieverPhonenumber" placeholder="123-123-1234" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="building" class="col-sm-2 col-form-label">Building:</label>
                            <div class="col-sm-4">
                                <input type="text" name="building" class="form-control" id="building" placeholder="Building no.">
                            </div>
                            <label for="unit" class="col-sm-2 col-form-label">Unit:</label>
                            <div class="col-sm-4">
                                <input type="text" name="unit" class="form-control" id="unit" placeholder="Unit (If applicable)">
                            </div>                                
                        </div>

                        <div class="form-group row">
                            <label for="deliveryStreet" class="col-sm-2 col-form-label">Street:</label>
                            <div class="col-sm-4">
                                <input type="text" name="recieverStreet" class="form-control" id="recieverStreet" placeholder="Street">
                            </div>
                            <label for="deliveryPostalCode" class="col-sm-2 col-form-label">Postal Code:</label>
                            <div class="col-sm-4">
                                <input type="text" name="recieverPostalCode" class="form-control" id="recieverPostalCode" placeholder="A1A1R1" partern="[A-Za-z][0-9][A-Za-z][0-9][A-Za-z][0-9]">
                            </div>                                
                        </div>

                        <div class="form-group row">
                            <label for="deliveryCity" class="col-sm-2 col-form-label">City:</label>
                            <div class="col-sm-4">
                                <input type="text" name="recieverCity" readonly class="form-control" id="recieverCity" value="Calgary">
                            </div>    
                            <label for="deliveryProvince" class="col-sm-2 col-form-label">Province:</label>
                            <div class="col-sm-4">
                                <input type="text" name="recieverProvince" readonly class="form-control" id="recieverProvince" value="AB">
                            </div>                                
                        </div>   
                </span>
            </div>

            <div class="col-lg bg-light" id="displayDeliverInfo">
                <span style="white-space:nowrap"> <!--forces the labels to not wrap with spaces !-->


                    <h2>Delivery Information</h2>


                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="duplicate" id="duplicate" onclick="autoWrite(this)" >

                        <label>Click to duplicate</label>
                    </div>


                    <div class="form-group row">
                        <label for="deliveryEmail" class="col-sm-2 col-form-label">Email:</label >
                        <div class="col-sm-4">
                            <input type="email" name="deliveryEmail" class="form-control" id="deliveryEmail" placeholder="email" >
                        </div>                                
                        <label for="deliveryPhonenumber" class="col-sm-2 col-form-label">Phone:</label>
                        <div class="col-sm-4">
                            <input type="tel" class="form-control" name="deliveryPhonenumber" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" id="deliveryPhonenumber" placeholder="123-123-1234">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="deliverybuilding" class="col-sm-2 col-form-label">Building:</label>
                        <div class="col-sm-4">
                            <input type="text" name="deliverybuilding" class="form-control" id="deliverybuilding" placeholder="Building no.">
                        </div>
                        <label for="deliveryunit" class="col-sm-2 col-form-label">Unit:</label>
                        <div class="col-sm-4">
                            <input type="text" name="deliveryunit" class="form-control" id="deliveryunit" placeholder="Unit (if applicable)">
                        </div>                                
                    </div>

                    <div class="form-group row">
                        <label for="deliveryStreet" class="col-sm-2 col-form-label">Street:</label>
                        <div class="col-sm-4">
                            <input type="text" name="deliveryStreet" class="form-control" id="deliveryStreet" placeholder="Street">
                        </div>
                        <label for="deliveryPostalCode" class="col-sm-2 col-form-label" >Postal Code:</label>
                        <div class="col-sm-4">
                            <input type="text" name="deliveryPostalCode" class="form-control" id="deliveryPostalCode" placeholder="A1A1R1" partern="[A-Za-z][0-9][A-Za-z][0-9][A-Za-z][0-9]"placeholder="A1A1R1">
                        </div>                                
                    </div>

                    <div class="form-group row">
                        <label for="deliveryCity" class="col-sm-2 col-form-label">City:</label>
                        <div class="col-sm-4">
                            <input type="text" name="deliveryCity" readonly class="form-control" id="deliveryCity" value="Calgary">
                        </div>    
                        <label for="deliveryProvince" class="col-sm-2 col-form-label">Province:</label>
                        <div class="col-sm-4">
                            <input type="text" readonly name="deliveryProvince" class="form-control" id="deliveryProvince" value="AB">
                        </div>                                
                    </div>  

                    <div class="form-group row">
                        <label for="deliveryNoteLabel" class="col-sm-2 col-form-label">Let me know about any allergies</label>
                    </div>

                    <div class="form-group row">
                        <label for="deliveryNote" class="col-sm-2 col-form-label">Note:</label>
                        <div class="col-sm-4">
                            <input type="text" name="deliveryNote"  class="form-control" id="deliveryNote">

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-10 offset-sm-2">
                            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Next</button>
                        </div>
                    </div>

                    <div class="form-group row">
                        <h4>${message}</h4>
                    </div>
                    <div class="modal fade" id="myModal" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title">Enter Password</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>

                                </div>
                                <div class="modal-body" style="width">
                                    <div class="form-group">
                                        <label for="password" class="col-sm-2 col-form-label" style="font-weight: bold">Password:</label>
                                        <input type="password" name="password" class="form-control" id="password" onchange="checkDuplicate()">
                                    </div>
                                    <div class="form-group">
                                        <label for="confirmPass" class="col-sm-2 col-form-label" style="font-weight: bold">Confirm Password:</label>
                                        <input type="password" name="confirmPass" class="form-control" id="confirmPass" onkeyup="checkDuplicate()">
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <input type="submit" class="btn btn-primary" value="Submit">
                                </div>
                            </div>

                        </div>
                    </div>
                    </form>
                </span>


            </div>
        </div>




        <!-- Always jQuery first, then Popper.js, then Bootstrap JS, then our custom JS files. -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="javascript/register.js"></script>
</body>
</html>
