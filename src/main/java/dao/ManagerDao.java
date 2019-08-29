package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import model.ChangePwRequest;
import model.LoginUser;
import model.Manufactures;
import model.Message;
import model.Sales;

public class ManagerDao {

	public int idCheck(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ms = 0;
		try {
			pstmt = conn.prepareStatement("select count(e_id) from employee where e_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ms = rs.getInt(1);
			}
			return ms;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void insertEmp(Connection conn,String id, String passwd, String name, String tel, String birth, String manager_num,String f_num, String pl_num, String email) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, birth);
			pstmt.setString(6, manager_num);
			pstmt.setString(7, f_num);
			pstmt.setString(8, pl_num);
			pstmt.setString(9, email);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<LoginUser> selectEmpData(Connection conn, String f_num) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LoginUser> list = new ArrayList<LoginUser>();
		LoginUser user = null;
		try {
			pstmt = conn.prepareStatement("select * from employee where f_num = ?");
			pstmt.setString(1, f_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					user = new LoginUser();
					user.setId(rs.getString("e_id"));
					user.setPasswd(rs.getString("e_passwd"));
					user.setName(rs.getString("e_name"));
					user.setTel(rs.getString("e_tel"));
					user.setBirth(rs.getString("e_birth"));
					user.setManager_num(rs.getString("manager_num"));
					user.setF_num(rs.getString("f_num"));
					user.setPl_num(rs.getString("pl_num"));
					user.setEmail(rs.getString("e_email"));
					list.add(user);
				} while (rs.next());
			}
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
}
