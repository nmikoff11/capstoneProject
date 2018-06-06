date1

var dateOne = null;
var dateTwo = null;
dateTwo
$(document).ready(function () {
  getEntriesBetweenDates();
});
function getEntriesBetweenDates(){
  var = dateOne;
  var = dateTwo;
  $('#searchDate').click(function(event){
    dateOne = $('#datepicker1').val();
    dateTwo = $()
  $.ajax({
          type: 'GET',
            url: 'http://localhost:8080/findEntry',
            success: function(data, status){
              $.each(data, function(index, entry){
                var entryDate = entry.entryDate;
                var entryTypeId = entry.entryTypeId;
                var amount = entry.amount;
                var categoryId = entry.categoryId;

                if( entryDate >= dateOne && entryDate<= dateTwo){
                  var tableInfo = '<tr>' + '<td>' + entryDate + '</td>'
                   + '<td>' + entryTypeId + '</td>'  + '<td>' + amount + '</td>'
                    + '<td>' + entryDate + '</td>' +'</tr>';
                  $('#dateRangeTable').append(tableInfo);
                }
              }
            }
          });
});
}
