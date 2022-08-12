package com.adobe.aem.guides.wknd.core.models;

public class ProductDTO {

    private String produtcId;
    private String productName;
    private String productDescription;
    private String productPrice;

    public ProductDTO() {
    }

    public ProductDTO(String produtcId, String productName, String productDescription, String productPrice) {
        this.produtcId = produtcId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public String getProdutcId() {
        return produtcId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }
}
