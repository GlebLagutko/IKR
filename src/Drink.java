import java.util.Objects;

public class Drink implements Comparable<Drink> {
    private String name;
    private int count;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String naem) {
        this.name = naem;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Drink(String name, int count, int price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public Drink() {
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price ;
    }

    @Override
    public int compareTo(Drink o) {
        return price - o.price == 0 ? name.compareTo(o.name) : -(price - o.price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return count == drink.count &&
                price == drink.price &&
                Objects.equals(name, drink.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, count, price);
    }
}
