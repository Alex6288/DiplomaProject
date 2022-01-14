function addProductToShopCart(productId){
    $.get(
        "/shopCart/addProduct/" + productId,
        function (responseData) {
            console.log(responseData);
            $("#labelShopCart").html(responseData);
        }
    );
}