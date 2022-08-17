/*
package com.adobe.aem.guides.wknd.core.testcontext;

import com.adobe.aem.guides.wknd.core.dao.ProductDao;
import com.adobe.aem.guides.wknd.core.models.Product;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;

public class ProductDataBaseTest {

    @Test
    void test(){
        // Generating class-specific clone
        ProductDao mock = Mockito.mock(ProductDao.class);
        // Pretending to look up dada from database
        Collection<Product> productFake = mock.getProducts();
        // Fake returned list
        Assert.assertTrue(productFake.isEmpty());

        Product product = new Product();
        Mockito.when(product.getProductId().thenReturn(productFake));

    }
}
*/
