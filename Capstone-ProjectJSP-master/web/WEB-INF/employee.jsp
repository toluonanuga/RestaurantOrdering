<%-- 
    Document   : employee
    Created on : Feb 20, 2020, 8:55:01 AM
    Author     : 553394
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/employee.css" type="text/css" rel="stylesheet" />
	
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>JSP Page</title>
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
        
        <div class="container">
            <div class="card-columns">
                <div class="card-rows" id="cardcontainer">   
                  
                    <div id="credentialModal" class="modal fade" style="z-index:100000">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <input type="hidden" id="pushVoidID">
                                    <input type="hidden" id="pushVoidCusID">
                                    <p>Manager Email<br><input type="text" id="managerEmail" data-tabindex="1"></p>
                                    <p>Password<br><input type="text" id="managerPass" data-tabindex="2"></p>
                                    <p>Reason<br><input type="text" id="managerReason" data-tabindex="2"></p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" data-dismiss="modal" class="btn">Close</button>
                                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="voidOrder()">Ok</button>
                                </div>  
                            </div>
                        </div>
                    </div>
                    
                    <div id="itemModal" class="modal fade" style="margin-left: 20%">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <ul id="itemList">
                                        <li>
                                            Item 1
                                        </li>
                                        <li>
                                            Item 2
                                        </li>
                                    </ul>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" data-dismiss="modal" class="btn">Close</button>
                                </div>  
                            </div>
                        </div>
                    </div>
            </div>
        </div>
        

        
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha384-vk5WoKIaW/vJyUAd9n/wmopsmNhiy+L2Z+SBxGYnUkunIxVxAv/UtMOhba/xskxh" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script type="text/javascript" src="javascript/employee.js"></script>
    </body>
</html>
