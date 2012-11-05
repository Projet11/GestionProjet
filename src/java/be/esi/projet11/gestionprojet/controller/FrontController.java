package be.esi.projet11.gestionprojet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author g35001
 */
public class FrontController extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
	{
        response.setContentType("text/html;charset=UTF-8");
        String page = null;
        request.setAttribute("error", null);
		
        if (request.getParameter("cible") == null)
		{
			page = "WEB-INF/index.jsp";
        }
		else if (request.getParameter("cible").equals("inscription"))
		{
			// Pas de page nécessaire: doInscription() écrit directement la réponse
            page = null;
            doInscription(request, response);
        }
		else
		{
            request.setAttribute("error", "Action non supportée par l'application J2EE.");
            page = "WEB-INF/inscription.jsp";
        }
		
		if (page != null)
			request.getRequestDispatcher(page).forward(request, response);
    }

    private void doInscription(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException ex) {
			Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
        try {
            String nom, prenom, login, password, email;
            nom = request.getParameter("txtNom");
            prenom = request.getParameter("txtPrenom");
            login = request.getParameter("txtLogin");
            password = request.getParameter("txtPass");
            email = request.getParameter("txtMail");
            //FacadeClient.createUser(login, password, email, nom, prenom);
			
			// Tout s'est bien passé, on envoie "1" au client
			out.println("%%OK%%");
        } catch (Exception e) {
			// Une erreur est survenue: en envoie le message d'erreur au client
			out.println(e.getMessage());
        }
		
		out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "FrontController";
    }
}
