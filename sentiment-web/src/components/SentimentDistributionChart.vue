<template>
  <div :id="id" class="row justify-content-center align-items-center"></div>
</template>

<script>
  export default {
    name: 'SentimentDistributionChart',
    props: {
      id: String,
      values: Array
    },
    data: function () {
      return {
        chartId: this.id,
        distributionArray: this.values
      }
    },
    mounted() {
      this.load(this.distributionArray, this.chartId)
    },
    methods: {
      load: function (distributionArray, chartId) {
        // Load google charts
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        // Draw the chart and set the chart values
        function drawChart() {
          var data = google.visualization.arrayToDataTable([
            ['Sentiment', 'Distribution'],
            ['Very Negative', distributionArray[0]],
            ['Negative', distributionArray[1]],
            ['Neutral', distributionArray[2]],
            ['Positive', distributionArray[3]],
            ['Very Positive', distributionArray[4]],
          ]);

          // set specific chart options
          var options = {
            'width': 325,
            'height': 300,
            'colors': ['#E01B00', '#E67F00', '#ECE900', '#9CF300', '#00F601'],
            is3D: true,
            legend: 'none'
          };

          // display the chart inside the specified div element
          var chart = new google.visualization.PieChart(document.getElementById(chartId));
          chart.draw(data, options);
        }
      }
    }
  }
</script>
