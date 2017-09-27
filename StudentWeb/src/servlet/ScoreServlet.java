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
import net.sf.json.JSONObject;

import util.Pagination;
import Dao.BanJiDao;
import Dao.ScoreDao;
import Dao.StudentDao;
import Dao.SubjectDao;
import Entity.BanJi;
import Entity.Score;
import Entity.Student;
import Entity.Subject;

public class ScoreServlet extends HttpServlet {

	StudentDao stuDao = new StudentDao();
	BanJiDao bjDao = new BanJiDao();
	ScoreDao scDao = new ScoreDao();
	SubjectDao subDao = new SubjectDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		String type = request.getParameter("type");
		if (type == null || type.equals("search")) {
			search(request, response);
		} else if (type.equals("manage")) {
			manage(request, response);
		} else if (type.equals("input")) {
			input(request, response);
		}
	}

	private void input(HttpServletRequest request, HttpServletResponse response) {

		try {

		boolean flag=false;
		Score sc = new Score();
		int scId = Integer.parseInt(request.getParameter("scId"));
		int score = Integer.parseInt(request.getParameter("score"));
		int stuId = Integer.parseInt(request.getParameter("stuId"));
		int subId = Integer.parseInt(request.getParameter("subId"));
		
		Student stu=new Student();
		stu.setID(stuId);
		Subject sub =new Subject();
		sub.setId(subId);
		sc.setStu(stu);
		sc.setSub(sub);
         sc.setScore(score);		
		if (scId == 0) {
			flag=scDao.add(sc);
		} else {
            sc.setId(scId);
            flag=scDao.update(sc);			
		}
		PrintWriter out = response.getWriter();
		if(flag){
			sc=scDao.searchGrade(sc);
			JSONObject json=JSONObject.fromObject(sc);
			out.print(json);
		}else{
			out.print(flag);
			
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private void manage(HttpServletRequest request, HttpServletResponse response) {
		List<BanJi> bjList = new ArrayList<BanJi>();
		bjList = bjDao.searchAll();
		List<Subject> subList = new ArrayList();
		subList = subDao.searchAll();

		String stuName = request.getParameter("stuName");
		if (stuName == null) {
			stuName = "";
		}
		int bjId = 0;
		if (request.getParameter("bjId") != null) {
			bjId = Integer.parseInt(request.getParameter("bjId"));
		}
		int subId = 0;
		if (request.getParameter("subId") != null) {
			subId = Integer.parseInt(request.getParameter("subId"));
		}

		List<Score> scList = new ArrayList<Score>();
		Score condition = new Score();
		Student stu = new Student();

		stu.setName(stuName);
		Subject sub = new Subject();
		sub.setId(subId);
		condition.setSub(sub);
		BanJi bj = new BanJi();
		bj.setId(bjId);
		stu.setBj(bj);
		condition.setStu(stu);

		try {
			int max = scDao.searchCount(condition);

			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			int yeNum = 5;
			int yeMa = 5;
			Pagination pagination = new Pagination(max, ye, yeNum, yeMa);
			ye = pagination.getYe();
			int begin = yeNum * (ye - 1);

			scList = scDao.searchByCondition(condition, begin, yeNum);
			request.setAttribute("scList", scList);
			request.setAttribute("bjList", bjList);
			request.setAttribute("subList", subList);
			request.setAttribute("pagination", pagination);
			request.setAttribute("condition", condition);

			request.getRequestDispatcher("WEB-INF/score/manage.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		doGet(request, response);
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {

		List<BanJi> bjList = new ArrayList<BanJi>();
		bjList = bjDao.searchAll();
		List<Subject> subList = new ArrayList();
		subList = subDao.searchAll();

		String stuName = request.getParameter("stuName");
		if (stuName == null) {
			stuName = "";
		}
		int bjId = 0;
		if (request.getParameter("bjId") != null) {
			bjId = Integer.parseInt(request.getParameter("bjId"));
		}
		int subId = 0;
		if (request.getParameter("subId") != null) {
			subId = Integer.parseInt(request.getParameter("subId"));
		}

		List<Score> scList = new ArrayList<Score>();
		Score condition = new Score();
		Student stu = new Student();
		stu.setName(stuName);

		Subject sub = new Subject();
		sub.setId(subId);
		condition.setSub(sub);

		BanJi bj = new BanJi();
		bj.setId(bjId);
		stu.setBj(bj);
		condition.setStu(stu);

		try {
			int max = scDao.searchCount(condition);

			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			int yeNum = 5;
			int yeMa = 5;
			Pagination pagination = new Pagination(max, ye, yeNum, yeMa);
			ye = pagination.getYe();
			int begin = yeNum * (ye - 1);

			scList = scDao.searchByCondition(condition, begin, yeNum);
			request.setAttribute("scList", scList);
			request.setAttribute("bjList", bjList);
			request.setAttribute("subList", subList);
			request.setAttribute("pagination", pagination);
			request.setAttribute("condition", condition);

			request.getRequestDispatcher("WEB-INF/score/score.jsp").forward(
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