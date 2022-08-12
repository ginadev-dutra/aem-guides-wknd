package com.adobe.aem.guides.wknd.core.models;

public class Invoice {

    private String invoiceId;
    private String invoiceClientId;
    private String invoicePrice;

    public Invoice(String invoiceId, String invoiceClientId, String invoicePrice) {
        this.invoiceId = invoiceId;
        this.invoiceClientId = invoiceClientId;
        this.invoicePrice = invoicePrice;
    }

    public Invoice(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Invoice() {
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
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

        return new InvoiceDTO(getInvoiceId(), getInvoiceClientId(), getInvoicePrice());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId='" + invoiceId + '\'' +
                ", invoiceClientId='" + invoiceClientId + '\'' +
                ", invoicePrice='" + invoicePrice + '\'' +
                '}';
    }
}
