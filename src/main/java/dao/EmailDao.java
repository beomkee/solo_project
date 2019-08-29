package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import model.SendEmail;

public class EmailDao {

	public List<String> getEmails(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		String email = "";
		try {
			pstmt = conn.prepareStatement("SELECT e_email FROM employee");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					email = rs.getString("e_email");
					list.add(email);
				} while (rs.next());
			}
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void insertSendEmail(Connection conn, String to, String title, String content, String writer)
			throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into sendemail values(0, ?, ?, ?, now(), ?)");
			pstmt.setString(1, writer);
			pstmt.setString(2, to);
			pstmt.setString(3, content);
			pstmt.setString(4, title);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public List<SendEmail> sendEmailList(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SendEmail> list = new ArrayList<SendEmail>();
		SendEmail sendEmail = null;
		try {
			pstmt = conn.prepareStatement("select * from sendemail where writer = ? order by num desc");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					sendEmail = new SendEmail();
					sendEmail.setNum(rs.getInt("num"));
					sendEmail.setWriter(rs.getString("writer"));
					sendEmail.setTitle(rs.getString("title"));
					sendEmail.setTo(rs.getString("to"));
					sendEmail.setContent(rs.getString("content"));
					sendEmail.setReg_date(rs.getDate("reg_date"));
					list.add(sendEmail);
				} while (rs.next());
			}
			return list;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<SendEmail> getEmails(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SendEmail> list = new ArrayList<SendEmail>();
		SendEmail sendEmail = null;
		try {
			pstmt = conn.prepareStatement("select * from sendemail where writer = ? order by num desc limit 0,5");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					sendEmail = new SendEmail();
					sendEmail.setNum(rs.getInt("num"));
					sendEmail.setWriter(rs.getString("writer"));
					sendEmail.setTitle(rs.getString("title"));
					sendEmail.setTo(rs.getString("to"));
					sendEmail.setContent(rs.getString("content"));
					sendEmail.setReg_date(rs.getDate("reg_date"));
					list.add(sendEmail);
				} while (rs.next());
			}
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public SendEmail emailContent(Connection conn, String num) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SendEmail email = new SendEmail();
		try {
			pstmt = conn.prepareStatement("select * from sendemail where num = ?");
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				email.setNum(rs.getInt("num"));
				email.setWriter(rs.getString("writer"));
				email.setTitle(rs.getString("title"));
				email.setTo(rs.getString("to"));
				email.setContent(rs.getString("content"));
				email.setReg_date(rs.getDate("reg_date"));
			}
			return email;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int getMaxNum(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			pstmt = conn.prepareStatement("select max(num) from sendemail");
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
			pstmt = conn.prepareStatement("select min(num) from sendemail");
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
	
	public int preEmail(Connection conn, String n_num) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pre = 0;
		try {
			pstmt = conn.prepareStatement("select num from sendemail where num = (select max(num) from sendemail where num < ?)");
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
	public int nextEmail(Connection conn, String n_num) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int next = 0;
		try {
			pstmt = conn.prepareStatement("select num from sendemail where num = (select min(num) from sendemail where num > ?)"); 
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
}
