// Code reference = André Monteiro Fernandes Lima
package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.models.Product;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public interface ProductServletService {

    void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    Collection<Product> getAllProducts();
}
