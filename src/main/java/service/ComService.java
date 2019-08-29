package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ChangePwDao;
import dao.ComDao;
import dao.ManufactureDao;
import dao.SaleDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import model.ChangePwRequest;
import model.Manufactures;
import model.Sales;

public class ComService {
	private ComDao dataDao = new ComDao();

	public String getFNum(String id) {
		String list = "";
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			list = dataDao.getFNum(conn, id);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public List getFMf(String id, int s, int end) {
		List list = new ArrayList();
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			list = dataDao.getFMf(conn, id, s, end);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int getFacMfCount(String fNum) {
		Connection conn = null;
		int num = 0;
		try {
			conn = ConnectionProvider.getConnection();
			num = dataDao.getMaxNum(conn, fNum);
			return num;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	
	// 차트 데이터 가져오기
		// ===================================================================
		public Map<String, Map<Integer, Integer>> getLineData(String fac) {
			Connection conn = null;
			Map<String, Map<Integer, Integer>> data = new HashMap<String, Map<Integer, Integer>>();
			try {
				conn = ConnectionProvider.getConnection();
				data = dataDao.getLineData(conn, fac);
			} catch (SQLException e) {
				e.printStackTrace();
				JdbcUtil.rollback(conn);
				throw new RuntimeException();
			} finally {
				JdbcUtil.close(conn);
			}
			return data;
		}
		public Map<String, Map<String, Integer>> getBarData(String fac) {
			Connection conn = null;
			Map<String, Map<String, Integer>> data = new HashMap<String, Map<String, Integer>>();
			try {
				conn = ConnectionProvider.getConnection();
				data = dataDao.getBarData(conn, fac);
			} catch (SQLException e) {
				e.printStackTrace();
				JdbcUtil.rollback(conn);
				throw new RuntimeException();
			} finally {
				JdbcUtil.close(conn);
			}
			return data;
		}
		
		public Map<String, Integer> getPieData(String fac) {
			Connection conn = null;
			Map<String,Integer> data = new HashMap<String, Integer>();
			try {
				conn = ConnectionProvider.getConnection();
				data = dataDao.getPieData(conn, fac);
			} catch (SQLException e) {
				e.printStackTrace();
				JdbcUtil.rollback(conn);
				throw new RuntimeException();
			} finally {
				JdbcUtil.close(conn);
			}
			
			return data;
		}
		public Map<String, Integer> getPolorData(String fac) {
			Connection conn = null;
			Map<String,Integer> data = new HashMap<String, Integer>();
			try {
				conn = ConnectionProvider.getConnection();
				data = dataDao.getPolorData(conn, fac);
			} catch (SQLException e) {
				e.printStackTrace();
				JdbcUtil.rollback(conn);
				throw new RuntimeException();
			} finally {
				JdbcUtil.close(conn);
			}
			return data;
		}
		
		
		public List<Sales> selectSaleData(String fNum) {
			Connection conn = null;
			List<Sales> list = new ArrayList<Sales>();
			try {
				conn = ConnectionProvider.getConnection();
				list = dataDao.selectSaleData(conn, fNum);
				return list;
			} catch (SQLException e) {
				JdbcUtil.rollback(conn);
				throw new RuntimeException();
			} finally {
				JdbcUtil.close(conn);
			}
		}
}
