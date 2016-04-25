package br.projetointegrador.anestwebm.controller;

import br.projetointegrador.anestwebm.model.Medicamento;
import br.projetointegrador.anestwebm.model.dao.GenericDao;
import br.projetointegrador.anestwebm.util.HibernateUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
@WebServlet({"/medicamentos", "/medicamentos/exclusao"})
public class ListagemMedicamentosServlet extends GenericHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Tenta recuperar a lista a partir do banco de dados.
        List<Medicamento> medicamentos = new ArrayList();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            medicamentos = (new GenericDao(session, Medicamento.class)).lista();
        } catch (Exception ex) {
            String msg = "Não foi possível carregar a listagem de medicamentos.";
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, msg, ex);
        }

        // Passa a lista para o JSP
        req.setAttribute("medicamentos", medicamentos);
        getServletContext().getRequestDispatcher("/medicamentos/listagem.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");

        final String id = req.getParameter("id");
        if (id != null) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            GenericDao<Medicamento> dao = new GenericDao<>(session, Medicamento.class);
            Medicamento p = dao.buscaPorId(Integer.parseInt(id));
            if (p != null) {
                dao.remove(p);
                resp.getWriter().println("OK");
            } else {
                resp.getWriter().println("O medicamento não foi localizado no banco de dados.");
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            resp.getWriter().println("O parâmetro id não foi especificado.");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
