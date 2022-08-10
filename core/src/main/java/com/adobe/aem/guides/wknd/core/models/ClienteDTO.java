package com.adobe.aem.guides.wknd.core.models;

public class ClienteDTO {

    private String idCliente;
    private String nomeCliente;

    public ClienteDTO() {
    }

    public ClienteDTO(String idCliente, String nomeCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
    }

    public String getIdCliente() {

        return idCliente;
    }

    public String getNomeCliente() {

        return nomeCliente;
    }
}
