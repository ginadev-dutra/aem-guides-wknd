// Code reference = André Monteiro Fernandes Lima
package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.dao.InvoiceDao;
import com.adobe.aem.guides.wknd.core.models.Invoice;
import com.adobe.aem.guides.wknd.core.models.InvoiceDTO;
import com.adobe.aem.guides.wknd.core.models.Message;
import com.google.gson.Gson;
import org.apache.tika.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.Collection;

@Component(immediate = true, service = InvoiceServletService.class)
public class InvoiceServletServiceImpl implements InvoiceServletService {

    @Reference
    private InvoiceDao invoiceDao;

    private void insert(String invoiceNumber, String invoiceClientId, String invoiceProductId, String invoicePrice) {

        invoiceDao.insertInvoice(invoiceNumber, invoiceClientId, invoiceProductId, invoicePrice);
    }

    @Override
    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String invoiceNumber = request.getParameter("invoiceNumber");
        String invoiceClientId = request.getParameter("invoiceClientId");
        String invoiceProductId = request.getParameter("invoiceProductId");
        String invoicePrice = request.getParameter("invoicePrice");
        String message;
        String userPostString = null;

        try {
            userPostString = IOUtils.toString(request.getReader());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (invoiceNumber != null && !invoiceNumber.isEmpty() && invoiceClientId != null && !invoiceClientId.isEmpty()
                && invoiceProductId != null && !invoiceProductId.isEmpty() && invoicePrice != null && !invoicePrice.isEmpty()) {

            Invoice invoiceConverter = new Invoice(invoiceNumber, invoiceClientId, invoiceProductId, invoicePrice);
            try {
                invoiceConverter = new Gson().fromJson(userPostString, Invoice.class);
                invoiceDao.insertInvoice(invoiceNumber, invoiceClientId, invoiceProductId, invoicePrice);

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
            response.getWriter().write(new Gson().toJson(new Message("The invoice has been generated!")));
            return;
        } else {
            response.setStatus(400);
            response.getWriter().write(new Gson().toJson(new Message("The invoice has not been generated!")));
            return;
        }
    }

    // Code reference = Matheus Fortes
    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        if (request.getParameter("invoiceNumber") != null) {
            String idString = request.getParameter("invoiceNumber");
            int id = 0;

            if (!(idString.isEmpty() || idString == null)) {
                try {
                    id = Integer.parseInt(idString);
                } catch (NumberFormatException e) {
                    response.setStatus(400);
                    response.getWriter().write(new Gson().toJson(new Message("Parameter must be an int")));
                    return;
                }
            } else {
                response.setStatus(400);
                response.getWriter().write(new Gson().toJson(new Message("Parameter can't be null")));
                return;
            }
            ;
            try {
                String clients = listInvoiceById(id);
                response.getWriter().write(clients);
            } catch (IOException e) {
                response.setStatus(400);
                throw new RuntimeException(new Gson().toJson(String.valueOf(new Message(e.getMessage()))));
            }
        } else {
            String clients = listInvoices();
            try {
                response.getWriter().write(clients);
            } catch (Exception e) {
                response.setStatus(400);
                throw new RuntimeException(new Gson().toJson(String.valueOf(new Message(e.getMessage()))));
            }
        }
    }
    @Override
    public void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String invoiceNumber = request.getParameter("invoiceNumber");
        String message;
        try {
            if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
                Invoice invoice = new Invoice(invoiceNumber);
                invoiceDao.deleteInvoice(invoiceNumber);
                response.setStatus(200);
                response.getWriter().write(new Gson().toJson(new Message("The invoice has been deleted!")));
                return;
            } else {
                response.setStatus(400);
                response.getWriter().write(new Gson().toJson(new Message("The invoice has not been deleted!")));
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String invoiceNumber = request.getParameter("invoiceNumber");
        String invoiceProductId = request.getParameter("invoiceProductId");
        String invoiceClientId = request.getParameter("invoiceClientId");
        String invoicePrice = request.getParameter("invoicePrice");
        String message;
        try {
            if (invoiceNumber != null && !invoiceNumber.isEmpty() && invoiceClientId != null && !invoiceClientId.isEmpty()
                    && invoiceProductId != null && !invoiceProductId.isEmpty() && invoicePrice != null && !invoicePrice.isEmpty()) {
                Invoice invoice = new Invoice(invoiceNumber, invoiceProductId, invoiceClientId, invoicePrice);
                invoiceDao.updateInvoice(invoice);
                response.setStatus(200);
                response.getWriter().write(new Gson().toJson(new Message("The invoice has been changed!")));
                return;
            } else {
                response.setStatus(400);
                response.getWriter().write(new Gson().toJson(new Message("The invoice has not been changed!")));
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String listInvoiceById(int id) {
        Invoice invoice = null;
        try {
            invoice = invoiceDao.searchInvoice(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Gson().toJson(invoice);
    }
    @Override
    public String listInvoices() {
        Collection<Invoice> invoice = null;
        try {
            invoice = invoiceDao.getInvoices();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Gson().toJson(invoice);
    }
    private InvoiceDTO getDTO(int invoiceNumber) {
        Invoice invoice = invoiceDao.searchInvoice(invoiceNumber);
        InvoiceDTO dto = new InvoiceDTO(invoice.getInvoiceNumber(), invoice.getInvoiceProductId(), invoice.getInvoiceClientId(), invoice.getInvoicePrice());
        return dto;
    }

    @Override
    public Collection<Invoice> getAllInvoices() {
        return invoiceDao.getInvoices();
    }
}
