package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ChangePwDao;
import dao.ManufactureDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import model.ChangePwRequest;
import model.Manufactures;
import mybatis.MybatisConnector;

@Service
public class ManufacturesService {
	
	private final String namespace = "mybatis.Manufacture";
	
	@Autowired
	public MybatisConnector mybatisConnentor;
	
	public List<String> proNums() {
		SqlSession sqlSession = mybatisConnentor.sqlSession();
		try {
			return sqlSession.selectList(namespace + ".proNums");
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}
	public String facNum(String id) {
		SqlSession sqlSession = mybatisConnentor.sqlSession();
		HashMap map = new HashMap();
		map.put("id", id);
		try {
			return sqlSession.selectOne(namespace + ".facNum", map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}
	public String plNum(String id) {
		SqlSession sqlSession = mybatisConnentor.sqlSession();
		HashMap map = new HashMap();
		map.put("id", id);
		try {
			return sqlSession.selectOne(namespace + ".plNum", map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}
	
	
	
	
	
	
//	private ManufactureDao dataDao = new ManufactureDao();
//
//	public List<Manufactures> selectData() {
//		List<Manufactures> list = new ArrayList<Manufactures>();
//		Connection conn = null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			list = dataDao.selectManufactures(conn);
//			return list;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//
//	public List<Manufactures> searchData(String[] dataset) {
//		List<Manufactures> list = new ArrayList<Manufactures>();
//		Connection conn = null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			list = dataDao.searchMf(conn, dataset);
//			return list;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//
//	public List<Manufactures> insertUpdate(String[] dataset) {
//		Connection conn = null;
//		List<Manufactures> list = new ArrayList<Manufactures>();
//		try {
//			conn = ConnectionProvider.getConnection();
//			boolean isExist = dataDao.isExist(conn, dataset[0]);
//			if (isExist) {
//				dataDao.update(conn, dataset);
//			} else {
//				int num = 0;
//				num = dataDao.maxNum(conn, dataset[0]);
//				dataDao.insert(conn, dataset, num);
//			}
//			list = dataDao.selectManufactures(conn);
//			return list;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//
//	public List<Manufactures> delete(String num) {
//		Connection conn = null;
//		List<Manufactures> list = new ArrayList<Manufactures>();
//		try {
//			conn = ConnectionProvider.getConnection();
//			dataDao.delete(conn, num);
//			list = dataDao.selectManufactures(conn);
//			return list;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//
//	}
//
//	// 차트 데이터 가져오기
//	// ===================================================================
//	public Map<String, Map<Integer, Integer>> getLineData() {
//		Connection conn = null;
//		Map<String, Map<Integer, Integer>> data = new HashMap<String, Map<Integer, Integer>>();
//		try {
//			conn = ConnectionProvider.getConnection();
//			data = dataDao.getLineData(conn);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		return data;
//	}
//	public Map<String, Map<String, Integer>> getBarData() {
//		Connection conn = null;
//		Map<String, Map<String, Integer>> data = new HashMap<String, Map<String, Integer>>();
//		try {
//			conn = ConnectionProvider.getConnection();
//			data = dataDao.getBarData(conn);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		return data;
//	}
//	
//	public Map<String, Integer> getPieData() {
//		Connection conn = null;
//		Map<String,Integer> data = new HashMap<String, Integer>();
//		try {
//			conn = ConnectionProvider.getConnection();
//			data = dataDao.getPieData(conn);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		
//		return data;
//	}
//	public Map<String, Integer> getPolorData() {
//		Connection conn = null;
//		Map<String,Integer> data = new HashMap<String, Integer>();
//		try {
//			conn = ConnectionProvider.getConnection();
//			data = dataDao.getPolorData(conn);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		return data;
//	}
//
//	public List<String> proNums() {
//		Connection conn = null;
//		List<String> list = null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			list = dataDao.selectProNum(conn);
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		return list;
//	}
//
//	public List<String> empNums() {
//		Connection conn = null;
//		List<String> list = null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			list = dataDao.selectENum(conn);
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		return list;
//	}
//	
//	public List<String> empNums2() {
//		Connection conn = null;
//		List<String> list = null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			list = dataDao.selectENum2(conn);
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		return list;
//	}
//
//	public List<String> facNums() {
//		Connection conn = null;
//		List<String> list = null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			list = dataDao.selectFNum(conn);
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		return list;
//	}
//	public List<String> selectManuNum() {
//		Connection conn = null;
//		List<String> list = null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			list = dataDao.selectManuNum(conn);
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		return list;
//	}
//	public String facNum(String id) {
//		Connection conn = null;
//		String fac = null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			fac = dataDao.selectFac(conn, id);
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		return fac;
//	}
//	public String plNum(String id) {
//		Connection conn = null;
//		String fac = null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			fac = dataDao.selectPl(conn, id);
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//		return fac;
//	}
}
