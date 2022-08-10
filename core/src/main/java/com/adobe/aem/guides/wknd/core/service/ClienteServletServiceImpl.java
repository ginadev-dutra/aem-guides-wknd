package com.adobe.aem.guides.wknd.core.service;

import com.adobe.aem.guides.wknd.core.dao.ClienteDao;
import com.adobe.aem.guides.wknd.core.models.Cliente;
import com.adobe.aem.guides.wknd.core.models.ClienteDTO;
import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.tika.io.IOUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collection;

@Component(immediate = true, service = ClienteServletService.class)
public class ClienteServletServiceImpl implements ClienteServletService{

    @Reference
    private ClienteDao clienteDao;


    private void salvar(String idCliente, String nomeCliente) {
        clienteDao.inserirCliente(idCliente, nomeCliente);
    }

    @Override
    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String idCliente = request.getParameter("idCliente");
        String nomeCliente = request.getParameter("nomeCliente");
        String mensagem;


        if(idCliente != null && !idCliente.isEmpty() && nomeCliente != null && !nomeCliente.isEmpty()){
            System.out.println(idCliente + " " + nomeCliente);

            Cliente cliente = new Cliente(idCliente, nomeCliente);
            clienteDao.inserirCliente(idCliente, nomeCliente);

            mensagem = "Cliente cadastrado";
            response.getWriter().write(mensagem);
        }else {
            mensagem = "Cliente não foi cadastrado";
            response.getWriter().write(mensagem);
        }
    }

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    @Override
    public void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) {

    }

    @Override
    public void doUpdate(SlingHttpServletRequest request, SlingHttpServletResponse response) {

    }
}





    /*private ClienteDTO pegaDTO(String idCliente){
        Cliente cliente = clienteDao.consultarCliente(idCliente);

                getWord(id);
        WordDTO dto = new WordDTO(word.getId(), word.getWord());
        return dto;*/
