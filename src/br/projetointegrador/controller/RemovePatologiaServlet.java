package br.projetointegrador.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.projetointegrador.DAO.DaoPatologias;
import br.projetointegrador.DAO.DaoPatologiasImpl;
import br.projetointegrador.model.Patologias;

/**
 * Servlet implementation class RemovePatologiaServlet
 */
@WebServlet("/RemovePatologiaServlet")
public class RemovePatologiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private DaoPatologias daoPatologias;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		daoPatologias = new DaoPatologiasImpl();
	}
	
    public RemovePatologiaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.valueOf(request.getParameter("id"));
		
		Patologias p = new Patologias();
		p.setId(id);
		
		try {
			daoPatologias.remove(p);
			
			response.sendRedirect("ListagemPatologias.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
