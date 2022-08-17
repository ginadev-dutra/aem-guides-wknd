// Code reference = André Monteiro Fernandes Lima
package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.dao.InvoiceDao;
import com.adobe.aem.guides.wknd.core.models.Invoice;
import com.adobe.aem.guides.wknd.core.models.InvoiceDTO;
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
                    response.getWriter().write("This is not a json!");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            message = "The invoice has been generated!";
            response.getWriter().write(message);
        } else {
            message = "The invoice has not been generated!";
            response.getWriter().write(message);
        }
    }

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        if (request.getParameter("number") != null) {
            String idString = request.getParameter("number");
            String invoiceNumber = null;
            try {
                response.getWriter().write(new Gson().toJson(getDTO(invoiceNumber)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (invoiceDao.getInvoices() != null) {
                Collection<Invoice> invoices = invoiceDao.getInvoices();
                String allInvoices = new Gson().toJson(invoices);
                try {
                    response.getWriter().write(allInvoices);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
                message = "The invoice has been deleted!";
                response.getWriter().write(message);
            } else {
                message = "The invoice has not been deleted!";
                response.getWriter().write(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        public void doPut (SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
            String invoiceNumber = request.getParameter("invoiceNumber");
            String invoiceProductId = request.getParameter("invoiceProductId");
            String invoiceClientId = request.getParameter("invoiceClientId");
            String invoicePrice = request.getParameter("invoicePrice");
            String message;
            try {
                if(invoiceNumber != null && !invoiceNumber.isEmpty() && invoiceClientId != null && !invoiceClientId.isEmpty()
                        && invoiceProductId != null && !invoiceProductId.isEmpty() && invoicePrice != null && !invoicePrice.isEmpty()){
                    Invoice invoice = new Invoice(invoiceNumber, invoiceProductId, invoiceClientId, invoicePrice);
                    invoiceDao.updateInvoice(invoice);
                    message = "The invoice has been changed!";
                    response.getWriter().write(message);
                } else{
                    message = "The invoice has not been changed!";
                    response.getWriter().write(message);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private InvoiceDTO getDTO (String invoiceNumber){
            Invoice invoice = invoiceDao.searchInvoice(invoiceNumber);
            InvoiceDTO dto = new InvoiceDTO(invoice.getInvoiceNumber(), invoice.getInvoiceProductId(), invoice.getInvoiceClientId(), invoice.getInvoicePrice());
            return dto;
        }

        @Override
        public Collection<Invoice> getAllInvoices () {
            return invoiceDao.getInvoices();
        }
    }
