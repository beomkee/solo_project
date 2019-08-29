var mix = null;
var bar = null;
var line1 = null;
var line2 = null;
var pie = null;
var chardatas = [];

function getChartData() {
	$.ajax({
		type : "POST",
		url : "/ProjectNo1/chart/" + getDataUrl,
		data : {"fac" : fac},
		datatype : "json",
		success : function(result) {
			chardatas = result.split('&');

			LineOptions.data.labels = chardatas[0].split(",");
			LineOptions.data.datasets[0].data = chardatas[1].split(",");
			LineOptions.data.datasets[1].data = chardatas[2].split(",");

			barOptions.data.labels = chardatas[3].split(",");
			barOptions.data.datasets[0].data = chardatas[4].split(",");
			barOptions.data.datasets[1].data = chardatas[5].split(",");

			doughnutOptions.data.labels = chardatas[6].split(",");
			doughnutOptions.data.datasets[0].data = chardatas[7].split(",");

			polarOptions.data.labels = chardatas[8].split(",");
			polarOptions.data.datasets[0].data = chardatas[9].split(",");

			drawGraph();
		}
	});
}

function drawGraph() {
	line = new Chart(chartjs_line, LineOptions);
	bar = new Chart(chartjs_bar, barOptions);
	doughnut = new Chart(chartjs_doughnut, doughnutOptions);
	polar = new Chart(chartjs_polar, polarOptions);
}

// Line Chart
// ===========================================================================================================================
var chartjs_line = document.getElementById('chartjs_line').getContext('2d');
var LineOptions = {
	type : 'line',
	data : {
		labels : [],
		datasets : [ lineData1 = {
			label : label1,
			data : [],
			backgroundColor: "rgba(89, 105, 255,0.5)",
			borderColor : "rgba(89, 105, 255,0.7)",
			borderWidth : 2
		}, lineData2 = {
			label : label2,
			data : [],
			
			backgroundColor : "rgba(255, 64, 123, 0.5)",
			borderColor : "rgba(255, 64, 123,0.7)",
			borderWidth : 2
		} ]
	},
	options : {
		legend : {
			display : true,
			position : 'bottom',
			labels : {
				fontColor : '#71748d',
				fontFamily : 'Circular Std Book',
				fontSize : 14,
			}
		},
		scales : {
			xAxes : [ {
				ticks : {
					fontSize : 14,
					fontFamily : 'Circular Std Book',
					fontColor : '#71748d',
				}
			} ],
			yAxes : [ {
				ticks : {
					fontSize : 14,
					fontFamily : 'Circular Std Book',
					fontColor : '#71748d',
				}
			} ]
		}
	}
};
// Bar Chart
// ===========================================================================================================================
var chartjs_bar = document.getElementById("chartjs_bar").getContext('2d');
var barOptions = {
	type : 'bar',
	data : {
		labels : [],
		datasets : [ barData1 = {
			label : label1,
			data : [],
			backgroundColor : "rgba(89, 105, 255,0.5)",
			borderColor : "rgba(89, 105, 255,0.7)",
			borderWidth : 2
		}, barData2 = {
			label : label2,
			data : [],
			backgroundColor : "rgba(255, 64, 123,0.5)",
			borderColor : "rgba(255, 64, 123,0.7)",
			borderWidth : 2
		} ]
	},
	options : {
		scales : {
			yAxes : [ {

			} ]
		},
		legend : {
			display : true,
			position : 'bottom',

			labels : {
				fontColor : '#71748d',
				fontFamily : 'Circular Std Book',
				fontSize : 14,
			}
		},
		scales : {
			xAxes : [ {
				ticks : {
					fontSize : 14,
					fontFamily : 'Circular Std Book',
					fontColor : '#71748d',
				}
			} ],
			yAxes : [ {
				ticks : {
					fontSize : 14,
					fontFamily : 'Circular Std Book',
					fontColor : '#71748d',
				}
			} ]
		}
	}
};

// Doughnut Chart
// ===========================================================================================================================
var chartjs_doughnut = document.getElementById("chartjs_doughnut").getContext('2d');
var doughnutOptions = {
	type : 'doughnut',
	data : {
		labels : [],
		datasets : [{
				backgroundColor : [ 
					"#5969ff",
					"#ff407b", 
					"#25d5f2", 
					"#ffc750", 
					"#2ec551", 
					"#7040fa", 
					"#ff004e" 
				],
				data : []
			}]
	},
	options : {
		legend : {
			display : true,
			position : 'bottom',
			labels : {
				fontColor : '#71748d',
				fontFamily : 'Circular Std Book',
				fontSize : 14,
			}
		},
	}
};

// Polar Chart
// ===========================================================================================================================
var chartjs_polar = document.getElementById("chartjs_polar").getContext('2d');
var polarOptions = {
	type : 'polarArea',
	data : {
		labels : [],
		datasets : [{
				backgroundColor : [ 
					"#5969ff", 
					"#ff407b", 
					"#25d5f2", 
					"#ffc750", 
					"#2ec551", 
					"#7040fa", 
					"#ff004e" 
				],
				data : []
			}]
	},
	options : {
		legend : {
			display : true,
			position : 'bottom',
			labels : {
				fontColor : '#71748d',
				fontFamily : 'Circular Std Book',
				fontSize : 14,
			}
		},
	}
};

//
//
//
//
//
//

