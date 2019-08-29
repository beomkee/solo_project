package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.msk.Action;

import JSON.JSONObject;
import JSON.JqGridJSONObject;
import model.Manufactures;
import service.ManufacturesService;

public class MfAction extends Action {

	private ManufacturesService manufacturesService = new ManufacturesService();

	public String manufactureGET(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JqGridJSONObject json = new JqGridJSONObject();
		List<Manufactures> list = null;
		String cols = "생산번호,공장번호,생산라인,담당직원,상품번호,생산수량,생산일자,삭제";
		String mods = "mf_num,f_num,pl_num,e_id,p_num,mf_count,mf_date";
		String ops = "0,0,0,0,0,0,0";
		json.putKey(cols);
		json.putVal(mods);
		json.setOption(ops);

		list = manufacturesService.selectData();

		String data = "[";
		for (Object ob : list) {
			data += ob.toString() + ",";
		}
		data = data.substring(0, data.length() - 1);
		data += "]";

		List<String> products = null;
		products = manufacturesService.proNums();

		List<String> emps = null;
		emps = manufacturesService.empNums();

		List<String> facs = null;
		facs = manufacturesService.facNums();

		/*
		 * System.out.println("colNames:"); System.out.println(json.getKey());
		 * System.out.println("\ncolModel:"); System.out.println(json.getValue());
		 * System.out.println("\ndata:"); System.out.println(data);
		 */

		// 바그래프 정보넘기는 부분
		JSONObject bars = new JSONObject();

		bars.setName("data");
		bars.putKey(mods);
		/*
		 * bars.putValue(barValue1); bars.putValue(barValue2);
		 */

		request.setAttribute("colNames", json.getKey());
		request.setAttribute("colModel", json.getValue());
		request.setAttribute("data", data);
		request.setAttribute("products", products);
		request.setAttribute("emps", emps);
		request.setAttribute("facs", facs);
		/* request.setAttribute("bar_data1", bars); */

		return "/concept-master/content/emp/e_manufacture.jsp";
	}

	public String searchMfPOST(HttpServletRequest request, HttpServletResponse res) throws Exception {

		String dataSet = request.getParameter("dataSet");
		String[] datas = dataSet.split(",", 7);

		/*
		 * for (int i = 0; i < datas.length; i++) { System.out.println("데이터" + (i + 1) +
		 * ": " + datas[i]); }
		 */

		List<Manufactures> searchList = manufacturesService.searchData(datas);

		request.setAttribute("list", searchList);

		return "/concept-master/ajaxResult/mfResult.jsp";
	}

	public String insertUpdateMfPOST(HttpServletRequest request, HttpServletResponse res) throws Exception {
		String dataSet = request.getParameter("dataSet");
		String[] datas = dataSet.split(",");
		List<Manufactures> list = new ArrayList<Manufactures>();
		list = manufacturesService.insertUpdate(datas);
		request.setAttribute("list", list);
		return "/concept-master/ajaxResult/mfResult.jsp";
	}

	public String deleteMfPOST(HttpServletRequest request, HttpServletResponse res) throws Exception {
		String num = request.getParameter("rows");
		List<Manufactures> list = new ArrayList<Manufactures>();
		list = manufacturesService.delete(num);
		request.setAttribute("list", list);
		return "/concept-master/ajaxResult/mfResult.jsp";
	}
}
