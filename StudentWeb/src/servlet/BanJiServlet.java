package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import util.Pagination;
import Dao.BanJiDao;
import Dao.SubjectDao;
import Dao.SubjectToBanJiDao;
import Entity.BanJi;
import Entity.Subject;

public class BanJiServlet extends HttpServlet {

	BanJiDao bjDao = new BanJiDao();
	SubjectDao subDao = new SubjectDao();
	SubjectToBanJiDao stbDao = new SubjectToBanJiDao();

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
		} else if (type.equals("showSub")) {
			showSub(request, response);
		}else if (type.equals("showSub2")) {
			showSub2(request, response);
		}else if (type.equals("addSub")) {
			addSub(request, response);
		} else if (type.equals("deleteSub")) {
			deleteSub(request, response);
		} else if (type.equals("searchSub")) {
			searchSub(request, response);
		}
	}

	private void deleteSub(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int bjId = Integer.parseInt(request.getParameter("bjId"));
			String[] subIds = request.getParameter("subIds").split(",");
			boolean flag = stbDao.deleteAll(bjId, subIds);
			PrintWriter out = response.getWriter();

			BanJi bj = bjDao.searchBjAndSubById(bjId);
			List<Subject> noSubs = subDao.searchNoSubByBjId(bjId);

			String str = JSONArray.fromObject(bj.getSubs()).toString();
			str += "-|-" + JSONArray.fromObject(noSubs).toString();

			out.print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addSub(HttpServletRequest request, HttpServletResponse response) {
		try {
			int bjId = Integer.parseInt(request.getParameter("bjId"));
			String[] subIds = request.getParameter("subIds").split(",");
			boolean flag = stbDao.addAll(bjId, subIds);
			PrintWriter out = response.getWriter();

			BanJi bj = bjDao.searchBjAndSubById(bjId);
			List<Subject> noSubs = subDao.searchNoSubByBjId(bjId);

			String str = JSONArray.fromObject(bj.getSubs()).toString();
			str += "-|-" + JSONArray.fromObject(noSubs).toString();

			out.print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchSub(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int bjId = Integer.parseInt(request.getParameter("bjId"));
			PrintWriter out = response.getWriter();

			BanJi bj = bjDao.searchBjAndSubById(bjId);
			List<Subject> noSubs = subDao.searchNoSubByBjId(bjId);

			String str = JSONArray.fromObject(bj.getSubs()).toString();
			str += "-|-" + JSONArray.fromObject(noSubs).toString();

			out.print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showSub(HttpServletRequest request,
			HttpServletResponse response) {
		List<BanJi> bjs = bjDao.searchAll();
		int bjId = 0;
		if (request.getParameter("id") == null) {
			bjId = bjs.get(0).getId();

		} else {
			bjId = Integer.parseInt(request.getParameter("id"));
		}
		BanJi bj = bjDao.searchBjAndSubById(bjId);
		List<Subject> noSub = subDao.searchNoSubByBjId(bjId);
		try {
			request.setAttribute("bj", bj);
			request.setAttribute("noSub", noSub);
			request.setAttribute("bjs", bjs);
			request.getRequestDispatcher("WEB-INF/stb/stb.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void showSub2(HttpServletRequest request,
			HttpServletResponse response) {
		List<BanJi> bjs = bjDao.searchAll();
		int bjId = 0;
		if (request.getParameter("id") == null) {
			bjId = bjs.get(0).getId();

		} else {
			bjId = Integer.parseInt(request.getParameter("id"));
		}
		BanJi bj = bjDao.searchBjAndSubById(bjId);
		List<Subject> noSub = subDao.searchNoSubByBjId(bjId);
		try {
			request.setAttribute("bj", bj);
			request.setAttribute("noSub", noSub);
			request.setAttribute("bjs", bjs);
			request.getRequestDispatcher("WEB-INF/stb/stb2.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	// private void showSub(HttpServletRequest request,
	// HttpServletResponse response) {
	// try {
	// int id = Integer.parseInt(request.getParameter("id"));
	// BanJi condition = new BanJi();
	// condition.setId(id);
	// int max = stbDao.searchCount(condition);
	//
	// int ye = 1;
	// if (request.getParameter("ye") != null) {
	// ye = Integer.parseInt(request.getParameter("ye"));
	// }
	// int yeNum = 5;
	// int yeMa = 5;
	// Pagination pagination = new Pagination(max, ye, yeNum, yeMa);
	// ye = pagination.getYe();
	// int begin = yeNum * (ye - 1);
	//
	// List<Subject> list = stbDao.searchByBjId(id);
	// request.setAttribute("list", list);
	// request.setAttribute("pagination", pagination);
	// request.setAttribute("condition", condition);
	// request.getRequestDispatcher("WEB-INF/stb/stb.jsp").forward(request,
	// response);
	// } catch (ServletException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// }

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

	// public void BanJiList(HttpServletRequest request,
	// HttpServletResponse response) {
	//
	// try {
	// List<BanJi> bjList = new ArrayList<BanJi>();
	// bjList = bjDao.searchAll();
	// int max = bjDao.searchCount();
	// int ye = 1;
	// if (request.getParameter("ye") != null) {
	// ye = Integer.parseInt(request.getParameter("ye"));
	// }
	// int yeNum = 5;
	// int yeMa = 5;
	// Pagination pagination = new Pagination(max, ye, yeNum, yeMa);
	// ye = pagination.getYe();
	// int begin = yeNum * (ye - 1);
	//
	// List<BanJi> list = bjDao.searchByBegin(begin, yeNum);
	// request.setAttribute("list", list);
	// request.setAttribute("bjList", bjList);
	// request.setAttribute("pagination", pagination);
	//
	// request.getRequestDispatcher("WEB-INF/banji.jsp").forward(
	// request, response);
	// } catch (ServletException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	private void showAdd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/banji/addBanJi.jsp").forward(
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

		// List<BanJi> bjList = new ArrayList<BanJi>();
		// request.setAttribute("bjList", bjList);
		// bjList = bjDao.searchAll();

		String name = request.getParameter("name");
		BanJi bj = new BanJi();
		bj.setName(name);

		// BanJi bj=new BanJi();
		// bj.setName(bjName);
		// bj.setBj(bj);
		bjDao.add(bj);
		try {
			response.sendRedirect("bj");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameter("id").split(",");
		boolean flag = false;
		for (int i = 0; i < ids.length; i++) {
			BanJi bj = new BanJi();
			bj.setId(Integer.parseInt(ids[i]));
			flag = bjDao.delete(bj);
			if (flag == false) {
				break;
			}

		}
		try {
			response.sendRedirect("bj");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showModify(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			BanJi bj = new BanJi();
			int id = Integer.parseInt(request.getParameter("id"));
			bj = bjDao.searchById(id);
			request.setAttribute("bj", bj);
			request.getRequestDispatcher("WEB-INF/banji/modify.jsp").forward(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		BanJi bj = new BanJi();

		String name = request.getParameter("name");
		bj.setId(id);
		bj.setName(name);

		boolean flag = bjDao.update(bj);
		if (flag) {

			try {
				response.sendRedirect("bj");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private void search(HttpServletRequest request, HttpServletResponse response) {

		List<BanJi> list = new ArrayList<BanJi>();
		BanJi condition = new BanJi();
		String name = request.getParameter("name");

		if (!"".equals(name)) {
			condition.setName(name);
		}

		// List<BanJi> bjList = new ArrayList<BanJi>();

		try {
			int max = bjDao.searchCount(condition);

			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			int yeNum = 5;
			int yeMa = 5;
			Pagination pagination = new Pagination(max, ye, yeNum, yeMa);
			ye = pagination.getYe();
			int begin = yeNum * (ye - 1);

			list = bjDao.searchByCondition(condition, begin, yeNum);
			request.setAttribute("list", list);
			request.setAttribute("pagination", pagination);
			request.setAttribute("condition", condition);
			// request.setAttribute("bjList", bjList);
			// bjList = bjDao.searchAll();
			// request.setAttribute("bjList", bjList);

			request.getRequestDispatcher("WEB-INF/banji/banji.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}