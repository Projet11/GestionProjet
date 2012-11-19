package be.esi.projet11.gestionprojet.controller;

import be.esi.projet11.gestionprojet.ejb.FacadeMembreEJB;
import be.esi.projet11.gestionprojet.ejb.TacheEJBLocal;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.exception.BusinessException;
import be.esi.projet11.gestionprojet.facade.BusinessFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author g35001
 */
public class FrontController extends HttpServlet {

    @EJB
    private FacadeMembreEJB facadeClientEJB;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String page = null;
        request.setAttribute("error", null);
        BusinessFacade facade = new BusinessFacade();

        if (request.getParameter("cible") == null) {
            page = "WEB-INF/index.jsp";
        } else if (request.getParameter("cible").equals("inscription")) {
            // Pas de page nécessaire: doInscription() écrit directement la réponse
            page = null;
            doInscription(request, response);
        } else if (request.getParameter("cible").equals("identification")) {
            page = doAuthentification(request, response);
        } else if (request.getParameter("cible").equals("choixMembresTache")) {
            TacheEJBLocal tacheEJB = facade.getTacheEJB();
            System.out.println("param: " + request.getParameter("numTache"));
            Tache tache = tacheEJB.getTache(Long.parseLong(request.getParameter("numTache")));
            System.out.println("tache:" + tache);
            request.setAttribute("tacheAttribuerMembres", tache);
            page = "WEB-INF/pages/FormAttribMembre.jsp";
        } else if (request.getParameter("cible").equals("membresAttribuesTache")) {
            TacheEJBLocal tacheEJB = facade.getTacheEJB();
            //page = tacheEJB.inscrireMembresATache(request);
        } else {
            request.setAttribute("error", "Action non supportée par l'application J2EE.");
            page = "WEB-INF/inscription.jsp";
        }

        if (page != null) {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    private String doAuthentification(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println(request.getContextPath());
            String login, password;
            login = request.getParameter("txtLogin");
            password = request.getParameter("txtPass");
            facadeClientEJB.authenticateUser(login, password);
        } catch (BusinessException ex) {
            request.setAttribute("error", ex.getMessage());
        } finally {
            return "WEB-INF/identification.jsp";
        }
    }

    private void doInscription(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            request.setAttribute("error", "doInscription(...) " + e.getMessage());
        }

        try {
            String nom, prenom, login, password, email;
            nom = request.getParameter("txtNom");
            prenom = request.getParameter("txtPrenom");
            login = request.getParameter("txtLogin");
            password = request.getParameter("txtPass");
            email = request.getParameter("txtMail");
            facadeClientEJB.createUser(login, password, email, nom, prenom);
            out.println("%%OK%%");
        } catch (Exception e) {
            System.out.println("FrontException");
            out.println(replaceLineFeed(e.getMessage() + "<br/>"));
        }
    }

    private String replaceLineFeed(String message) {
        return message.replaceAll("\n", "<br/>");
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
