package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ChangePwDao;
import dao.ManufactureDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import model.ChangePwRequest;
import model.Manufactures;

public class DrawGridService {
	private ManufactureDao dataDao = new ManufactureDao();

	public List<String> empNums() {
		Connection conn = null;
		List<String> list = null;
		try {
			conn = ConnectionProvider.getConnection();
			list = dataDao.selectENum(conn);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		return list;
	}

	public List<String> cusNums() {
		Connection conn = null;
		List<String> list = null;
		try {
			conn = ConnectionProvider.getConnection();
			list = dataDao.selectCNum(conn);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		return list;
	}

	public List<String> facNums() {
		Connection conn = null;
		List<String> list = null;
		try {
			conn = ConnectionProvider.getConnection();
			list = dataDao.selectFNum(conn);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		return list;
	}

	public List<String> proNums() {
		Connection conn = null;
		List<String> list = null;
		try {
			conn = ConnectionProvider.getConnection();
			list = dataDao.selectProNum(conn);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		return list;
	}

	public List<String> saleNums() {
		Connection conn = null;
		List<String> list = null;
		try {
			conn = ConnectionProvider.getConnection();
			list = dataDao.selectSaleNum(conn);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		return list;
	}

	public List<String> manuNums() {
		Connection conn = null;
		List<String> list = null;
		try {
			conn = ConnectionProvider.getConnection();
			list = dataDao.selectManuNum(conn);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		return list;
	}
}
