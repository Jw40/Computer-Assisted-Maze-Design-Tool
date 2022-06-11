package View;

import javax.swing.*;
//import net.proteanit.sql.DbUtils;

import java.awt.*;

public class SearchPanel extends JPanel {
    JPanel panel, textPanel, tablePanel;
    public SearchPanel() throws HeadlessException {
        panel = new JPanel();
        panel.setLayout((new BorderLayout(10, 10)));

        JLabel titleLabel = new JLabel("Search for a maze or author name:");
        titleLabel.setPreferredSize(new Dimension(50, 30));
        titleLabel.setHorizontalAlignment(0);
        panel.add(titleLabel, BorderLayout.PAGE_START);

        textPanel = new JPanel();
        //textPanel.setPreferredSize(new Dimension(70, 20));
        textPanel.setLayout(new BorderLayout(10, 10));
        panel.add(textPanel, BorderLayout.LINE_START);

        JTextField searchable = new JTextField(15);
        //searchable.setPreferredSize(new Dimension(1, 1));
        searchable.setHorizontalAlignment(2);
        panel.add(searchable);

        JButton searchB = new JButton("Search");
        //searchB.setPreferredSize(new Dimension(70, 30));
        searchB.setHorizontalAlignment(4);
        textPanel.add(searchB,BorderLayout.LINE_START);

        tablePanel = new JPanel();

        JTable result = new JTable();
        JScrollPane scrollPane = new JScrollPane(result);



        //scrollPane.setPreferredSize(new Dimension(170, 170));
        //searchable.setSize(50,50);

        //scrollPane.setPreferredSize(new Dimension(470, 470));
        //scrollPane.setSize(200,200);
        //searchB.setSize(1,1);
        tablePanel.add(scrollPane);
        panel.add(tablePanel, BorderLayout.PAGE_END);

        add(panel);
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //panel.setLayout((new GridLayout(0,1)));

        //setTable();
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void addComponents() {

    }
/*
    private void setTable() {
        searchB.addActionListener(e -> result.setModel(DbUtils.resultSetToTableModel(
                new DataBase().search(searchable.getText(), panel))));
    }
*/

}

