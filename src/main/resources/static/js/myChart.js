new Chart(document.getElementById('myPieChart'), {
  type: 'pie',
  data: {
    labels: ['Jan', 'Feb', 'Mar'],
    datasets: [{
      label: 'My First dataset',
      backgroundColor: ['#3e95ccd', '#8e5ea2', '#3cba9f'],
      borderColor: 'rgb(255, 99, 132)',
      data: [15, 10, 5],
    }]
  },
  oprtions: {
    animations: {
      tension: {
        duration: 1000,
        easing: 'linear',
        from: 1,
        to: 0,
        loop: true
      }
    },
    scales: {
      y: { // defining min and max so hiding the dataset does not change scale range
        min: 0,
        max: 100
      }
    }
  }
});
