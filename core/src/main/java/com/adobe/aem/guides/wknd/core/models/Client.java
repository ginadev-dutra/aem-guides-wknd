package com.adobe.aem.guides.wknd.core.models;

public class Client {

    private String clientId;
    private String clientName;

    public Client(String clientId, String clientName) {
        this.clientId = clientId;
        this.clientName = clientName;
    }

    public Client(String clientId) {

        this.clientId = clientId;
    }

    public Client() {
    }

    public String getClientId() {

        return clientId;
    }

    public void setClientId(String clientId) {

        this.clientId = clientId;
    }

    public String getClientName() {

        return clientName;
    }

    public void setClientName(String clientName) {

        this.clientName = clientName;
    }

   public ClientDTO generateDTO(){

        return new ClientDTO(getClientId(), getClientName());
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}

