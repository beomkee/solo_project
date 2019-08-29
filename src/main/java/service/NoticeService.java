package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.NoticeDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import model.Notice;

public class NoticeService {

	NoticeDao noticeDao = new NoticeDao();
	
	public List<Notice> getNoticeList(int startRow,int pageSize) {
		Connection conn = null;
		List<Notice> list = new ArrayList<Notice>();
		try {
			conn = ConnectionProvider.getConnection();
			list = noticeDao.getNoticeList(conn, startRow, pageSize);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public int getMaxNum() {
		Connection conn = null;
		int num = 0;
		try {
			conn = ConnectionProvider.getConnection();
			num = noticeDao.getMaxNum(conn);
			return num;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	public int getMinNum() {
		Connection conn = null;
		int num = 0;
		try {
			conn = ConnectionProvider.getConnection();
			num = noticeDao.getMinNum(conn);
			return num;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public int getNoticeCount() {
		Connection conn = null;
		int num = 0;
		try {
			conn = ConnectionProvider.getConnection();
			num = noticeDao.getMaxNum(conn);
			return num;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public Notice getContent(int nNum) {
		Connection conn = null;
		Notice notice = new Notice();
		try {
			conn = ConnectionProvider.getConnection();
			notice = noticeDao.getContent(conn, nNum);
			return notice;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public void writeNotice(String writer, String title, String content) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			noticeDao.writeNotice(conn, writer, title, content);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public void updateNotice(String n_num, String title, String content) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			noticeDao.updateNotice(conn, n_num, title, content);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public void deleteNotice(String n_num) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			noticeDao.deleteNotice(conn, n_num);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public int preNotice(int n_num) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int pre = noticeDao.preNotice(conn, n_num);
			return pre;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public int nextNotice(int n_num) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int next = noticeDao.nextNotice(conn, n_num);
			return next;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
