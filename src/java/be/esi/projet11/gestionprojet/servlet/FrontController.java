/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.servlet;

import be.esi.projet11.gestionprojet.ejb.TacheEJBLocal;
import be.esi.projet11.gestionprojet.facade.BusinessFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String page = "WEB-INF/pages/Timer.jsp";
            BusinessFacade facade=new BusinessFacade();
            if (request.getParameter("action") != null) {
                if (request.getParameter("action").equals("attribuerMembres")) {
//                    Tache tache = new Tache();
//                    request.setAttribute("tacheAttribuerMembres", tache);
//                    page = "WEB-INF/Pages/FormAttribMembre.jsp";
                }
                if (request.getParameter("action").equals("startTimer")) {
                    TacheEJBLocal tacheEJB= facade.getTacheEJB();
                    tacheEJB.startTimer(Long.parseLong(request.getParameter("numTache")));
                    request.setAttribute("numTache", request.getParameter("numTache"));
                    request.setAttribute("timerLance", 1);
                    page="WEB-INF/pages/Timer.jsp";
                }
            }
            request.getRequestDispatcher(page).forward(request, response);
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
