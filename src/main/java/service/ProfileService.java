package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ChangePwDao;
import dao.ProfileDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import model.ChangePwRequest;
import model.Message;

public class ProfileService {
	private ProfileDao pfDao = new ProfileDao();

	public List getWorks(String id, String pl) {
		Connection conn = null;
		List list = new ArrayList();
		try {
			conn = ConnectionProvider.getConnection();
			list = pfDao.getWorks(conn, id, pl);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int getMaxSale() {
		Connection conn = null;
		int ms = 0;
		try {
			conn = ConnectionProvider.getConnection();
			ms = pfDao.getMaxSale(conn);
			return ms;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int getMaxMf() {
		Connection conn = null;
		int ms = 0;
		try {
			conn = ConnectionProvider.getConnection();
			ms = pfDao.getMaxMf(conn);
			return ms;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List<Message> getMessage(String id) {
		Connection conn = null;
		List<Message> list = new ArrayList<Message>();
		try {
			conn = ConnectionProvider.getConnection();
			list = pfDao.getMessage(conn, id);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	public List<Message> getMessages(String id) {
		Connection conn = null;
		List<Message> list = new ArrayList<Message>();
		try {
			conn = ConnectionProvider.getConnection();
			list = pfDao.getMessages(conn, id);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public Message getMsDetail(String num) {
		Connection conn = null;
		Message list = new Message();
		try {
			conn = ConnectionProvider.getConnection();
			list = pfDao.getMsDetail(conn, num);
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
			num = pfDao.getMaxNum(conn);
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
			num = pfDao.getMinNum(conn);
			return num;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int preNotice(String n_num) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int pre = pfDao.preMs(conn, n_num);
			return pre;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int nextNotice(String n_num) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int next = pfDao.nextMs(conn, n_num);
			return next;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List<String> getUsers() {
		Connection conn = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = ConnectionProvider.getConnection();
			list = pfDao.getUsers(conn);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public int sendMessage(String id, String[] receivers, String title, String content) {
		Connection conn = null;
		int stat = 0;
		try {
			conn = ConnectionProvider.getConnection();
			stat = pfDao.sendMessage(conn, id, receivers, title, content);
			return stat;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
