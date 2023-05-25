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

function findAddr(){
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}