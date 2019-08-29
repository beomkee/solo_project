	var list = {};
	
	$(document).ready(function() {
		list = data;
		$("#gridTable").clearGridData();
		makeTable('gridTable', list, colNames, colModel);
		
		$(window).resize(function() {
			$("#gridTable").setGridWidth($('#gridbody').width());
		});
	});
	
	
	
/*===================================================== JqGrid 그리는 스크립트 ====================================================== */
	function makeTable(id, array, name, model){
			$("#"+id).jqGrid({
				datatype: "local",                                                                              
				height: 'auto',
				autowidth: true,
				colNames: name,                                      
				colModel: model,                    
				editable		: true,
				rowNum			: 10,
				rowList			: [10,20,25],
				pager			: '#gridPaging',
		        loadonce   		: false,
		        sortname		: 'division',
				//cellEdit		: true, 
	      		//multiselect   	: true, 
				viewrecords		: true,
				onCellSelect 	: function(){
	    			//console.log("한번클릭")
	    		},
	    		gridComplete	: function(){
	    			$("#gridTable").trigger("reloadGrid");
	    		},
	    		ondblClickRow 	: function(rowId, iRow, iCol, e) {
	    			var values = [];
	    			for (var i = 1; i <= columLength; i++) {
						values.push($("#gridTable").getCell(rowId, i));
					}
	    			dubleClick(values);
	   	        }
				//caption: "Data"
				});

		for ( var row in array) {
			$("#" + id).jqGrid('addRowData', row + 1, array[row]);
		} 
	}
	
	function delButton (cellvalue, options, rowObject) {
		return '<input type="button" class="btn btn-outline-warning btn-xs" onclick="deleteGridRow('+rowObject[id]+')" value="del"/>'; 
	};
	
	
//===================================================== 삭제 ======================================================  
	function deleteGridRow(rows){
		if (confirm("정말로 삭제 하시겠습니까?")){
			$.ajax({                          
		        type: "POST",
		        url: "/ProjectNo1/"+deleteUrl,
					data : {"rows" : rows},
					datatype : "json",
					success : function(result) {
						var json = JSON.parse(result);
						list = json;
						$("#gridTable").clearGridData();
						makeTable('gridTable', list, colNames, colModel);
					}
				});
			$("#main").append("<div class='alert alert-success alert-dismissible fade show' role='alert'>삭제 되었습니다.<a href='#' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></a></div>");
		}
	}
//===================================================== 검색 ======================================================  
	function search() {
		setData();
		 $.ajax({                          
	        type: "POST",
	        url: "/ProjectNo1/"+searchUrl,
			data : {"dataSet" : dataSet},
			datatype : "json",
			success : function(result) {
				var json = JSON.parse(result); 
				$("#gridTable").clearGridData();
				makeTable('gridTable', json, colNames, colModel);
			} 
		}); 
	}
	
//===================================================== 등록/수정 ====================================================== 	
	function insertUpdate() {
		setData();
		if (!isFilled()) {
			alert("입력란을 모두 채워주세요");
		} else {
			 $.ajax({                          
		        type: "POST",
		        url: "/ProjectNo1/" + insertUrl,
		        data : {
					"dataSet" : dataSet
				},
				datatype : "json",
				success : function(result) {
					var json = JSON.parse(result);
					list = json;
					$("#gridTable").clearGridData();
					makeTable('gridTable', list, colNames, colModel);
					$("#main").append("<div class='alert alert-success alert-dismissible fade show'  role='alert'>등록/수정 되었습니다.<a href='#' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></a></div>");
				}
			});
		}
	}
