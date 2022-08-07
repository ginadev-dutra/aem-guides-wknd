package com.adobe.aem.guides.wknd.core.models;

public class Cliente {

    private String idCliente;

    private String nomeCliente;

    public Cliente(String idCliente, String nomeCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
    }

    public Cliente() {
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idcliente='" + idCliente + '\'' +
                ", nome='" + nomeCliente + '\'' +
                '}';
    }
}

