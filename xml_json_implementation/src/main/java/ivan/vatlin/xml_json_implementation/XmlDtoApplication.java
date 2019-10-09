package ivan.vatlin.xml_json_implementation;

import ivan.vatlin.data.entities.Category;
import ivan.vatlin.xml_json_implementation.converter.XmlToDtoConverter;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XmlDtoApplication {
    public static void run() {
        String element = "category";
        try {
            String xmlFilePath = getXmlPathFromProperty();
            System.out.println(xmlFilePath);
            XmlToDtoConverter<Category> xmlToDtoConverter = new XmlToDtoConverter<>(xmlFilePath,
                    Category.class, element);
            List<Category> categories = xmlToDtoConverter.getList();
            if (categories != null) {
                categories.forEach(XmlDtoApplication::printCategory);
            } else {
                System.out.println("Элементов " + element + " не найдено");
            }

            String attribute = "name";
            String attributeValue = "Games";
            Category category = xmlToDtoConverter.getElementWithAttribute(attribute, attributeValue);
            if (category != null) {
                System.out.println("Поиск по атрибуту " + attribute + " и значению " + attributeValue + ":\n");
                printCategory(category);
            } else {
                System.out.println("Элемент " + element + " с атрибутом "
                        + attribute + "  и значением " + attributeValue + " не найден");
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
            System.out.println("Ошибка при чтении xml файла");
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("Ошибка при создании объектов из XML файла");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Невозможно получить путь до XML файла");
        }
    }

    private static String getXmlPathFromProperty() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = XmlDtoApplication.class.getClassLoader().getResourceAsStream("xml.properties")) {
            properties.load(input);
        }

        return properties.getProperty("xml-path");
    }

    private static void printCategory(Category category) {
        System.out.println("Category: " + category.getName());

        category.getSubCategories().forEach(subCategory -> {
            System.out.println("Subcategory: " + subCategory.getName());
            System.out.println(subCategory.getItems());
        });

        System.out.println();
    }
}
