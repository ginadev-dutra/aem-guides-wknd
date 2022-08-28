// Code reference = André Monteiro Fernandes Lima
package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.models.Invoice;

import java.util.Collection;

public interface InvoiceDao {

    void insertInvoice(String invoiceNumber, String invoiceClientId, String invoiceProductId, String invoicePrice);

    Invoice searchInvoice(int invoiceNumber);

    void deleteInvoice(String invoiceNumber);

    void updateInvoice(Invoice invoice);

    Collection<Invoice> getInvoices();
}
