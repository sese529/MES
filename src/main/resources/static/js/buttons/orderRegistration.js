// 등록 버튼
var modal = document.getElementById("myModal");
var btn = document.getElementById("openModalBtn");
var span = document.getElementsByClassName("regClose")[0];

// 등록 버튼 클릭 시 모달 표시 / input 요소에 현재 날짜와 시간 설정
btn.onclick = function () {
  var currentDate = new Date();
  currentDate.setHours(currentDate.getHours() + 9);
  var currentDateTime = currentDate.toISOString().slice(0, 16);
  document.getElementById("regOrderCurrentTimeInput").value = currentDateTime;

  modal.style.display = "block";
}

// 취소 버튼 눌렀을 때 모달 사라짐
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
