package com.adobe.aem.guides.wknd.core.models;

public class ClientReport {

    private String clientId;

    private String clientName;

    public ClientReport(String clientId, String clientName) {
        this.clientId = clientId;
        this.clientName = clientName;
    }

    public ClientReport() {
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

}
