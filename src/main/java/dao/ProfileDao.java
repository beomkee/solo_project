package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import model.ChangePwRequest;
import model.Manufactures;
import model.Message;
import model.Sales;

public class ProfileDao {

	public List getWorks(Connection conn, String id, String pl) throws SQLException {
		List list = new ArrayList();
		Manufactures manufactures = null;
		Sales sale = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (pl.equals("mf")) {
				pstmt = conn.prepareStatement("select * from manufactures where e_id = ? order by mf_num desc");
				pstmt.setString(1, id);
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
			} else {
				pstmt = conn.prepareStatement("select * from sales where e_id = ? order by s_obtain_date desc");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						sale = new Sales();
						sale.setS_num(rs.getString("s_num"));
						sale.setMf_num(rs.getString("mf_num"));
						sale.setE_id(rs.getString("e_id"));
						sale.setC_id(rs.getString("c_id"));
						sale.setP_num(rs.getString("p_num"));
						sale.setS_obtain_date(rs.getString("s_obtain_date"));
						sale.setS_contract_sum(rs.getString("s_contract_sum"));
						sale.setS_complete_date(rs.getString("s_complete_date"));
						list.add(sale);
					} while (rs.next());
				}
			}
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int getMaxSale(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ms = 0;
		try {
			pstmt = conn.prepareStatement("select max(s_num) from sales");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ms = rs.getInt(1);
			}
			return ms;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int getMaxMf(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ms = 0;
		try {
			pstmt = conn.prepareStatement("select max(mf_num) from manufactures");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ms = rs.getInt(1);
			}
			return ms;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<Message> getMessage(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Message> list = new ArrayList<Message>();
		Message ms = null;
		try {
			pstmt = conn.prepareStatement("select * from message where receiver = ? order by num desc");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					ms = new Message();
					ms.setNum(rs.getInt("num"));
					ms.setTitle(rs.getString("title"));
					ms.setContent(rs.getString("content"));
					ms.setTo(rs.getString("receiver"));
					ms.setFrom(rs.getString("from"));
					ms.setReg_date(rs.getDate("reg_date"));
					ms.setReadcount(rs.getInt("readcount"));
					list.add(ms);
				} while (rs.next());
			}
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<Message> getMessages(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Message> list = new ArrayList<Message>();
		Message ms = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from message where receiver = ? and readcount = 0 order by num desc limit 0,5");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					ms = new Message();
					ms.setNum(rs.getInt("num"));
					ms.setTitle(rs.getString("title"));
					ms.setContent(rs.getString("content"));
					ms.setTo(rs.getString("receiver"));
					ms.setFrom(rs.getString("from"));
					ms.setReg_date(rs.getDate("reg_date"));
					ms.setReadcount(rs.getInt("readcount"));
					list.add(ms);
				} while (rs.next());
			}
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Message getMsDetail(Connection conn, String num) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Message ms = null;
		try {
			pstmt = conn.prepareStatement("update message set readcount = readcount + 1 where num = ?");
			pstmt.setString(1, num);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("select * from message where num = ?");
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ms = new Message();
				ms.setNum(rs.getInt("num"));
				ms.setTitle(rs.getString("title"));
				ms.setContent(rs.getString("content"));
				ms.setTo(rs.getString("receiver"));
				ms.setFrom(rs.getString("from"));
				ms.setReg_date(rs.getDate("reg_date"));
				ms.setReadcount(rs.getInt("readcount"));
			}
			return ms;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int getMaxNum(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			pstmt = conn.prepareStatement("select max(num) from message");
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

	public int getMinNum(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			pstmt = conn.prepareStatement("select min(num) from message");
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

	public int preMs(Connection conn, String n_num) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pre = 0;
		try {
			pstmt = conn.prepareStatement(
					"select num from message where num = (select max(num) from message where num < ?)");
			pstmt.setString(1, n_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pre = rs.getInt(1);
			}
			return pre;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public int nextMs(Connection conn, String n_num) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int next = 0;
		try {
			pstmt = conn.prepareStatement(
					"select num from message where num = (select min(num) from message where num > ?)");
			pstmt.setString(1, n_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				next = rs.getInt(1);
			}
			return next;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public List<String> getUsers(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			pstmt = conn.prepareStatement("select e_id from employee");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					list.add(rs.getString("e_id"));
				} while (rs.next());
			}
			return list;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int sendMessage(Connection conn, String id, String[] receivers, String title, String content) throws SQLException {
		PreparedStatement pstmt = null;
		int stat = 0;
		try {
			for (String receiver : receivers) {
				pstmt = conn.prepareStatement("insert into message values(0, ?, ?, ?, ?, now(), 0, 0, 0)");
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				pstmt.setString(3, id);
				pstmt.setString(4, receiver);
				stat = pstmt.executeUpdate();
			}
			return stat;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
