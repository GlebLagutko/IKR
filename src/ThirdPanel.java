import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ThirdPanel extends JPanel {
    private List<Drink> list;
    private JTextArea output;

    public ThirdPanel(List<Drink> model) {
        super();
        this.list = model;
        output = new JTextArea();
        this.add(output);
        this.update();
    }

    public void update() {
        output.setText("");
        List<String> temp = list.stream().map(Drink::getName).sorted().collect(Collectors.toList());
        /*temp = new ArrayList<>(list);
         Collections.sort(temp);
         */
        for (String s : temp) {
            output.append(s + "\n");
        }
    }
}