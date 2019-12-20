import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.ConstructorProperties;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.*;

public class MyFrame extends JFrame {
    private List<Drink> myList;
    private JFileChooser fileChooser = new JFileChooser("C:/Users/Dell/IdeaProjects/IKR");
    private Map<String, Constructor> register = new HashMap<>();

    public MyFrame() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            setSize(700, 500);
            MyFrame.this.setLocationRelativeTo(null);
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentHidden(ComponentEvent e) {
                    System.exit(0);
                }
            });
        });
        register.put("tea", Arrays.stream(Tea.class.getConstructors()).filter(constructor -> constructor.getAnnotation(ConstructorProperties.class) != null).findAny().get());
        register.put("coffee", Arrays.stream(Coffee.class.getConstructors()).filter(constructor -> constructor.getAnnotation(ConstructorProperties.class) != null).findAny().get());

    }

    @Override
    public JRootPane createRootPane() {
        JRootPane pane = new JRootPane();
        JPanel panel = new JPanel();
        pane.setContentPane(panel);
        panel.setLayout(new BorderLayout());
        myList = new ArrayList<>();
        JMenuBar menuBar = new JMenuBar();
        JTabbedPane tabbedPane = new JTabbedPane();
        FirstPanel firstTask = new FirstPanel(myList);
        SecondPanel secondTask = new SecondPanel(myList);
        ThirdPanel thirdTask = new ThirdPanel(myList);
        tabbedPane.addTab("Task1", firstTask);
        tabbedPane.addTab("Task2", secondTask);
        tabbedPane.addTab("Task3", thirdTask);
        JMenu submenu = new JMenu("File");
        menuBar.add(submenu);
        JMenuItem open = new JMenuItem("Open");
        submenu.add(open);
        open.addActionListener(e -> {
            fileChooser.setDialogTitle("Октрытие файла");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) try {
                myList.clear();
                DocumentBuilderFactory products = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = products.newDocumentBuilder();
                Document document = builder.parse(fileChooser.getSelectedFile());
                NodeList teaElements = document.getDocumentElement().getChildNodes();

                for (int i = 0; i < teaElements.getLength(); i++) {
                    Node node = teaElements.item(i);
                    String nodeName = node.getNodeName();
                    if (nodeName.equals("tea") || nodeName.equals("coffee")) {
                        Object object = register.get(nodeName);
                        myList.add((Drink) ((Constructor) object).newInstance(node.getFirstChild().getNextSibling()));
                    }
                }


            } catch (ParserConfigurationException | SAXException | IOException | IllegalAccessException | InstantiationException | InvocationTargetException e1) {
                e1.printStackTrace();
            }
            firstTask.update();
            secondTask.update();
            thirdTask.update();
        });
        JMenu submenu1 = new JMenu("Data");
        menuBar.add(submenu1);
        JMenuItem task1 = new JMenuItem("Task1");
        JMenuItem task2 = new JMenuItem("Task2");
        JMenuItem task3 = new JMenuItem("Task3");
        task1.addActionListener(e -> {
            tabbedPane.setSelectedIndex(0);
        });
        task2.addActionListener(e -> {
            tabbedPane.setSelectedIndex(1);
        });

        task3.addActionListener(e -> {
            tabbedPane.setSelectedIndex(2);
        });

        submenu1.add(task1);
        submenu1.add(task2);
        submenu1.add(task3);


        panel.add(tabbedPane, BorderLayout.CENTER);
        panel.add(menuBar, BorderLayout.NORTH);
        return pane;
    }

}
