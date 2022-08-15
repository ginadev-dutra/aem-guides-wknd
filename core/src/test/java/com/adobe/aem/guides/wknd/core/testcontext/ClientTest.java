package com.adobe.aem.guides.wknd.core.testcontext;

import com.adobe.aem.guides.wknd.core.models.Client;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    void testingClientIdAndNameGetting() {

        Client client = new Client();

        client.setClientId("5");

        client.setClientName("Roberto");

        String clientId = client.getClientId();

        String clientName = client.getClientName();

        Assert.assertNotNull(clientId);

        Assert.assertNotNull(clientName);
    }
}
