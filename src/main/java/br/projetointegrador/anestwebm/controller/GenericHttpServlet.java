package br.projetointegrador.anestwebm.controller;

import br.projetointegrador.anestwebm.model.Profissional;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
public abstract class GenericHttpServlet extends HttpServlet {

    private Profissional profissional_conectado = null;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Cache-Control", "no-store");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);

        profissional_conectado = (Profissional) req.getSession().getAttribute("conectado");
        if (profissional_conectado == null) {
            String contextPath = req.getContextPath();
            String currentURI = req.getRequestURI();
            String loginURI = contextPath + "/entrar";
            if (currentURI.equals(loginURI)) {
                super.service(req, resp);
            } else {
                resp.sendRedirect(loginURI);
            }
        } else {
            req.setAttribute("conectado", profissional_conectado);
            super.service(req, resp);
        }
    }

    protected Profissional getProfissionalConectado() {
        return profissional_conectado;
    }
}
