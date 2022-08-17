package com.adobe.aem.guides.wknd.core.models;

public class InvoiceReportDTO {

    private String invoiceNumber;
    private String invoiceProductId;
    private String invoiceClientId;
    private String invoicePrice;

    public InvoiceReportDTO(String invoiceNumber, String invoiceProductId, String invoiceClientId, String invoicePrice) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceProductId = invoiceProductId;
        this.invoiceClientId = invoiceClientId;
        this.invoicePrice = invoicePrice;
    }

    public InvoiceReportDTO() {
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getInvoiceProductId() {
        return invoiceProductId;
    }

    public String getInvoiceClientId() {
        return invoiceClientId;
    }

    public String getInvoicePrice() {
        return invoicePrice;
    }
}
