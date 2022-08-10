package com.adobe.aem.guides.wknd.core.dao;


import com.adobe.aem.guides.wknd.core.models.Cliente;

import java.sql.*;

public interface ClienteDao {

    void inserirCliente(String idCliente, String nomeCliente);
    void consultarCliente(String idCliente);
    void deletarClientePorId(String idCliente);
    void alteraCliente(Cliente cliente);

}
