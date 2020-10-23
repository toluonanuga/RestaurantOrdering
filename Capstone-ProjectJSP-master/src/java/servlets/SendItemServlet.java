/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for handling Item/Menu updates, deletes, and page loading.
 */
package servlets;

import brokers.MenuBroker;
import com.google.gson.Gson;
import com.main.menu.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.MenuService;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for handling Item/Menu updates, deletes, and page loading.
 *
 * @author cuong
 */
@WebServlet(name = "SendItemServlet", urlPatterns = {"/SendItemServlet"})
public class SendItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        MenuBroker mb = new MenuBroker();
        List itemList = mb.getAllItem();
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(itemList));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action").toString();
        MenuBroker mb = new MenuBroker();
        if (action.equals("2")) {

            String recievedItem = request.getParameter("sendItem").toString();
            Item item = null;
            Gson gson = new Gson();
            item = gson.fromJson(recievedItem, Item.class);

            boolean updateCheck = mb.updateItem(item);
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(updateCheck));
        } else if (action.equals("3")) {
            
            MenuService ms = new MenuService();
            ms.deleteItemFromList(Integer.parseInt(request.getParameter("deleteItem")));
        }


    }

}
