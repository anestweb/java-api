package br.projetointegrador.anestwebm.controller;

import br.projetointegrador.anestwebm.model.Medicamento;
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
@WebServlet({"/medicamentos/novo", "/medicamentos/edicao"})
public class NovoMedicamentoServlet extends GenericHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Medicamento medicamento = (Medicamento) req.getAttribute("medicamento");

        final String id = req.getParameter("id");
        boolean isEditing = (id != null);

        // Se o doPost não tiver instanciado um medicamento, instancia um.
        if (medicamento == null) {
            if (isEditing) {
                Session s = HibernateUtil.getSessionFactory().openSession();
                Dao<Medicamento> dao = new GenericDao<>(s, Medicamento.class);
                medicamento = dao.buscaPorId(Integer.parseInt(id));
            } else {
                medicamento = new Medicamento();
            }
        }

        req.setAttribute("medicamento", medicamento);
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
        Medicamento medicamento;
        if (id != null) {
            // Editando...
            final Dao<Medicamento> dao = new GenericDao(session, Medicamento.class);
            medicamento = dao.buscaPorId(Integer.parseInt(id));
        } else {
            // Criando...
            medicamento = new Medicamento();
        }

        // Replica os dados informados no formulário
        medicamento.setDescricao(req.getParameter("descricao"));

        // Disponibiliza as informações para o JSP
        req.setAttribute("medicamento", medicamento);

        try {
            final Dao<Medicamento> medicamentoDao = new GenericDao(session, Medicamento.class);
            medicamentoDao.salva(medicamento);
            resp.sendRedirect(getServletContext().getContextPath() + "/medicamentos");
        } catch (ConstraintViolationException ex) {
            final String msg = "Erro de validação: " + ex.getMessage() + ".";
            List msgs = ValidatorUtil.messagesFromConstraints(ex.getConstraintViolations());
            req.setAttribute("validations", msgs);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, ex);
            doGet(req, resp); // Reexibe o JSP
        } catch (Throwable ex) {
            final String msg = "Não foi possível cadastrar o medicamento: " + ex.getMessage() + ".";
            req.setAttribute("error", msg);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, ex);
            doGet(req, resp); // Reexibe o JSP
        }
    }

}
