// Code reference = André Monteiro Fernandes Lima
package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.models.Product;
import com.adobe.aem.guides.wknd.core.service.DataBaseService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Component(immediate = true, service = ProductDao.class)
public class ProductDaoImpl implements ProductDao {

    @Reference
    private DataBaseService dataBaseService;
    private Statement stm;

    @Override
    public void insertProduct(String productId, String productName, String productDescription, String productPrice) {

        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "Error while trying to connect to database");
        }

        String sql = "INSERT INTO PRODUCT (productId, productName, productDescription, productPrice) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstm = stm.getConnection().prepareStatement(sql)) {
            pstm.setString(1, productId);
            pstm.setString(2, productName);
            pstm.setString(3, productDescription);
            pstm.setString(4, productPrice);
            pstm.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage() + "Error while inserting products");
        }
    }

    @Override
    public Product searchProduct(int productId) {

       try(Connection connection = dataBaseService.getConnection()) {
            String sql = "SELECT * FROM PRODUCT WHERE productId=?";
            Product product = new Product();
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,productId);
                try(ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        product = new Product(rs.getString("productId"),rs.getString("productName"), rs.getString("productDescription"), rs.getString("productPrice"));
                    }
                    return product;
                }
            }catch (Exception e){
                throw new RuntimeException(e.getMessage()+"Error while getting words");
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage()+"Error while trying to connect to database");
        }
    }

    @Override
    public Collection<Product> getProducts() {
        try (Connection connection = dataBaseService.getConnection()) {
            String sql = "SELECT * FROM PRODUCT";
            Collection<Product> products = new ArrayList<Product>();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        products.add(new Product(rs.getString("productId"), rs.getString("productName"), rs.getString("productDescription"), rs.getString("productPrice")));
                    }
                    return products;
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage() + "Error while getting products");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "Error while trying to connect to database");
        }
    }

    @Override
    public void deleteProduct(String productId) {

        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "Error while trying to connect to database");
        }

        String sql = "DELETE FROM PRODUCT WHERE productId = ?";
        try (java.sql.PreparedStatement pstm = stm.getConnection().prepareStatement(sql)) {
            pstm.setString(1, productId);
            pstm.execute();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage() + "Error while deleting products");
        }
    }

    @Override
    public void updateProduct(Product product) {

        Connection f = dataBaseService.getConnection();
        try {
            stm = f.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + "Error while trying to connect to database");
        }

        String sql = "UPDATE PRODUCT SET productName = ?, productDescription = ?, productPrice = ?  WHERE productId = ?";
        try (PreparedStatement ps = stm.getConnection().prepareStatement(sql)) {
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductDescription());
            ps.setString(3, product.getProductPrice());
            ps.setString(4, product.getProductId());
            ps.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage() + "Error while updating products");
        }
    }
}
