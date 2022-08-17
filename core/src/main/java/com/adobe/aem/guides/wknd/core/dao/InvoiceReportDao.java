package com.adobe.aem.guides.wknd.core.dao;

import com.adobe.aem.guides.wknd.core.models.InvoiceReport;

import java.util.ArrayList;

public interface InvoiceReportDao {



    ArrayList<InvoiceReport> getInvoices();

}
