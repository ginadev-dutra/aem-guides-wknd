// Code reference = André Monteiro Fernandes Lima
package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.models.Invoice;
import com.adobe.aem.guides.wknd.core.service.DataBaseService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Component(immediate = true, service = InvoiceDao.class)
public class InvoiceDaoImpl implements InvoiceDao {

    @Reference
    private DataBaseService dataBaseService;

    private Statement stm;

    @Override
    public void insertInvoice(String invoiceNumber, String invoiceClientId, String invoiceProductId, String invoicePrice) {

        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "Error while trying to connect to database");
        }

        String sql = "INSERT INTO INVOICE (invoiceNumber, invoiceClientId, invoiceProductId, invoicePrice) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstm = stm.getConnection().prepareStatement(sql)) {
            pstm.setString(1, invoiceNumber);
            pstm.setString(2, invoiceClientId);
            pstm.setString(3, invoiceProductId);
            pstm.setString(4, invoicePrice);
            pstm.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage() + "Error while inserting invoices");
        }
    }

    @Override
    public Invoice searchInvoice(String invoiceNumber) {

        try (Connection connection = dataBaseService.getConnection()) {
            String sql = "SELECT * FROM INVOICE WHERE invoiceNumber=?";
            Invoice invoice = new Invoice();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, invoiceNumber);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        invoice = new Invoice(rs.getString("invoiceNumber"), rs.getString("invoiceClientId"), rs.getString("invoiceProductId"), rs.getString("invoicePrice"));
                    }
                    return invoice;
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage() + "Error while getting invoices");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "Error while trying to connect to database");
        }
    }

    @Override
    public Collection<Invoice> getInvoices() {
        try (Connection connection = dataBaseService.getConnection()) {
            String sql = "SELECT * FROM INVOICE";
            Collection<Invoice> invoices = new ArrayList<Invoice>();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        invoices.add(new Invoice(rs.getString("invoiceNumber"), rs.getString("invoiceClientId"), rs.getString("invoiceProductId"), rs.getString("invoicePrice")));
                    }
                    return invoices;
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage() + "Error while getting invoices");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "Error while trying to connect to database");
        }
    }

    @Override
    public void deleteInvoice(String invoiceNumber) {

        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "Error while trying to connect to database");
        }

        String sql = "DELETE FROM INVOICE WHERE invoiceNumber = ?";
        try (java.sql.PreparedStatement pstm = stm.getConnection().prepareStatement(sql)) {
            pstm.setString(1, invoiceNumber);
            pstm.execute();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage() + "Error while deleting invoices");
        }
    }

    @Override
    public void updateInvoice(Invoice invoice) {

        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "Error while trying to connect to database");
        }

        String sql = "UPDATE INVOICE SET invoiceProductId = ?, invoiceClientId = ?, invoicePrice = ?  WHERE invoiceNumber = ?";
        try (PreparedStatement ps = stm.getConnection().prepareStatement(sql)) {
            ps.setString(1, invoice.getInvoiceProductId());
            ps.setString(2, invoice.getInvoiceClientId());
            ps.setString(3, invoice.getInvoicePrice());
            ps.setString(4, invoice.getInvoiceNumber());
            ps.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage() + "Error while updating invoices");
        }
    }
}
