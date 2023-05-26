document.getElementById("confirmedButton").addEventListener("click", function() {
    //TODO:수주일이 지금보다 이전인 날짜를 찾아서 오늘 날짜로 바꾸겠냐는 알림창 띄우기

    // 체크된 체크박스의 부모 행 선택
    var checkedRows = document.querySelectorAll('input[type="checkbox"]:checked');

// 각 체크된 행에 대해 처리
    checkedRows.forEach(function (row) {
        // 체크된 체크박스의 부모 행에서 날짜 값
        var dateCell = row.closest('tr').querySelector('.date-cell');
        var dateString = dateCell.textContent; // 또는 innerText

        // 날짜 값을 JavaScript Date 객체로 변환
        var date = new Date(dateString);

        // 현재 시간과 비교
        var currentTime = new Date();
        var msg = "";
        if (date < currentTime) {
            msg = "수주일이 현재 시간보다 이전인 경우가 있습니다. 계속 진행할 시 현재 시간보다 과거인 수주일이 현재 시간으로 모두 변동됩니다. 확정하시겠습니까?";
        } else {
            msg = "확정하시겠습니까?";
        }

        if (confirm(msg)) {
            // 확정 동작을 실행하는 코드 작성
            console.log("확정 실행");
            $('#confirmedForm').submit(); // 폼 제출
        } else {
            // 취소 동작을 실행하는 코드 작성
            console.log("확정 취소");
        }
    });


    // if (confirm("확정하시겠습니까?")) {
    //     // 확정 동작을 실행하는 코드 작성
    //     console.log("확정 실행");
    //     $('#confirmedForm').submit(); // 폼 제출
    // } else {
    //     // 취소 동작을 실행하는 코드 작성
    //     console.log("확정 취소");
    // }
});