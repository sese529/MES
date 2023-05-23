// 등록 버튼
var stockRegModal = document.getElementById("stockRegModal");
var stockRegBtn = document.getElementById("stockRegBtn");
var stockRegClose = document.getElementsByClassName("stockRegClose")[0];
stockRegBtn.onclick = function () {
    stockRegModal.style.display = "block";
}
stockRegClose.onclick = function () {
    stockRegModal.style.display = "none";
}
window.onclick = function (event) {
    if (event.target == stockRegModal) {
        stockRegModal.style.display = "none";
    }
}

$(document).ready(function () {
    $("#datepicker").datepicker();
});
