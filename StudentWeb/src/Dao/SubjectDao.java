package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import Entity.Subject;

public class SubjectDao extends BaseDao {
	List<Subject> list;
	static Subject sub;
	JTable table;

	public List<Subject> searchAll() {
		list = new ArrayList<Subject>();
		try {
			getStatement();
			rs = stat
					.executeQuery("select sub.*,sub.id as sub_id from subject as sub");
			while (rs.next()) {
				sub = new Subject();
				sub.setId(rs.getInt("sub_id"));
				sub.setName(rs.getString("Name"));
				list.add(sub);
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

	public List<Subject> searchNoSubByBjId(int bjId) {
		List<Subject> list = new ArrayList<Subject>();
		try {
			getStatement();
			rs = stat
					.executeQuery("select *from subject where id not in( select subId from v_bj_sub where bjId="
							+ bjId + " and subId is not null)");
			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setName(rs.getString("name"));
				list.add(sub);
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

	public List<Subject> searchByBegin(int begin, int num) {
		List<Subject> list = new ArrayList<Subject>();
		Connection conn = null;
		try {
			getStatement();
			rs = stat.executeQuery("SELECT * FROM subject  limit " + begin
					+ "," + num);
			while (rs.next()) {
				Subject stu = new Subject();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				list.add(sub);
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
			rs = stat.executeQuery("select count(id)  from subject");
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;

	}

	public int searchCount(Subject condition) {
		int count = 0;

		try {
			getStatement();
			Statement stat = conn.createStatement();

			String where = "where 1=1 ";
			if (condition.getName() != null) {
				where += " and name like '%" + condition.getName() + "%'";
			}

			// if (condition.getBj()!=null&&condition.getBj().getId() == -1) {
			// where += str + " sub_id=0 or sub_id is null ";
			// str = " and ";
			// }
			// if (condition.getBj()!=null&&condition.getBj().getId() != 0) {
			// where += str + " sub_id=" + condition.getBj().getId() + " ";
			//
			// }

			String sql = "select count(id) FROM subject  " + where;
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

	public int add(Subject sub) {
		int result = 0;
		try {
			// 创建SQL执行器
			getStatement();
			// 创建SQL语句
			String sql = "insert into subject(name) values ('" + sub.getName()
					+ "')";

			result = stat.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
			// 关闭链接
		}

		return 0;

	}

	public Subject searchById(int id) {
		Subject sub = new Subject();

		try {
			getStatement();
			rs = stat.executeQuery("select*from subject where id=" + id);

			while (rs.next()) {
				sub.setId(rs.getInt("id"));
				sub.setName(rs.getString("Name"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
			// 关闭链接
		}
		return sub;
	}

	public boolean update(Subject sub) {
		boolean flage = false;
		try {

			String sql = "update subject set name=? where id=?";
			getPreparedStatement(sql);
			pstat.setString(1, sub.getName());
			pstat.setInt(2, sub.getId());

			int result = pstat.executeUpdate();
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

	public boolean delete(Subject sub) {
		boolean flage = false;
		String sql;
		try {
			getConnection();
			Statement stat = conn.createStatement();
			conn.setAutoCommit(false);

			// Subject stu = new Subject();
			sql = "delete from subject where id=" + sub.getId();

			int result = stat.executeUpdate(sql);
			// if (result > 0) {
			// flage = true;
			// }
			// sql = "update  student set  sub_id=null  where sub_id= " +
			// sub.getId();
			//
			// result = stat.executeUpdate(sql);
			conn.commit();
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

	public List<Subject> searchByCondition(Subject condition, int begin,
			int yeNum) {
		List<Subject> list = new ArrayList<Subject>();

		getStatement();
		String where = "where 1=1";
		System.out.println(condition);
		if (condition.getName() != null) {
			where += " and name like '%" + condition.getName() + "%'";

		}
		String sql = "select * from subject " + where + " limit " + begin + ","
				+ yeNum;
		

		try {
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setName(rs.getString("Name"));

				list.add(sub);
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

	public List<Subject> searchSubByBj(int bjId) {

		List<Subject> list = new ArrayList<Subject>();

		getStatement();

		String sql="";
		if(bjId==0){
			 sql = "select sub.id as subId, sub.name as subName from subject as sub";
		}else{	
		 sql = "select sub.id as subId, sub.name as subName " 
		        + " from subject as sub inner join r_bj_id_sub_id as r on"
				+ " r.sub_id=sub.id inner join banji as bj "
				+ " on bj.id=r.bj_id where bj_id=" + bjId;
		}
		
		try {
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("subId"));
				sub.setName(rs.getString("subName"));

				list.add(sub);
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
