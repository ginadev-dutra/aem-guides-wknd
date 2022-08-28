// Code reference = André Monteiro Fernandes Lima
package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.models.Client;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public interface ClientServletService {

    void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;


    String listClientById(int id);

    String listClients();

    Collection<Client> getAllClients();
}