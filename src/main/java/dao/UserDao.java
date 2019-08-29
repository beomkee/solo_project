package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.LoginUser;
import model.Sales;

public class UserDao {

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	private UserDao() {
	}

	private Connection getConnection() throws Exception {
		Connection conn = null;
		String jdbcUrl = "jdbc:mysql://localhost:3306/projectdb";
		String dbId = "scott";
		String dbPasswd = "1111";
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPasswd);
		return conn;
	}

	public int userCheck(String id, String passwd) {
		Connection conn = null;
		Statement stmtE = null;
		Statement stmtC = null;
		ResultSet rsE = null;
		ResultSet rsC = null;
		String answer = null;
		if (id.equals("admin")) {
			if (passwd.equals("1")) {
				return 1;
			} else {
				return 0;
			}
		} else {
			try {
				conn = getConnection();
				String queryE = "select * from employee where e_id =" + id;
				String queryC = "select * from customer where c_id =" + id;
				stmtE = conn.createStatement();
				stmtC = conn.createStatement();
				rsE = stmtE.executeQuery(queryE);
				rsC = stmtC.executeQuery(queryC);
				if (rsE.next()) {
					answer = (String) rsE.getString("e_passwd");
					if (passwd.equals(answer)) {
						return 1;
					} else {
						return 0;
					}
				} else if (rsC.next()) {
					answer = (String) rsC.getString("c_passwd");
					if (passwd.equals(answer)) {
						return 1;
					} else {
						return 0;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (stmtE != null || stmtC != null) {
					try {
						stmtE.close();
						stmtC.close();
					} catch (SQLException e2) {
					}
				}
				if (rsE != null || rsC != null) {
					try {
						rsE.close();
						rsC.close();
					} catch (SQLException e2) {
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e2) {
					}
				}
			}
		}
		return 2;
	}

	public LoginUser getUserInfo(String id) {
		LoginUser member = new LoginUser();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int div = -1;
		try {
			conn = getConnection();
			String query1 = "select * from employee where e_id = ?";
			String query2 = "select * from customer where c_id = ?";
			if (id.charAt(0) == '3') {
				stmt = conn.prepareStatement(query2);
				div = 3;
			} else {
				stmt = conn.prepareStatement(query1);
			}
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (div == 3) {
				if (rs.next()) {
					member.setId(rs.getString("c_id"));
					member.setPasswd(rs.getString("c_passwd"));
					member.setName(rs.getString("c_name"));
					member.setContract_date(rs.getString("c_contract_date"));
				}
			} else {
				if (rs.next()) {
					member.setId(rs.getString("e_id"));
					member.setPasswd(rs.getString("e_passwd"));
					member.setName(rs.getString("e_name"));
					member.setTel(rs.getString("e_tel"));
					member.setBirth(rs.getString("e_birth"));
					member.setManager_num(rs.getString("manager_num"));
					member.setF_num(rs.getString("f_num"));
					member.setPl_num(rs.getString("pl_num"));
					member.setEmail(rs.getString("e_email"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
				}
			}
		}
		return member;
	}

	public List<Sales> getSalesList(String id) {
		Sales sale = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Sales> salesList = new ArrayList<Sales>();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from sales where e_id = ?");
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				do {
					sale = new Sales();
					sale.setS_num(rs.getString("s_num"));
					sale.setMf_num(rs.getString("mf_num"));
					sale.setE_id(rs.getString("e_id"));
					sale.setC_id(rs.getString("c_id"));
					sale.setP_num(rs.getString("p_num"));
					sale.setS_obtain_date(rs.getString("s_obtain_date"));
					sale.setS_contract_sum(rs.getString("s_contract_sum"));
					sale.setS_complete_date(rs.getString("s_complete_date"));
					salesList.add(sale);
				} while (rs.next());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
				}
			}
		}
		return salesList;
	}

	public int insertUpdate(LoginUser dataBean, String target) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int stat = -1;
		try {
			conn = getConnection();
			if (target.equals("직원")) {
				pstmt = conn.prepareStatement("select * from employee where e_id = ?");
				pstmt.setString(1, dataBean.getId());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					String dbpass = rs.getString("e_passwd");
					if (dbpass.equals(dataBean.getPasswd())) {
						pstmt = conn.prepareStatement("update employee set "
								+ " e_passwd=?, e_name=?, e_tel=?, e_birth=?, manager_num=?, f_num=?,"
								+ "pl_num=? where e_id = ?");
						pstmt.setString(1, dataBean.getPasswd());
						pstmt.setString(2, dataBean.getName());
						pstmt.setString(3, dataBean.getTel());
						pstmt.setString(4, dataBean.getBirth());
						pstmt.setString(5, dataBean.getManager_num());
						pstmt.setString(6, dataBean.getF_num());
						pstmt.setString(7, dataBean.getPl_num());
						pstmt.setString(8, dataBean.getId());
						pstmt.executeUpdate();
						stat = 2;
					} else {
						stat = 3;
					}
				} else {
					pstmt = conn.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?)");
					pstmt.setString(1, dataBean.getId());
					pstmt.setString(2, dataBean.getPasswd());
					pstmt.setString(3, dataBean.getName());
					pstmt.setString(4, dataBean.getTel());
					pstmt.setString(5, dataBean.getBirth());
					pstmt.setString(6, dataBean.getManager_num());
					pstmt.setString(7, dataBean.getF_num());
					pstmt.setString(8, dataBean.getPl_num());
					pstmt.executeUpdate();
					stat = 1;
				}
			} else {
				pstmt = conn.prepareStatement("select * from customer where c_id = ?");
				pstmt.setString(1, dataBean.getId());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					String dbpass = rs.getString("e_passwd");
					if (dbpass.equals(dataBean.getPasswd())) {
						pstmt = conn.prepareStatement(
								"update customer set " + ", c_passwd=?, c_name=?, c_contract_date=? where c_id = ?");
						pstmt.setString(1, dataBean.getPasswd());
						pstmt.setString(2, dataBean.getName());
						pstmt.setString(3, dataBean.getContract_date());
						pstmt.setString(8, dataBean.getId());
						pstmt.executeUpdate();
						stat = 2;
					} else {
						stat = 3;
					}
				} else {
					pstmt = conn.prepareStatement("insert into employee values(?,?,?,?)");
					pstmt.setString(1, dataBean.getId());
					pstmt.setString(2, dataBean.getPasswd());
					pstmt.setString(3, dataBean.getName());
					pstmt.setString(4, dataBean.getContract_date());
					pstmt.executeUpdate();
					stat = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
				}
			}
		}
		return stat;
	}

	public List<LoginUser> getEmpCusList(String target) {
		LoginUser user = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<LoginUser> userList = new ArrayList<LoginUser>();
		try {
			conn = getConnection();
			if (target.equals("직원")) {
				stmt = conn.prepareStatement("select * from employee");
				rs = stmt.executeQuery();
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
						userList.add(user);
					} while (rs.next());
				}
			} else {
				stmt = conn.prepareStatement("select * from customer");
				rs = stmt.executeQuery();
				if (rs.next()) {
					do {
						user = new LoginUser();
						user.setId(rs.getString("c_id"));
						user.setPasswd(rs.getString("c_passwd"));
						user.setName(rs.getString("c_name"));
						user.setContract_date(rs.getString("c_contract_date"));
						userList.add(user);
					} while (rs.next());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
				}
			}
		}
		return userList;
	}

	public List<LoginUser> serchEmpCusList(String colum, String serchVar, String target) {
		LoginUser user = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<LoginUser> userList = new ArrayList<LoginUser>();
		try {
			conn = getConnection();
			if (target.equals("직원")) {
				stmt = conn.prepareStatement("select * from employee where " + colum + " = ?");
				stmt.setString(1, serchVar);
				rs = stmt.executeQuery();
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
						userList.add(user);
					} while (rs.next());
				}
			} else {
				stmt = conn.prepareStatement("select * from customer where " + colum + " = ?");
				stmt.setString(1, serchVar);
				rs = stmt.executeQuery();
				if (rs.next()) {
					do {
						user = new LoginUser();
						user.setId(rs.getString("c_id"));
						user.setPasswd(rs.getString("c_passwd"));
						user.setName(rs.getString("c_name"));
						user.setContract_date(rs.getString("c_contract_date"));
						userList.add(user);
					} while (rs.next());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
				}
			}
		}
		return userList;
	}

	public void deleteEmpCus(String id, String target) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (target.equals("직원")) {
				stmt = conn.prepareStatement("delete from employee where e_id = ?");
			} else {
				stmt = conn.prepareStatement("delete from customer where c_id = ?");
			}
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
				}
			}
		}
	}

	public List<String> getFacs(String colum) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select distinct f_num from employee where f_num is not null");
			rs = stmt.executeQuery();
			if (rs.next()) {
				do {
					list.add(rs.getString(colum));
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
				}
			}
		}
		return list;
	}

	public List<String> getPlMas(String colum, String param) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = getConnection();
			if (colum.equals("pl_num")) {
				stmt = conn.prepareStatement(
						"select distinct pl_num from employee where f_num = ? and pl_num is not null");
				stmt.setString(1, param);
			} else {
				stmt = conn.prepareStatement(
						"select distinct manager_num from employee where f_num = ? and manager_num is not null");
				stmt.setString(1, param);
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				do {
					list.add(rs.getString(colum));
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
				}
			}
		}
		return list;
	}

	public boolean idValidationCheck(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean isExist = false;
		try {
			conn = getConnection();
			String query1 = "select e_id from employee where e_id = ?";
			String query2 = "select c_id from customer where c_id = ?";
			if (id.charAt(0) == '3') {
				stmt = conn.prepareStatement(query2);
			} else {
				stmt = conn.prepareStatement(query1);
			}
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				isExist = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
				}
			}
		}
		return isExist;
	}
}
