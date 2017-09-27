package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Subject;

public class SubjectToBanJiDao extends BaseDao {
	List<Subject> list;
	Subject sub;
	
	public boolean addAll(int bjId, String[] subIds) {
		boolean flag = true;
		Connection conn=getConnection();

		for(int i=0;i<subIds.length;i++  ){
			boolean f= add(conn,bjId,Integer.parseInt(subIds[i]));			
			if(f==false){
				flag=false;
				break;
			}
		}
		closeAll();
		return flag;
	
	}
	
	public boolean deleteAll(int bjId, String[] subIds) {
		boolean flag = true;
		Connection conn=getConnection();

		for(int i=0;i<subIds.length;i++  ){
		boolean f= delete(conn,bjId,Integer.parseInt(subIds[i]));
	
		if(f==false){
			flag=false;
			break;
		}							
		}
		closeAll();
		return flag;
	
	}
	
	public boolean add(Connection conn, int bjId, int subId) {
		boolean flag = false;
		try {
			
			// 创建SQL语句
			String sql = "insert into r_bj_id_sub_id  (bj_id,sub_id)" 
			+" values(?,?)";
		PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setInt(1, bjId);
			pstat.setInt(2, subId);
			int result=pstat.executeUpdate();
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public boolean delete(Connection conn,int bjId, int subId) {
		boolean flag = false;
		try {

			String sql = "delete from r_bj_id_sub_id where bj_id=?  and sub_id=?" ;
			
		    pstat=conn.prepareStatement(sql);
		    pstat.setInt(1, bjId);
		    pstat.setInt(2, subId);
			int result = pstat.executeUpdate();
			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return flag;

	}
	

	public List<Subject> searchByBjId(int bjId) {
		List<Subject> list = new ArrayList<Subject>();
		getStatement();

		String sql = "select sub.* from banji as bj "
				+ " INNER JOIN  r_bj_id_sub_id as r on bj.id=r.bj_id"
				+ " INNER JOIN  subject as sub   on r.sub_id=sub.id"
				+ " where bj.id=" + bjId;
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

	public List<Subject> searchByNotBjId(int bjId) {
		List<Subject> list = new ArrayList<Subject>();
		getStatement();

		String sql = "select *from subject where id not in ("
				+ " select sub.id from banji as bj "
				+ " INNER JOIN  r_bj_id_sub_id as r on bj.id=r.bj_id"
				+ " INNER JOIN  subject as sub   on r.sub_id=sub.id"
				+ " where bj.id=" + bjId + ")";
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
}
