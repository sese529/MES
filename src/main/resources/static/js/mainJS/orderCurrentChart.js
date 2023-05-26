google.charts.load('current', {'packages': ['gantt']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Task ID');
    data.addColumn('string', 'Task Name');
    data.addColumn('string', 'Resource');
    data.addColumn('date', 'Start Date');
    data.addColumn('date', 'End Date');
    data.addColumn('number', 'Duration');
    data.addColumn('number', 'Percent Complete');
    data.addColumn('string', 'Dependencies');

    data.addRows([
        ['1', 'ROD30', 'Resource 1', new Date(2023, 4, 1), new Date(2023, 4, 5), null, 100, null],
        ['2', 'ROD31', 'Resource 2', new Date(2023, 4, 6), new Date(2023, 4, 10), null, 50, null],
        ['3', 'ROD32', 'Resource 3', new Date(2023, 4, 11), new Date(2023, 4, 15), null, 75, null],
        ['4', 'ROD33', 'Resource 4', new Date(2023, 4, 16), new Date(2023, 4, 20), null, 80, null],
        ['5', 'ROD34', 'Resource 5', new Date(2023, 4, 21), new Date(2023, 4, 25), null, 60, null],
        ['6', 'ROD35', 'Resource 6', new Date(2023, 4, 11), new Date(2023, 4, 15), null, 75, null],
        ['7', 'ROD36', 'Resource 7', new Date(2023, 4, 16), new Date(2023, 4, 20), null, 80, null],
        ['8', 'ROD37', 'Resource 8', new Date(2023, 4, 21), new Date(2023, 4, 25), null, 60, null]
    ]);

    var options = {
        width: 800,
        height: 400,
        barHeight: 50
    };

    var chart = new google.visualization.Gantt(document.getElementById('orderCurrent'));

    chart.draw(data, options);
}