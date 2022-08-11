package com.adobe.aem.guides.wknd.core.models;

public class ClientDTO {

    private String clientId;
    private String clientName;

    public ClientDTO() {
    }

    public ClientDTO(String clientId, String clientName) {
        this.clientId = clientId;
        this.clientName = clientName;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }
}
