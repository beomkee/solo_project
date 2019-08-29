package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import model.Notice;

public class NoticeDao {

	public List<Notice> getNoticeList(Connection conn, int startRow, int pageSize) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList<Notice>();
		Notice notice = null;
		try {
			pstmt = conn.prepareStatement("select * from notice_board order by n_num desc limit ?,?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					notice = new Notice();
					notice.setN_num(rs.getInt("n_num"));
					notice.setWriter(rs.getString("writer"));
					notice.setTitle(rs.getString("title"));
					notice.setContent(rs.getString("content"));
					notice.setReg_date(rs.getDate("reg_date"));
					notice.setMod_date(rs.getDate("mod_date"));
					list.add(notice);
				} while (rs.next());
			}
			return list;
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
			pstmt = conn.prepareStatement("select max(n_num) from notice_board");
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
			pstmt = conn.prepareStatement("select min(n_num) from notice_board");
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
	
	public int getNoticeCount(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			pstmt = conn.prepareStatement("select ifnull(count(*),0) from notice_board");
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
	
	public Notice getContent(Connection conn, int nNum) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = new Notice();
		try {
			pstmt = conn.prepareStatement("select * from notice_board where n_num = ?");
			pstmt.setInt(1, nNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
					notice.setN_num(rs.getInt("n_num"));
					notice.setWriter(rs.getString("writer"));
					notice.setTitle(rs.getString("title"));
					notice.setContent(rs.getString("content"));
					notice.setReg_date(rs.getDate("reg_date"));
					notice.setMod_date(rs.getDate("mod_date"));
			}
			return notice;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void writeNotice(Connection conn, String writer, String title, String content) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into notice_board(writer, title, content, reg_date, mod_date) values(?, ?, ?, now(), now())");
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public void updateNotice(Connection conn, String n_num, String title, String content) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update notice_board set title = ? , content = ?, mod_date = now() where n_num = ?");
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, n_num);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public void deleteNotice(Connection conn, String n_num) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete from notice_board where n_num = ?");
			pstmt.setString(1, n_num);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int preNotice(Connection conn, int n_num) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pre = 0;
		try {
			pstmt = conn.prepareStatement("select n_num from notice_board where n_num = (select max(n_num) from notice_board where n_num < ?)");
			pstmt.setInt(1, n_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pre = rs.getInt(1);
			}
			return pre;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	public int nextNotice(Connection conn, int n_num) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int next = 0;
		try {
			pstmt = conn.prepareStatement("select n_num from notice_board where n_num = (select min(n_num) from notice_board where n_num > ?)"); 
			pstmt.setInt(1, n_num);
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
