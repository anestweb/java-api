package br.projetointegrador.anestwebm.controller;

import br.projetointegrador.anestwebm.model.Patologia;
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
@WebServlet({"/patologias/novo", "/patologias/edicao"})
public class NovaPatologiaServlet extends GenericHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Patologia patologia = (Patologia) req.getAttribute("patologia");

        final String id = req.getParameter("id");
        boolean isEditing = (id != null);

        // Se o doPost não tiver instanciado um patologia, instancia um.
        if (patologia == null) {
            if (isEditing) {
                Session s = HibernateUtil.getSessionFactory().openSession();
                Dao<Patologia> dao = new GenericDao<>(s, Patologia.class);
                patologia = dao.buscaPorId(Integer.parseInt(id));
            } else {
                patologia = new Patologia();
            }
        }

        req.setAttribute("patologia", patologia);
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
        Patologia patologia;
        if (id != null) {
            // Editando...
            final Dao<Patologia> dao = new GenericDao(session, Patologia.class);
            patologia = dao.buscaPorId(Integer.parseInt(id));
        } else {
            // Criando...
            patologia = new Patologia();
        }

        // Replica os dados informados no formulário
        patologia.setDescricao(req.getParameter("descricao"));

        // Disponibiliza as informações para o JSP
        req.setAttribute("patologia", patologia);

        try {
            final Dao<Patologia> patologiaDao = new GenericDao(session, Patologia.class);
            patologiaDao.salva(patologia);
            resp.sendRedirect(getServletContext().getContextPath() + "/patologias");
        } catch (ConstraintViolationException ex) {
            final String msg = "Erro de validação: " + ex.getMessage() + ".";
            List msgs = ValidatorUtil.messagesFromConstraints(ex.getConstraintViolations());
            req.setAttribute("validations", msgs);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, ex);
            doGet(req, resp); // Reexibe o JSP
        } catch (Throwable ex) {
            final String msg = "Não foi possível cadastrar a patologia: " + ex.getMessage() + ".";
            req.setAttribute("error", msg);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, ex);
            doGet(req, resp); // Reexibe o JSP
        }
    }

}
