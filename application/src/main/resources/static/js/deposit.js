const deposit_btn = document.querySelector(".deposit_btn");
const deposit_input = document.querySelector(".deposit_input");
const amount_input = document.querySelector(".amount_input");
// 금액 투입 버튼에 click 이벤트 추가
deposit_btn.addEventListener('click', () => {

    const amount = deposit_input.value;

    // 금액 투입 API 요청
    const httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                // 금액 투입 API 요청 성공 시, 응답 데이터를 가져와서 남은 금액을 출력
                const response = JSON.parse(httpRequest.response);
                amount_input.value = `잔액 ${response.balance}원`;
            } else {
                // 금액 투입 API 요청 실패 시, 에러 메시지 출력
                alert('서버 에러가 발생하였습니다.');
            }
        }
    };
    httpRequest.open('PUT', '/cash/deposit');
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    httpRequest.send(JSON.stringify({ amount }));
});