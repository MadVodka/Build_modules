package ivan.vatlin.data;

import ivan.vatlin.data.entities.Shop;
import ivan.vatlin.data.xml.XmlDataHolder;
import ivan.vatlin.xml_json_implementation.converter.JsonXmlConverter;
import ivan.vatlin.xml_json_implementation.exceptions.XmlValidationException;
import ivan.vatlin.xml_json_implementation.validators.XmlValidator;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class JsonXmlApplication {
    public static void run() {
        JsonXmlConverter jsonXmlConverter = new JsonXmlConverter();

        String element = "shop";
        Class<?> classOfElement = Shop.class;

        String jsonResult = null;

        try {
            jsonResult = jsonXmlConverter.convertXmlToJson(XmlDataHolder.PATH_TO_TEST_XML, classOfElement, element);

            System.out.println("Conversion XML to JSON result:");
            System.out.println(jsonResult);
        } catch (JAXBException e) {
            System.out.println("Ошибка при создании объектов из XML файла");
        } catch (XMLStreamException e) {
            System.out.println("Ошибка при чтении файла " + XmlDataHolder.PATH_TO_TEST_XML);
        }

        System.out.println();

        try {
            String xmlResult = jsonXmlConverter.convertJsonToXmlAsString(jsonResult, classOfElement);
            System.out.println("Conversion JSON to XML result:");
            System.out.println(xmlResult);

            if (xmlResult != null) {
                XmlValidator xmlValidator = new XmlValidator();
                if (xmlValidator.isXmlValid(xmlResult, XmlDataHolder.PATH_TO_XSD)) {
                    System.out.println("Полученный xml соответствует xsd: " + XmlDataHolder.PATH_TO_XSD);
                } else {
                    System.out.println("Полученный xml не соответствует xsd: " + XmlDataHolder.PATH_TO_XSD);
                }
            }
        } catch (JAXBException e) {
            System.out.println("Ошибка при конвертации объектов в XML файл");
        } catch (IOException e) {
            System.out.println("Ошибка при выводе XML файла в консоль");
        } catch (XmlValidationException e) {
            System.out.println(e.getMessage());
        }
    }
}
