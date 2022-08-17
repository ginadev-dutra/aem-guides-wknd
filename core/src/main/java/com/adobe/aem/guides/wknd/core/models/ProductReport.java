package com.adobe.aem.guides.wknd.core.models;

public class ProductReport {

    private String productId;
    private String productName;
    private String productDescription;
    private String productPrice;

    public ProductReport(String productId, String productName, String productDescription, String productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public ProductReport() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public ProductReportDTO generateDTO(){

        return new ProductReportDTO(getProductId(), getProductName(), getProductDescription(), getProductPrice());
    }

    @Override
    public String toString() {
        return "ProductReport{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice='" + productPrice + '\'' +
                '}';
    }
}


