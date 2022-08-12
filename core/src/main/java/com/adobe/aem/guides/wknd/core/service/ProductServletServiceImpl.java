package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.dao.ProductDao;
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
                    response.getWriter().write("This is not a json!");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            message = "The product has been registered!";
            response.getWriter().write(message);
        } else {
            message = "The product has not been registered!";
            response.getWriter().write(message);
        }
    }
    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        if (request.getParameter("productId") != null) {
            String idString = request.getParameter("productId");
            String productId = null;
            try {
                response.getWriter().write(new Gson().toJson(getDTO(productId)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (productDao.getProducts() != null) {
                Collection<Product> words = productDao.getProducts();
                String allProducts= new Gson().toJson(words);
                try {
                    response.getWriter().write(allProducts);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        String message = "The product has been deleted!";
        String productId = request.getParameter("productId");
        Product product = new Product(productId);
        productDao.deleteProduct(productId);
        response.getWriter().write(message);

    }

    @Override
    public void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        String message = "The product has been changed!";
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String productPrice = request.getParameter("productPrice");

        Product product = new Product(productId, productName, productDescription, productPrice);
        productDao.updateProduct(product);
        response.getWriter().write(message);
    }

    private ProductDTO getDTO(String productId) {
        Product product = productDao.searchProduct(productId);
        ProductDTO dto = new ProductDTO(product.getProductId(), product.getProductName(), product.getProductDescription(), product.getProductPrice());
        return dto;
    }

    @Override
    public Collection<Product> getAllProducts() {
        return productDao.getProducts();
    }


}

