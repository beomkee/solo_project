<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Guriddo_jqGrid_JS_5.3.2/css/ui.jqgrid.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/Guriddo_jqGrid_JS_5.3.2/js/jquery.jqGrid.min.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-ui-1.12.1.custom/jquery-ui.css" >
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-ui-1.12.1.custom/jquery-ui.js" ></script>
<script src="<%=request.getContextPath()%>/Guriddo_jqGrid_JS_5.3.2/js/i18n/grid.locale-kr.js"></script>
<script>   
	var list = {};
	var target = "";
	var colNames = [];
	var colModel = {};
/*===================================================== JqGrid 그리는 스크립트 ====================================================== */
	function makeTable(id, array){
			$("#"+id).jqGrid({
				datatype: "local",                                                                              
				height: 'auto',
				autowidth: true,
				colNames:['num','text','depth','collapse','division','url','use_yn','parent_num','del'],                                      
				colModel:[
					{name:'num',		width: 80,			align:'center',			key: true},
					{name:'text',		width:250,			align:'right', 			editable: true,			sortable:false},                                        
					{name:'depth',		align:'right', 		editable: true, 		},                                     
					{name:'collapse',	align:'right', 		editable: true,
						edittype   : "select",
						editoptions : {                     
		                  value : { "1" : "y","2" : "n"}   
		               }},
					{name:'division',	align:'right', 		editable: true},
					{name:'url',		width:600,		 	align:'right', 			editable: true,			sortable:false},
					{name:'use_yn',		align:'right', 		editable: true,
						edittype   : "select",
						editoptions : {                     
			                  value : { "1" : "y","2" : "n"}   
			               }},
					{name:'parent_num',	align:'right', 		editable: true,			sortable:false},
					{name:'btn',		align:'center', 	formatter: delButton}          
				],                    
				editable		: true,
				rowNum			: 10,
				rowList			: [10,20,30],
				pager			: '#gridPaging', 
		        loadonce   		: false,
		        sortname		: 'division',
				//cellEdit		: true, 
	      		multiselect   	: true, 
				viewrecords		: true,
				onCellSelect 	: function(){
	    			//console.log("한번클릭")
	    		},
	    		gridComplete	: function(){
	    			$("#gridTable").trigger("reloadGrid");
	    		},
	    		ondblClickRow 	: function(rowId, iRow, iCol, e) {
	    		
	   	        }
				//caption: "Left Menu Data"
				});

		for ( var row in array) {
			$("#" + id).jqGrid('addRowData', row + 1, array[row]);
		} 
		function delButton (cellvalue, options, rowObject) {
   	    	return '<button class="btn" type="button" onclick="deleteGridRow('+options.rowId+')">DEL</button>'; 
   	    };
	}
	$(document).ready(function() {
		resetSerch();
	});
	$(window).resize(function() {
		$("#gridTable").setGridWidth($('#container').width());
	});
	
	function deleteGridRow(rowId, text){
		//$("#jqGrid").delRowData(rowid);
		if (confirm("정말로 삭제 하시겠습니까?")){
			$.ajax({                          
		        type: "POST",
		        url: "",
					data : {"serchVar" : serchVar,
							"target" : target,
							"colum" : colum},
					datatype : "json",
					success : function(result) {
						var temp = result.trim();
						var json = JSON.parse(temp);
						list = json;
						$("#gridTable").clearGridData();
						makeTable('gridTable', list);
					}
				});
		}
		
	}
/*===================================================== Ajax로 검색하는 스크립트 ====================================================== */ 
	function serchResult(){
		var serchVar =  $('#serchVar').val();
		var colum = $("input[name=colum]:checked").val();
		if (serchVar == '') {
			alert("검색어를 입력해주세요");
			return false;
		}else if(colum == null){
			alert("검색 조건을 선택해 주세요");
			return false;
		}
		$.ajax({                          
	        type: "POST",
	        url: "<%=request.getContextPath()%>/metro/ajax/serchGridPro.jsp",
				data : {"serchVar" : serchVar,
						"target" : target,
						"colum" : colum},
				datatype : "json",
				success : function(result) {
					var temp = result.trim();
					var json = JSON.parse(temp);
					list = json;
					$("#gridTable").clearGridData();
					makeTable('gridTable', list);
				}
			});
	}
/*===================================================== 검색 결과 초기화 스크립트 ====================================================== */
	function resetSerch(){
		$('#serchVar').val('');
		$.ajax({                          
	        type: "POST",
	        url: "<%=request.getContextPath()%>/metro/ajax/leftSearchPro.jsp",
			data : "target=" + target,
			datatype : "json",
			success : function(result) {
				var json = JSON.parse(result);
				list = json;
				$("#gridTable").clearGridData();
				makeTable('gridTable', json);
			}
		});
	}
</script>
