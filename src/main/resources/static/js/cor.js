// 수정 버튼
var editModal = document.getElementById("editModal");
var editBtn = document.getElementById("editModalBtn");
var editClose = document.getElementsByClassName("editClose")[0];

editBtn.onclick = function () {
    editModal.style.display = "block";
}

editClose.onclick = function () {
    editModal.style.display = "none";
}

window.onclick = function (event) {
    if (event.target == editModal) {
        editModal.style.display = "none";
    }
}

$(document).ready(function () {
    $("#editDatepicker").datepicker();
});