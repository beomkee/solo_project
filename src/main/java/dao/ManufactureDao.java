package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.JdbcUtil;
import model.Manufactures;

public class ManufactureDao {

	// 생산데이터
	// 조회============================================================================================================
	public List<Manufactures> selectManufactures(Connection conn) throws SQLException {
		Manufactures manufactures = null;
		List<Manufactures> list = new ArrayList<Manufactures>();

		try (PreparedStatement pstmt = conn.prepareStatement("select * from manufactures")) {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					manufactures = new Manufactures();
					manufactures.setMf_num(rs.getString("mf_num"));
					manufactures.setF_num(rs.getString("f_num"));
					manufactures.setPl_num(rs.getString("pl_num"));
					manufactures.setE_id(rs.getString("e_id"));
					manufactures.setP_num(rs.getString("p_num"));
					manufactures.setMf_count(rs.getString("mf_count"));
					manufactures.setMf_date(rs.getString("mf_date"));
					list.add(manufactures);
				} while (rs.next());
			}
			return list;
		}
	}

	// 검색결과
	// 조회=======================================================================================
	public List<Manufactures> searchMf(Connection conn, String[] searchOption) throws SQLException {
		Manufactures manufactures = null;
		List<Manufactures> list = new ArrayList<Manufactures>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from manufactures "
					+ "where mf_num = ? or f_num = ? or pl_num = ? or e_id = ? or p_num = ? or mf_count = ? or mf_date = ?");
			for (int i = 0; i < searchOption.length; i++) {
				pstmt.setString(i + 1, searchOption[i]);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					manufactures = new Manufactures();
					manufactures.setMf_num(rs.getString("mf_num"));
					manufactures.setF_num(rs.getString("f_num"));
					manufactures.setPl_num(rs.getString("pl_num"));
					manufactures.setE_id(rs.getString("e_id"));
					manufactures.setP_num(rs.getString("p_num"));
					manufactures.setMf_count(rs.getString("mf_count"));
					manufactures.setMf_date(rs.getString("mf_date"));
					list.add(manufactures);
				} while (rs.next());
			}
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 입력 &
	// 수정======================================================================================================
	public boolean isExist(Connection conn, String num) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from manufactures where mf_num = ?");
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return false;
	}

	// 삭제======================================================================================================
	public void delete(Connection conn, String num) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete from manufactures where mf_num = ?");
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 단일값 조회
	// ===============================================================================================
	public int maxNum(Connection conn, String num) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxNum = 0;
		try {
			pstmt = conn.prepareStatement("select max(mf_num) from manufactures");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxNum = rs.getInt(1);
			}
			return maxNum + 1;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void insert(Connection conn, String[] dataset, int num) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into manufactures values(?,?,?,?,?,?,?)");
			pstmt.setInt(1, num);
			for (int i = 1; i < dataset.length; i++) {
				pstmt.setString(i + 1, dataset[i]);
			}
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void update(Connection conn, String[] dataset) throws SQLException {
		PreparedStatement pstmt = null;
		String mfId = dataset[0];
		try {
			pstmt = conn.prepareStatement("update manufactures "
					+ "set f_num = ?, pl_num = ?,e_id = ?,p_num = ?, mf_count = ?, mf_date = ? where mf_num = ?");
			for (int i = 1; i < dataset.length; i++) {
				pstmt.setString(i, dataset[i]);
			}
			pstmt.setString(7, mfId);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 차트 데이터 조회
	// ==========================================================================================================
	public Map<String, Map<Integer, Integer>> getLineData(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<Integer, Integer> sw = new HashMap<Integer, Integer>();
		Map<Integer, Integer> yi = new HashMap<Integer, Integer>();
		Map<String, Map<Integer, Integer>> line = new HashMap<String, Map<Integer, Integer>>();
		try {
			pstmt = conn.prepareStatement("select DATE_FORMAT(mf_date,'%m') as month , sum(mf_count) as mf_sum from manufactures where f_num='sw_12345' group by month order by month desc limit 0,6");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					sw.put(rs.getInt("month"),rs.getInt("mf_sum"));
				} while (rs.next());
				line.put("sw_12345", sw);
			}
			pstmt = conn.prepareStatement("select DATE_FORMAT(mf_date,'%m') as month , sum(mf_count) as mf_sum from manufactures where f_num='yi_45678' group by month order by month desc limit 0,6");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					yi.put(rs.getInt("month"),rs.getInt("mf_sum"));
				} while (rs.next());
				line.put("yi_45678", yi);
			}
			return line;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Map<String, Map<String, Integer>> getBarData(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Integer> sw = new HashMap<String, Integer>();
		Map<String, Integer> yi = new HashMap<String, Integer>();
		Map<String, Map<String, Integer>> bar = new HashMap<String, Map<String, Integer>>();
		try {
			pstmt = conn.prepareStatement(
					"select sum(mf_count) from manufactures where f_num = 'sw_12345' and pl_num = 'p_1'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sw.put("p_1", rs.getInt(1));
			}
			pstmt = conn.prepareStatement(
					"select sum(mf_count) from manufactures where f_num = 'sw_12345' and pl_num = 'p_2'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sw.put("p_2", rs.getInt(1));
			}
			pstmt = conn.prepareStatement(
					"select sum(mf_count) from manufactures where f_num = 'sw_12345' and pl_num = 'p_3'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sw.put("p_3", rs.getInt(1));
			}
			bar.put("sw_12345", sw);
			
			pstmt = conn.prepareStatement(
					"select sum(mf_count) from manufactures where f_num = 'yi_45678' and pl_num = 'p_1'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				yi.put("p_1", rs.getInt(1));
			}
			pstmt = conn.prepareStatement(
					"select sum(mf_count) from manufactures where f_num = 'yi_45678' and pl_num = 'p_2'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				yi.put("p_2", rs.getInt(1));
			}
			pstmt = conn.prepareStatement(
					"select sum(mf_count) from manufactures where f_num = 'yi_45678' and pl_num = 'p_3'");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				yi.put("p_3", rs.getInt(1));
			}
			bar.put("yi_45678", yi);
			return bar;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Map<String, Integer> getPieData(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Integer> pie = new HashMap<String, Integer>(); 
		try {
			pstmt = conn.prepareStatement("select p_num, sum(mf_count) as mf_sum from manufactures where MONTH(mf_date) = 06 group by p_num order by mf_sum desc limit 0,6");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					pie.put(rs.getString("p_num"),rs.getInt("mf_sum"));
				} while (rs.next());
			}
			return pie;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public Map<String, Integer> getPolorData(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Integer> polor = new HashMap<String, Integer>(); 
		try {
			pstmt = conn.prepareStatement("select p_num, sum(mf_count) as mf_sum from manufactures group by p_num order by mf_sum desc limit 0,6");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					polor.put(rs.getString("p_num"),rs.getInt("mf_sum"));
				} while (rs.next());
			}
			return polor;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	// 단일항목 조회
	// 쿼리들===========================================================================================
	public List<String> selectENum(Connection conn) throws SQLException {
		List<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select e_id from employee where pl_num in ('p_1','p_2','p_3')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					list.add(rs.getString("e_id"));
				} while (rs.next());
			}
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public List<String> selectENum2(Connection conn) throws SQLException {
		List<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select e_id from employee where pl_num in ('s_1','s_2','s_3')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					list.add(rs.getString("e_id"));
				} while (rs.next());
			}
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<String> selectCNum(Connection conn) throws SQLException {
		List<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select c_id from customer");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					list.add(rs.getString("c_id"));
				} while (rs.next());
			}
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<String> selectFNum(Connection conn) throws SQLException {
		List<String> list = new ArrayList<String>();
		try (PreparedStatement pstmt = conn.prepareStatement("select f_num from factory")) {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					list.add(rs.getString("f_num"));
				} while (rs.next());
			}
			return list;
		}
	}
	
	public String selectFac(Connection conn, String id) throws SQLException {
		String list = "";
		try (PreparedStatement pstmt = conn.prepareStatement("select f_num from employee where e_id = ?")) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				list = rs.getString("f_num");
			}
			return list;
		}
	}
	public String selectPl(Connection conn, String id) throws SQLException {
		String list = "";
		try (PreparedStatement pstmt = conn.prepareStatement("select pl_num from employee where e_id = ?")) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				list = rs.getString("pl_num");
			}
			return list;
		}
	}

	public List<String> selectProNum(Connection conn) throws SQLException {
		List<String> list = new ArrayList<String>();
		try (PreparedStatement pstmt = conn.prepareStatement("select p_num from product")) {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					list.add(rs.getString("p_num"));
				} while (rs.next());
			}
			return list;
		}
	}

	public List<String> selectSaleNum(Connection conn) throws SQLException {
		List<String> list = new ArrayList<String>();
		try (PreparedStatement pstmt = conn.prepareStatement("select s_num from sales")) {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					list.add(rs.getString("s_num"));
				} while (rs.next());
			}
			return list;
		}
	}

	public List<String> selectManuNum(Connection conn) throws SQLException {
		List<String> list = new ArrayList<String>();
		try (PreparedStatement pstmt = conn.prepareStatement("select mf_num from manufactures")) {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					list.add(rs.getString("mf_num"));
				} while (rs.next());
			}
			return list;
		}
	}
}
