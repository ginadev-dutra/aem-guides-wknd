
package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.models.Cliente;
import com.adobe.aem.guides.wknd.core.service.DataBaseService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.*;

@Component(immediate = true, service = ClienteDao.class)
public class ClienteDaoImpl implements ClienteDao {

    @Reference
    private DataBaseService dataBaseService;
    private Statement stm;

    @Override
    public void inserirCliente(String idCliente, String nomeCliente) {

        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO CLIENTE (idCliente, nomeCliente) VALUES (?, ?)";
        try (PreparedStatement pstm = stm.getConnection().prepareStatement(sql)) {
            pstm.setString(1, idCliente);
            pstm.setString(2, nomeCliente);
            pstm.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void consultarCliente(String idCliente)  {
        Cliente usuarioRetornado = new Cliente();
        String sql = "SELECT idCliente, nomeCliente FROM CLIENTE WHERE idCliente = ?";
        try(PreparedStatement pstm = stm.getConnection().prepareStatement(sql)){
            pstm.setString(1,idCliente);
            pstm.execute();
            ResultSet rst = pstm.getResultSet();
            while (rst.next()) {
                String identidadeCliente = rst.getString("idCliente");
                String nomeDoCliente = rst.getString("nomeCliente");
                usuarioRetornado=(new Cliente(identidadeCliente, nomeDoCliente));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deletarClientePorId(String idCliente) {
        try (java.sql.PreparedStatement pstm = stm.getConnection().prepareStatement("DELETE FROM CLIENTE WHERE idCliente = " +idCliente)){
            pstm.execute();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void alteraCliente(Cliente cliente){
        String sql = "UPDATE CLIENTE SET cliente.idCliente = ?, cliente.nomeCliente = ? WHERE idCliente = ?";
        try (PreparedStatement ps = stm.getConnection().prepareStatement(sql)) {
            ps.setString(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNomeCliente());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

