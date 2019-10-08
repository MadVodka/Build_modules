package ivan.vatlin.xml_json_implementation.xml;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

public class XmlStreamReaderFactory {
    private XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

    public XMLStreamReader getXmlStreamReader(String pathToXml) throws XMLStreamException {
        StreamSource streamSource = new StreamSource(pathToXml);
        return xmlInputFactory.createXMLStreamReader(streamSource);
    }
}
