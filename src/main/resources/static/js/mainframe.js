window.onload = function() {


    document.querySelectorAll(".drinkCheckBox").forEach(function (element) {
        element.addEventListener("click",() => {
            document.querySelectorAll(".drinkCheckBox").forEach(function (element) {
                element.disabled = true;
            });
        });
    });

    var httpRequest;
    document.querySelector("#itemBuyCall").addEventListener('click', () => {

        var selectedDrinkName = document.querySelector(".drinkCheckBox:checked").value;
        var amount = document.querySelector("#amount").value;

        httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = () => {

            if (httpRequest.readyState === XMLHttpRequest.DONE) {
                if (httpRequest.status === 200) {
                    let response = JSON.parse(httpRequest.response);
                    const element = document.querySelector('#receipt');
                    element.innerHTML =
                        '<h3>영수증</h3>' +
                        '<p> 구매하신 음료수 :' + response.drinkName + '</p>' +
                        '<p> 지불하신 금액 :' + response.drinkPrice + '</p>';
                } else {
                    alert('Request Error!');
                }
            }
        };

        httpRequest.open('POST', '/drink_buy.json');
        httpRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        httpRequest.send(JSON.stringify({ "drinkName": selectedDrinkName , "drinkPrice" : amount}));
    });
}


