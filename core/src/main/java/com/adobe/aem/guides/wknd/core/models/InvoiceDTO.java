package com.adobe.aem.guides.wknd.core.models;

public class InvoiceDTO {

    private String invoiceId;
    private String invoiceClientId;
    private String invoicePrice;

    public InvoiceDTO(String invoiceId, String invoiceClientId, String invoicePrice) {
        this.invoiceId = invoiceId;
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

    public String getInvoicePrice() {
        return invoicePrice;
    }
}
