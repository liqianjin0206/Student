package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import Entity.BanJi;
import Entity.Subject;

public class BanJiDao extends BaseDao {
	List<BanJi> list;
	static BanJi bj;
	JTable table;

	public List<BanJi> searchAll() {
		list = new ArrayList<BanJi>();
		try {
			getStatement();
			rs = stat.executeQuery("select bj.*,bj.id as bj_id from banji as bj");
			while (rs.next()) {
				bj = new BanJi();
				bj.setId(rs.getInt("bj_id"));
				bj.setName(rs.getString("Name"));
				bj.setStuNums(rs.getInt("stuNums"));
				list.add(bj);

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

	public List<BanJi> searchByBegin(int begin, int num) {
		List<BanJi> list = new ArrayList<BanJi>();
		Connection conn = null;
		try {
			getStatement();
			rs = stat
					.executeQuery("SELECT * FROM banji  limit "
							+ begin + "," + num);
			while (rs.next()) {
				BanJi stu = new BanJi();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setStuNums(rs.getInt("stuNums"));
				list.add(bj);
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
			rs = stat.executeQuery("select count(id)  from banji");
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;

	}

	public int searchCount(BanJi condition) {
		int count = 0;

		try {
			getStatement();
			Statement stat = conn.createStatement();

			String where = "where 1=1 ";
			if (condition.getName() != null) {
				where += " and name like '%" + condition.getName() + "%'";
			}
			
		
			// if (condition.getBj()!=null&&condition.getBj().getId() == -1) {
			// where += str + " bj_id=0 or bj_id is null ";
			// str = " and ";
			// }
			// if (condition.getBj()!=null&&condition.getBj().getId() != 0) {
			// where += str + " bj_id=" + condition.getBj().getId() + " ";
			//
			// }

			String sql = "select count(id) FROM banji  " + where;
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

	public int add(BanJi bj) {
		int result = 0;
		try {
			// 创建SQL执行器
			getStatement();
			// 创建SQL语句
			String sql = "insert into banji(name) values ('" + bj.getName()
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

	
	
	public BanJi searchById(int id) {
		BanJi bj = new BanJi();

		try {
			getStatement();
			rs = stat.executeQuery("select*from banji where id=" + id);
			while (rs.next()) {
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("Name"));
				bj.setStuNums(rs.getInt("stuNums"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
			// 关闭链接
		}
		return bj;
	}

	public BanJi searchBjAndSubById(int id) {
		BanJi bj = new BanJi();
		List<Subject> list=new ArrayList<Subject>();

		try {
			getStatement();
			
			rs = stat.executeQuery("select*from v_bj_sub where bjId=" + id);
			int i=0;
			while (rs.next()) {
				if(i==0){
				bj.setId(rs.getInt("bjId"));
				bj.setName(rs.getString("bjName"));
				bj.setStuNums(rs.getInt("stuNums"));			
				}
				Subject sub=new Subject();
				sub.setId(rs.getInt("subId"));
				sub.setName(rs.getString("subName"));
				if(sub.getId()!=0){
				list.add(sub);
				}
				i++;
			}
			bj.setSubs(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
			// 关闭链接
		}
		return bj;
	}
	
	
	
	
	
	
	
	
	
	public boolean update(BanJi bj) {
		boolean flage = false;
		try {

			String sql = "update banji set name=? where id=?";
			getPreparedStatement(sql);
			pstat.setString(1, bj.getName());
			pstat.setInt(2, bj.getId());

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

	public boolean delete(BanJi bj) {
		boolean flage = false;
		String sql;
		try {
			getConnection();
			Statement stat = conn.createStatement();
			conn.setAutoCommit(false);
			BanJi stu = new BanJi();
			sql = "delete from banji where id=" + bj.getId();

			int result = stat.executeUpdate(sql);
			if (result > 0) {
				flage = true;
			}
			sql = "update  student set  bj_id=null  where bj_id= " + bj.getId();
			
			result = stat.executeUpdate(sql);
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

	public List<BanJi> searchByCondition(BanJi condition,int begin,int yeNum) {
		List<BanJi> list = new ArrayList<BanJi>();

		getStatement();
		String where = "where 1=1";
		System.out.println(condition);
		if (condition.getName()!=null)
		{
			where += " and name like '%" + condition.getName() + "%'";
			
		}
		String sql = "select * from banji " + where+" limit "+begin+","+yeNum;
		System.out.println(sql);

		try {
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("Name"));
				bj.setStuNums(rs.getInt("stuNums"));
				list.add(bj);
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
