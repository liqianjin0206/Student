package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.BanJi;
import Entity.Score;
import Entity.Student;
import Entity.Subject;

public class ScoreDao extends BaseDao {

	public int searchCount(Score condition) {
		int count = 0;

		try {
			getStatement();
			Statement stat = conn.createStatement();

			String where = " where 1=1 ";
			if (!condition.getStu().getName().equals("")) {
				where += " and stuName like '%" + condition.getStu().getName()
						+ "%'";
			}
			if (condition.getSub().getId() != 0) {
				where += " and subId=" + condition.getSub().getId();
			}
			if (condition.getStu().getBj().getId() != 0) {
				where += " and bjId=" + condition.getStu().getBj().getId();
			}

			String sql = "select count(*) as c FROM v_stu_bj_sub_score  "
					+ where;
			rs = stat.executeQuery(sql);
	
			if (rs.next()) {
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return count;

	}

	public List<Score> searchByCondition(Score condition, int begin, int yeNum) {
		List<Score> list = new ArrayList<Score>();
		try {

			getStatement();
			String where = "";

			if (!condition.getStu().getName().equals("")) {
				where += " and stuName like '%" + condition.getStu().getName()
						+ "%' ";
			}
			if (condition.getSub().getId() != 0) {
				where += " and subId=" + condition.getSub().getId();
			}
			if (condition.getStu().getBj().getId() != 0) {
				where += " and bjId=" + condition.getStu().getBj().getId();
			}

			String sql = "SELECT*from v_stu_bj_sub_score where 1=1 " + where
					+ " limit " + begin + "," + yeNum;
			rs = stat.executeQuery(sql);
		
			while (rs.next()) {
				Score sc = new Score();
				sc.setId(rs.getInt("scId"));
				sc.setScore((Integer) rs.getObject("score"));
				sc.setGrade(rs.getString("grade"));
				Subject sub = new Subject();
				sub.setId(rs.getInt("subId"));
				sub.setName(rs.getString("subName"));
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("bjId"));
				bj.setName(rs.getString("bjName"));
				Student stu = new Student();
				stu.setID(rs.getInt("stuId"));
				stu.setName(rs.getString("stuName"));
				stu.setBj(bj);
				sc.setStu(stu);
				sc.setSub(sub);
				list.add(sc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
			// 关闭链接
		}

		return list;

	}


	public boolean add(Score sc) {
		boolean flag = false;
		try {
			getStatement();
			String sql = "insert into chengji (stu_id,sub_id,score) "
					+ "values (" + sc.getStu().getID() + ","
					+ sc.getSub().getId() + "," + sc.getScore() + ")";

			int result = stat.executeUpdate(sql);
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
			// 关闭链接
		}

		return flag;
	}

	public boolean update(Score sc) {
		boolean flag = false;
		try {
			getStatement();
			String sql = "update chengji set score=" + sc.getScore()
					+ " where id=" + sc.getId();

			int result = stat.executeUpdate(sql);
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
			// 关闭链接
		}
		return flag;

	}

	public Score searchGrade(Score sc) {
		Score score=null;
		try {

			getStatement();
			
			String sql = "SELECT * from chengji where stu_id="+sc.getStu().getID()+
					" and sub_id="+sc.getSub().getId();
			rs = stat.executeQuery(sql);
		
			while (rs.next()) {
				score = new Score();
				score.setId(rs.getInt("id"));
				score.setScore((Integer) rs.getObject("score"));
				score.setGrade(rs.getString("grade"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
			// 关闭链接
		}

		return score;
	}

}
