// Code reference = André Monteiro Fernandes Lima
package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.dao.ClientDao;
import com.adobe.aem.guides.wknd.core.models.Client;
import com.adobe.aem.guides.wknd.core.models.ClientDTO;
import com.google.gson.Gson;
import org.apache.tika.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import com.adobe.aem.guides.wknd.core.models.Message;


import java.io.IOException;
import java.util.Collection;

@Component(immediate = true, service = ClientServletService.class)
public class ClientServletServiceImpl implements ClientServletService {

    @Reference
    private ClientDao clientDao;


    private void insert(String clientId, String clientName) {

        clientDao.insertClient(clientId, clientName);
    }

    @Override
    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");


        String clientId = request.getParameter("clientId");
        String clientName = request.getParameter("clientName");
        String message;
        String userPostString = null;

        try {
            userPostString = IOUtils.toString(request.getReader());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (clientId != null && !clientId.isEmpty() && clientName != null && !clientName.isEmpty()) {
            System.out.println(clientId + " " + clientName);

            Client clientConverter = new Client(clientId, clientName);
            try {
                clientConverter = new Gson().fromJson(userPostString, Client.class);
                clientDao.insertClient(clientId, clientName);

            } catch (Exception e) {
                try {
                    response.setStatus(400);
                    response.getWriter().write(new Gson().toJson(new Message("This is not a json")));
                    return;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            response.setStatus(200);
            response.getWriter().write(new Gson().toJson(new Message("The client has been registered!")));
            return;
        } else {
            response.setStatus(400);
            response.getWriter().write(new Gson().toJson(new Message("The client has not been registered!")));
            return;
        }
    }

    // Code reference = Matheus Fortes
    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        if(request.getParameter("clientId") != null){
            String idString = request.getParameter("clientId");
            int id = 0;

            if(!(idString.isEmpty() || idString == null)){
                try{
                    id = Integer.parseInt(idString);
                } catch (NumberFormatException e){
                    response.setStatus(400);
                    response.getWriter().write(new Gson().toJson(new Message("Parameter must be an int")));
                    return;
                }
            }else{
                response.setStatus(400);
                response.getWriter().write(new Gson().toJson(new Message("Parameter can't be null")));
                return;
            };
            try{
                String clients = listClientById(id);
                response.getWriter().write(clients);
            } catch (IOException e){
                response.setStatus(400);
                throw new RuntimeException(new Gson().toJson(String.valueOf(new Message(e.getMessage()))));
            }
        } else{
            String clients = listClients();
            try{
                response.getWriter().write(clients);
            } catch (Exception e){
                response.setStatus(400);
                throw new RuntimeException(new Gson().toJson(String.valueOf(new Message(e.getMessage()))));
            }
        }
    }

    @Override
    public void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String clientId = request.getParameter("clientId");
        String message;
        try{
            if(clientId != null && !clientId.isEmpty()){
                Client client = new Client(clientId);
                clientDao.deleteClient(clientId);
                response.setStatus(200);
                response.getWriter().write(new Gson().toJson(new Message("The client has been deleted!")));
                return;
            }else{
                response.setStatus(400);
                response.getWriter().write(new Gson().toJson(new Message("The client has not been deleted!")));
                return;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String message;
        String clientId = request.getParameter("clientId");
        String clientName = request.getParameter("clientName");
        try{
            if(clientId != null && !clientId.isEmpty() && clientName != null && !clientName.isEmpty()){
                Client client = new Client(clientId, clientName);
                clientDao.updateClient(client);
                response.setStatus(200);
                response.getWriter().write(new Gson().toJson(new Message("The client has been changed!")));
                return;
            }else{
                response.setStatus(400);
                response.getWriter().write(new Gson().toJson(new Message("The client has not been changed!")));
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String listClientById(int id) {
        Client client = null;
        try{
            client = clientDao.searchClient(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Gson().toJson(client);
    }

    @Override
    public String listClients() {
        Collection<Client> client = null;
        try{
            client = clientDao.getClients();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Gson().toJson(client);
    }

    private ClientDTO getDTO(int clientId) {
        Client client = clientDao.searchClient(clientId);
        ClientDTO dto = new ClientDTO(client.getClientId(), client.getClientName());
        return dto;
    }

    @Override
    public Collection<Client> getAllClients() {

        return clientDao.getClients();
    }


}





