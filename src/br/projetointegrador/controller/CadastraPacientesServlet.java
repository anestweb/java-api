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

/**
 * Servlet implementation class CadastraPacientesServlet
 */
@WebServlet("/CadastraPacientesServlet")
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
	
    public CadastraPacientesServlet() {
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
		String nome = request.getParameter("nome");
		String dataNasc = request.getParameter("dataNasc");
		String cpf = request.getParameter("cpf");
		String rg = request.getParameter("rg");
		String sexo = request.getParameter("sexo");
		Integer prof = Integer.parseInt(request.getParameter("profissional"));
		Integer local = Integer.parseInt(request.getParameter("local"));
		
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
			if(idStr == null){
				daoPacientes.save(pa);
			
				response.sendRedirect("CadastroPacientes.jsp");
			}else{
				Integer id = Integer.valueOf(idStr);
				
				pa.setId(id);
				daoPacientes.update(pa);
				
				HttpSession session = request.getSession();
				session.removeAttribute("Pacientes");
				
				response.sendRedirect("ListagemPacientes.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
