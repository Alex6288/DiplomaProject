package com.example.diploma.service;

import com.example.diploma.entity.Category;
import com.example.diploma.entity.Product;
import com.example.diploma.repas.ICategory;
import com.example.diploma.repas.IProductRep;
import com.example.diploma.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRep productRep;
    @Autowired
    ICategory categoryRep;

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    @Override
    public void addProduct(Product product) {
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRep.findById(id).get();
    }

    @Override
    public Product getProductById(Long id) {
        return productRep.getById(id);
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//        return this.productRep.findAll(pageable);
        return findPaginatedSearch(pageNo, pageSize, null, null, null, null, null);
    }

    @Override
    public Page<Product> findPaginatedSearch(int pageNo, int pageSize
            , String nameProduct
            , List<Object> brandNames
            , List<Object> categoryNames
            , String priceStart
            , String priceEnd) {
        Stream<Product> searchingProduct = productRep.findAll().stream();
        if (nameProduct != null && nameProduct != "") {
            System.out.println("по имени");
            searchingProduct = searchingProduct
                    .filter(product -> product.getProductName().toLowerCase().contains(nameProduct.toLowerCase()));
        }
        if (brandNames != null && !brandNames.isEmpty()) {
            System.out.println("по бренду");
            System.out.println(brandNames);
//            if (brandNames.size() > 1)
            searchingProduct = searchingProduct
                    .filter(product -> haveElement(brandNames, product.getBrand().getBrandName()));
        }
        if (categoryNames != null && !categoryNames.isEmpty()) {
            System.out.println("по категории");
            System.out.println(categoryNames);
//            if (categoryNames.size() > 1)
            searchingProduct = searchingProduct
                    .filter(product -> haveElement(categoryNames, product.getCategory().getCategoryName()));
        }
        if (priceStart != null && priceStart != "") {
            System.out.println("по старт");
            searchingProduct = searchingProduct.filter(product -> product.getPrice() >= Integer.parseInt(priceStart));
        }
        if (priceEnd != null && priceEnd != "") {
            System.out.println("по енд");
            searchingProduct = searchingProduct.filter(product -> product.getPrice() <= Integer.parseInt(priceEnd));
        }
        List<Product> resultList = searchingProduct.collect(Collectors.toList());

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Product> resultPage = resultList.stream()
                .skip((pageNo - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        Page<Product> page = new PageImpl<>(resultPage
                , pageable,
                resultList.size());
        return page;
    }

    private boolean haveElement(List<Object> elementSearch, String elementProduct) {
        Iterator<Object> iterator = elementSearch.iterator();
        System.out.println(" 1 elem = " + elementSearch.get(0));
        while (iterator.hasNext()) {
            if (String.valueOf(iterator.next()).equals(elementProduct)) {
                return true;
            }
        }
        return false;
    }
}
