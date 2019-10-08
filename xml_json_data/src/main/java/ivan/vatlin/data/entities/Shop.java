package ivan.vatlin.data.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "shop")
@XmlRootElement
public class Shop {
    @XmlElement(name = "category")
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }
}