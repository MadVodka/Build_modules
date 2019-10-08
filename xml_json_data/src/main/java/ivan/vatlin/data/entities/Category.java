package ivan.vatlin.data.entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "category")
public class Category {
    @XmlAttribute()
    private String name;
    @XmlElement(name = "subcategory")
    private List<SubCategory> subCategories;

    public String getName() {
        return name;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }
}