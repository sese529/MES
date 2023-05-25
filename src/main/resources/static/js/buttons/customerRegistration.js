// 등록 버튼
var customerRegModal = document.getElementById("customerRegModal");
var customerRegBtn = document.getElementById("customerRegBtn");
var customerRegClose = document.getElementsByClassName("customerRegClose")[0];
customerRegBtn.onclick = function () {
    customerRegModal.style.display = "block";
}
customerRegClose.onclick = function () {
    customerRegModal.style.display = "none";
}
window.onclick = function (event) {
    if (event.target == customerRegModal) {
        customerRegModal.style.display = "none";
    }
}

$(document).ready(function () {
    $("#datepicker").datepicker();
});