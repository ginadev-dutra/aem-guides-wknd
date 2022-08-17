package com.adobe.aem.guides.wknd.core.servlets;


import com.adobe.aem.guides.wknd.core.service.ReportClientServletService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.component.annotations.Reference;


import javax.servlet.Servlet;
import javax.servlet.ServletException;

import java.io.IOException;

import static org.apache.sling.api.servlets.ServletResolverConstants.*;

@Component(service = {Servlet.class}, property = {
        SLING_SERVLET_PATHS + "=" + "/bin/report",
        SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET,
        SLING_SERVLET_EXTENSIONS + "=" + "pdf"
})
@ServiceDescription("Servlet Serve Client")
public class ReportServlet extends SlingAllMethodsServlet {
    private static final long serialVersionUID = 1L;

    @Reference
    private ReportClientServletService reportClientServletService;


    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException, IOException {
        reportClientServletService.doGet(request, response);
    }

}