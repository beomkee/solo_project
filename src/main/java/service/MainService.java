package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MainDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import model.Factory;
import model.LeftMenu;
import model.LoginUser;
import model.Notice;
import mybatis.MybatisConnector;

@Service
public class MainService {

	private final String namespace = "mybatis.Login";
	private final String namespace2 = "mybatis.Left";

	@Autowired
	public MybatisConnector mybatisConnentor;

	public int userCheck(LoginUser loginUser) throws Exception {
		SqlSession sqlSession = mybatisConnentor.sqlSession();
		HashMap map = new HashMap();
		map.put("id", loginUser.getId());
		map.put("passwd", loginUser.getPasswd());
		int x = -1;
		try {
			x = sqlSession.selectOne(namespace + ".userCheck", map);
			if (x != 1) {
				x = sqlSession.selectOne(namespace + ".idCheck", map);
				if (x == 1) {
					x = 3;
				} else {
					x = 2;
				}
			}
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return x;
	}
	
	public LoginUser getUser(LoginUser loginUser) throws Exception {
		SqlSession sqlSession = mybatisConnentor.sqlSession();
		HashMap map = new HashMap();
		map.put("id", loginUser.getId());
		LoginUser x = new LoginUser();
		try {
			x = sqlSession.selectOne(namespace + ".getUser", map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return x;
	}
	
	public List<LeftMenu> getLeftMenus(String div) throws Exception {
		SqlSession sqlSession = mybatisConnentor.sqlSession();
		HashMap map = new HashMap();
		map.put("div", div);
		try {
			return sqlSession.selectList(namespace2 + ".getLeftMenus", map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}

//	public List getAllNodeDiv2() throws Exception {
//		SqlSession sqlSession = mybatisConnentor.sqlSession();
//		try {
//			return sqlSession.selectList(namespace + ".getAllNodeDiv2");
//		} finally {
//			sqlSession.close();
//		}
//	}
//==========================================================================================================================	
	MainDao mainDao = new MainDao();

	public List<Notice> getNotice() {
		Connection conn = null;
		List<Notice> list = new ArrayList<Notice>();
		try {
			conn = ConnectionProvider.getConnection();
			list = mainDao.getNotice(conn);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List getWorks(String id) {
		Connection conn = null;
		List list = new ArrayList();
		try {
			conn = ConnectionProvider.getConnection();
			list = mainDao.getWorks(conn, id);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public String getPl(String id) {
		Connection conn = null;
		String pl = "";
		try {
			conn = ConnectionProvider.getConnection();
			pl = mainDao.getPl(conn, id);
			return pl;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int lastNotice() {
		Connection conn = null;
		int num = 0;
		try {
			conn = ConnectionProvider.getConnection();
			num = mainDao.lastNotice(conn);
			return num;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int lastWork(String id) {
		Connection conn = null;
		int num = 0;
		try {
			conn = ConnectionProvider.getConnection();
			num = mainDao.lastWork(conn, id);
			return num;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int lastEmail(String id) {
		Connection conn = null;
		int num = 0;
		try {
			conn = ConnectionProvider.getConnection();
			num = mainDao.lastEmail(conn, id);
			return num;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int unReadMs(String id) {
		Connection conn = null;
		int num = 0;
		try {
			conn = ConnectionProvider.getConnection();
			num = mainDao.unReadMs(conn, id);
			return num;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List<LoginUser> getMainEmp(String id) {
		Connection conn = null;
		List<LoginUser> list = new ArrayList<LoginUser>();
		try {
			conn = ConnectionProvider.getConnection();
			list = mainDao.getMainEmp(conn, id);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public int getMyEmps(String id) {
		Connection conn = null;
		int num = 0;
		try {
			conn = ConnectionProvider.getConnection();
			num = mainDao.getMyEmps(conn, id);
			return num;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public Factory getFac(String id) {
		Connection conn = null;
		Factory fac = new Factory();
		int num = 0;
		try {
			conn = ConnectionProvider.getConnection();
			fac = mainDao.getFac(conn, id);
			return fac;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
