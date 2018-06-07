$(document).ready(function () {

  $.getJSON("/findEntry")
      .done(function(dayTotal){
        $.each(data, function(index, dayTotal){
        var expense = dayTotal.expenseTotal;
        var date = dayTotal.entryDate;

        pie.content.label.append(expense);
        pie.content.value.append(date);

      });
    })

  });

  var pie = new d3pie("pieChart", {
  	"header": {
  		"title": {
  			"text": "Category BreakDown",
  			"fontSize": 24,
  			"font": "open sans"
  		},
  		"subtitle": {
  			"color": "#999999",
  			"fontSize": 12,
  			"font": "open sans"
  		},
  		"location": "top-left",
  		"titleSubtitlePadding": 9
  	},
  	"footer": {
  		"color": "#999999",
  		"fontSize": 10,
  		"font": "open sans",
  		"location": "bottom-left"
  	},
  	"size": {
  		"canvasHeight": 340,
  		"canvasWidth": 600,
  		"pieOuterRadius": "100%"
  	},
  	"data": {
  		"sortOrder": "value-desc",
  		"content": [
  			{
  				"label": "6-09-2018",
  				"value": 42.00,
  				"color": "#2484c1"
  			},
        {
          "label": "6-10-2018",
          "value": 53.00,
          "color": "#2484c1"
        }
  		]
  	},
  	"labels": {
  		"outer": {
  			"pieDistance": 32
  		},
  		"inner": {
  			"hideWhenLessThanPercentage": 3
  		},
  		"mainLabel": {
  			"fontSize": 11
  		},
  		"percentage": {
  			"color": "#ffffff",
  			"decimalPlaces": 0
  		},
  		"value": {
  			"color": "#adadad",
  			"fontSize": 11
  		},
  		"lines": {
  			"enabled": true
  		},
  		"truncation": {
  			"enabled": true
  		}
  	},
  	"effects": {
  		"pullOutSegmentOnClick": {
  			"effect": "linear",
  			"speed": 400,
  			"size": 8
  		}
  	},
  	"misc": {
  		"gradient": {
  			"enabled": true,
  			"percentage": 100
  		}
  	}
  });
