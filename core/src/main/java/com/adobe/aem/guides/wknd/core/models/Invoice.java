package com.adobe.aem.guides.wknd.core.models;

public class Invoice {

    private String invoiceNumber;
    private String invoiceProductId;
    private String invoiceClientId;
    private String invoicePrice;

    public Invoice(String invoiceNumber, String invoiceClientId, String invoiceProductId, String invoicePrice) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceProductId = invoiceProductId;
        this.invoiceClientId = invoiceClientId;
        this.invoicePrice = invoicePrice;
    }

    public Invoice(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Invoice() {
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

    public InvoiceDTO generateDTO(){

        return new InvoiceDTO(getInvoiceNumber(), getInvoiceClientId(), getInvoiceProductId(), getInvoicePrice());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId='" + invoiceNumber + '\'' +
                ", invoiceClientId='" + invoiceClientId + '\'' +
                ", invoicePrice='" + invoicePrice + '\'' +
                '}';
    }
}
