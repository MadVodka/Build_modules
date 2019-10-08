package ivan.vatlin.data.xml;

import java.time.format.DateTimeFormatter;

public class XmlDataHolder {
    public static final String PATH_TO_TEST_XML = "xml_json_data/src/main/resources/xml/shop.xml";
    public static final String PATH_TO_XSD = "xml_json_data/src/main/resources/xml/shop_schema.xsd";
    public static final String DATE_PATTERN_XML = "dd-MM-yyyy";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN_XML);

    private XmlDataHolder() {
    }
}
