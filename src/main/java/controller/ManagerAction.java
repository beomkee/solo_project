package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.msk.Action;

import JSON.JSONObject;
import JSON.JqGridJSONObject;
import model.ChangePwRequest;
import model.LoginUser;
import model.Manufactures;
import model.Sales;
import model.WriteRequest;
import service.ComService;
import service.ManagerService;
import service.ManufacturesService;

public class ManagerAction extends Action {

	ManagerService managerService = new ManagerService();

	public String empManageGET(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/concept-master/content/manager/m_empWrite.jsp";
	}

	public String insertEmpPOST(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("empid");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String tel = tel1 + "-" + tel2 + "-" + tel3;
		String birth = request.getParameter("birth");
		String manager_num = request.getParameter("manager_num");
		String f_num = request.getParameter("f_num");
		String pl_num = request.getParameter("pl_num");
		String email = request.getParameter("email");
		System.out.println(id + "," + passwd + "," + name + "," + tel + "," + birth + "," + manager_num + "," + f_num
				+ "," + pl_num + "," + email);

		WriteRequest wrq = new WriteRequest();
		wrq.setPasswd1(passwd);
		wrq.setPasswd2(request.getParameter("passwdCheck"));
		Map<String, Boolean> stat = new HashMap<>();
		if (!wrq.isPasswordEqualToConfirm()) {
			stat.put("notEq", Boolean.TRUE);
			request.setAttribute("stat", stat);
			return "/concept-master/content/manager/m_empWrite.jsp";
		} else {
			int idCheck = managerService.idCheck(id);
			if (idCheck != 0) {
				stat.put("existid", Boolean.TRUE);
				request.setAttribute("stat", stat);
				return "/concept-master/content/manager/m_empWrite.jsp";
			} else {
				managerService.insertEmp(id, passwd, name, tel, birth, manager_num, f_num, pl_num, email);
				return "/concept-master/content/manager/m_empList.jsp";
			}
		}
	}

	public String empListGET(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGINED_ID");
		ComService comService = new ComService();
		String f_num = comService.getFNum(id);
		
		JqGridJSONObject json = new JqGridJSONObject();

		List<LoginUser> list = null;

		String cols = "아이디,비밀번호,이름,전화번호,생년월일,매니저번호,공장번호,라인번호,이메일,삭제";
		String mods = "id,passwd,name,tel,birth,manager_num,f_num,pl_num,email";
		String ops = "0,0,0,0,0,0,0,0,0";

		json.putKey(cols);
		json.putVal(mods);
		json.setOption(ops);

		list = managerService.selectEmpData(f_num);

		String data = "[";
		for (Object ob : list) {
			data += ob.toString() + ",";
		}
		data = data.substring(0, data.length() - 1);
		data += "]";

		request.setAttribute("colNames", json.getKey());
		request.setAttribute("colModel", json.getValue());
		request.setAttribute("data", data);

		return "/concept-master/content/manager/m_empList.jsp";
	}

	public String facMGET(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ComService comService = new ComService();

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGINED_ID");
		String f_num = comService.getFNum(id);

		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int count = comService.getFacMfCount(f_num);
		int startRow = (currentPage - 1) * pageSize;
		int endRow = currentPage * pageSize;
		if (count < endRow) {
			endRow = count;
		}
		int number = count - ((currentPage - 1) * pageSize);

		List f_mfs = comService.getFMf(id, startRow, pageSize);

		int bottomLine = 3;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		request.setAttribute("f_num", f_num);
		request.setAttribute("f_mfs", f_mfs);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("number", number);

		return "/concept-master/content/manager/m_f_info.jsp";
	}

	public String saleMGET(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ComService comService = new ComService();

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGINED_ID");
		String f_num = comService.getFNum(id);

		JqGridJSONObject json = new JqGridJSONObject();
		List<Sales> list = null;
		String cols = "판매번호,생산번호,담당직원,고객번호,상품번호,판매일자,거래일자,완료일,삭제";
		String mods = "s_num,mf_num,e_id,c_id,p_num,s_obtain_date,s_contract_sum,s_complete_date";
		String ops = "0,0,0,0,0,0,0,0";
		json.putKey(cols);
		json.putVal(mods);
		json.setOption(ops);
		list = comService.selectSaleData(f_num);
		
		String data = "[";
		for (Object ob : list) {
			data += ob.toString() + ",";
		}
		data = data.substring(0, data.length() - 1);
		data += "]";

		ManufacturesService manufacturesService = new ManufacturesService();
		
		List<String> products = null;
		products = manufacturesService.proNums();

		List<String> emps = null;
		emps = manufacturesService.empNums2();

		List<String> mfs = null;
		mfs = manufacturesService.selectManuNum();

		request.setAttribute("colNames", json.getKey());
		request.setAttribute("colModel", json.getValue());
		request.setAttribute("data", data);
		request.setAttribute("products", products);
		request.setAttribute("e_ids", emps);
		request.setAttribute("mf_nums", mfs);

		return "/concept-master/content/manager/m_sales.jsp";
	}

}
