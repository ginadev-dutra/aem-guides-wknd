package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.models.ClientReport;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public interface ClientReportServletService {


    void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void generateClientReport(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    Collection<ClientReport> getAllClients();
}