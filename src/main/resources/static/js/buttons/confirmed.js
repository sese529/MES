document.getElementById("confirmedButton").addEventListener("click", function() {
    if (confirm("확정하시겠습니까?")) {
        // 확정 동작을 실행하는 코드 작성
        console.log("확정 실행");
        $('#confirmedForm').submit(); // 폼 제출
    } else {
        // 취소 동작을 실행하는 코드 작성
        console.log("확정 취소");
    }
});