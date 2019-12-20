import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class Coffee extends Drink {
    private CoffeeType type;

    public CoffeeType getType() {
        return type;
    }

    public void setType(CoffeeType type) {
        this.type = type;
    }

    public Coffee() {
    }

    public Coffee(String name, int count, int price, CoffeeType type) {
        super(name, count, price);
        this.type = type;
    }

    @ConstructorProperties("")
    public Coffee(Node node) {
        super(node.getAttributes().getNamedItem("name").getNodeValue(),Integer.parseInt(node.getAttributes().getNamedItem("count").getNodeValue()),
                Integer.parseInt(node.getAttributes().getNamedItem("price").getNodeValue()));
        Node coffee = node.getParentNode().getPreviousSibling().getNextSibling();
        this.type = CoffeeType.valueOf(coffee.getAttributes().getNamedItem("coffeeType").getNodeValue());
    }

    @Override
    public String toString() {
        return super.toString() +
                "type=" + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Coffee coffee = (Coffee) o;
        return type == coffee.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}
