// 수정 버튼
var stockCorModal = document.getElementById("stockCorModal");
var stockCorBtn = document.getElementById("stockCorBtn");
var stockCorClose = document.getElementsByClassName("stockCorClose")[0];
stockCorBtn.onclick = function () {
    stockCorModal.style.display = "block";
}
stockCorClose.onclick = function () {
    stockCorModal.style.display = "none";
}
window.onclick = function (event) {
    if (event.target == stockCorModal) {
        stockCorModal.style.display = "none";
    }
}

$(document).ready(function () {
    $("#datepicker").datepicker();
});
