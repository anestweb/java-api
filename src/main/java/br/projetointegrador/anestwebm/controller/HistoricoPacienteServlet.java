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
@WebServlet(urlPatterns = {"/pacientes/historico"})
public class HistoricoPacienteServlet extends GenericHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final String id = req.getParameter("id");

        Session s = HibernateUtil.getSessionFactory().openSession();

        Dao<Paciente> dao = new GenericDao<>(s, Paciente.class);
        Paciente paciente = dao.buscaPorId(Integer.parseInt(id));
        req.setAttribute("paciente", paciente);

        Dao<Avaliacao> daoAvaliacao = new GenericDao<>(s, Avaliacao.class);
        HashMap<String, Object> mapAv = new HashMap();
        mapAv.put("profissional", super.getProfissionalConectado());
        mapAv.put("paciente", paciente);
        List<Avaliacao> avaliacoes = daoAvaliacao.buscaPorAtributos(mapAv, 0);
        req.setAttribute("avaliacoes", avaliacoes);

        req.getRequestDispatcher("historico.jsp").forward(req, resp);
    }

}
