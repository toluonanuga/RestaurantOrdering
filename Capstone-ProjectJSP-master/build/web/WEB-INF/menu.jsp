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
        <div class="container">
            <div class=row">
                <h2>Appetizers</h2>
                <div class="card-columns">
                    <c:forEach items="${appetizers}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" value="no"  data-dismiss="modal" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                         <div>                    
                                                 <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" data-toggle="tooltip" title="Hooray!" onclick="AddFavoriteItem(this,${currItem.itemId})">
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>       
            
            
            
            <div class=row">
                <h2>Chef's Special</h2>
                <div class="card-columns">
                    <c:forEach items="${chefs}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" data-dismiss="modal" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>       
            
            
            
            <div class=row">
                <h2>Breads</h2>
                <div class="card-columns">
                    <c:forEach items="${breads}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" data-dismiss="modal" id="add" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>     
            
            
            
            
            <div class=row">
                <h2>Biryani</h2>
                <div class="card-columns">
                    <c:forEach items="${biryani}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" data-dismiss="modal" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>       
            
            
            
            <div class=row">
                <h2>Rice</h2>
                <div class="card-columns">
                    <c:forEach items="${rice}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" data-dismiss="modal" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>       
            
            
            
            
            <div class=row">
                <h2>Kadahi Specials</h2>
                <div class="card-columns">
                    <c:forEach items="${kadahi}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" data-dismiss="modal" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>   
            
            
            
            
            <div class=row">
                <h2>Beef Curries</h2>
                <div class="card-columns">
                    <c:forEach items="${beefCurry}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" data-dismiss="modal" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>       
            
            
            
            <div class=row">
                <h2>Favourites</h2>
                <div class="card-columns">
                    <c:forEach items="${favourites}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" data-dismiss="modal" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>       
            
            
            
            <div class=row">
                <h2>Lamb/Goat Curries</h2>
                <div class="card-columns">
                    <c:forEach items="${lambGoat}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" data-dismiss="modal" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>       
            
            
            
            <div class=row">
                <h2>Chicken Curries</h2>
                <div class="card-columns">
                    <c:forEach items="${chicken}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" data-dismiss="modal" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>       
            
            
            
            <div class=row">
                <h2>Vegetable Curries</h2>
                <div class="card-columns">
                    <c:forEach items="${veg}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" name="FoodImage" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4><br>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                   </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" data-dismiss="modal" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>       
            
            
            
            <div class=row">
                <h2>Indian Chinese</h2>
                <div class="card-columns">
                    <c:forEach items="${indoChinese}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" data-dismiss="modal" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>    
            
            
            
            <div class=row">
                <h2>Sides & Dip</h2>
                <div class="card-columns">
                    <c:forEach items="${sides}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" data-dismiss="modal" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>    
            
            
            
            <div class=row">
                <h2>Drinks</h2>
                <div class="card-columns">
                    <c:forEach items="${drinks}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" data-dismiss="modal" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>  
            
            
            
            <div class=row">
                <h2>Desserts</h2>
                <div class="card-columns">
                    <c:forEach items="${dessert}" var="currItem" varStatus="status">
                        <div class="card" style="max-width: 300px;  max-height:200px;" data-toggle="modal" data-target="#myModal${currItem.itemId}">
                            <div class="row no-gutters">
                                <div class="col-md-6">
                                    <img src="${currItem.imagePath}" class="card-img" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title">${currItem.itemName}</h5>
                                        <p class="card-text">${currItem.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>            
                                    
                        <div class="modal fade" id="myModal${currItem.itemId}" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">${currItem.itemName}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${currItem.description}</p>
                                    </div>
                                    <div class="modal-footer">   
                                        <p>$${currItem.price}</p>
                                         <input type="number" min="0" id="qty${currItem.itemId}">
                                         <button type="button" id="add" data-dismiss="modal" value="no" onclick="AddToOrder(${currItem.itemId}, ${currItem.price},'foodname${currItem.itemName}')">Add to order</button>
                                         <div style="display: none" id="foodname${currItem.itemName}">${currItem.itemName}</div>
                                        <div>
                                            <input type ="checkbox" id="FavButton${currItem.itemId}" class="css-checkbox" onclick="AddFavoriteItem(this,${currItem.itemId})" >
                                            <label for="FavButton${currItem.itemId}" class="css-label"></label> 
                                        </div>  
                                        
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                            
                    </c:forEach>
                </div>
            </div>       
        </div>

        <!-- Always jQuery first, then Popper.js, then Bootstrap JS, then our custom JS files. -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="javascript/menu.js"></script>
    </body>
</html>
