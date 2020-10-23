/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for constructing all the Menu items for the JSP.
 */
package servlets;

import brokers.MenuBroker;
import com.main.menu.Item;
import com.main.menu.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.MenuService;
import services.StaticOrderList;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for constructing all the Menu items for the JSP.
 *
 * @author Chris
 */
public class MenuServlet extends HttpServlet {

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
        
        List<Item> items = new ArrayList<Item>();
        MenuBroker mb = new MenuBroker();
        items = mb.getAllItem();
        List<Item> appetizers = new ArrayList<>();
        List<Item> chefs = new ArrayList<>();
        List<Item> breads = new ArrayList<>();
        List<Item> biryani = new ArrayList<>();
        List<Item> rice = new ArrayList<>();
        List<Item> kadahi = new ArrayList<>();
        List<Item> beefCurry = new ArrayList<>();
        List<Item> lambGoat = new ArrayList<>();
        List<Item> favourites = new ArrayList<>();
        List<Item> kebab = new ArrayList<>();
        List<Item> chicken = new ArrayList<>();
        List<Item> veg = new ArrayList<>();
        List<Item> indoChinese = new ArrayList<>();
        List<Item> sides = new ArrayList<>();
        List<Item> drinks = new ArrayList<>();
        List<Item> dessert = new ArrayList<>();
        /*
        items.add(new Item(100, "beef", 3.99, "It yummy beef!", "appetizer", "Test.png"));
        items.add(new Item(200, "Chicken", 1.00, "It ugly chicken!", "mains", "Test.png"));
        items.add(new Item(300, "Chicken1", 2.00, "It ugly chicken!", "mains", "Test.png"));
        items.add(new Item(400, "Chicken2", 3.00, "It ugly chicken!", "mains", "Test.png"));
        items.add(new Item(500, "Chicken3", 4.00, "It ugly chicken!", "mains", "Test.png"));
        items.add(new Item(600, "Chicken4", 5.00, "It ugly chicken!", "mains", "Test.png"));
        items.add(new Item(700, "Chicken5", 5.00, "It ugly chicken!", "mains", "Fakerz"));
        items.add(new Item(80, "Chicken6", 5.00, "It ugly chicken!", "mains", "Test.png"));
        items.add(new Item(900, "Chicken7", 5.00, "It ugly chicken!", "mains", "Test.png"));
        items.add(new Item(1100, "Chicken8", 5.00, "It ugly chicken!", "mains", "Test.png"));
        items.add(new Item(1200, "Chicken9", 5.00, "It ugly chicken!", "mains", "Test.png"));
        items.add(new Item(1300, "Chicken10", 5.00, "It ugly chicken!", "mains", "Test.png"));
        */
        
        for (Item i : items) {
            switch (i.getCategory()){
                case("appetizers") :
                    appetizers.add(i);
                    break;
                case("chefs") :
                    chefs.add(i);
                    break;
                case("breads") :
                    breads.add(i);
                    break;
                case("biryani") :
                    biryani.add(i);
                    break;
                case("rice") :
                    rice.add(i);
                    break;
                case("kadahi") :
                    kadahi.add(i);
                    break;
                case("beef curry") :
                    beefCurry.add(i);
                    break;
                case("lamb_goat") :
                    lambGoat.add(i);
                    break;
                case("favorites") :
                    favourites.add(i);
                    break;
                case("kebab") :
                    kebab.add(i);
                    break;
                case("chicken") :
                    chicken.add(i);
                    break;
                case("veg") :
                    veg.add(i);
                    break;
                case("indo_chinese") :
                    indoChinese.add(i);
                    break;
                case("sides") :
                    sides.add(i);
                    break;
                case("drinks") :
                    drinks.add(i);
                    break;
                case("desert") :
                    dessert.add(i);
                    break;
            }
        }
        
        request.setAttribute("appetizers", appetizers);
        request.setAttribute("chefs", chefs);
        request.setAttribute("breads", breads);
        request.setAttribute("biryani", biryani);
        request.setAttribute("rice", rice);
        request.setAttribute("kadahi", kadahi);
        request.setAttribute("beefCurry", beefCurry);
        request.setAttribute("lambGoat", lambGoat);
        request.setAttribute("favourites", favourites);
        request.setAttribute("kebab", kebab);
        request.setAttribute("chicken", chicken);
        request.setAttribute("veg", veg);
        request.setAttribute("indoChinese", indoChinese);
        request.setAttribute("sides", sides);
        request.setAttribute("drinks", drinks);
        request.setAttribute("dessert", dessert);
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/menu.jsp").forward(request, response);
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
        getServletContext().getRequestDispatcher("/WEB-INF/menu.jsp").forward(request, response);
    }

// </editor-fold>

}
