document.getElementById("deleteButton").addEventListener("click", function() {
    if (confirm("삭제하시겠습니까?")) {
        // 삭제 동작을 실행하는 코드 작성
        console.log("삭제 실행");
    } else {
        // 취소 동작을 실행하는 코드 작성
        console.log("삭제 취소");
    }
});