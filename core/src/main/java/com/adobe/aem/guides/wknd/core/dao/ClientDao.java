package com.adobe.aem.guides.wknd.core.dao;


import com.adobe.aem.guides.wknd.core.models.Client;

public interface ClientDao {

    void insertClient(String clientId, String clientName);
    void searchClient(String clientId);
    void deleteClient(String clientId);
    void changeClient(Client client);

}
