package br.projetointegrador.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.projetointegrador.DAO.DaoPacientesImpl;
import br.projetointegrador.model.Pacientes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class CadastraPacientesServlet
 */
@WebServlet("/pacientes/novo")
public class CadastraPacientesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    private DaoPacientesImpl daoPacientes;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        daoPacientes = new DaoPacientesImpl();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pacientes/cadastro.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome_completo");
        String dataNasc = request.getParameter("data_nascimento");
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String sexo = request.getParameter("sexo");
        Integer prof = 0;
        Integer local = 0;
//        Integer prof = Integer.parseInt(request.getParameter("profissional"));
//        Integer local = Integer.parseInt(request.getParameter("local"));;

        String idStr = request.getParameter("id");

        Pacientes pa = new Pacientes();
        pa.setNomeCompleto(nome);
        pa.SetDataNascimento(dataNasc);
        pa.setCpf(cpf);
        pa.setRg(rg);
        pa.SetSexo(sexo);
        pa.setProfissional(prof);
        pa.setLocal(local);

        try {
            if (idStr == null) {
                daoPacientes.save(pa);

                response.sendRedirect("/anestweb/pacientes/novo");
            } else {
                Integer id = Integer.valueOf(idStr);

                pa.setId(id);
                daoPacientes.update(pa);

                HttpSession session = request.getSession();
                session.removeAttribute("Pacientes");

                response.sendRedirect("/anestweb/pacientes");
            }
        } catch (SQLException e) {
            Logger.getLogger(CadastraPacientesServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
