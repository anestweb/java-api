package br.projetointegrador.anestwebm.controller;

import br.projetointegrador.anestwebm.model.Profissional;
import br.projetointegrador.anestwebm.model.dao.GenericDao;
import br.projetointegrador.anestwebm.util.HibernateUtil;
import br.projetointegrador.anestwebm.util.StringUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
@WebServlet(urlPatterns = {"/entrar", "/sair"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = req.getContextPath();
        String requestURI = req.getRequestURI();

        if (requestURI.equals(contextPath + "/sair")) {
            req.getSession().removeAttribute("conectado");
            req.getSession().invalidate();
            resp.sendRedirect(contextPath + "/entrar");
        } else {
            if (req.getSession().getAttribute("conectado") == null) {
                // Nenhum usuário autenticado. Mostra página de login.
                req.getRequestDispatcher("/login/entrar.jsp").forward(req, resp);
            } else {
                // Usuário já autenticado.
                resp.sendRedirect(contextPath + "/pacientes");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String crm = (String) req.getParameter("crm");
        String senha = (String) req.getParameter("senha");

        req.setAttribute("crm", crm.replaceAll("[^0-9/A-Z]", ""));

        if (crm.isEmpty() || senha.isEmpty()) {
            req.setAttribute("error", "Informe ambos o seu CRM e a sua senha para entrar.");
        } else {
            Session session = HibernateUtil.getSessionFactory().openSession();
            GenericDao<Profissional> dao = new GenericDao<>(session, Profissional.class);
            List<Profissional> lista = dao.buscaPorAtributo("crm", crm, 1);
            if (lista.size() == 1) {
                Profissional profissional = lista.get(0);
                if (profissional.getSenha().equals(StringUtil.sha256(senha))) {
                    req.getSession().setAttribute("conectado", profissional);
                    resp.sendRedirect(req.getContextPath() + "/pacientes");
                    return;
                } else {
                    req.setAttribute("error", "A senha digitada não confere.");
                }
            } else {
                req.setAttribute("error", "Este CRM não está cadastrado.");
            }
        }

        doGet(req, resp);
    }

}
