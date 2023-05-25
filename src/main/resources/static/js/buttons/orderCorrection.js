// 수정 버튼
var editModal = document.getElementById("editModal");
var editBtn = document.getElementById("editModalBtn");
var editClose = document.getElementsByClassName("editClose")[0];

editBtn.onclick = function () {
    var checkedCnt = document.querySelectorAll('input[type="checkbox"]:checked').length;
    console.log('-----------' + checkedCnt);
    if(checkedCnt == 0)
        alert("수정할 행을 선택해주세요.");
    else if(checkedCnt > 1)
        alert("1개의 행만 선택해주세요.");
    else {
        var selectedId = document.querySelector('input[type=checkbox][name=selectedIds]:checked').value;
        console.log("checkedId=" + selectedId);
        $.ajax({
            url: '/rorder/edit',
            method: 'GET',
            data: {
                 selectedId: selectedId
            },
            success: function (response) {
                //수주일 설정

                //수주번호 설정
                var editInfoId = response.editInfoId;
                document.getElementById('editId').value = editInfoId;

                //거래처 설정
                var editInfoCustomerId = response.editInfoCustomerId;
                var customerList = document.getElementById('editCustomerId');

                for(let i = 0; i < customerList.options.length; i++) {
                    if(customerList.options[i].value == editInfoCustomerId) {
                        customerList.options[i].selected = true;
                        break;
                    }
                }

                //품목 설정
                var editInfoProductId = response.editInfoProductId;
                var productList = document.getElementById('editProductId');

                for(let i = 0; i < productList.options.length; i++) {
                    if(productList.options[i].value == editInfoProductId) {
                        productList.options[i].selected = true;
                        break;
                    }
                }

                //수주량 설정
                var editInfoCnt = response.editInfoCnt;
                document.getElementById('editCnt').value = editInfoCnt;

                //납품예정일 설정
            },
            error: function (error) {
                // 오류 처리
                console.error('-----------------AJAX 요청 실패:', error);
            }
        })
        editModal.style.display = "block";
    }
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