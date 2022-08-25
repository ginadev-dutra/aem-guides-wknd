package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.api.resource.Resource;


@Model(adaptables = Resource.class)
public class Message {

    private String message;

    public Message(String message) {
        this.message = message;
    }
}
