// 등록 버튼
var modal = document.getElementById("myModal");
var btn = document.getElementById("openModalBtn");
var span = document.getElementsByClassName("regClose")[0];
btn.onclick = function () {
  // 현재 날짜와 시간을 계산
  var currentDate = new Date();
  var currentDateTime = currentDate.toLocaleString(); // 날짜와 시간을 문자열로 변환

  // input 요소에 현재 날짜와 시간 설정
  document.getElementById("regOrderCurrentTimeInput").value = currentDateTime;

  modal.style.display = "block";
}
span.onclick = function () {
  modal.style.display = "none";
}
window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

$(document).ready(function () {
  $("#datepicker").datepicker();
});
