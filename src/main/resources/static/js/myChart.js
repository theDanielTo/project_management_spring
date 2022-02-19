var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);

var values = chartJsonArray.map(data => data.value);
var labels = chartJsonArray.map(data => data.label);

new Chart(document.getElementById('myPieChart'), {
  type: 'doughnut',
  data: {
    labels: labels,
    datasets: [{
      label: 'My First dataset',
      backgroundColor: ['#3e95cd', '#8e5ea2', '#3cba9f'],
      data: values,
    }]
  },
  options: {
    plugins: {
      title: {
        display: true,
        text: 'Project Statuses'
      }
    }
  }
});

// "[{"value": 1, "label": "COMPLETED"}, {"value": 2, "label": "INPROGRESS"}, {"value": 1, "label": "NOTSTARTED"}]"
function decodeHtml(html) {
  var txt = document.createElement("textarea");
  txt.innerHTML = html;
  return txt.value;
}
