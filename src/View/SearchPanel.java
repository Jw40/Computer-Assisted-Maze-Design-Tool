package View;

import javax.swing.*;
//import net.proteanit.sql.DbUtils;

import java.awt.*;

public class SearchPanel extends JPanel {
    private JTextField searchable = new JTextField(30);
    private JButton searchB = new JButton("Search");
    private JTable result = new JTable();
    private JPanel panel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(result);

    SearchPanel() throws HeadlessException {

        setSize(600, 600);
        //setResizable(true);
        addComponents();
        //setTable();
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addComponents() {
        panel.add(searchable);
        panel.add(searchB);
        panel.add(scrollPane);
        add(panel);
    }
/*
    private void setTable() {
        searchB.addActionListener(e -> result.setModel(DbUtils.resultSetToTableModel(
                new DataBase().search(searchable.getText(), panel))));
    }

 */
}

