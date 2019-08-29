package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.JdbcUtil;
import model.ChangePwRequest;
import model.Manufactures;
import model.Sales;

public class ComDao {

	public String getFNum(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String f_num = "";
		try {
			pstmt = conn.prepareStatement("select f_num from employee where e_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				f_num = rs.getString("f_num");
			}
			return f_num;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List getFMf(Connection conn, String id, int s, int end) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		Manufactures mf = null;
		String f_num = "";
		try {
			pstmt = conn.prepareStatement("select f_num from employee where e_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				f_num = rs.getString("f_num");
				pstmt = conn.prepareStatement("select * from manufactures where f_num = ? order by mf_num desc limit ?,?");
				pstmt.setString(1, f_num);
				pstmt.setInt(2, s);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						mf = new Manufactures();
						mf.setMf_num(rs.getString("mf_num"));
						mf.setF_num(rs.getString("f_num"));
						mf.setPl_num(rs.getString("pl_num"));
						mf.setE_id(rs.getString("e_id"));
						mf.setP_num(rs.getString("p_num"));
						mf.setMf_count(rs.getString("mf_count"));
						mf.setMf_date(rs.getString("mf_date"));
						list.add(mf);
					} while (rs.next());
				}
			}
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int getMaxNum(Connection conn, String fNum) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			pstmt = conn.prepareStatement("select max(mf_num) from manufactures where f_num = ?");
			pstmt.setString(1, fNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
			}
			return num;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 차트 데이터 조회
		// ==========================================================================================================
		public Map<String, Map<Integer, Integer>> getLineData(Connection conn, String fac) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Map<Integer, Integer> fq = new HashMap<Integer, Integer>();
			Map<Integer, Integer> sq = new HashMap<Integer, Integer>();
			Map<String, Map<Integer, Integer>> line = new HashMap<String, Map<Integer, Integer>>();
			try {
				pstmt = conn.prepareStatement("select DATE_FORMAT(mf_date,'%m') as month , sum(mf_count) as mf_sum from manufactures where f_num=? group by month order by month asc limit 0,3");
				pstmt.setString(1, fac);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						fq.put(rs.getInt("month"),rs.getInt("mf_sum"));
					} while (rs.next());
					line.put("First_Quater", fq);
				}
				pstmt = conn.prepareStatement("select DATE_FORMAT(mf_date,'%m') as month , sum(mf_count) as mf_sum from manufactures where f_num=? group by month order by month desc limit 3,6");
				pstmt.setString(1, fac);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						sq.put(rs.getInt("month"),rs.getInt("mf_sum"));
					} while (rs.next());
					line.put("Second_Quater", sq);
				}
				return line;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
		
		public Map<String, Map<String, Integer>> getBarData(Connection conn, String fac) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Map<String, Integer> fq = new HashMap<String, Integer>();
			Map<String, Integer> sq = new HashMap<String, Integer>();
			Map<String, Map<String, Integer>> bar = new HashMap<String, Map<String, Integer>>();
			try {
				pstmt = conn.prepareStatement(
						"select DATE_FORMAT(mf_date,'%m') as month , sum(mf_count) as mf_sum from manufactures where f_num=? and pl_num='p_1' group by month order by month asc limit 0,3");
				pstmt.setString(1, fac);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					fq.put("p_1", rs.getInt(1));
				}
				pstmt = conn.prepareStatement(
						"select DATE_FORMAT(mf_date,'%m') as month , sum(mf_count) as mf_sum from manufactures where f_num=? and pl_num='p_2' group by month order by month asc limit 0,3");
				pstmt.setString(1, fac);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					fq.put("p_2", rs.getInt(1));
				}
				pstmt = conn.prepareStatement(
						"select DATE_FORMAT(mf_date,'%m') as month , sum(mf_count) as mf_sum from manufactures where f_num=? and pl_num='p_2' group by month order by month asc limit 0,3");
				pstmt.setString(1, fac);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					fq.put("p_3", rs.getInt(1));
				}
				bar.put("First_Quater", fq);
				
				pstmt = conn.prepareStatement(
						"select DATE_FORMAT(mf_date,'%m') as month , sum(mf_count) as mf_sum from manufactures where f_num=? and pl_num='p_1' group by month order by month asc limit 0,3");
				pstmt.setString(1, fac);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					sq.put("p_1", rs.getInt(1));
				}
				pstmt = conn.prepareStatement(
						"select DATE_FORMAT(mf_date,'%m') as month , sum(mf_count) as mf_sum from manufactures where f_num=? and pl_num='p_2' group by month order by month asc limit 0,3");
				pstmt.setString(1, fac);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					sq.put("p_2", rs.getInt(1));
				}
				pstmt = conn.prepareStatement(
						"select DATE_FORMAT(mf_date,'%m') as month , sum(mf_count) as mf_sum from manufactures where f_num=? and pl_num='p_2' group by month order by month asc limit 0,3");
				pstmt.setString(1, fac);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					sq.put("p_3", rs.getInt(1));
				}
				bar.put("Second_Quater", sq);
				return bar;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
		
		public Map<String, Integer> getPieData(Connection conn, String fac) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Map<String, Integer> pie = new HashMap<String, Integer>(); 
			try {
				pstmt = conn.prepareStatement("select e_id, sum(mf_count) as mf_sum from manufactures where f_num = ? and MONTH(mf_date) = 06 group by e_id order by mf_sum desc limit 0,6");
				pstmt.setString(1, fac);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						pie.put(rs.getString("e_id"),rs.getInt("mf_sum"));
					} while (rs.next());
				}
				return pie;
			} finally {
				JdbcUtil.close(pstmt);
			}
		}
		
		public Map<String, Integer> getPolorData(Connection conn, String fac) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Map<String, Integer> polor = new HashMap<String, Integer>(); 
			try {
				pstmt = conn.prepareStatement("select e_id, sum(mf_count) as mf_sum from manufactures where f_num = ? group by e_id order by mf_sum desc limit 0,6");
				pstmt.setString(1, fac);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						polor.put(rs.getString("e_id"),rs.getInt("mf_sum"));
					} while (rs.next());
				}
				return polor;
			} finally {
				JdbcUtil.close(pstmt);
			}
		}
		
		public List<Sales> selectSaleData(Connection conn, String fNum) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Sales> list = new ArrayList<Sales>();
			Sales sales = null;
			try {
				pstmt = conn.prepareStatement("select s.* from sales s, employee e where e.f_num = ? and e.e_id = s.e_id");
				pstmt.setString(1, fNum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						sales = new Sales();
						sales.setS_num(rs.getString("s_num"));
						sales.setMf_num(rs.getString("mf_num"));
						sales.setE_id(rs.getString("e_id"));
						sales.setC_id(rs.getString("c_id"));
						sales.setP_num(rs.getString("p_num"));
						sales.setS_obtain_date(rs.getString("s_obtain_date"));
						sales.setS_contract_sum(rs.getString("s_contract_sum"));
						/*if (rs.getString("s_complete_date") == null) {
							sales.setS_complete_date("");
						}else {*/
							sales.setS_complete_date(rs.getString("s_complete_date"));
						//}
						list.add(sales);
					} while (rs.next());
				}
				return list;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
}
