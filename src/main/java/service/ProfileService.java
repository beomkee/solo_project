package service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Manufactures;
import model.Sales;
import mybatis.MybatisConnector;

@Service
public class ProfileService {

	private final String namespace = "mybatis.Profile";

	@Autowired
	public MybatisConnector mybatisConnentor;

	public int updatePw(String id, String passwd, String tel) throws Exception {
		SqlSession sqlSession = mybatisConnentor.sqlSession();
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("passwd", passwd);
		map.put("tel", tel);
		try {
			return sqlSession.update(namespace + ".updatePw", map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}

	public int getMaxSale() {
		SqlSession sqlSession = mybatisConnentor.sqlSession();
		try {
			return sqlSession.selectOne(namespace + ".getMaxSale");
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}

	public int getMaxMf() {
		SqlSession sqlSession = mybatisConnentor.sqlSession();
		try {
			return sqlSession.selectOne(namespace + ".getMaxMf");
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}

	public List getWorks(String id, String pl) {
		SqlSession sqlSession = mybatisConnentor.sqlSession();
		HashMap map = new HashMap();
		map.put("id", id);
		try {
			if (pl.equals("mf")) {
				return sqlSession.selectList(namespace + ".mfList", map);
			} else {
				return sqlSession.selectList(namespace + ".saleList", map);
			}
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}
	
	public void insertWork(String id, String pl, Manufactures manufactures, Sales sales) {
		SqlSession sqlSession = mybatisConnentor.sqlSession();
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("manufactures", manufactures);
		map.put("sales", sales);
		try {
			if (pl.equals("mf")) {
				sqlSession.insert(namespace + ".mfInsert", map);
			} else {
				sqlSession.insert(namespace + ".saleInsert", map);
			}
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}

//	private ProfileDao pfDao = new ProfileDao();
//
//	public List getWorks(String id, String pl) {
//		Connection conn = null;
//		List list = new ArrayList();
//		try {
//			conn = ConnectionProvider.getConnection();
//			list = pfDao.getWorks(conn, id, pl);
//			return list;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//
//	public int getMaxSale() {
//		Connection conn = null;
//		int ms = 0;
//		try {
//			conn = ConnectionProvider.getConnection();
//			ms = pfDao.getMaxSale(conn);
//			return ms;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//
//	public int getMaxMf() {
//		Connection conn = null;
//		int ms = 0;
//		try {
//			conn = ConnectionProvider.getConnection();
//			ms = pfDao.getMaxMf(conn);
//			return ms;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//
//	public List<Message> getMessage(String id) {
//		Connection conn = null;
//		List<Message> list = new ArrayList<Message>();
//		try {
//			conn = ConnectionProvider.getConnection();
//			list = pfDao.getMessage(conn, id);
//			return list;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//	public List<Message> getMessages(String id) {
//		Connection conn = null;
//		List<Message> list = new ArrayList<Message>();
//		try {
//			conn = ConnectionProvider.getConnection();
//			list = pfDao.getMessages(conn, id);
//			return list;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//
//	public Message getMsDetail(String num) {
//		Connection conn = null;
//		Message list = new Message();
//		try {
//			conn = ConnectionProvider.getConnection();
//			list = pfDao.getMsDetail(conn, num);
//			return list;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//
//	public int getMaxNum() {
//		Connection conn = null;
//		int num = 0;
//		try {
//			conn = ConnectionProvider.getConnection();
//			num = pfDao.getMaxNum(conn);
//			return num;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//
//	public int getMinNum() {
//		Connection conn = null;
//		int num = 0;
//		try {
//			conn = ConnectionProvider.getConnection();
//			num = pfDao.getMinNum(conn);
//			return num;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//
//	public int preNotice(String n_num) {
//		Connection conn = null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			int pre = pfDao.preMs(conn, n_num);
//			return pre;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//
//	public int nextNotice(String n_num) {
//		Connection conn = null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			int next = pfDao.nextMs(conn, n_num);
//			return next;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//
//	public List<String> getUsers() {
//		Connection conn = null;
//		List<String> list = new ArrayList<String>();
//		try {
//			conn = ConnectionProvider.getConnection();
//			list = pfDao.getUsers(conn);
//			return list;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
//	
//	public int sendMessage(String id, String[] receivers, String title, String content) {
//		Connection conn = null;
//		int stat = 0;
//		try {
//			conn = ConnectionProvider.getConnection();
//			stat = pfDao.sendMessage(conn, id, receivers, title, content);
//			return stat;
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException();
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}
}
