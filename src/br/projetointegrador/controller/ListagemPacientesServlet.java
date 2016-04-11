package br.projetointegrador.controller;

import br.projetointegrador.DAO.DaoPacientesImpl;
import br.projetointegrador.model.Pacientes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
@WebServlet("/pacientes")
public class ListagemPacientesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Pacientes> pacientes = new ArrayList<>();

        try {
            pacientes = (new DaoPacientesImpl()).list();
        } catch (Exception ex) {
            Logger.getLogger(ListagemPacientesServlet.class.getName())
                  .log(Level.SEVERE, "Falha ao recuperar lista de pacientes.", ex);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/pacientes/listagem.jsp");
        req.setAttribute("pacientes", pacientes);
        dispatcher.forward(req, resp);
    }

}
