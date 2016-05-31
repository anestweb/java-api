package br.projetointegrador.anestwebm.controller;

import br.projetointegrador.anestwebm.model.Avaliacao;
import br.projetointegrador.anestwebm.model.Paciente;
import br.projetointegrador.anestwebm.model.dao.Dao;
import br.projetointegrador.anestwebm.model.dao.GenericDao;
import br.projetointegrador.anestwebm.util.HibernateUtil;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
@WebServlet(urlPatterns = {"/pacientes/avaliacao/nova"})
public class NovaAvaliacaoServlet extends GenericHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final String pid = req.getParameter("pid");

        Session s = HibernateUtil.getSessionFactory().openSession();
        Dao<Paciente> dao = new GenericDao<>(s, Paciente.class);
        Paciente paciente = dao.buscaPorId(Integer.parseInt(pid));

        req.setAttribute("paciente", paciente);

        req.getRequestDispatcher("nova.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Verifica se pelo menos um campo foi preenchido
        Enumeration<String> params = req.getParameterNames();
        boolean preenchido = false;
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            if (!param.equals("pid")) {
                String value = req.getParameter(param);
                if (null != value && !value.trim().isEmpty()) {
                    // Pelo menos um dos campos foi preenchido!
                    preenchido = true;
                    break;
                }
            }
        }

        if (preenchido) {
            String pid = req.getParameter("pid");

            Session s = HibernateUtil.getSessionFactory().openSession();
            Dao<Paciente> dao = new GenericDao<>(s, Paciente.class);
            Paciente paciente = dao.buscaPorId(Integer.parseInt(pid));

            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setDataAvaliacao(new Date());
            avaliacao.setProfissional(super.getProfissionalConectado());
            avaliacao.setPaciente(paciente);

            avaliacao.setPeso(req.getParameter("peso"));
            avaliacao.setAltura(req.getParameter("altura"));
            avaliacao.setPa(req.getParameter("pa"));
            avaliacao.setPalidez(req.getParameter("palidez"));
            avaliacao.setDentadura(req.getParameter("dentadura"));
            avaliacao.setDentes(req.getParameter("dentes"));
            avaliacao.setBoca(req.getParameter("boca"));
            avaliacao.setPescoco(req.getParameter("pescoco"));
            avaliacao.setPescocoFlexao(req.getParameter("pescoco_flexao"));
            avaliacao.setMallampati(req.getParameter("mallampati"));
            avaliacao.setAnotacaoFisico(req.getParameter("anotacao_fisico"));
            avaliacao.setAnotacoes(req.getParameter("anotacoes"));
            avaliacao.setCirurgia(req.getParameter("cirurgia"));
            avaliacao.setProcedimento(req.getParameter("procedimento"));

            Dao<Avaliacao> daoAvaliacao = new GenericDao<>(s, Avaliacao.class);
            daoAvaliacao.salva(avaliacao);

            resp.sendRedirect(req.getContextPath() + "/pacientes/historico?id=" + pid);
        } else {
            req.setAttribute("error", "Nenhum campo foi preenchido.");
            doGet(req, resp);
        }
    }

}
