package com.adobe.aem.guides.wknd.core.servlets;



import com.adobe.aem.guides.wknd.core.service.ClienteServletService;
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
        SLING_SERVLET_PATHS + "=" + "/bin/cliente",
        SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET,
        SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_POST,
        SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_DELETE,
        SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_PUT,
        SLING_SERVLET_EXTENSIONS + "=" + "json"
})
@ServiceDescription("Servlet Serve Cliente")
public class ClienteServlet extends SlingAllMethodsServlet {
    private static final long serialVersionUID = 1L;

    @Reference
    private ClienteServletService clienteServletService;


    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException, ServletException {
        clienteServletService.doPost(request,response);
    }

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException, IOException {
        clienteServletService.doGet(request,response);
    }
    @Override
    protected void doDelete(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException, IOException {
        clienteServletService.doDelete(request,response);

    }
    @Override
    protected void doPut(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException, IOException {
        clienteServletService.doUpdate(request,response);
    }

}
