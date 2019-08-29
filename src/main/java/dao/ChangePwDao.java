package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.ChangePwRequest;

public class ChangePwDao {
	public void updatePw(Connection conn, ChangePwRequest cpReq) throws SQLException {
		String query = "";
		if (cpReq.getTel() == null) {
			query = "update customer set c_passwd = ? where c_id = ?";
		} else {
			query = "update employee set e_passwd = ?, e_tel = ? where e_id = ?";
		}
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, cpReq.getPasswd());
			if (cpReq.getTel() != null) {
				pstmt.setString(2, cpReq.getTel());
				pstmt.setString(3, cpReq.getId());
			}else {
				pstmt.setString(2, cpReq.getId());
			}
			pstmt.executeUpdate();
		}
	}
}
