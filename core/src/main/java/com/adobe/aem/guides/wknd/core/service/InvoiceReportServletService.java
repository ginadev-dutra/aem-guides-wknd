package com.adobe.aem.guides.wknd.core.service;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;

public interface InvoiceReportServletService {


    void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void generateInvoiceReport(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;



}
