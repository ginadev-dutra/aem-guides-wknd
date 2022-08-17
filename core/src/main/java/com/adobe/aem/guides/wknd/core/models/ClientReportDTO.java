package com.adobe.aem.guides.wknd.core.models;

public class ClientReportDTO {

    private String clientId;

    private String clientName;

    public ClientReportDTO(String clientId, String clientName) {
        this.clientId = clientId;
        this.clientName = clientName;
    }

    public ClientReportDTO() {
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }
}
