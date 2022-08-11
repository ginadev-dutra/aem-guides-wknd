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
            try{
                clientConverter = new Gson().fromJson(userPostString, Client.class);
                clientDao.insertClient(clientId, clientName);

            }catch (Exception e){
                try {
                    response.getWriter().write("This is not a json!");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            message = "The client has been registered!";
            response.getWriter().write(message);
        } else {
            message = "The client has not been registered!";
            response.getWriter().write(message);
        }
    }

        @Override
        public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
            response.setContentType("application/json");
            if(request.getParameter("clientId")!=null) {
                String idString = request.getParameter("clientId");
                String clientId = null;
                try {
                    response.getWriter().write(new Gson().toJson(getDTO(clientId)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                if(clientDao.getClients()!=null) {
                    Collection<Client> words = clientDao.getClients();
                    String allClients = new Gson().toJson(words);
                    try {
                        response.getWriter().write(allClients);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        @Override
        public void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException{

         String message = "The client has been deleted!";
         String clientId = request.getParameter("clientId");
         Client client = new Client(clientId);
         clientDao.deleteClient(clientId);
         response.getWriter().write(message);

    }

        @Override
        public void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

          String message = "The client has been changed!";
          String clientId = request.getParameter("clientId");
          String clientName = request.getParameter("clientName");
          Client client = new Client(clientId, clientName);
          clientDao.updateClient(client);
          response.getWriter().write(message);
    }

        private ClientDTO getDTO(String clientId) {
            Client client = clientDao.searchClient(clientId);
            ClientDTO dto = new ClientDTO(client.getClientId(), client.getClientName());
            return dto;
  }
        @Override
        public Collection<Client> getAllClients() {
           return clientDao.getClients();
    }


}





