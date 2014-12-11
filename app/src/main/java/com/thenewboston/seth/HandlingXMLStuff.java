package com.thenewboston.seth;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by 22707561 on 12/11/2014.
 */
public class HandlingXMLStuff extends DefaultHandler {
    XMLDataCollected info = new XMLDataCollected();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("city")){
            String city = attributes.getValue("name").toString();
            info.setCity(city);
        }else if (localName.equals("temperature")) {
            String t = attributes.getValue("value");
            float temp = Float.parseFloat(t);
            info.setTemp(temp);
        }
    }

    public String getInformation() {
        return info.dataToString();
    }
}
