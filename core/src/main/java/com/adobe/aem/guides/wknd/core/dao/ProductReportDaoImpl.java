package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.models.ProductReport;
import com.adobe.aem.guides.wknd.core.service.DataBaseService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.*;
import java.util.ArrayList;

@Component(immediate = true, service = ProductReportDao.class)
public class ProductReportDaoImpl implements ProductReportDao{

    @Reference
    private DataBaseService dataBaseService;

    private Statement stm;

    @Override
    public ArrayList<ProductReport> getProducts() {
        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "Error while trying to connect to database");
        }
        ArrayList<ProductReport> products = new ArrayList<ProductReport>();
        String sql = "SELECT * FROM PRODUCT";
        try (PreparedStatement pstm = stm.getConnection().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                String productId = rs.getString(1);
                String productName = rs.getString(2);
                String productDescription = rs.getString(3);
                String productPrice = rs.getString(4);

                products.add(new ProductReport(productId,productName, productDescription, productPrice));
            }
            f.close();
            return products;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage() + "Error while inserting clients");
        }
    }
}
