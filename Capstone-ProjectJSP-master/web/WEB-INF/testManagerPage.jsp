<%-- 
    Document   : testManagerPage
    Created on : Mar 18, 2020, 11:27:10 PM
    Author     : 763199
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>TESTING MANAGER PAGE</h1>
        
        <form action="Manager?mybutton=confirm" method="POST"> 
            <input type="submit" value="Confirm Order">
        </form>
        <br>
        <form action="Manager?mybutton=complete" method="POST"> 
            <input type="submit" value="Complete Order">
        </form>
        <br>
        <form action="Manager?mybutton=voidOrder" method="POST"> 
            <input type="submit" value="Void Order">
        </form>
        <br>
    </body>
</html>
