/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for getting all the History and Favourites of a Customer.
 */
package servlets;

import brokers.ActorBroker;
import brokers.FavouritesBroker;
import com.main.menu.Item;
import com.main.menu.Reorder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for getting all the History and Favourites of a Customer.
 *
 * @author Gabriel Erhman
 */
@WebServlet(name = "FavouritesServlet", urlPatterns = {"/FavouritesServlet"})
public class FavouritesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String actorEmail = (String) session.getAttribute("actorEmail");
        if (actorEmail == null) {
            actorEmail = "false";
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            ActorBroker ab = new ActorBroker();
            FavouritesBroker fb = new FavouritesBroker();
            ArrayList<Item> favouriteList = fb.getItemsByActorId(ab.getActorID(actorEmail));
            ArrayList<Reorder> orderedItemList = fb.getOrdersByActorId(ab.getActorID(actorEmail));
            session.setAttribute("favouriteList", favouriteList);
            session.setAttribute("orderedItemList", orderedItemList);
            String orderList = "";
            String csv = "";
            for (Reorder re : orderedItemList) {
                csv = commaSeperate(re.getItemList());
                orderList += "<div id = reorder" + re.getOrderID() + ">" + csv + "</div>";
                csv = "";
            }
            request.setAttribute("orderList", orderList);
            getServletContext().getRequestDispatcher("/WEB-INF/favourites.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
// </editor-fold>

    private String commaSeperate(List<Item> itemList) {
        String csv = "";
        StringBuilder builder = new StringBuilder();
        for (Item i : itemList) {
            builder.append(i.getItemId()).append(",").append(i.getItemName()).append(",")
                    .append(i.getPrice()).append(",").append(i.getQuantity()).append(",");
        }
        csv = builder.deleteCharAt(builder.length() - 1).toString();
        System.out.println(csv);
        return csv;
    }
}
