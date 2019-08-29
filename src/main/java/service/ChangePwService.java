package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ChangePwDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import model.ChangePwRequest;

public class ChangePwService {
	private ChangePwDao pwDao = new ChangePwDao();
	
	public void updatePw(ChangePwRequest cpReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			pwDao.updatePw(conn, cpReq);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
