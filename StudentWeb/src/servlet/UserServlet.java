package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.RandomNumber;
import util.ValidateCode;

import Dao.UserDao;
import Entity.User;

public class UserServlet extends HttpServlet {
	UserDao userDao = new UserDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String type = request.getParameter("type");

			if (type == null || type.equals("showLogin")) {
				showLogin(request, response);
			} else if (type.equals("doLogin")) {
				doLogin(request, response);
			} else if (type.equals("randomImage")) {
				randomImage(request, response);
			} else if (type.equals("showRegister")) {
				showRegister(request, response);
			} else if (type.equals("doRegister")) {
				doRegister(request, response);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void doRegister(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String rand = request.getParameter("random");
			if (rand.equals(request.getSession().getAttribute("rand"))) {
				String name = request.getParameter("name");
				String pwd = request.getParameter("pwd");
				// MD5加密
				
				User user = new User();
				Date date = new Date();
				java.sql.Timestamp time = new java.sql.Timestamp(date.getTime());
				user.setTime(time);
				
				
				user.setUsername(name);
				User u = userDao.add(user);			
			    u=userDao.searchByName(u.getUsername());	    
			    pwd = CreateMD5.getMd5(pwd,u.getTime().toString());
			    u.setPwd(pwd);
				
			    u=userDao.update(u);
			    
			  //  System.out.println(u.getId()+u.getUsername());
			    request.getSession().setAttribute("user", user);
			    response.sendRedirect("index.jsp");

			} else {
				request.setAttribute("mes", "验证码错误！");
				request.getRequestDispatcher("user?type=showRegister").forward(
						request, response);

			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showRegister(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			request.getRequestDispatcher("WEB-INF/Users/register.jsp").forward(
					request, response);

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void randomImage(HttpServletRequest request,
			HttpServletResponse response) {

		RandomNumber rn = new RandomNumber();
		try {
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ValidateCode vc = rn.generateImage();
			ServletOutputStream outStream = response.getOutputStream();
			// 输出图象到页面
			ImageIO.write(vc.getImage(), "JPEG", outStream);
			outStream.close();
			request.getSession().setAttribute("rand", vc.getRand());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/Users/login.jsp").forward(
				request, response);

	}

	public void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// method="get"时用 username=new
		// String(username.getBytes("ISO-8859-1"),"utf-8");
		
		String rand = request.getParameter("random");
		String username = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		
		if (rand.equals(request.getSession().getAttribute("rand"))) {
		
		User searchUser = new User();
		searchUser.setUsername(username);
		User u=userDao.searchByName(username);
		searchUser.setPwd(CreateMD5.getMd5(pwd+u.getTime()));
		
		UserDao uDao = new UserDao();
		User user = uDao.searchByUserNameAndPwd(searchUser);
	
		if (user != null) {
           request.getSession().setAttribute("user", user);
			// out.print("成功");
			response.sendRedirect("index.jsp");
			// 重定向 后不能再转发

			// request.getRequestDispatcher("WEB-INF/index.jsp").forward(request,
			// response);
			// 转发
		} else {
			// out.print("失败");
			response.sendRedirect("WEB-INF/Users/login.jsp");
			// request.getRequestDispatcher("WEB-INF/login.jsp").forward(request,
			// response);
		}
		
		}else{
			
			request.setAttribute("mes", "验证码错误！");
			request.getRequestDispatcher("user?type=showLogin").forward(
					request, response);
		}
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}
}
