package br.projetointegrador.anestwebm.controller;

import br.projetointegrador.anestwebm.model.Profissional;
import br.projetointegrador.anestwebm.model.dao.Dao;
import br.projetointegrador.anestwebm.model.dao.GenericDao;
import br.projetointegrador.anestwebm.util.HibernateUtil;
import br.projetointegrador.anestwebm.util.ValidatorUtil;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import org.hibernate.Session;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
@WebServlet({"/profissionais/novo", "/profissionais/edicao"})
public class NovoProfissionalServlet extends GenericHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Profissional profissional = (Profissional) req.getAttribute("profissional");

        final String id = req.getParameter("id");
        boolean isEditing = (id != null);

        // Se o doPost não tiver instanciado um profissional, instancia um.
        if (profissional == null) {
            if (isEditing) {
                Session s = HibernateUtil.getSessionFactory().openSession();
                Dao<Profissional> dao = new GenericDao<>(s, Profissional.class);
                profissional = dao.buscaPorId(Integer.parseInt(id));
            } else {
                profissional = new Profissional();
            }
        }

        req.setAttribute("profissional", profissional);
        req.setAttribute("isEditing", isEditing);
        req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Corrige problemas com acentos ao enviar o formulário.
        req.setCharacterEncoding("UTF-8");

        final Session session = HibernateUtil.getSessionFactory().openSession();
        final String id = req.getParameter("id");
        Profissional profissional;
        if (id != null) {
            // Editando...
            final Dao<Profissional> dao = new GenericDao(session, Profissional.class);
            profissional = dao.buscaPorId(Integer.parseInt(id));
        } else {
            // Criando...
            profissional = new Profissional();
        }

        // Replica os dados informados no formulário
        profissional.setNome(req.getParameter("nome"));
        profissional.setCrm(req.getParameter("crm"));
        profissional.setEmail(req.getParameter("email"));
        profissional.setSenha(req.getParameter("senha"));

        // Disponibiliza as informações para o JSP
        req.setAttribute("profissional", profissional);

        try {
            final Dao<Profissional> profissionalDao = new GenericDao(session, Profissional.class);
            profissionalDao.salva(profissional);
            resp.sendRedirect(getServletContext().getContextPath() + "/profissionais");
        } catch (ConstraintViolationException ex) {
            final String msg = "Erro de validação: " + ex.getMessage() + ".";
            List msgs = ValidatorUtil.messagesFromConstraints(ex.getConstraintViolations());
            req.setAttribute("validations", msgs);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, ex);
            doGet(req, resp); // Reexibe o JSP
        } catch (Throwable ex) {
            final String msg = "Não foi possível cadastrar o profissional: " + ex.getMessage() + ".";
            req.setAttribute("error", msg);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, ex);
            doGet(req, resp); // Reexibe o JSP
        }
    }

}
