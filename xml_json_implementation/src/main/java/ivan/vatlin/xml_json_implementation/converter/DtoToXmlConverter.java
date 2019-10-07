package ivan.vatlin.xml_json_implementation.converter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;

public class DtoToXmlConverter<T> {
    private Class<T> type;
    private T objectToParse;

    public DtoToXmlConverter(Class<T> type, T objectToParse) {
        this.type = type;
        this.objectToParse = objectToParse;
    }

    public String getXmlAsString() throws JAXBException, IOException {
        try (StringWriter stringWriter = new StringWriter()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(objectToParse, stringWriter);

            return stringWriter.toString();
        }
    }
}
