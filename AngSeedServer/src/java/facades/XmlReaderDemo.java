/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import deploy.DeploymentConfiguration;
import entity.Currency;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Sindt
 */
public class XmlReaderDemo extends DefaultHandler {

    CurrencyFacade facade = new CurrencyFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document (Sax-event)");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document (Sax-event)");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        List<String> elements = new ArrayList();
        System.out.print("Element: " + localName + ": ");
        for (int i = 0; i < attributes.getLength(); i++) {
            if (localName.equals("currency")) {
                elements.add(attributes.getValue(i));
            }
            //System.out.print("[ VALUE: " + attributes.getValue(i) + "] ");
        }
        System.out.println("VAL = " + elements);
        if (!elements.isEmpty()) {
            Currency cur = new Currency();
            cur.setCode(elements.get(0));
            cur.setDesc(elements.get(1));
            if (elements.get(2).equalsIgnoreCase("-")) {
                elements.set(2, "0");
            }
            cur.setRate(Double.parseDouble(elements.get(2)));
            facade.addCurruncy(cur);

            System.out.println(elements);

        }

        System.out.println("");

        for (String element : elements) {
            //System.out.println(element + " = element ");

        }
    }

    public static void main(String[] argv) {
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReaderDemo());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
