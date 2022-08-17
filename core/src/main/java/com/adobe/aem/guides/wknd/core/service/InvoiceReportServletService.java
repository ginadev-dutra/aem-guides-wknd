package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.models.InvoiceReport;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public interface InvoiceReportServletService {


    void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void generateInvoiceReport(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    Collection<InvoiceReport> getAllInvoices();
}
