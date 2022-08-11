package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.dao.ClientDao;
import com.adobe.aem.guides.wknd.core.models.Client;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;

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


        if (clientId != null && !clientId.isEmpty() && clientName != null && !clientName.isEmpty()) {
            System.out.println(clientId + " " + clientName);

            Client client = new Client(clientId, clientName);
            clientDao.insertClient(clientId, clientName);

            message = "The client has been registered!";
            response.getWriter().write(message);
        } else {
            message = "The client has not been registered!";
            response.getWriter().write(message);
        }
    }

        @Override
        public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

            String clientId = request.getParameter("clientId");
            Client client = new Client(clientId);
            clientDao.searchClient(clientId);
            response.getWriter().write(clientId);

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
          clientDao.changeClient(client);
          response.getWriter().write(message);


    }

   /* private ClientDTO getDTO(String clientId) {
        Client client = clientDao.consultarCliente(clientId);
        ClientDTO dto = new ClientDTO(client.getClientId(), client.getClientName());
        return dto;
    }*/
}





