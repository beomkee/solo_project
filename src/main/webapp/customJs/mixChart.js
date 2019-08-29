

//mix 차트 쿼리 ==================================================================
var mixChart = document.getElementById('mixChart').getContext('2d');
var mixOptions = {
	type : 'bar',
	data : {
		datasets : [ {
			label : 'Bar Dataset',
			data : data1
		}, {
			label : 'Line Dataset',
			data : data2,
			type : 'line'
		} ],
		labels : keyData
	},
	options : {
	}
};


//line 차트 쿼리 ==================================================================
var lineChart = document.getElementById('lineChart').getContext('2d');
var LineOptions = {
	type : 'line',
	label : 'Line Dataset',
	data : {
		data2,
		labels : keyData
	},
	options : {
	}
};


//bar 차트 쿼리 ==================================================================
var barChart = document.getElementById('barChart').getContext('2d');
var barOptions = {
	type : 'bar',
	data : {
		data3,
		labels : keyData
	},
	options : {
	}
};


//pie 차트 쿼리 ==================================================================
var pieChart = document.getElementById('pieChart').getContext('2d');
var pieOptions = {
	type : 'doughnut',
	data : {
		data3,
		labels : keyData
	},
	options : {
	}
};






