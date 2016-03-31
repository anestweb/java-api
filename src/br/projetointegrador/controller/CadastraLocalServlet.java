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

import br.projetointegrador.DAO.DaoLocalImpl;
import br.projetointegrador.model.Local;

/**
 * Servlet implementation class CadastraLocalServlet
 */
@WebServlet("/CadastraLocalServlet")
public class CadastraLocalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private DaoLocalImpl daoLocal;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		daoLocal = new DaoLocalImpl();
	}
	
    public CadastraLocalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomeCurto = request.getParameter("nomeCurto");
		String nomeLongo = request.getParameter("nomeLongo");
		String cnpj = request.getParameter("cnpj");
		
		String idStr = request.getParameter("id");
		
		Local local = new Local();
		local.setCnpj(cnpj);
		local.setNomeCurto(nomeCurto);
		local.setNomeLongo(nomeLongo);
		
		try {
			if(idStr == null){
				daoLocal.save(local);
				
				response.sendRedirect("CadastroLocal.jsp");
			}else{
				Integer id = Integer.valueOf(idStr);
				
				local.setId(id);
				daoLocal.update(local);
				
				HttpSession session = request.getSession();
				session.removeAttribute("Local");
				
				response.sendRedirect("ListagemLocal.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
