package br.projetointegrador.controller;

import br.projetointegrador.model.Paciente;
import br.projetointegrador.model.dao.Dao;
import br.projetointegrador.model.dao.GenericDao;
import br.projetointegrador.model.dao.HibernateUtil;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        Paciente paciente = new Paciente();
        paciente.setNomeCompleto(req.getParameter("nome_completo"));
        paciente.setCpf(req.getParameter("cpf").replaceAll("[^0-9]", ""));
        paciente.setRg(req.getParameter("rg").replaceAll("[^0-9]", ""));

        try {
            // Gênero
            paciente.setGenero(req.getParameter("sexo").charAt(0));

            // Data de Nascimento
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            paciente.setDataNascimento(dateFormat.parse(req.getParameter("data_nascimento")));
        } catch (EnumConstantNotPresentException ex) {
            req.setAttribute("error", "Opção de gênero inválida.");
        } catch (ParseException ex) {
            req.setAttribute("error", "Erro na conversão da data de nascimento: " + ex.getMessage() + ".");
        } finally {
            if (req.getAttribute("error") == null) {
                try {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Dao<Paciente> pacienteDao = new GenericDao(session, Paciente.class);
                    pacienteDao.salva(paciente);
                    resp.sendRedirect(getServletContext().getContextPath() + "/pacientes");
                } catch (HibernateException | IOException ex) {
                    final String msg = "Não foi possível cadastrar o paciente: " + ex.getMessage() + ".";
                    req.setAttribute("error", msg);
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, ex);
                } finally {
                    if (!resp.isCommitted()) {
                        getServletContext().getRequestDispatcher("/pacientes/cadastro.jsp")
                            .forward(req, resp);
                    }
                }
            }
        }
    }

}
