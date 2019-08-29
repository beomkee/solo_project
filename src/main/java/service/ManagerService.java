package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MainDao;
import dao.ManagerDao;
import dao.NoticeDao;
import dao.UserDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import model.Factory;
import model.LoginUser;
import model.Notice;
import model.SendEmail;

public class ManagerService {

	ManagerDao manaDao = new ManagerDao();

	public int idCheck(String id) {
		Connection conn = null;
		int num = 0;
		try {
			conn = ConnectionProvider.getConnection();
			num = manaDao.idCheck(conn, id);
			return num;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public void insertEmp(String id, String passwd, String name, String tel, String birth, String manager_num,String f_num, String pl_num, String email) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			manaDao.insertEmp(conn, id, passwd, name, tel, birth, manager_num, f_num, pl_num, email);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	public List<LoginUser> selectEmpData(String f_num) {
		Connection conn = null;
		List<LoginUser> list = new ArrayList<LoginUser>();
		try {
			conn = ConnectionProvider.getConnection();
			list = manaDao.selectEmpData(conn, f_num);
			return list;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
