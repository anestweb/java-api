package br.projetointegrador.anestwebm.controller;

import br.projetointegrador.anestwebm.model.Paciente;
import br.projetointegrador.anestwebm.model.dao.GenericDao;
import br.projetointegrador.anestwebm.model.dao.HibernateUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet({"/pacientes"})
public class ListagemPacientesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Tenta recuperar a lista a partir do banco de dados.
        List<Paciente> pacientes = new ArrayList();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            pacientes = (new GenericDao(session, Paciente.class)).lista();
        } catch (Exception ex) {
            String msg = "Não foi possível carregar a listagem de pacientes.";
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, ex);
        }

        // Passa a lista para o JSP
        req.setAttribute("pacientes", pacientes);
        getServletContext().getRequestDispatcher("/pacientes/listagem.jsp")
                .forward(req, resp);
    }

}
