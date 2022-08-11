
package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.models.Client;
import com.adobe.aem.guides.wknd.core.service.DataBaseService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.*;

@Component(immediate = true, service = ClientDao.class)
public class ClientDaoImpl implements ClientDao {

    @Reference
    private DataBaseService dataBaseService;
    private Statement stm;

    @Override
    public void insertClient(String clientId, String clientName) {

        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO CLIENT (clientId, clientName) VALUES (?, ?)";
        try (PreparedStatement pstm = stm.getConnection().prepareStatement(sql)) {
            pstm.setString(1, clientId);
            pstm.setString(2, clientName);
            pstm.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void searchClient(String clientId)  {

        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Client returnedClient = new Client();
        /*String sql = "SELECT clientId, clientName FROM CLIENT WHERE clientId = ?";*/
        String sql = "SELECT * FROM CLIENT WHERE clientId = ?";
        try(PreparedStatement pstm = stm.getConnection().prepareStatement(sql)){
            pstm.setString(1,clientId);
            pstm.execute();
            ResultSet rst = pstm.getResultSet();
            while (rst.next()) {
                String clientIdentity = rst.getString("clientId");
                String nameOfClient = rst.getString("clientName");
                returnedClient=(new Client(clientIdentity, nameOfClient));
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void deleteClient(String clientId) {

        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM CLIENT WHERE clientId = ?";
        try (java.sql.PreparedStatement pstm = stm.getConnection().prepareStatement(sql)){
            pstm.setString(1, clientId);
            pstm.execute();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateClient(Client client){

        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE CLIENT SET clientName = ? WHERE clientId = ?";
        try (PreparedStatement ps = stm.getConnection().prepareStatement(sql)) {
            ps.setString(1, client.getClientName());
            ps.setString(2, client.getClientId());
            ps.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

