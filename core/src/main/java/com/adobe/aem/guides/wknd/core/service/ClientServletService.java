package com.adobe.aem.guides.wknd.core.service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;

public interface ClientServletService {

    void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

   void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;
}