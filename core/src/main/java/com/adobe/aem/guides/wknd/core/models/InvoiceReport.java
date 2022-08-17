package com.adobe.aem.guides.wknd.core.models;

public class InvoiceReport {

    private String invoiceNumber;
    private String invoiceProductId;
    private String invoiceClientId;
    private String invoicePrice;

    public InvoiceReport(String invoiceNumber, String invoiceProductId, String invoiceClientId, String invoicePrice) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceProductId = invoiceProductId;
        this.invoiceClientId = invoiceClientId;
        this.invoicePrice = invoicePrice;
    }

    public InvoiceReport() {
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceProductId() {
        return invoiceProductId;
    }

    public void setInvoiceProductId(String invoiceProductId) {
        this.invoiceProductId = invoiceProductId;
    }

    public String getInvoiceClientId() {
        return invoiceClientId;
    }

    public void setInvoiceClientId(String invoiceClientId) {
        this.invoiceClientId = invoiceClientId;
    }

    public String getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(String invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public ProductReportDTO generateDTO(){

        return new ProductReportDTO(getInvoiceNumber(), getInvoiceProductId(), getInvoiceClientId(), getInvoicePrice());
    }

    @Override
    public String toString() {
        return "InvoiceReport{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", invoiceProductId='" + invoiceProductId + '\'' +
                ", invoiceClientId='" + invoiceClientId + '\'' +
                ", invoicePrice='" + invoicePrice + '\'' +
                '}';
    }
}


