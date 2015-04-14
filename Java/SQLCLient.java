package SQL;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Created by Chris Medina on 3/29/2015.
 * Purpose: A GUI SQL Client to execute statements on a database and have the results show in the window.
 */
public class SQLCLient extends JFrame {
    public static final int DEFAULT_WIDTH = 1024;
    public static final int DEFAULT_HEIGHT = 768;
    private JPanel connectionPanel, commandPanel, resultPanel, comboPanel;
    private JButton clearResult, exit;

    //SQL Specifics
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private boolean connected;

    private int screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
            .getDisplayMode().getWidth();
    private int screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
            .getDisplayMode().getHeight();

    public SQLCLient () throws HeadlessException {
        super("SQL Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation((screenWidth / 2) - (DEFAULT_WIDTH / 2), (screenHeight / 2) - (DEFAULT_HEIGHT / 2));
        setResizable(false);
        setLayout(new BorderLayout(5, 5));

        connectionPanel = new ConnectionPanel();
        commandPanel = new SQLCommandPanel();
        resultPanel = new SQLResultPanel();
        clearResult = new JButton("Clear result");
        clearResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                ((SQLResultPanel) resultPanel).result.setText("");
            }
        });
        exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));

        comboPanel = new JPanel();
        comboPanel.add(connectionPanel);
        comboPanel.add(commandPanel);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(clearResult);
        btnPanel.add(exit);

        add(comboPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        setVisible(true);
        pack();
    }

    public static void main (String[] args) {
        new SQLCLient();
    }

    private class ConnectionPanel extends JPanel {
        private JLabel lbl_driver, lbl_URL, lbl_userName, lbl_passWord, lbl_status;
        private TextField tf_driver, tf_URL, tf_userName, tf_status;
        private JPasswordField jpf_passWord;
        private JButton connect, disconnect;
        private JPanel panel1, panel2, panel3, panel4, panel5, btnPanel;
        private String database;

        public ConnectionPanel () {
            setLayout(new GridLayout(6, 1, 3, 3));
            setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Enter Database Information"));

            //LABELS
            lbl_driver = new JLabel("JDBC Driver: ");
            lbl_URL = new JLabel("Database URL: ");
            lbl_userName = new JLabel("Username: ");
            lbl_passWord = new JLabel("Password: ");
            lbl_status = new JLabel("Status: ");

            //TEXT FIELDS
            tf_driver = new TextField("com.mysql.jdbc.Driver");
            tf_URL = new TextField("jdbc:mysql://localhost:3306/customer");
            tf_userName = new TextField("root");
            jpf_passWord = new JPasswordField("1mysql2root");
            tf_status = new TextField("Not connected", 30);
            tf_status.setEditable(false);

            //BUTTONS
            disconnect = new JButton("Disconnect");
            disconnect.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e) {
                    if (connected) {
                        try {
                            connection.close();
                            tf_driver.setText("");
                            tf_URL.setText("");
                            tf_userName.setText("");
                            jpf_passWord.setText("");
                            tf_status.setText("Disconnected");
                            ((SQLResultPanel) resultPanel).result.setText("");
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        connected = false;
                    }
                }
            });

            connect = new JButton("Connect to Database");
            connect.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e) {
                    try {
                        database = tf_URL.getText().substring(tf_URL.getText().lastIndexOf("/") + 1);
                        Class.forName(tf_driver.getText());
                        connection = DriverManager.getConnection(tf_URL.getText(), tf_userName.getText(), String
                                .valueOf(jpf_passWord.getPassword()));
                        connected = true;
                        statement = connection.createStatement();
                        tf_status.setText("Connected to Database: " + database);
                        ((SQLResultPanel) resultPanel).showResult("SELECT * FROM " + database);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            //PANEL PROPERTIES
            panel1.add(lbl_driver);
            panel1.add(tf_driver);

            panel2.add(lbl_URL);
            panel2.add(tf_URL);

            panel3.add(lbl_userName);
            panel3.add(tf_userName);

            panel4.add(lbl_passWord);
            panel4.add(jpf_passWord);

            panel5.add(lbl_status);
            panel5.add(tf_status);

            btnPanel.add(disconnect);
            btnPanel.add(connect);

            add(panel1);
            add(panel2);
            add(panel3);
            add(panel4);
            add(panel5);
            add(btnPanel);
        }
    }

    private class SQLCommandPanel extends JPanel {
        JTextArea txtArea;
        private JButton clear, execute;
        private JPanel btnPanel;
        private JScrollPane scrollPane;

        public SQLCommandPanel () {
            setLayout(new BorderLayout(3, 3));
            setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Enter an SQL Query"));
            btnPanel = new JPanel();
            txtArea = new JTextArea(10, 25);
            txtArea.setWrapStyleWord(true);
            txtArea.setLineWrap(true);

            clear = new JButton("Clear");
            clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e) {
                    txtArea.setText("");
                    try {
                        statement.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            execute = new JButton("Execute");
            execute.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e) {
                    if (connected) {
                        try {
                            String sqlStatement = txtArea.getText();
                            String database = ((ConnectionPanel) connectionPanel).database;
                            statement = connection.createStatement();
                            //SELECT Statement, show the result
                            if (sqlStatement.startsWith("SELECT") || sqlStatement.startsWith("select"))
                                ((SQLResultPanel) resultPanel).showResult(txtArea.getText());
                            else {
                                //Non-SELECT statement, show current table (After update, insert, or altering, etc...)
                                statement.executeUpdate(sqlStatement);
                                ((SQLResultPanel) resultPanel).showResult("SELECT * FROM " + database);
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            scrollPane = new JScrollPane(txtArea, 20, 30);
            add(scrollPane, BorderLayout.CENTER);
            btnPanel.add(clear);
            btnPanel.add(execute);
            add(btnPanel, BorderLayout.SOUTH);
        }
    }

    private class SQLResultPanel extends JPanel {
        private JTextArea result;
        private JScrollPane scrollPane;

        public SQLResultPanel () {
            setLayout(new BorderLayout(3, 3));
            setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "SQL Execution Result"));
            result = new JTextArea(15, 20);
            result.setEditable(false);

            scrollPane = new JScrollPane(result, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            setVisible(true);
            add(scrollPane, BorderLayout.CENTER);
        }

        public void showResult (String query) {
            result.setText("");
            result.setFont(new Font(Font.SERIF, Font.BOLD, 14));
            try {
                StringBuilder output = new StringBuilder();
                resultSet = statement.executeQuery(query);
                metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    output.append(String.format("%s\t", metaData.getColumnName(i).toUpperCase()));
                }

                output.append(System.lineSeparator());
                for (int i = 1; i <= columnCount; i++) {
                    double factor = metaData.getColumnName(i).length() > 2 ? 1.7 : 1;
                    double dashCount = metaData.getColumnName(i).length() * factor;
                    while (dashCount > 0) {
                        output.append("-");
                        dashCount--;
                    }
                    output.append(String.format("\t"));
                }

                while (resultSet.next()) {
                    output.append(System.lineSeparator());
                    for (int col = 1; col <= columnCount; col++)
                        output.append(String.format("%s\t", resultSet.getString(col)));
                }
                result.setText(output.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
