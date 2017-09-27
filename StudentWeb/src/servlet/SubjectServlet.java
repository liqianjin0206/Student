package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import util.Pagination;
import Dao.SubjectDao;
import Entity.Subject;

public class SubjectServlet extends HttpServlet {

	SubjectDao subDao = new SubjectDao();


	public void doGet(HttpServletRequest request, HttpServletResponse response) {
	
		String type = request.getParameter("type");
		if (type == null) {
			search(request, response);
		} else if (type.equals("showAdd")) {
			showAdd(request, response);
		} else if (type.equals("add")) {
			add(request, response);
		} else if (type.equals("delete")) {
			delete(request, response);
		} else if (type.equals("showModify")) {
			showModify(request, response);
		} else if (type.equals("modify")) {
			modify(request, response);
		} else if (type.equals("search")) {
			search(request, response);
		} else if (type.equals("searchSubByBj")) {
			searchSubByBj(request, response);
		}
		
	}

	private void searchSubByBj(HttpServletRequest request,
			HttpServletResponse response) {
		try {
		int bjId=Integer.parseInt(request.getParameter("bjId"));
	  
		List<Subject> subList=subDao.searchSubByBj(bjId);
	    JSONArray json=JSONArray.fromObject(subList);
	
		PrintWriter out=response.getWriter();
		out.print(json.toString());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

//	public void SubjectList(HttpServletRequest request,
//			HttpServletResponse response) {
//
//		try {
//			List<Subject> subList = new ArrayList<Subject>();
//			subList = subDao.searchAll();
//			int max = subDao.searchCount();
//			int ye = 1;
//			if (request.getParameter("ye") != null) {
//				ye = Integer.parseInt(request.getParameter("ye"));
//			}
//			int yeNum = 5;
//			int yeMa = 5;
//			Pagination pagination = new Pagination(max, ye, yeNum, yeMa);
//			ye = pagination.getYe();
//			int begin = yeNum * (ye - 1);
//
//			List<Subject> list = subDao.searchByBegin(begin, yeNum);
//			request.setAttribute("list", list);
//			request.setAttribute("subList", subList);
//			request.setAttribute("pagination", pagination);
//
//			request.getRequestDispatcher("WEB-INF/subject.jsp").forward(
//					request, response);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	private void showAdd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/subject/addSubject.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void add(HttpServletRequest request, HttpServletResponse response) {

		// List<Subject> subList = new ArrayList<Subject>();
		// request.setAttribute("subList", subList);
		// subList = subDao.searchAll();

		String name = request.getParameter("name");
		Subject sub = new Subject();
		sub.setName(name);


		// Susubect sub=new Subject();
		// sub.setName(subName);
		// sub.setBj(sub);
		subDao.add(sub);
		try {
			response.sendRedirect("sub");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameter("id").split(",");
		boolean flag = false;
		for (int i = 0; i < ids.length; i++) {
			Subject sub = new Subject();
			sub.setId(Integer.parseInt(ids[i]));
			flag = subDao.delete(sub);
			if (flag == false) {
				break;
			}
		}
		try {
			response.sendRedirect("sub");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void showModify(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Subject sub= new Subject();
			int id = Integer.parseInt(request.getParameter("id"));
			sub = subDao.searchById(id);
			request.setAttribute("sub", sub);
			request.getRequestDispatcher("WEB-INF/subject/modify.jsp").forward(request,
					response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Subject sub = new Subject();

		String name = request.getParameter("name");		
		sub.setId(id);
		sub.setName(name);

		boolean flag = subDao.update(sub);
		if (flag) {

			try {
				response.sendRedirect("sub");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private void search(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Subject> list = new ArrayList<Subject>();
		Subject condition = new Subject();
		String name = request.getParameter("name");
		
		if (!"".equals(name)) {
			condition.setName(name);
		}

		//List<Subject> subList = new ArrayList<Subject>();
		 
		try {
			int	max = subDao.searchCount(condition);
		
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int yeNum = 5;
		int yeMa = 5;
		Pagination pagination = new Pagination(max, ye, yeNum, yeMa);
		ye = pagination.getYe();
		int begin = yeNum * (ye - 1);		

		list=subDao.searchByCondition(condition,begin,yeNum);
		request.setAttribute("list", list);
		request.setAttribute("pagination", pagination);
		request.setAttribute("condition", condition);
		//request.setAttribute("subList", subList);
		//subList = subDao.searchAll();
		//request.setAttribute("subList", subList);
		
			request.getRequestDispatcher("WEB-INF/subject/subject.jsp").forward(request,
					response);
		}
		 catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}