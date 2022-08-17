/*
// Reference Code = Curso Java WEB (Java EE) - Criando relatórios em PDF com a biblioteca itextpdf - #15
package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.dao.ProductReportDao;
import com.adobe.aem.guides.wknd.core.models.ProductReport;
import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;

@Component(immediate = true, service = ProductReportServletService.class)
public class ProductReportServletServiceImpl implements ProductReportServletService {

    @Reference
    private ProductReportDao productReportDao;

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
    ArrayList<ProductReport> list = new ArrayList<ProductReport>();
        response.setContentType("application/json");
        if (request.getParameter("id") != null) {
            String idString = request.getParameter("id");
            String productId = null;
            try {
                response.getWriter().write(productId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (productReportDao.getProducts() != null) {
                ArrayList<ProductReport> productReport = productReportDao.getProducts();
                String allInvoices = new Gson().toJson(productReport);

                try {
                    response.getWriter().write(allInvoices);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void generateProductReport(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        Document document = new Document();
        try {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline;filename=" + "products.pdf");
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new Paragraph("Product List:"));
            document.add(new Paragraph(""));
            PdfPTable table = new PdfPTable(4);
            PdfPCell col1 = new PdfPCell(new Paragraph("productId"));
            PdfPCell col2 = new PdfPCell(new Paragraph("productName"));
            PdfPCell col3 = new PdfPCell(new Paragraph("productDescription"));
            PdfPCell col4 = new PdfPCell(new Paragraph("productPrice"));

            table.addCell(col1);
            table.addCell(col2);
            table.addCell(col3);
            table.addCell(col4);

            ArrayList<ProductReport> productList = productReportDao.getProducts();
            for(int i = 0; i < productList.size(); i++){
                table.addCell(productList.get(i).getProductId());
                table.addCell(productList.get(i).getProductName());
                table.addCell(productList.get(i).getProductDescription());
                table.addCell(productList.get(i).getProductPrice());
            }
            document.add(table);
            document.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}

*/




