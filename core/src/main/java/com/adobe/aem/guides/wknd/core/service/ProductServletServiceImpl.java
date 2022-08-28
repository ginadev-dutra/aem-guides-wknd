// Code reference = André Monteiro Fernandes Lima
package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.dao.ProductDao;
import com.adobe.aem.guides.wknd.core.models.Client;
import com.adobe.aem.guides.wknd.core.models.Message;
import com.adobe.aem.guides.wknd.core.models.Product;
import com.adobe.aem.guides.wknd.core.models.ProductDTO;
import com.google.gson.Gson;
import org.apache.tika.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.Collection;

@Component(immediate = true, service = ProductServletService.class)
public class ProductServletServiceImpl implements ProductServletService {

    @Reference
    private ProductDao productDao;


    private void insert(String productId, String productName, String productDescription, String productPrice) {

        productDao.insertProduct(productId, productName, productDescription, productPrice);
    }

    @Override
    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String productPrice = request.getParameter("productPrice");
        String message;
        String userPostString = null;

        try {
            userPostString = IOUtils.toString(request.getReader());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (productId != null && !productId.isEmpty() && productName != null && !productName.isEmpty()
                && productDescription != null && !productDescription.isEmpty() && productPrice != null && !productPrice.isEmpty()) {

            Product productConverter = new Product(productId, productName, productDescription, productPrice);
            try {
                productConverter = new Gson().fromJson(userPostString, Product.class);
                productDao.insertProduct(productId, productName, productDescription, productPrice);

            } catch (Exception e) {
                try {
                    response.setStatus(400);
                    response.getWriter().write(new Gson().toJson(new Message("This is not a json")));
                    return;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            response.setStatus(200);
            response.getWriter().write(new Gson().toJson(new Message("The product has been registered!")));
            return;
        } else {
            response.setStatus(400);
            response.getWriter().write(new Gson().toJson(new Message("The product has not been registered!")));
            return;
        }
    }

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        if(request.getParameter("productId") != null){
            String idString = request.getParameter("productId");
            int id = 0;

            if(!(idString.isEmpty() || idString == null)){
                try{
                    id = Integer.parseInt(idString);
                } catch (NumberFormatException e){
                    response.setStatus(400);
                    response.getWriter().write(new Gson().toJson(new Message("Parameter must be an int")));
                    return;
                }
            }else{
                response.setStatus(400);
                response.getWriter().write(new Gson().toJson(new Message("Parameter can't be null")));
                return;
            };
            try{
                String clients = listProductById(id);
                response.getWriter().write(clients);
            } catch (IOException e){
                response.setStatus(400);
                throw new RuntimeException(new Gson().toJson(String.valueOf(new Message(e.getMessage()))));
            }
        } else{
            String clients = listProducts();
            try{
                response.getWriter().write(clients);
            } catch (Exception e){
                response.setStatus(400);
                throw new RuntimeException(new Gson().toJson(String.valueOf(new Message(e.getMessage()))));
            }
        }
    }
    @Override
    public void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String productId = request.getParameter("productId");
        String message;
        try{
            if(productId != null && !productId.isEmpty()){
                Product product = new Product(productId);
                productDao.deleteProduct(productId);
                response.setStatus(200);
                response.getWriter().write(new Gson().toJson(new Message("The product has been deleted!")));
                return;
            } else{
                response.setStatus(400);
                response.getWriter().write(new Gson().toJson(new Message("The product has not been deleted!")));
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String productPrice = request.getParameter("productPrice");
        String message;
        try{
            if(productId != null && !productId.isEmpty() && productName != null && !productName.isEmpty()
                    && productDescription != null && !productDescription.isEmpty() && productPrice != null && !productPrice.isEmpty()){
                Product product = new Product(productId, productName, productDescription, productPrice);
                productDao.updateProduct(product);
                response.setStatus(200);
                response.getWriter().write(new Gson().toJson(new Message("The product has been changed!")));
                return;
            }else {
                response.setStatus(400);
                response.getWriter().write(new Gson().toJson(new Message("The product has not been changed!")));
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String listProductById(int id) {
        Product product = null;
        try{
            product = productDao.searchProduct(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Gson().toJson(product);
    }
    @Override
    public String listProducts() {
        Collection<Product> product = null;
        try{
            product = productDao.getProducts();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Gson().toJson(product);
    }
    private ProductDTO getDTO(int productId) {
        Product product = productDao.searchProduct(productId);
        ProductDTO dto = new ProductDTO(product.getProductId(), product.getProductName(), product.getProductDescription(), product.getProductPrice());
        return dto;
    }

    @Override
    public Collection<Product> getAllProducts() {
        return productDao.getProducts();
    }


}