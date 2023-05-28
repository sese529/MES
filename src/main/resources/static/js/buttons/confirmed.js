document.getElementById("confirmedButton").addEventListener("click", function() {
    // TODO: 수주일이 지금보다 이전인 날짜를 찾아서 오늘 날짜로 바꾸겠냐는 알림창 띄우기
    Swal.fire({
        title: "확정하시겠습니까?",
        icon: "question",
        showCancelButton: true,
        confirmButtonText: "확정",
        cancelButtonText: "취소"
    }).then((result) => {
        if (result.isConfirmed) {
            // 확정 동작을 실행하는 코드 작성
            console.log("확정 실행");
            $('#confirmedForm').submit(); // 폼 제출
        } else {
            // 취소 동작을 실행하는 코드 작성
            console.log("확정 취소");
        }
    });
});
