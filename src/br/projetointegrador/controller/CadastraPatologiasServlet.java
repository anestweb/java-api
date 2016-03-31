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

import br.projetointegrador.DAO.DaoPatologiasImpl;
import br.projetointegrador.model.Patologias;

/**
 * Servlet implementation class CadastraPatologiasServlet
 */
@WebServlet("/CadastraPatologiasServlet")
public class CadastraPatologiasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private DaoPatologiasImpl daoPatologias;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		daoPatologias = new DaoPatologiasImpl();
	}
	
    public CadastraPatologiasServlet() {
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
		String descricao = request.getParameter("descricao");
		
		String idStr = request.getParameter("id");
		
		Patologias patologias = new Patologias();
		patologias.setDescricao(descricao);
		
		try {
			if(idStr == null){
				daoPatologias.save(patologias);
				
				response.sendRedirect("CadastroPatologias.jsp");
			}else{
				Integer id = Integer.valueOf(idStr);
				
				patologias.setId(id);
				daoPatologias.update(patologias);
				
				HttpSession session = request.getSession();
				session.removeAttribute("Patologias");
				
				response.sendRedirect("ListagemPatologias.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
