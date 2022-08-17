package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.models.ClientReport;
import com.adobe.aem.guides.wknd.core.service.DataBaseService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.*;
import java.util.ArrayList;

@Component(immediate = true, service = ClientReportDao.class)
public class ClientReportDaoImpl implements ClientReportDao{

    @Reference
    private DataBaseService dataBaseService;

    private Statement stm;

    @Override
    public ArrayList<ClientReport> getClients() {
        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "Error while trying to connect to database");
        }
        ArrayList<ClientReport> clients = new ArrayList<ClientReport>();
        String sql = "SELECT * FROM CLIENT";
        try (PreparedStatement pstm = stm.getConnection().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                String clientId = rs.getString(1);
                String clientName = rs.getString(2);
                clients.add(new ClientReport(clientId,clientName));
            }
            f.close();
            return clients;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage() + "Error while inserting clients");
        }
    }
}
