package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JSON.JSONObject;

public class BarGraphHandler implements CommandHandler {

	private static final String FORM_VIEW = "/concept-master/ajaxResult/barResult.jsp";

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JSONObject json = new JSONObject();
		String aaa = request.getParameter("barKey");
		String bbb = request.getParameter("barValue1");
		String ccc = request.getParameter("barValue2");

		String[] barKey = aaa.split(",");
		String[] barValue1 = bbb.split(",");
		String[] barValue2 = ccc.split(",");
		
		json.setName("data");
		json.putKey(barKey);
		json.putValue(barValue1);
		json.putValue(barValue2);

		request.setAttribute("barGraph", json);

		return FORM_VIEW;
	}


}
