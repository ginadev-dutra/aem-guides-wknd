package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.models.InvoiceReport;
import com.adobe.aem.guides.wknd.core.service.DataBaseService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.*;
import java.util.ArrayList;

@Component(immediate = true, service = InvoiceReportDao.class)
public class InvoiceReportDaoImpl implements InvoiceReportDao{

    @Reference
    private DataBaseService dataBaseService;

    private Statement stm;

    @Override
    public ArrayList<InvoiceReport> getInvoices() {
        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "Error while trying to connect to database");
        }
        ArrayList<InvoiceReport> invoices = new ArrayList<InvoiceReport>();
        String sql = "SELECT * FROM INVOICE";
        try (PreparedStatement pstm = stm.getConnection().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                String invoiceNumber = rs.getString(1);
                String invoiceProductId = rs.getString(2);
                String invoiceClientId = rs.getString(3);
                String invoicePrice = rs.getString(4);

                invoices.add(new InvoiceReport(invoiceNumber,invoiceProductId, invoiceClientId, invoicePrice));
            }
            f.close();
            return invoices;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage() + "Error while inserting clients");
        }
    }
}
