function changePriceProductEnd(element){
    let productId = element.id;
    let valueNum = document.getElementById(productId).value;
    if (valueNum < 1) {
        document.getElementById(productId).value = 1;
    }
    let numProduct = valueNum < 1 ? 1 : valueNum;
    let priceProduct = document.getElementById("price" + productId).innerHTML;
    let endPrice = numProduct * priceProduct;
    document.getElementById("priceProducEnd" + productId).innerText = endPrice;
    updateProductNum(productId, numProduct);
    showBtnPlaceOrder(calkEndPrice());
}

$(document).ready(function (){
    showBtnPlaceOrder(calkEndPrice());
})

function calkEndPrice (){
    let totalPrice = 0;
    $.each(document.getElementsByName("productPriceTotal"),function(index,value) {
        totalPrice += Number(value.innerText);
    })
    document.getElementById("totalPrice").innerText = totalPrice;
    return totalPrice;
}
function deleteProduct(btnDelete){
    console.log("function delete")
    console.log("btnDelete value = " + btnDelete.value);
    $.get(
        "/shopCart/deleteProduct/" + btnDelete.value,
        function (responseData) {
            location.reload()
        })
}

function updateProductNum(productId, productNum){
    $.get(
        "/shopCart/updateProductNum/" + productId + "/" + productNum,
        function (responseData){
            console.log(responseData);
        }
    )
}

function showBtnPlaceOrder(totalPrice){
    if (totalPrice > 0) {
        document.getElementById("btnPlaceOrder").style.display = "block";
    } else {
        document.getElementById("btnPlaceOrder").style.display = "none";
    }
}

