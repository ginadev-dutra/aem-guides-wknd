package com.adobe.aem.guides.wknd.core.models;

public class InvoiceDTO {

    private String invoiceId;
    private String invoiceProductId;
    private String invoiceClientId;
    private String invoicePrice;

    public InvoiceDTO(String invoiceId, String invoiceProductId, String invoiceClientId, String invoicePrice) {
        this.invoiceId = invoiceId;
        this.invoiceProductId = invoiceProductId;
        this.invoiceClientId = invoiceClientId;
        this.invoicePrice = invoicePrice;
    }

    public InvoiceDTO() {
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public String getInvoiceClientId() {
        return invoiceClientId;
    }

    public String getInvoiceProductId() {
        return invoiceProductId;
    }

    public String getInvoicePrice() {
        return invoicePrice;
    }
}
