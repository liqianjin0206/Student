package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import Entity.BanJi;
import Entity.Student;

public class StudentDao extends BaseDao {
	List<Student> list;
	List<BanJi> Bjlist;
	static Student stu;
	BanJi bj;
	JTable table;

	public List<Student> searchAll() {
		list = new ArrayList<Student>();
		try {
			getStatement();
			rs = stat
					.executeQuery("SELECT s.*,bj.name as bjName,bj.stuNums FROM student as s left JOIN banji as bj on s.bj_id=bj.id");
			while (rs.next()) {
				stu = new Student();
				stu.setID(rs.getInt("id"));
				stu.setName(rs.getString("Name"));
				stu.setSex(rs.getString("Sex"));
				stu.setAge(rs.getInt("age"));
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("bj_id"));
				bj.setName(rs.getString("bjName"));
				bj.setStuNums(rs.getInt("stuNums"));
				stu.setBj(bj);
				list.add(stu);

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

	public List<Student> searchByBegin(int begin, int num) {
		List<Student> list = new ArrayList<Student>();
		Connection conn = null;
		try {
			getStatement();
			rs = stat
					.executeQuery("SELECT s.*,bj.name as bjName,bj.stuNums FROM student as s left JOIN banji as bj on s.bj_id=bj.id limit "
							+ begin + "," + num);
			while (rs.next()) {
				Student stu = new Student();
				stu.setID(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));

				BanJi bj = new BanJi();
				bj.setId(rs.getInt("bj_id"));
				bj.setName(rs.getString("bjName"));
				bj.setStuNums(rs.getInt("stuNums"));
				stu.setBj(bj);
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	public int searchCount() {
		int count = 0;

		try {

			getStatement();
			rs = stat.executeQuery("select count(id)  from student");
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;

	}

	public int searchCount(Student condition) {
		int count = 0;

		try {
			getStatement();
			Statement stat = conn.createStatement();

			String where = "where 1=1 ";
			if (condition.getName() != null) {
				where += " and name like '%" + condition.getName() + "%'";
			}
			if (condition.getSex() != null) {
				where += " and sex='" + condition.getSex() + "'";

			}
			if (condition.getAge() != -1) {
				where += " and age=" + condition.getAge() + " ";

			}
			// if (condition.getBj()!=null&&condition.getBj().getId() == -1) {
			// where += str + " bj_id=0 or bj_id is null ";
			// str = " and ";
			// }
			if (condition.getBj().getId() != 0) {
				where += " and bj_id=" + condition.getBj().getId() + " ";

			}

			String sql = "select count(id) FROM student  " + where;
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;

	}

	public int add(Student stu) {
		int result = 0;
		try {
			// 创建SQL执行器
			getStatement();
			// 创建SQL语句
			String sql = "insert into student(name,sex,age,bj_id,photo)values ('"
					+ stu.getName()
					+ "','"
					+ stu.getSex()
					+ "',"
					+ stu.getAge()
					+ ","
					+ stu.getBj().getId()
					+ ",'"
					+ stu.getPhoto() + "')";

			result = stat.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
			// 关闭链接
		}

		return 0;

	}

	public Student searchByID(int id) {
		Student stu = new Student();

		try {
			getStatement();
			rs = stat
					.executeQuery("SELECT s.*,bj.name as bjName,bj.stuNums FROM student as s left JOIN banji as bj on s.bj_id=bj.id where s.id="
							+ id);

			while (rs.next()) {
				stu.setID(rs.getInt("id"));
				stu.setName(rs.getString("Name"));
				stu.setSex(rs.getString("Sex"));
				stu.setAge(rs.getInt("age"));
				stu.setPhoto(rs.getString("photo"));
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("bj_id"));
				bj.setName(rs.getString("bjName"));
				bj.setStuNums(rs.getInt("stuNums"));
				stu.setBj(bj);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
			// 关闭链接
		}
		return stu;
	}

	public boolean update(Student stu) {
		boolean flage = false;
		try {

			String sql = "update student set name=?,sex=?,age=?,bj_id=?,photo=?  where id=?";
			getPreparedStatement(sql);
			pstat.setString(1, stu.getName());
			pstat.setString(2, stu.getSex());
			pstat.setInt(3, stu.getAge());
			pstat.setInt(4, stu.getBj().getId());
			pstat.setString(5, stu.getPhoto());
			pstat.setInt(6, stu.getID());

			int result = pstat.executeUpdate();
			System.out.println(result);
			if (result > 0) {
				flage = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
			// 关闭链接
		}
		return true;
	}

	public boolean delete(Student stu) {
		boolean flage = false;
		try {
			getConnection();
			Statement stat = conn.createStatement();
			String sql = "delete from student where id=" + stu.getID();
			int result = stat.executeUpdate(sql);
			if (result > 0) {
				flage = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
			// 关闭链接
		}

		return true;

	}

	public List<Student> searchByCondition(Student condition, int begin,
			int yeNum) {
		List<Student> list = new ArrayList<Student>();
		try {

			getStatement();

			String where = "where 1=1 ";
			if (condition.getName() != null) {
				where += " and s.name like '%" + condition.getName() + "%'";

			}
			if (condition.getSex() != null) {
				where += " and sex='" + condition.getSex() + "'";

			}
			if (condition.getAge() != -1) {
				where += " and age=" + condition.getAge() + " ";

			}
			if (condition.getBj().getId() != 0) {
				where += " and bj_id=" + condition.getBj().getId() + " ";

			}

			String sql = "select s.* ,bj.name as bjName FROM student as s left JOIN banji as bj on s.bj_id=bj.id  "
					+ where + " limit " + begin + "," + yeNum;

			System.out.println(sql);

			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Student stu = new Student();
				stu.setID(rs.getInt("id"));
				stu.setName(rs.getString("Name"));
				stu.setSex(rs.getString("Sex"));
				stu.setAge(rs.getInt("age"));
				stu.setPhoto(rs.getString("photo"));
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("bj_id"));
				bj.setName(rs.getString("bjName"));
				stu.setBj(bj);
				list.add(stu);
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

}
