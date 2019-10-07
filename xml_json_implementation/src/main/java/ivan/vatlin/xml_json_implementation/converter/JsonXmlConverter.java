package ivan.vatlin.xml_json_implementation.converter;

import com.google.gson.Gson;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class JsonXmlConverter {
    public <T> String convertXmlToJson(String pathToXml, Class<T> type, String elementToParse)
            throws JAXBException, XMLStreamException {

        XmlToDtoConverter<T> xmlToDtoConverter = new XmlToDtoConverter<>(pathToXml, type, elementToParse);
        T element = xmlToDtoConverter.getElement();

        Gson gson = new Gson();
        return gson.toJson(element);
    }

    public <T> String convertJsonToXmlAsString(String jsonToParse, Class<T> classType)
            throws JAXBException, IOException {
        Gson gson = new Gson();
        T object = gson.fromJson(jsonToParse, classType);

        DtoToXmlConverter<T> dtoToXmlConverter = new DtoToXmlConverter<>(classType, object);
        return dtoToXmlConverter.getXmlAsString();
    }
}
