var top_div = document.getElementById("top_div");
var bottom_div = document.getElementById("bottom_div");
var img_path = "/static/img/"

window.onload = function() {

    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = () => {

        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                let response = JSON.parse(httpRequest.response);

                response.filter(e => e.status === "AVAILABLE").forEach(function(r){

                    if(r.position > 5) {
                        bottom_div.children.item(r.position+1).querySelector("img").src = img_path + r.name +".png"
                    } else {
                        top_div.children.item(r.position+1).querySelector("img").src = img_path + r.name +".png"
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


