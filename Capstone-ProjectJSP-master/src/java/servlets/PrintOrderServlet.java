/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for handling Report Generation for Managers.
 */
package servlets;

import brokers.AdminBroker;
import brokers.ManagerBroker;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for handling Report Generation for Managers.
 *
 * @author cuong
 */
@WebServlet(name = "PrintOrderServlet", urlPatterns = {"/PrintOrderServlet"})
public class PrintOrderServlet extends HttpServlet {



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
            String action = request.getParameter("action");
            String reportFileName = request.getParameter("filename");
            boolean checkPrint = false; 
            if(action.equals("printAll"))
            {
                ManagerBroker mb = new ManagerBroker();
                reportFileName += ".csv";
                checkPrint = mb.printReportOfAll(reportFileName);
                Gson gson = new Gson();
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(checkPrint));
            }
            else if(action.equals("printMonth"))
            {
                ManagerBroker mb = new ManagerBroker();
                reportFileName += ".csv";
                checkPrint = mb.printReportOfLastMonth(reportFileName);
                Gson gson = new Gson();
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(checkPrint));
            }
            else if(action.equals("printWeek"))
            {
                ManagerBroker mb = new ManagerBroker();
                reportFileName += ".csv";
                checkPrint = mb.printReportOflastWeek(reportFileName);
                Gson gson = new Gson();
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(checkPrint));
            }
            

        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
