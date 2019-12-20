import javax.swing.*;
import java.util.*;

public class FourthPanel extends JPanel {
    private List<Drink> list;
    private JTextArea output;

    public FourthPanel(List<Drink> model) {
        super();
        this.list = model;


        this.add(output);
        this.update();
    }

    public void update() {
        output.setText("");
        Map<String,Double> map =  new HashMap<>();

        Iterator<Drink> iter = list.iterator();
        while (iter.hasNext()) {
            output.append(iter.next().toString() + "\n");
        }
    }
}