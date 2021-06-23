google.charts.load('current', {'packages':['corechart']});

google.charts.setOnLoadCallback(graficoPrincipal);

function graficoPrincipal() {
      $.ajax({
        url: "http://localhost:8080/api/dashboard/pedidostotales",
        dataType: "json",
      }).done(function (jsonData) {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'descripcion');
        data.addColumn('number', 'cantidad');
        

        jsonData.forEach(function (row) {
          data.addRow([
            row.descripcion,
            row.cantidad
          ]);
        });

        var options = {
          chart: {
            width: 600,
            height: 400,
            title: 'Reporte de  Ventas',
            sliceVisibilityThreshold: 0.0001,
            legend: { position: 'top'}
          },
          bars: 'vertical' // Required for Material Bar Charts.
        };

        var formatter = new google.visualization.NumberFormat({pattern:'###,###', fractionDigits: 2} );
        formatter.format(data, 1);

        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options );
      }).fail(function (jq, text, err) {
        console.log(text + ' - ' + err);
      });
}
