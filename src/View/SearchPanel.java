package View;

import Model.DBStatements;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.time.LocalDate;

/**
 * Search panel to hold the search text bar, button and table in a separate pane on the main frame
 */
public class SearchPanel extends JPanel {
    /**
     * main panel
     */
    protected JPanel panel;
    /**
     * text panel
     */
    protected JPanel textPanel;
    /**
     * table panel
     */
    protected JPanel tablePanel;

    /**
     * used to search for mazes or authors in the database and display the results to screen
     * @throws HeadlessException a
     */
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

        scrollPane.setPreferredSize(new Dimension(570, 570));
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

        /* Action Listeners */
        // Getting info from the text box and getting results from database
        searchB.addActionListener (e -> {
            System.out.println(searchable.getText() + " will be added to the database, yet to be implimented");
            // Initialising connection to DB
            DBStatements statements = new DBStatements();
            // DB Statement to insert data into the database
            statements.GetAuthorOrMaze(searchable.getText());

            // Schedule
            // 430 - 930 mon
            // 12-3 tues thurs
            // No work wed, fri and weekend
            //
        });

    }



/*
    private void setTable() {
        searchB.addActionListener(e -> result.setModel(DbUtils.resultSetToTableModel(
                new DataBase().search(searchable.getText(), panel))));
    }
*/

}

