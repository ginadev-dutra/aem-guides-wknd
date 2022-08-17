/*
// Reference Code = Curso Java WEB (Java EE) - Criando relatórios em PDF com a biblioteca itextpdf - #15
package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.dao.InvoiceReportDao;
import com.adobe.aem.guides.wknd.core.models.InvoiceReport;
import com.adobe.aem.guides.wknd.core.models.InvoiceReportDTO;
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
import java.util.Collection;

import com.itextpdf.text.Document;

@Component(immediate = true, service = InvoiceReportServletService.class)
public class InvoiceReportServletServiceImpl implements InvoiceReportServletService {

    @Reference
    private InvoiceReportDao invoiceReportDao;

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
    ArrayList<InvoiceReport> list = new ArrayList<InvoiceReport>();
        response.setContentType("application/json");
        if (request.getParameter("id") != null) {
            String idString = request.getParameter("id");
            String invoiceId = null;
            try {
                response.getWriter().write(invoiceId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (invoiceReportDao.getInvoices() != null) {
                ArrayList<InvoiceReport> invoiceReport = invoiceReportDao.getInvoices();
                String allInvoices = new Gson().toJson(invoiceReport);

                try {
                    response.getWriter().write(allInvoices);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void generateInvoiceReport(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        Document document = new Document();
        try {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline;filename=" + "invoices.pdf");
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new Paragraph("Invoice List:"));
            document.add(new Paragraph(""));
            PdfPTable table = new PdfPTable(4);
            PdfPCell col1 = new PdfPCell(new Paragraph("invoiceNumber"));
            PdfPCell col2 = new PdfPCell(new Paragraph("invoiceProductId"));
            PdfPCell col3 = new PdfPCell(new Paragraph("invoiceClientId"));
            PdfPCell col4 = new PdfPCell(new Paragraph("invoicePrice"));

            table.addCell(col1);
            table.addCell(col2);
            table.addCell(col3);
            table.addCell(col4);

            ArrayList<InvoiceReport> invoiceList = invoiceReportDao.getInvoices();
            for(int i = 0; i < invoiceList.size(); i++){
                table.addCell(invoiceList.get(i).getInvoiceNumber());
                table.addCell(invoiceList.get(i).getInvoiceProductId());
                table.addCell(invoiceList.get(i).getInvoiceClientId());
                table.addCell(invoiceList.get(i).getInvoicePrice());
            }
            document.add(table);
            document.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
    private InvoiceReportDTO getDTO(String invoiceNumber, String invoiceClientId, String invoiceProductId, String invoicePrice) {
        ArrayList<InvoiceReport> invoiceReport = invoiceReportDao.getInvoices();
        InvoiceReportDTO dto = new InvoiceReportDTO(invoiceReport.getInvoiceNumber(), invoiceReport,getInvoiceClientId, invoiceReport.getInvoiceProductId(), invoiceReport.getInvoicePrice);
        return dto;
    }
    @Override
    public Collection<InvoiceReport> getAllInvoices() {

        return invoiceReportDao.getInvoices();
    }
}


*/


