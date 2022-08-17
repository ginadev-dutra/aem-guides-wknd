/*
// Reference Code = Curso Java WEB (Java EE) - Criando relatórios em PDF com a biblioteca itextpdf - #15
package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.dao.ClientReportDao;
import com.adobe.aem.guides.wknd.core.models.ClientReport;
import com.adobe.aem.guides.wknd.core.models.ClientReportDTO;
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

@Component(immediate = true, service = ClientReportServletService.class)
public class ClientReportServletServiceImpl implements ClientReportServletService {

    @Reference
    private ClientReportDao clientReportDao;

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
    ArrayList<ClientReport> list = new ArrayList<ClientReport>();
        response.setContentType("application/json");
        if (request.getParameter("id") != null) {
            String idString = request.getParameter("id");
            String clientId = null;
            try {
                response.getWriter().write(clientId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (clientReportDao.getClients() != null) {
                ArrayList<ClientReport> clientReport = clientReportDao.getClients();
                String allClients = new Gson().toJson(clientReport);

                try {
                    response.getWriter().write(allClients);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void generateClientReport(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        Document document = new Document();
        try {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline;filename=" + "clients.pdf");
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new Paragraph("Client List:"));
            document.add(new Paragraph(""));
            PdfPTable table = new PdfPTable(2);
            PdfPCell col1 = new PdfPCell(new Paragraph("clientId"));
            PdfPCell col2 = new PdfPCell(new Paragraph("clientName"));
            table.addCell(col1);
            table.addCell(col2);
            ArrayList<ClientReport> clientList = clientReportDao.getClients();
            for(int i = 0; i < clientList.size(); i++){
                table.addCell(clientList.get(i).getClientId());
                table.addCell(clientList.get(i).getClientName());
            }
            document.add(table);
            document.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
    private ClientReportDTO getDTO(String clientId, String clientName) {
        ArrayList<ClientReport> clientReport = clientReportDao.getClients();
        ClientReportDTO dto = new ClientReportDTO(clientReport.getClientId(), clientReport.getClientName());
        return dto;
    }
    @Override
    public Collection<ClientReport> getAllClients() {

        return clientReportDao.getClients();
    }
}

*/

