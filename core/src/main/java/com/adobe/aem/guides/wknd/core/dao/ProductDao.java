// Code reference = André Monteiro Fernandes Lima
package com.adobe.aem.guides.wknd.core.dao;


import com.adobe.aem.guides.wknd.core.models.Product;

import java.util.Collection;

public interface ProductDao {

    void insertProduct(String productId, String productName, String productDescription, String productPrice);

    Product searchProduct(String productId);

    void deleteProduct(String productId);

    void updateProduct(Product product);

    Collection<Product> getProducts();

}