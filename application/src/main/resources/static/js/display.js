const top_div = document.getElementById("top_div");
const bottom_div = document.getElementById("bottom_div");
const img_path = "/static/img/"

window.onload = function() {

    var displayRequest = new XMLHttpRequest();
    displayRequest.onreadystatechange = () => {

        if (displayRequest.readyState === XMLHttpRequest.DONE) {
            if (displayRequest.status === 200) {
                let response = JSON.parse(displayRequest.response);

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

    displayRequest.open('GET', '/drinks/display');
    displayRequest.send();

}

function drinkDivInit(drink,divItem) {
    divItem.querySelector("img").src = img_path + drink.name +".png"
    divItem.querySelector(".product_price").value = drink.price + "원"
}