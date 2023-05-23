// 등록 버튼
var modal = document.getElementById("myModal");
var btn = document.getElementById("openModalBtn");
var span = document.getElementsByClassName("regClose")[0];

btn.onclick = function () {
  // input 요소에 현재 날짜와 시간 설정
  var currentDate = new Date();
  currentDate.setHours(currentDate.getHours() + 9);
  var currentDateTime = currentDate.toISOString().slice(0, 16);
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
