package View;

import Model.DBConnection;
import Model.DBStatements;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import net.proteanit.sql.DbUtils;

import  java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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


    private PreparedStatement getMaze;
    public SearchPanel() throws HeadlessException {

        Connection connection = DBConnection.getInstance();


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

        // Display each piece of information




        /* Action Listeners */
        // Getting info from the text box and getting results from database
        //Attempting to at least display data in console
        searchB.addActionListener (e -> {
        try {
            String sqlStatement = "SELECT * FROM user_data";
            String results;

            System.out.println(searchable.getText() + " will be added to the database, yet to be implemented");
            // Initialising connection to DB
            DBStatements statements = new DBStatements();
            // DB Statement to insert data into the database
            statements.GetAuthorOrMaze(searchable.getText());

            getMaze = connection.prepareStatement(sqlStatement);
            getMaze.executeQuery();



            System.out.println(getMaze);
            // Schedule
            // 430 - 930 mon
            // 12-3 tues thurs
            // No work wed, fri and weekend
            //


        }

        catch(Exception f)
        {
            System.out.println(f.getMessage());
        }
        });

    }



/*
    private void setTable() {
        searchB.addActionListener(e -> result.setModel(DbUtils.resultSetToTableModel(
                new DataBase().search(searchable.getText(), panel))));
    }
*/

}

