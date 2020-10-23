<%-- 
    Document   : manager
    Created on : Mar 2, 2020, 4:15:14 PM
    Author     : 763199
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                    <a onclick="recieveOrderList()" class="nav-link active" id="home-tab" data-toggle="tab"  href="#home" role="tab" aria-controls="home" aria-selected="true">View Order</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Print Reports</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">

                <!--View Orders-->
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <h3>View Order</h3>
                    <div class="row">
                        <form class="form-inline">
                            <label for="searchOrder" class="col-sm-2 col-form-label">Search Order:</label>
                            <input type="text" class="form-control mb-2 mr-sm-2" id="searchOrder" name="search" placeholder="Search orders by Order Id" >
                            <button type="button" class="btn btn-primary mb-2" onclick="searchOrderById(searchOrder.value)">Search</button>
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
                                                            <label for="orderprice${order.orderID}_${orderItem.itemId}" class="col-sm-3 col-form-label">${orderItem.itemName}</label>
                                                            <div class="col-sm-2">
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


                <!--Print Report-->
                <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
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
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha384-vk5WoKIaW/vJyUAd9n/wmopsmNhiy+L2Z+SBxGYnUkunIxVxAv/UtMOhba/xskxh" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="javascript/printReportFile.js"></script>
        <!--<script type="text/javascript" src="javascript/employee.js"></script>-->
        <script type="text/javascript" src="javascript/manager.js"></script>
    </body>
</html>
