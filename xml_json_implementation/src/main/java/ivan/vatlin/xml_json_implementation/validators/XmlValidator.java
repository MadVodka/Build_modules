package ivan.vatlin.xml_json_implementation.validators;

import ivan.vatlin.xml_json_implementation.exceptions.XmlValidationException;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class XmlValidator {
    public boolean isXmlValid(String xmlAsString, String pathXsd) throws XmlValidationException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Reader readerXml = new StringReader(xmlAsString);
        File xsdFile = new File(pathXsd);
        Source sourceXsd = new StreamSource(xsdFile);
        Source sourceXml = new StreamSource(readerXml);

        try {
            Schema schema = schemaFactory.newSchema(sourceXsd);
            Validator validator = schema.newValidator();
            validator.validate(sourceXml);
            return true;
        } catch (SAXException e) {
            return false;
        } catch (IOException e) {
            throw new XmlValidationException("Ошибка во время валидации xml");
        }
    }
}
