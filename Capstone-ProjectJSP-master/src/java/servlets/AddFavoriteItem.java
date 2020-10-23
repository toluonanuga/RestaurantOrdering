/*
 * Class Description: A bridge between JSPs and the Services: This Servlet works on Adding and Removing Favourite Items.
 */
package servlets;

import brokers.AdminBroker;
import com.main.menu.Item;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AdminService;
import services.CustomerService;
import services.MenuService;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet works on Adding and Removing Favourite Items.
 *
 * @author cuong
 */
@WebServlet(name="AddFavourite", urlPatterns = "/addFav")
public class AddFavoriteItem extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
//        int itemId = request.get
        CustomerService cs = new CustomerService();
        AdminBroker ab = new AdminBroker();
        String email = (String) session.getAttribute("actorEmail");
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        String action = (String) request.getParameter("action");
        Item item = new Item();
        MenuService ms = new MenuService();
        item = ms.getItemById(itemId);
        if(item != null && action.equals("1"))
        {
          cs.addFavoriteItem( ab.getActorbyEmail(email).getId(), item);
        }
        else if (item != null && action.equals("0"))
        {
            cs.deleteCusFavoriteItem(ab.getActorbyEmail(email).getId(), item);
        }
//       response.getWriter().print("hi ");
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
}
