package servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.Pagination;
import Dao.BanJiDao;
import Dao.StudentDao;
import Entity.BanJi;
import Entity.Student;

public class StudentServlet extends HttpServlet {

	StudentDao stuDao = new StudentDao();
	BanJiDao bjDao = new BanJiDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		String type = request.getParameter("type");
		if (type == null) {
			search(request, response);
		} else if (type.equals("showAdd")) {
			showAdd(request, response);
		} else if (type.equals("add")) {
			add(request, response);
		} else if (type.equals("add2")) {
			add2(request, response);
		} else if (type.equals("delete")) {
			delete(request, response);
		} else if (type.equals("showModify")) {
			showModify(request, response);
		} else if (type.equals("modify")) {
			modify(request, response);
		} else if (type.equals("search")) {
			search(request, response);
		} else if (type.equals("upload")) {
			upload(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

	// public void StudentList(HttpServletRequest request,
	// HttpServletResponse response) {
	//
	// try {
	// List<BanJi> bjList = new ArrayList<BanJi>();
	// bjList = bjDao.searchAll();
	// int max = stuDao.searchCount();
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
	// List<Student> list = stuDao.searchByBegin(begin, yeNum);
	// request.setAttribute("list", list);
	// request.setAttribute("bjList", bjList);
	// request.setAttribute("pagination", pagination);
	//
	// request.getRequestDispatcher("WEB-INF/student.jsp").forward(
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
			List<BanJi> bjList = new ArrayList<BanJi>();
			bjList = bjDao.searchAll();
			request.setAttribute("bjList", bjList);

			request.getRequestDispatcher("WEB-INF/student/addStudent.jsp")
					.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void add(HttpServletRequest request, HttpServletResponse response) {

		try {
			String name = "";
			String sex = "";
			String newFileName = "";
			int age = 0;
			int bjId = 0;

			FileItemFactory factory = new DiskFileItemFactory(); // 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都会存到一个List中
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);

			String url = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/photos/";
			for (int i = 0; i < items.size(); i++) {
				if ("name".equals(items.get(i).getFieldName())) {
					name = new String(items.get(i).getString()
							.getBytes("ISO-8859-1"), "UTF-8");
				} else if ("sex".equals(items.get(i).getFieldName())) {
					sex = new String(items.get(i).getString()
							.getBytes("ISO-8859-1"), "UTF-8");
				} else if ("age".equals(items.get(i).getFieldName())) {
					age = Integer.parseInt(items.get(i).getString());
				} else if ("bjId".equals(items.get(i).getFieldName())) {
					bjId = Integer.parseInt(items.get(i).getString());
				}

				else if ("photo".equals(items.get(i).getFieldName())) {

					UUID uuid = UUID.randomUUID();
					String oldFileName = items.get(i).getName();
					String houzhui = oldFileName.substring(oldFileName
							.lastIndexOf("."));
					newFileName = uuid + houzhui;
					File file = new File(url + "/" + newFileName);
					items.get(i).write(file);

				}
			}
			Student stu = new Student();
			stu.setName(name);
			stu.setSex(sex);
			stu.setAge(age);
			stu.setPhoto(newFileName);

			BanJi bj = new BanJi();
			bj.setId(bjId);
			stu.setBj(bj);
			stuDao.add(stu);

			response.sendRedirect("stu?type=search");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void add2(HttpServletRequest request, HttpServletResponse response) {

		try {
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String newFileName = request.getParameter("photo");
			int age = 0;
			if (request.getParameter("age") != null
					&& !"".equals(request.getParameter("age"))) {
				age = Integer.parseInt(request.getParameter("age"));

			}

			int bjId = 0;
			if (request.getParameter("bjId") != null
					&& !"".equals(request.getParameter("bjId"))) {
				bjId = Integer.parseInt(request.getParameter("bjId"));

			}
			Student stu = new Student();
			stu.setName(name);
			stu.setSex(sex);
			stu.setAge(age);
			stu.setPhoto(newFileName);

			BanJi bj = new BanJi();
			bj.setId(bjId);
			stu.setBj(bj);
			stuDao.add(stu);

			response.sendRedirect("stu?type=search");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void upload(HttpServletRequest request, HttpServletResponse response) {

		try {
			String name = "";
			String sex = "";
			String newFileName = "";
			int age = 0;
			int bjId = 0;

			FileItemFactory factory = new DiskFileItemFactory(); // 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都会存到一个List中
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);

			String url = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/photos/";
			ArrayList<String> list = new ArrayList<String>();
			for (int i = 0; i < items.size(); i++) {
				
				if ("photo".equals(items.get(i).getFieldName())) {

					UUID uuid = UUID.randomUUID();
					String oldFileName = items.get(i).getName();
					String houzhui = oldFileName.substring(oldFileName
							.lastIndexOf("."));
					newFileName = uuid + houzhui;
					File file = new File(url + "/" + newFileName);
					items.get(i).write(file);
					list.add(newFileName);
				}
			}
			
			response.getWriter().print(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameter("id").split(",");
		boolean flag = false;
		for (int i = 0; i < ids.length; i++) {
			Student stu = new Student();
			stu.setID(Integer.parseInt(ids[i]));
			flag = stuDao.delete(stu);
			if (flag == false) {
				break;
			}

		}
		try {
			response.sendRedirect("stu?type=search");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showModify(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			List<BanJi> bjList = new ArrayList<BanJi>();
			bjList = bjDao.searchAll();
			request.setAttribute("bjList", bjList);

			Student stu = new Student();
			int id = Integer.parseInt(request.getParameter("id"));

			stu = stuDao.searchByID(id);
			request.setAttribute("stu", stu);

			request.getRequestDispatcher("WEB-INF/student/modify.jsp").forward(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = "";
			String sex = "";
			String newFileName = "";
			int age = 0;
			int id = 0;
			int bjId = 0;

			FileItemFactory factory = new DiskFileItemFactory(); // 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都会存到一个List中
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);

			String url = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/photos/";
			for (int i = 0; i < items.size(); i++) {
				if ("name".equals(items.get(i).getFieldName())) {
					name = new String(items.get(i).getString()
							.getBytes("ISO-8859-1"), "UTF-8");
				} else if ("sex".equals(items.get(i).getFieldName())) {
					sex = new String(items.get(i).getString()
							.getBytes("ISO-8859-1"), "UTF-8");
				} else if ("age".equals(items.get(i).getFieldName())) {
					age = Integer.parseInt(items.get(i).getString());
				} else if ("bjName".equals(items.get(i).getFieldName())) {
					bjId = Integer.parseInt(items.get(i).getString());
				} else if ("id".equals(items.get(i).getFieldName())) {
					id = Integer.parseInt(items.get(i).getString());
				}

				else if ("photo".equals(items.get(i).getFieldName())) {

					UUID uuid = UUID.randomUUID();
					String oldFileName = items.get(i).getName();
					String houzhui = oldFileName.substring(oldFileName
							.lastIndexOf("."));
					newFileName = uuid + houzhui;
					File file = new File(url + "/" + newFileName);
					items.get(i).write(file);

				}
			}
			Student stu = new Student();
			stu.setID(id);
			stu.setName(name);
			stu.setSex(sex);
			stu.setAge(age);
			stu.setPhoto(newFileName);

			BanJi bj = new BanJi();
			bj.setId(bjId);
			stu.setBj(bj);
			stuDao.update(stu);

			response.sendRedirect("stu?type=search");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void search(HttpServletRequest request, HttpServletResponse response) {

		List<Student> list = new ArrayList<Student>();
		Student condition = new Student();
		String name = request.getParameter("name");

		if (!"".equals(name)) {
			condition.setName(name);
		}
		String sex = request.getParameter("sex");
		if ("男".equals(sex) || "女".equals(sex)) {
			condition.setSex(sex);

		}
		if (null == request.getParameter("age")
				|| "".equals(request.getParameter("age"))) {
			condition.setAge(-1);
		} else {
			int age = Integer.parseInt(request.getParameter("age"));
			condition.setAge(age);
		}

		int bjId = 0;

		if (null != request.getParameter("bjName")) {
			bjId = Integer.parseInt(request.getParameter("bjName"));
		}
		BanJi bj = new BanJi();
		bj.setId(bjId);
		condition.setBj(bj);

		try {
			int max = stuDao.searchCount(condition);

			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			int yeNum = 5;
			int yeMa = 5;
			Pagination pagination = new Pagination(max, ye, yeNum, yeMa);
			ye = pagination.getYe();
			int begin = yeNum * (ye - 1);

			List<BanJi> bjList = new ArrayList<BanJi>();
			bjList = bjDao.searchAll();

			list = stuDao.searchByCondition(condition, begin, yeNum);
			request.setAttribute("list", list);
			request.setAttribute("bjList", bjList);
			request.setAttribute("pagination", pagination);
			request.setAttribute("condition", condition);

			request.getRequestDispatcher("WEB-INF/student/student.jsp")
					.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}