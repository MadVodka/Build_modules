package ivan.vatlin.data.entities;

import ivan.vatlin.data.adapters.LocalDateAdapter;
import ivan.vatlin.data.xml.XmlDataHolder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlType(name = "item")
public class Item {
    @XmlElement
    private String company;
    @XmlElement
    private String model;
    @XmlElement(name = "dateProduction")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate productionDate;
    @XmlElement
    private String color;
    @XmlElement
    private double price;
    @XmlElement
    private int quantity;

    public String getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", productionDate=" + productionDate.format(XmlDataHolder.DATE_TIME_FORMATTER) +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!company.equals(item.company)) return false;
        if (!model.equals(item.model)) return false;
        if (!productionDate.equals(item.productionDate)) return false;
        return color.equals(item.color);
    }

    @Override
    public int hashCode() {
        int result = company.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + productionDate.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }
}
