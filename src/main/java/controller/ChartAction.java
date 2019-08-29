package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.msk.Action;

import JSON.ChartJSONObject;
import service.ComService;
import service.ManufacturesService;

public class ChartAction extends Action {

	public String mfDataPOST(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ManufacturesService service = new ManufacturesService();

		// line 차트 데이터
		Map<String, Map<Integer, Integer>> lineData = service.getLineData();
		System.out.println(lineData);
		int il = 0;
		String lineKey1 = "";
		String lineVal1 = "";
		String lineVal2 = "";
		for (String keys : lineData.keySet()) {
			int j = 0;
			if (il == 0) {
				for (Integer ii : lineData.get(keys).keySet()) {
					lineKey1 += String.valueOf(ii) + ",";
					lineVal1 += String.valueOf(lineData.get(keys).get(ii)) + ",";
					j++;
				}
			} else {
				for (Integer ii : lineData.get(keys).keySet()) {
					lineVal2 += String.valueOf(lineData.get(keys).get(ii)) + ",";
					j++;
				}
			}
			il++;
		}
		request.setAttribute("lineKey1", lineKey1.substring(0, lineKey1.length() - 1));
		request.setAttribute("lineVal1", lineVal1.substring(0, lineVal1.length() - 1));
		request.setAttribute("lineVal2", lineVal2.substring(0, lineVal2.length() - 1));

		// bar 차트 데이터
		Map<String, Map<String, Integer>> barData = service.getBarData();
		System.out.println(barData);
		int ib = 0;
		String barKey1 = "";
		String barVal1 = "";
		String barVal2 = "";
		for (String keys : barData.keySet()) {
			int j = 0;
			if (ib == 0) {
				for (String ii : barData.get(keys).keySet()) {
					barKey1 += ii + ",";
					barVal1 += String.valueOf(barData.get(keys).get(ii)) + ",";
					j++;
				}
			} else {
				for (String ii : barData.get(keys).keySet()) {
					barVal2 += String.valueOf(barData.get(keys).get(ii)) + ",";
					j++;
				}
			}
			ib++;
		}
		request.setAttribute("barKey1", barKey1.substring(0, barKey1.length() - 1));
		request.setAttribute("barVal1", barVal1.substring(0, barVal1.length() - 1));
		request.setAttribute("barVal2", barVal2.substring(0, barVal2.length() - 1));

		// pie 차트 데이터
		Map<String, Integer> pieData = service.getPieData();
		System.out.println(pieData);
		int ip = 0;
		String pieKey1 = "";
		String pieVal1 = "";
		for (String keys : pieData.keySet()) {
			pieKey1 += keys + ",";
			pieVal1 += String.valueOf(pieData.get(keys)) + ",";
			ip++;
		}
		request.setAttribute("pieKey1", pieKey1.substring(0, pieKey1.length() - 1));
		request.setAttribute("pieVal1", pieVal1.substring(0, pieVal1.length() - 1));

		// polor 차트 데이터
		Map<String, Integer> polorData = service.getPolorData();
		System.out.println(polorData);
		int ipo = 0;
		String polorKey1 = "";
		String polorVal1 = "";
		for (String keys : polorData.keySet()) {
			polorKey1 += keys + ",";
			polorVal1 += String.valueOf(polorData.get(keys)) + ",";
			ipo++;
		}
		request.setAttribute("polorKey1", polorKey1.substring(0, polorKey1.length() - 1));
		request.setAttribute("polorVal1", polorVal1.substring(0, polorVal1.length() - 1));

		return "/concept-master/ajaxResult/ChartResult.jsp";
	}

	public String facDataPOST(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ComService service = new ComService();
		String fac = request.getParameter("fac");
		// line 차트 데이터
		Map<String, Map<Integer, Integer>> lineData = service.getLineData(fac);
		System.out.println(lineData);
		int il = 0;
		String lineKey1 = "";
		String lineVal1 = "";
		String lineVal2 = "";
		for (String keys : lineData.keySet()) {
			int j = 0;
			if (il == 0) {
				for (Integer ii : lineData.get(keys).keySet()) {
					lineKey1 += String.valueOf(ii) + ",";
					lineVal1 += String.valueOf(lineData.get(keys).get(ii)) + ",";
					j++;
				}
			} else {
				for (Integer ii : lineData.get(keys).keySet()) {
					lineVal2 += String.valueOf(lineData.get(keys).get(ii)) + ",";
					j++;
				}
			}
			il++;
		}
		request.setAttribute("lineKey1", lineKey1.substring(0, lineKey1.length() - 1));
		request.setAttribute("lineVal1", lineVal1.substring(0, lineVal1.length() - 1));
		request.setAttribute("lineVal2", lineVal2.substring(0, lineVal2.length() - 1));

		// bar 차트 데이터
		Map<String, Map<String, Integer>> barData = service.getBarData(fac);
		System.out.println(barData);
		int ib = 0;
		String barKey1 = "";
		String barVal1 = "";
		String barVal2 = "";
		for (String keys : barData.keySet()) {
			int j = 0;
			if (ib == 0) {
				for (String ii : barData.get(keys).keySet()) {
					barKey1 += ii + ",";
					barVal1 += String.valueOf(barData.get(keys).get(ii)) + ",";
					j++;
				}
			} else {
				for (String ii : barData.get(keys).keySet()) {
					barVal2 += String.valueOf(barData.get(keys).get(ii)) + ",";
					j++;
				}
			}
			ib++;
		}
		request.setAttribute("barKey1", barKey1.substring(0, barKey1.length() - 1));
		request.setAttribute("barVal1", barVal1.substring(0, barVal1.length() - 1));
		request.setAttribute("barVal2", barVal2.substring(0, barVal2.length() - 1));

		// pie 차트 데이터
		Map<String, Integer> pieData = service.getPieData(fac);
		System.out.println(pieData);
		int ip = 0;
		String pieKey1 = "";
		String pieVal1 = "";
		for (String keys : pieData.keySet()) {
			pieKey1 += keys + ",";
			pieVal1 += String.valueOf(pieData.get(keys)) + ",";
			ip++;
		}
		request.setAttribute("pieKey1", pieKey1.substring(0, pieKey1.length() - 1));
		request.setAttribute("pieVal1", pieVal1.substring(0, pieVal1.length() - 1));

		// polor 차트 데이터
		Map<String, Integer> polorData = service.getPolorData(fac);
		System.out.println(polorData);
		int ipo = 0;
		String polorKey1 = "";
		String polorVal1 = "";
		for (String keys : polorData.keySet()) {
			polorKey1 += keys + ",";
			polorVal1 += String.valueOf(polorData.get(keys)) + ",";
			ipo++;
		}
		request.setAttribute("polorKey1", polorKey1.substring(0, polorKey1.length() - 1));
		request.setAttribute("polorVal1", polorVal1.substring(0, polorVal1.length() - 1));
		
		return "/concept-master/ajaxResult/ChartResult.jsp";
	}
}
