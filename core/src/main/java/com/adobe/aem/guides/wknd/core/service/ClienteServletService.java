package com.adobe.aem.guides.wknd.core.service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public interface ClienteServletService {

    void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;
    void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;
    void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response);
    void doUpdate(SlingHttpServletRequest request, SlingHttpServletResponse response);

}