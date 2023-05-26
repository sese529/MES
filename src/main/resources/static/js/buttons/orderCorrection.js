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
                var editInfoDate = response.editInfoDate;
                var formattedDate = editInfoDate.replace(' ', 'T');

                console.log("editInfoDate=" + formattedDate);
                document.getElementById('editOrderCurrentTimeInput').value = formattedDate;

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
                var editInfoDeadline = response.editInfoDeadline;
                var dateObj = new Date(editInfoDeadline);
                var year = dateObj.getFullYear();
                var month = ('0' + (dateObj.getMonth() + 1)).slice(-2);
                var day = ('0' + dateObj.getDate()).slice(-2);
                var hour = ('0' + dateObj.getHours()).slice(-2);
                var minute = ('0' + dateObj.getMinutes()).slice(-2);
                var formattedDate = year + '-' + month + '-' + day + ' ' + (hour < 12 ? '오전' : '오후') + ' ' + ('0' + (hour % 12 || 12)).slice(-2) + ':' + ('0' + minute).slice(-2);

                document.getElementById('editDeliveryDate').value = formattedDate;
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