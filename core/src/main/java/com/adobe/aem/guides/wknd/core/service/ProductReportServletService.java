package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.models.ProductReport;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public interface ProductReportServletService {


    void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void generateProductReport(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    Collection<ProductReport> getAllProducts();
}
