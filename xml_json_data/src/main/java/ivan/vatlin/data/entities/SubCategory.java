package ivan.vatlin.data.entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "subcategory")
public class SubCategory {
    @XmlAttribute()
    private String name;
    @XmlElement(name = "item")
    private List<Item> items;

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
