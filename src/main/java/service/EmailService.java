package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EmailDao;
import dao.MainDao;
import dao.NoticeDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import model.Notice;
import model.SendEmail;

public class EmailService {

	EmailDao mainDao = new EmailDao();

	public void insertSendEmail(String to, String title, String content, String writer) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			mainDao.insertSendEmail(conn, to, title, content, writer);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List<String> getEmailList() {
		Connection conn = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = ConnectionProvider.getConnection();
			list = mainDao.getEmails(conn);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public List<SendEmail> sendEmailList(String id) {
		Connection conn = null;
		List<SendEmail> list = new ArrayList<SendEmail>();
		try {
			conn = ConnectionProvider.getConnection();
			list = mainDao.sendEmailList(conn, id);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public List<SendEmail> getEmails(String id) {
		Connection conn = null;
		List<SendEmail> list = new ArrayList<SendEmail>();
		try {
			conn = ConnectionProvider.getConnection();
			list = mainDao.getEmails(conn, id);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public SendEmail emailContent(String num) {
		Connection conn = null;
		SendEmail email = new SendEmail();
		try {
			conn = ConnectionProvider.getConnection();
			email = mainDao.emailContent(conn, num);
			return email;
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
			num = mainDao.getMaxNum(conn);
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
			num = mainDao.getMinNum(conn);
			return num;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public int preEmail(String n_num) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int pre = mainDao.preEmail(conn, n_num);
			return pre;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public int nextEmail(String n_num) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int next = mainDao.nextEmail(conn, n_num);
			return next;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
