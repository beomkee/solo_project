var MONTHS = [ "January", "February", "March", "April", "May", "June", "July",
		"August", "September", "October", "November", "December" ];
var randomScalingFactor = function() {
	return Math.round(Math.random() * 100 * (Math.random() > 0.5 ? -1 : 1));
};
var randomColorFactor = function() {
	return Math.round(Math.random() * 255);
};
var randomColor = function(opacity) {
	return 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ','
			+ randomColorFactor() + ',' + (opacity || '.3') + ')';
};

var config = {
	type : 'line',
	barGraph : {
		labels : [ "January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November",
				"December" ],
		barGraphsets : [
				{
					label : "My First barGraphset",
					barGraph : [ barGraph.sample[0].January, barGraph.sample[0].February,
							barGraph.sample[0].March, barGraph.sample[0].April,
							barGraph.sample[0].May, barGraph.sample[0].June,
							barGraph.sample[0].July, barGraph.sample[0].August,
							barGraph.sample[0].September, barGraph.sample[0].October,
							barGraph.sample[0].November, barGraph.sample[0].December ],
					fill : false,
					borderDash : [ 5, 5 ],
				},
				{
					label : "My Second barGraphset",
					barGraph : [ barGraph.sample[1].January, barGraph.sample[1].February,
							barGraph.sample[1].March, barGraph.sample[1].April,
							barGraph.sample[1].May, barGraph.sample[1].June,
							barGraph.sample[1].July, barGraph.sample[1].August,
							barGraph.sample[1].September, barGraph.sample[1].October,
							barGraph.sample[1].November, barGraph.sample[1].December ],
					fill : false,
					borderDash : [ 5, 5 ],
				},
				{
					label : "My Third barGraphset - No bezier",
					barGraph : [ barGraph.sample[2].January, barGraph.sample[2].February,
							barGraph.sample[2].March, barGraph.sample[2].April,
							barGraph.sample[2].May, barGraph.sample[2].June,
							barGraph.sample[2].July, barGraph.sample[2].August,
							barGraph.sample[2].September, barGraph.sample[2].October,
							barGraph.sample[2].November, barGraph.sample[2].December ],
					lineTension : 0,
					fill : false,
				},
				{
					label : "My Fourth barGraphset",
					barGraph : [ barGraph.sample[3].January, barGraph.sample[3].February,
							barGraph.sample[3].March, barGraph.sample[3].April,
							barGraph.sample[3].May, barGraph.sample[3].June,
							barGraph.sample[3].July, barGraph.sample[3].August,
							barGraph.sample[3].September, barGraph.sample[3].October,
							barGraph.sample[3].November, barGraph.sample[3].December ],
					fill : false,
				} ]
	},
	options : {
		responsive : true,
		legend : {
			position : 'bottom',
		},
		hover : {
			mode : 'label'
		},
		scales : {
			xAxes : [ {
				display : true,
				scaleLabel : {
					display : true,
					labelString : 'Month'
				}
			} ],
			yAxes : [ {
				display : true,
				scaleLabel : {
					display : true,
					labelString : 'Value'
				}
			} ]
		},
		title : {
			display : true,
			text : 'Chart.js Line Chart - Legend'
		}
	}
};

$.each(config.barGraph.barGraphsets, function(i, barGraphset) {
	var background = randomColor(0.5);
	barGraphset.borderColor = background;
	barGraphset.backgroundColor = background;
	barGraphset.pointBorderColor = background;
	barGraphset.pointBackgroundColor = background;
	barGraphset.pointBorderWidth = 1;
});

window.onload = function() {
	var ctx = document.getElementById("canvas_curve").getContext("2d");
	window.myLine = new Chart(ctx, config);
};

$('#randomizebarGraph').click(function() {
	$.each(config.barGraph.barGraphsets, function(i, barGraphset) {
		barGraphset.barGraph = barGraphset.barGraph.map(function() {
			return randomScalingFactor();
		});

	});

	window.myLine.update();
});

$('#addbarGraphset').click(function() {
	var background = randomColor(0.5);
	var newbarGraphset = {
		label : 'barGraphset ' + config.barGraph.barGraphsets.length,
		borderColor : background,
		backgroundColor : background,
		pointBorderColor : background,
		pointBackgroundColor : background,
		pointBorderWidth : 1,
		fill : false,
		barGraph : [],
	};

	for (var index = 0; index < config.barGraph.labels.length; ++index) {
		newbarGraphset.barGraph.push(randomScalingFactor());
	}

	config.barGraph.barGraphsets.push(newbarGraphset);
	window.myLine.update();
});

$('#addbarGraph').click(function() {
	if (config.barGraph.barGraphsets.length > 0) {
		var month = MONTHS[config.barGraph.labels.length % MONTHS.length];
		config.barGraph.labels.push(month);

		$.each(config.barGraph.barGraphsets, function(i, barGraphset) {
			barGraphset.barGraph.push(randomScalingFactor());
		});

		window.myLine.update();
	}
});

$('#removebarGraphset').click(function() {
	config.barGraph.barGraphsets.splice(0, 1);
	window.myLine.update();
});

$('#removebarGraph').click(function() {
	config.barGraph.labels.splice(-1, 1); // remove the label first

	config.barGraph.barGraphsets.forEach(function(barGraphset, barGraphsetIndex) {
		barGraphset.barGraph.pop();
	});

	window.myLine.update();
});