// 검색폼 속성=====================
var mf_num, f_num, pl_num, e_id, p_num, mf_count, mf_date = "";
var dataSet = "";
var id = "mf_num";
var columLength = colNames.length - 1;

// ajaxURl설정=====================
var deleteUrl = "mf/deleteMf.do";
var searchUrl = "mf/searchMf.do";
var insertUrl = "mf/insertUpdateMf.do";
var getDataUrl = "mfData.do";

//Chart 속성=====================
var label1 = "yi_45678";
var label2 = "sw_12345";

// 검색폼 초기화===================
function reset() {
	$('#mf_num').val(' --- ');
	$('#f_num').val('');
	$('#pl_num').val('');
	$('#e_id').val('');
	$('#p_num').val('');
	$('#mf_count').val('');
	$('#mf_date').val('');
	$("#gridTable").clearGridData();
	makeTable('gridTable', list, colNames, colModel);
}

// 그리드항목 검색폼 전환==========
function dubleClick(values) {
	$('#mf_num').val(values[0]);
	$('#f_num').val(values[1]);
	$('#pl_num').val(values[2]);
	$('#e_id').val(values[3]);
	$('#p_num').val(values[4]);
	$('#mf_count').val(values[5]);
	$('#mf_date').val(values[6]);
}

// 검색폼 데이터 세팅==============
function setData() {
	mf_num = $('#mf_num').val();
	f_num = $('#f_num option:selected').val();
	pl_num = $('#pl_num option:selected').val();
	e_id = $('#e_id option:selected').val();
	p_num = $('#p_num option:selected').val();
	mf_count = $('#mf_count').val();
	mf_date = $('#mf_date').val();
	dataSet = $('#mf_num').val() + "," + $('#f_num option:selected').val()
			+ "," + $('#pl_num option:selected').val() + ","
			+ $('#e_id option:selected').val() + ","
			+ $('#p_num option:selected').val() + "," + $('#mf_count').val()
			+ "," + $('#mf_date').val();
}

// 검색폼 입력여부 확인============
function isFilled() {
	setData();
	if (mf_num == null || f_num == null || pl_num == null || e_id == null
			|| p_num == null || mf_count == null || mf_date == null
			|| mf_num == "" || f_num == "" || pl_num == "" || e_id == ""
			|| p_num == "" || mf_count == "" || mf_date == "") {
		return false;
	} else {
		return true;
	}
}