import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class Tea extends Drink {
    private TeaType type;


    public TeaType getType() {
        return type;
    }

    public void setType(TeaType type) {
        this.type = type;
    }

    @ConstructorProperties("")
    public Tea(Node node) {
        super(node.getAttributes().getNamedItem("name").getNodeValue(),Integer.parseInt(node.getAttributes().getNamedItem("count").getNodeValue()),
                Integer.parseInt(node.getAttributes().getNamedItem("price").getNodeValue()));
        Node tea = node.getParentNode().getPreviousSibling().getNextSibling();
        this.type = TeaType.valueOf(tea.getAttributes().getNamedItem("teaType").getNodeValue());
    }

    public Tea(String name, int count, int price, TeaType type) {
        super(name, count, price);
        this.type = type;
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
        Tea tea = (Tea) o;
        return type == tea.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}
