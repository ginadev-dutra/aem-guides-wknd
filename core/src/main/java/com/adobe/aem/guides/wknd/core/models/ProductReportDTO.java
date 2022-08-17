package com.adobe.aem.guides.wknd.core.models;

public class ProductReportDTO {

    private String productId;
    private String productName;
    private String productDescription;
    private String productPrice;

    public ProductReportDTO(String productId, String productName, String productDescription, String productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public ProductReportDTO() {
    }

    public String getProductId() {
        return productId;
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
