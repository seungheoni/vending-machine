var top_div = document.getElementById("top_div");
var bottom_div = document.getElementById("bottom_div");
var img_path = "/static/img/"

window.onload = function() {

    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = () => {

        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                let response = JSON.parse(httpRequest.response);

                response.filter(e => e.status === "AVAILABLE").forEach(function(drink){

                    if(drink.position < 5) {
                        drinkDivInit(drink,top_div.children.item(drink.position-1))
                    } else {
                        drinkDivInit(drink,bottom_div.children.item(drink.position-1))
                    }

                });

            } else {
                alert('서버 에러가 발생하였습니다.');
            }
        }
    };

    httpRequest.open('GET', '/drinks/display');
    httpRequest.send();
}

function drinkDivInit(drink,divItem) {
    divItem.querySelector("img").src = img_path + drink.name +".png"
    divItem.querySelector(".product_price").value = drink.price + "원"
}