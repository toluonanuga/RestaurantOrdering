package servlets;

/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for handling Actors trying to login.
 */
import brokers.AdminBroker;
import brokers.CustomerBroker;
import com.main.actor.Actor;
import com.main.delivery.Delivery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.ActorService;
import services.AdminService;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for handling people trying to login.
 *
 * @author Nguyen Khanh Duy Phan
 */
public class LoginServlet extends HttpServlet {


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

        HttpSession session = request.getSession();
        session.invalidate();

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
        
        HttpSession session = request.getSession();

        String v_email = request.getParameter("email");
        String v_pass = request.getParameter("password");
        
        ActorService as = new ActorService();
        AdminBroker ab = new AdminBroker();
        CustomerBroker cb = new CustomerBroker();
        
        boolean checkResult =  as.checkCredential(v_email, v_pass);
        // this message of "Successful login" in  if statement below will be deleted when the front-end team create homepage.jsp
        if(checkResult == true)
        {
            Actor currentActor = ab.getActorbyEmail(v_email);
            session.setAttribute("ActorId", currentActor.getId());

            session.setAttribute("loggedActor", currentActor);
            session.setAttribute("loggedIn", "true");
            session.setAttribute("actorEmail", v_email);
            
            if (currentActor.getRole().equalsIgnoreCase("c"))
            {
                Delivery d = new Delivery();
                d = cb.getDeliveryByID(currentActor.getId());
                session.setAttribute("deliveryInfo", d);
                response.sendRedirect("menu");
                return;
            }else if (currentActor.getRole().equalsIgnoreCase("m"))
            {
                response.sendRedirect("manager");
                return;
            }else if (currentActor.getRole().equalsIgnoreCase("e"))
            {
                response.sendRedirect("Employee");
                return;
            }else if (currentActor.getRole().equalsIgnoreCase("a"))
            {
                response.sendRedirect("admin");
                return;
            }
            
        }
        else
        {
            request.setAttribute("login_message", "Invalid login" );
        }
                
          
            
        //Front-end team should change this code to redirect() to redirect to the account page 
       getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    
    }
// </editor-fold>

}
