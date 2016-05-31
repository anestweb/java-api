package br.projetointegrador.anestwebm.controller;

import br.projetointegrador.anestwebm.model.Avaliacao;
import br.projetointegrador.anestwebm.model.Paciente;
import br.projetointegrador.anestwebm.model.dao.Dao;
import br.projetointegrador.anestwebm.model.dao.GenericDao;
import br.projetointegrador.anestwebm.util.HibernateUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
@WebServlet(urlPatterns = {"/pacientes/avaliacao/detalhes", "/pacientes/avaliacao/exclusao"})
public class AvaliacaoServlet extends GenericHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final String id = req.getParameter("id");

        Session s = HibernateUtil.getSessionFactory().openSession();

        Dao<Avaliacao> dao = new GenericDao<>(s, Avaliacao.class);
        Avaliacao avaliacao = dao.buscaPorId(Integer.parseInt(id));
        req.setAttribute("paciente", avaliacao.getPaciente());
        req.setAttribute("avaliacao", avaliacao);

        req.getRequestDispatcher("detalhes.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");

        final String id = req.getParameter("id");
        if (id != null) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            GenericDao<Avaliacao> dao = new GenericDao<>(session, Avaliacao.class);
            Avaliacao p = dao.buscaPorId(Integer.parseInt(id));
            if (p != null) {
                dao.remove(p);
                resp.getWriter().println("OK");
            } else {
                resp.getWriter().println("A avaliação não foi localizada no banco de dados.");
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            resp.getWriter().println("O parâmetro id não foi especificado.");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
