package br.projetointegrador.anestwebm.controller;

import br.projetointegrador.anestwebm.model.Paciente;
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
@WebServlet({"/pacientes/novo", "/pacientes/edicao"})
public class NovoPacienteServlet extends GenericHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Paciente paciente = (Paciente) req.getAttribute("paciente");

        final String id = req.getParameter("id");
        boolean isEditing = (id != null);

        // Se o doPost não tiver instanciado um paciente, instancia um.
        if (paciente == null) {
            if (isEditing) {
                Session s = HibernateUtil.getSessionFactory().openSession();
                Dao<Paciente> dao = new GenericDao<>(s, Paciente.class);
                paciente = dao.buscaPorId(Integer.parseInt(id));
            } else {
                paciente = new Paciente();
            }
        }

        req.setAttribute("paciente", paciente);
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
        Paciente paciente;
        if (id != null) {
            // Editando...
            final Dao<Paciente> dao = new GenericDao(session, Paciente.class);
            paciente = dao.buscaPorId(Integer.parseInt(id));
        } else {
            // Criando...
            paciente = new Paciente();
        }

        // Replica os dados informados no formulário
        paciente.setNomeCompleto(req.getParameter("nome_completo"));
        paciente.setDataNascimento(req.getParameter("data_nascimento"));
        paciente.setGenero(req.getParameter("sexo"));
        paciente.setEmail(req.getParameter("email"));
        paciente.setCpf(req.getParameter("cpf"));
        paciente.setRg(req.getParameter("rg"));

        // Disponibiliza as informações para o JSP
        req.setAttribute("paciente", paciente);

        try {
            final Dao<Paciente> pacienteDao = new GenericDao(session, Paciente.class);
            pacienteDao.salva(paciente);
            resp.sendRedirect(getServletContext().getContextPath() + "/pacientes");
        } catch (ConstraintViolationException ex) {
            final String msg = "Erro de validação: " + ex.getMessage() + ".";
            List msgs = ValidatorUtil.messagesFromConstraints(ex.getConstraintViolations());
            req.setAttribute("validations", msgs);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, ex);
            doGet(req, resp); // Reexibe o JSP
        } catch (Throwable ex) {
            final String msg = "Não foi possível cadastrar o paciente: " + ex.getMessage() + ".";
            req.setAttribute("error", msg);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, ex);
            doGet(req, resp); // Reexibe o JSP
        }
    }

}
