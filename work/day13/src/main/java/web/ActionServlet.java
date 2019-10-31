package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;


public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void service(
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, 
					IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//���������Դ·��
		String uri = request.getRequestURI();
		//��ȡ������Դ·����һ���֣�����Ƚ�
		String path = 
			uri.substring(uri.lastIndexOf("/"),
					uri.lastIndexOf("."));
		System.out.println("path:" + path);
		
		//��������·��������Ӧ����
		if("/login".equals(path)){
			processLogin(request,response);
		}else if("/list".equals(path)){
			processList(request,response);
		}else if("/del".equals(path)){
			processDel(request,response);
		}else if("/add".equals(path)){
			processAdd(request,response);
		}else if("/toLogin".equals(path)){
			request.getRequestDispatcher(
					"/WEB-INF/login.jsp")
			.forward(request, response);
		}else if("/toAdd".equals(path)){
			request.getRequestDispatcher(
					"/WEB-INF/addUser.jsp")
			.forward(request, response);
		}
		
	}


	private void processAdd(
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if(obj == null){
			//û�е�¼���ض��򵽵�¼ҳ��
			response.sendRedirect("toLogin.do");
			return;
		}
		
		String username = 
				request.getParameter("username");
		String pwd = 
				request.getParameter("pwd");
		String email = 
				request.getParameter("email");
		
		//���û���Ϣ���뵽���ݿ�
		UserDAO dao = new UserDAO();
		try {
			User user = new User();
			user.setUsername(username);
			user.setPwd(pwd);
			user.setEmail(email);
			dao.save(user);
			//�ض����û��б�
			response.sendRedirect("list.do");
			
		} catch (SQLException e) {
			//����־(�����ֳ�)
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}


	private void processDel(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if(obj == null){
			//û�е�¼���ض��򵽵�¼ҳ��
			response.sendRedirect("toLogin.do");
			return;
		}
		
		//��ȡҪɾ�����û���id
		int id = 
				Integer.parseInt(
						request.getParameter("id"));
		//ɾ��ָ��id���û�
		UserDAO dao = new UserDAO();
		try {
			dao.delete(id);
			//�ض����û��б�
			response.sendRedirect("list.do");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}


	private void processList(
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		/*
		 * �鿴�û���û�е�¼��ֻ�е�¼�����û����ܷ���
		 * �û��б�
		 */
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if(obj == null){
			//û�е�¼���ض��򵽵�¼ҳ��
			response.sendRedirect("toLogin.do");
			return;
		}
		UserDAO dao = new UserDAO();
		try {
			List<User> users = dao.findAll();
			//�����ݵ�request������
			request.setAttribute("users", users);
			//���ת����
			RequestDispatcher rd= 
					request.getRequestDispatcher(
							"/WEB-INF/listUser.jsp");	
			//ת��
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}


	private void processLogin(
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws IOException, ServletException {
		
		//��ȡ�û���������
		String username = 
				request.getParameter("username");
		String pwd = 
				request.getParameter("pwd");
		//�鿴���ݿ⣬����û��ƥ�������ļ�¼
		UserDAO dao = new UserDAO();
		try {
			User user = dao.find(username);
			if(user != null 
					&& user.getPwd().equals(pwd)){
				//��ƥ�������ļ�¼����¼�ɹ�
				HttpSession session = 
						request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("list.do");
			}else{
				//��¼ʧ��
				request.setAttribute("login_failed",
						"�û������������");
				request.getRequestDispatcher("/WEB-INF/login.jsp")
				.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//���쳣�׸�����������
			throw new ServletException(e);
		}
		
	}

}
