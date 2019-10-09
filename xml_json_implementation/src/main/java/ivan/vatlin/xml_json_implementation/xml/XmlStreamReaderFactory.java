package ivan.vatlin.xml_json_implementation.xml;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

public class XmlStreamReaderFactory {
    private XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

    public XMLStreamReader getXmlStreamReader(String pathToXml) throws XMLStreamException {
        InputStream inputStream = getClass().getResourceAsStream(pathToXml);
        return xmlInputFactory.createXMLStreamReader(inputStream);
    }
}
