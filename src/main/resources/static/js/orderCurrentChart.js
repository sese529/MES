// 예시 데이터
var data = [
    {name: "수주 A", startDate: new Date(2023, 0, 1), endDate: new Date(2023, 0, 10)},
    {name: "수주 B", startDate: new Date(2023, 0, 5), endDate: new Date(2023, 0, 15)},
    {name: "수주 C", startDate: new Date(2023, 0, 12), endDate: new Date(2023, 0, 20)},
    // 추가 데이터 입력
];

var canvas = document.getElementById("orderCurrent");
var ctx = canvas.getContext("2d");

// Gantt 차트 그리기
function drawGanttChart(data) {
    var barHeight = 30; // 막대 높이
    var barMargin = 10; // 막대 간격

    // 최소 날짜와 최대 날짜 계산
    var minDate = new Date(Math.min(...data.map(item => item.startDate)));
    var maxDate = new Date(Math.max(...data.map(item => item.endDate)));

    // x축과 y축 설정
    var xAxisStart = 100; // x축 시작 위치
    var yAxisStart = 50; // y축 시작 위치
    var chartWidth = canvas.width - xAxisStart - 20; // 차트의 너비
    var chartHeight = canvas.height - yAxisStart - 20; // 차트의 높이

    // x축 그리기
    ctx.beginPath();
    ctx.moveTo(xAxisStart, yAxisStart + chartHeight);
    ctx.lineTo(xAxisStart + chartWidth, yAxisStart + chartHeight);
    ctx.stroke();

    // y축 그리기
    ctx.beginPath();
    ctx.moveTo(xAxisStart, yAxisStart);
    ctx.lineTo(xAxisStart, yAxisStart + chartHeight);
    ctx.stroke();

    // 막대 그리기
    for (var i = 0; i < data.length; i++) {
        var item = data[i];
        var barY = yAxisStart + (barHeight + barMargin) * i;

        // 막대 그리기
        ctx.fillStyle = "#2196f3"; // 막대 색상
        ctx.fillRect(
            xAxisStart + (item.startDate - minDate) / (maxDate - minDate) * chartWidth,
            barY,
            (item.endDate - item.startDate) / (maxDate - minDate) * chartWidth,
            barHeight
        );

        // 막대 라벨 그리기
        ctx.fillStyle = "#000"; // 라벨 색상
        ctx.fillText(item.name, xAxisStart - 10, barY + barHeight / 2 + 5);
    }
}

drawGanttChart(data);