package br.projetointegrador.anestwebm.controller;

import br.projetointegrador.anestwebm.model.Paciente;
import br.projetointegrador.anestwebm.model.dao.Dao;
import br.projetointegrador.anestwebm.model.dao.GenericDao;
import br.projetointegrador.anestwebm.model.dao.HibernateUtil;
import br.projetointegrador.anestwebm.util.ValidatorUtils;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
@WebServlet({"/pacientes/novo"})
public class NovoPacienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final Paciente paciente = new Paciente();
        paciente.setNomeCompleto(req.getParameter("nome_completo"));
        paciente.setDataNascimento(req.getParameter("data_nascimento"));
        paciente.setGenero(req.getParameter("sexo"));
        paciente.setEmail(req.getParameter("email"));
        paciente.setCpf(req.getParameter("cpf").replaceAll("[^0-9]", ""));
        paciente.setRg(req.getParameter("rg").replaceAll("[^0-9]", ""));

        try {
            final Session session = HibernateUtil.getSessionFactory().openSession();
            final Dao<Paciente> pacienteDao = new GenericDao(session, Paciente.class);
            pacienteDao.salva(paciente);
            resp.sendRedirect(getServletContext().getContextPath() + "/pacientes");
        } catch (ConstraintViolationException ex) {
            List<String> messages = ValidatorUtils.messagesFromConstraints(ex.getConstraintViolations());
            req.setAttribute("validations", messages);
            doGet(req, resp); // Reexibe o JSP
        } catch (HibernateException | IOException ex) {
            final String msg = "Não foi possível cadastrar o paciente: " + ex.getMessage() + ".";
            req.setAttribute("error", msg);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, ex);
            doGet(req, resp); // Reexibe o JSP
        }
    }

}
