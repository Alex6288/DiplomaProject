function showFilterBlock() {
    let filter = document.getElementById("filterBlock");
    console.log('display = ' + filter.style.display);
    if (filter.style.display == 'flex') {
        filter.style.display = 'none';
    } else {
        filter.style.display = 'flex';
    }
}

function getProductContentFixSize(pageNo, pageSize, searchData) {
    if (searchData == null || searchData == 'null')
        $.get("/productShops/" + pageNo + "/" + pageSize + "/",
            function (responseData) {
                viewProductList(responseData.productList);
                viewPagination(responseData);
            }
        )
    else
        $.post("/productShops/" + pageNo + "/" + pageSize,
            {
                searchData: searchData
            },
            function (responseData) {
                viewProductList(responseData.productList);
                viewPagination(responseData);
            }
        )
}

function getProductContent(pageNo, searchData) {

    getProductContentFixSize(pageNo, null, searchData);
}

//ajax request first start page
$(document).ready(function () {
    //get all product after first load
    let categoryId = findGetParameter("categoryId");
    if (categoryId == null) {
        let findByName = document.getElementById("findByName").value;
        $.post(
            "/productShops",
            {
                findByName: findByName
            },
            function (responseData) {
                console.log(responseData);
                viewProductList(responseData.productList);
                viewPagination(responseData);
            }
        );
    } else {
        $.get("/productShops/null/null/" + categoryId,
            function (responseData) {
                viewProductList(responseData.productList);
                viewPagination(responseData);
            })
    }
})

function findGetParameter(parameterName) {
    var result = null,
        tmp = [];
    var items = location.search.substr(1).split("&");
    for (var index = 0; index < items.length; index++) {
        tmp = items[index].split("=");
        if (tmp[0] === parameterName) result = decodeURIComponent(tmp[1]);
    }
    return result;
}

// ajax post request search
searchButton.addEventListener("click", function (event) {
    let brandNames = new Array();
    $('input[name="brandNames"]').each(function () {
        if ($(this).is(':checked'))
            brandNames.push($(this).val());
    })

    let categoryNames = new Array();
    $('input[name="categoryNames"]').each(function () {
        if ($(this).is(':checked'))
            categoryNames.push($(this).val());
    })

    let priceStart = $('input[name="priceStart"]').val()
    let priceEnd = $('input[name="priceEnd"]').val()
    let findByName = $('input[name="findByName"]').val()

    $.post(
        "/productShops",
        {
            brandNames: JSON.stringify(brandNames),
            priceStart: priceStart,
            priceEnd: priceEnd,
            categoryNames: JSON.stringify(categoryNames),
            findByName: findByName
        },
        function (responseData) {
            viewProductList(responseData.productList);
            viewPagination(responseData);
        }
    );
});

function viewProductList(productList) {
    let view = "";
    productList.forEach(function (product, i, productList) {
        view +=
            '<div class="content-products-block-item" >' +
            '<img class="content-products-block-item-foto" alt="foto prod" src="' + product.mainPhotoPath + '">' +
            '<p class="content-products-block-item-short-dscr" >' + product.productName + '</p>' +
            '<p class="content-products-block-item-rating"> Рейтинг :' + product.productRating.av_mark + '</p>' +
            '<p class="content-products-block-item-price" > Стоимость : ' + product.price + '</p>' +
            '<button onclick="addProductToShopCart(' + product.id + ')" class="content-products-block-item-add-button">В корзину</button>' +
            '</div>'
    })
    $("#content-products-block-product-list").html(view);
}

function viewPagination(responseData) {
    let view = '<div class="pagination-total-product">  Всего продуктов : ' + responseData.totalProduct + '</div>';
    view += '<div class="pagenation-pages">' +
        '<span>Страница :&nbsp;</span> <span>';
    for (let i = 1; i <= responseData.totalPages; i++) {
        if (responseData.currentPage != i) {
            view += '<button id="pag-num-' + i + '"> ' + i + '</button>';
        } else {
            view += '<button id="pag-num-' + i + '" style="color: white; background-color: black" >' + i + '</button> &nbsp;';
        }
    }
    view += '</span></div>';
    view += '<div className="pagenation-pages-next">';
    if (responseData.currentPage > 1) {
        view += '<button id="button-preview" onclick="getProductContent(' + (responseData.currentPage - 1) + ', ' + responseData.searchData + ')">Предыдущая</button>';
    } else
        view += '<button style="background-color: black; color: white">Предыдущая</button>';

    if (responseData.currentPage < responseData.totalPages)
        view += '<button id="button-next" onclick="getProductContent(' + (responseData.currentPage + 1) + ', ' + responseData.searchData + ')">Следующая</button>';
    else
        view += '<button style="background-color: black; color: white" >Следующая</button>';
    view += '</div>';
    $("#pagination").html(view);
    // add listener on pagination buttons
    for (let i = 1; i <= responseData.totalPages; i++)
        if (responseData.currentPage != i) {
            let name = "pag-num-" + i;
            $('#' + name).attr('onclick', 'getProductContent(' + i + ', \'' + JSON.stringify(responseData.searchData) + '\')');
        }
    //add listener on next-preview page button
    if (responseData.currentPage > 1) {
        $('#button-preview').attr('onclick', 'getProductContent(' + (responseData.currentPage - 1) + ', \'' + JSON.stringify(responseData.searchData) + '\')');
    }
    if (responseData.currentPage < responseData.totalPages) {
        $('#button-next').attr('onclick', 'getProductContent(' + (responseData.currentPage + 1) + ', \'' + JSON.stringify(responseData.searchData) + '\')');
    }
}


