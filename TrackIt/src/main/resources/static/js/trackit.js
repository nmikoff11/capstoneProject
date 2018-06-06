
$(document).ready(function () {

});
function pieChart(){
  $.ajax({
        type: 'GET',
            url: 'http://localhost:8080/index',
              success: function(data, status){
                $.each(data, function(index, entry)){

                  var chart = new CanvasJS.Chart("chartContainer", {
                  exportEnabled: true,
                  animationEnabled: true,
                  title:{
                  	text: "State Operating Funds"
                  },
                  legend:{
                  	cursor: "pointer",
                  	itemclick: explodePie
                  },
                  data: [{
                  	type: "pie",
                  	showInLegend: true,
                  	toolTipContent: "{name}: {y}%",
                  	indexLabel: "{name} - {y}%",
                  	dataPoints: [
                  		{ y: data., name: "School Aid", exploded: true },
              chart.render();
}
          }]
        });
        $('#categoryPieChart').append(chart);
      }
