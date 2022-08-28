// Code reference = André Monteiro Fernandes Lima
package com.adobe.aem.guides.wknd.core.dao;


import com.adobe.aem.guides.wknd.core.models.Client;

import java.util.Collection;

public interface ClientDao {

    void insertClient(String clientId, String clientName);

    void deleteClient(String clientId);

    void updateClient(Client client);

    Client searchClient(int clientId);

    Collection<Client> getClients();

}
