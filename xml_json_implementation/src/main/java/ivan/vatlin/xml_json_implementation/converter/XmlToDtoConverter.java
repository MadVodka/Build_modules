package ivan.vatlin.xml_json_implementation.converter;

import ivan.vatlin.xml_json_implementation.xml.XmlStreamReaderFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class XmlToDtoConverter<T> {
    private XmlStreamReaderFactory xmlStreamReaderFactory;
    private XMLStreamReader xmlStreamReader;
    private String pathToXml;
    private Class<T> type;
    private String element;

    public XmlToDtoConverter(String pathToXml, Class<T> type, String element) {
        xmlStreamReaderFactory = new XmlStreamReaderFactory();
        this.pathToXml = pathToXml;
        this.type = type;
        this.element = element;
    }

    public List<T> getList() throws XMLStreamException, JAXBException {
        List<T> list = null;
        try {
            xmlStreamReader = xmlStreamReaderFactory.getXmlStreamReader(pathToXml);

            while (xmlStreamReader.hasNext()) {
                int event = xmlStreamReader.next();
                if (isStartElementFound(event, element)) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(getBoundDto());
                }
            }

            return list;
        } finally {
            if (xmlStreamReader != null) {
                xmlStreamReader.close();
            }
        }
    }

    public T getElement() throws XMLStreamException, JAXBException {
        try {
            xmlStreamReader = xmlStreamReaderFactory.getXmlStreamReader(pathToXml);

            while (xmlStreamReader.hasNext()) {
                int event = xmlStreamReader.next();
                if (isStartElementFound(event, element)) {
                    return getBoundDto();
                }
            }
            return null;
        } finally {
            if (xmlStreamReader != null) {
                xmlStreamReader.close();
            }
        }
    }

    public T getElementWithAttribute(String attribute, String attributeValue)
            throws XMLStreamException, JAXBException {
        try {
            xmlStreamReader = xmlStreamReaderFactory.getXmlStreamReader(pathToXml);

            while (xmlStreamReader.hasNext()) {
                int event = xmlStreamReader.next();
                if (isStartElementFound(event, element) && isAttributeFound(attribute, attributeValue)) {
                    return getBoundDto();
                }
            }
            return null;
        } finally {
            if (xmlStreamReader != null) {
                xmlStreamReader.close();
            }
        }
    }

    private boolean isStartElementFound(int event, String element) {
        return event == START_ELEMENT && element.equals(xmlStreamReader.getLocalName());
    }

    private boolean isAttributeFound(String attribute, String attributeValue) {
        return xmlStreamReader.getAttributeValue(null, attribute).equals(attributeValue);
    }

    private T getBoundDto() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement<T> jaxbElement = unmarshaller.unmarshal(xmlStreamReader, type);

        return jaxbElement.getValue();
    }
}
