package sy;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class JavaCrud {

    private JFrame frame;
    private JTextField txtTitle;
    private JTextField txtArtist;
    private JTextField txtDate;
    private JTextField txtRnum;
    private JTable table1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JavaCrud window = new JavaCrud();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public JavaCrud() {
        initialize();
        Connect();
        table_load();
    }

    Connection con;
    PreparedStatement pat;// An object that represents a precompiled SQL statement.
    ResultSet rs;

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");// Returns the Class object associated with the class or interface
            // with the given string name.
            // Attempts to establish a connection to the given database URL.
            // The DriverManager attempts to select an appropriate driver from the set of
            // registered JDBC drivers.
            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root", "");

        } catch (ClassNotFoundException ex) // if the class cannot be located
        {

        } catch (SQLException ex)// if a database access error occurs or the url is null
        {

        }

    }

    public void table_load() {
        try {
            pat = con.prepareStatement("select * from record1");
            rs = pat.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 981, 632);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Record Shop");
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
        lblNewLabel.setBounds(320, 31, 306, 63);
        frame.getContentPane().add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(12, 104, 467, 216);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("title");
        lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 18));
        lblNewLabel_1.setBounds(23, 39, 155, 29);
        panel.add(lblNewLabel_1);

        txtTitle = new JTextField();
        txtTitle.setBounds(219, 35, 236, 39);
        panel.add(txtTitle);
        txtTitle.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("artist");
        lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 18));
        lblNewLabel_1_1.setBounds(23, 98, 155, 29);
        panel.add(lblNewLabel_1_1);

        txtArtist = new JTextField();
        txtArtist.setColumns(10);
        txtArtist.setBounds(219, 94, 236, 39);
        panel.add(txtArtist);

        JLabel lblNewLabel_1_2 = new JLabel("date");
        lblNewLabel_1_2.setFont(new Font("굴림", Font.BOLD, 18));
        lblNewLabel_1_2.setBounds(23, 158, 155, 29);
        panel.add(lblNewLabel_1_2);

        txtDate = new JTextField();
        txtDate.setColumns(10);
        txtDate.setBounds(219, 154, 236, 39);
        panel.add(txtDate);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_1.setBounds(12, 459, 467, 126);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_1_3 = new JLabel("Record #");
        lblNewLabel_1_3.setFont(new Font("굴림", Font.BOLD, 18));
        lblNewLabel_1_3.setBounds(12, 48, 155, 29);
        panel_1.add(lblNewLabel_1_3);

        txtRnum = new JTextField();
        txtRnum.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                try {
                    String id = txtRnum.getText();

                    pat = con.prepareStatement("select title as title, artist as artist, date as date from record1 " +
                            "where id = ?");
                    pat.setString(1, id);// 물음표가 매개변수이고 첫번쨰니까=>1
                    ResultSet rs = pat.executeQuery();

                    if (rs.next() == true)// row가 한개라도 있으면
                    {
                        String title = rs.getString(1);
                        String artist = rs.getString(2);
                        String date = rs.getString(3);

                        txtTitle.setText(title);
                        txtArtist.setText(artist);
                        txtDate.setText(date);

                    } else {
                        txtTitle.setText("");
                        txtArtist.setText("");
                        txtDate.setText("");

                    }
                } catch (SQLException ex) {

                }

            }
        });
        txtRnum.setColumns(10);
        txtRnum.setBounds(208, 44, 236, 39);
        panel_1.add(txtRnum);

        JButton btnNewButton = new JButton("Save");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String rtitle, artist, date;
                rtitle = txtTitle.getText();
                artist = txtArtist.getText();
                date = txtDate.getText();

                try {
                    pat = con.prepareStatement("insert into record1(title, artist, date)values(?,?,?)");
                    pat.setString(1, rtitle);
                    pat.setString(2, artist);
                    pat.setString(3, date);
                    pat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record added!!");
                    table_load();
                    txtTitle.setText("");
                    txtArtist.setText("");
                    txtDate.setText("");
                    txtTitle.requestFocus();
                }

                catch (SQLException el) {
                    el.printStackTrace();
                }

            }
        });
        btnNewButton.setBounds(12, 353, 135, 72);
        frame.getContentPane().add(btnNewButton);

        JButton btnEdit = new JButton("Exit");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });
        btnEdit.setBounds(181, 353, 135, 72);
        frame.getContentPane().add(btnEdit);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                txtTitle.setText("");
                txtArtist.setText("");
                txtDate.setText("");
                txtTitle.requestFocus();

            }
        });
        btnClear.setBounds(344, 353, 135, 72);
        frame.getContentPane().add(btnClear);

        table1 = new JTable();
        table1.setBounds(528, 104, 400, 296);
        frame.getContentPane().add(table1);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String rtitle, artist, date, rnum;
                rtitle = txtTitle.getText();
                artist = txtArtist.getText();
                date = txtDate.getText();
                rnum = txtRnum.getText();
                try {
                    pat = con.prepareStatement("update record1 set title= ?, artist= ?, date= ? where id =?");
                    pat.setString(1, rtitle);
                    pat.setString(2, artist);
                    pat.setString(3, date);
                    pat.setString(4, rnum);
                    pat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updated!!");
                    table_load();
                    txtTitle.setText("");
                    txtArtist.setText("");
                    txtDate.setText("");
                    txtTitle.requestFocus();
                }

                catch (SQLException el) {
                    el.printStackTrace();
                }

            }
        });
        btnUpdate.setBounds(571, 434, 135, 72);
        frame.getContentPane().add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String rnum;
                rnum = txtRnum.getText();
                try {
                    pat = con.prepareStatement("delete from record1 where id =?");
                    pat.setString(1, rnum);
                    pat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleted!!");
                    table_load();

                    txtTitle.setText("");
                    txtArtist.setText("");
                    txtDate.setText("");
                    txtRnum.setText("");
                    txtTitle.requestFocus();
                }

                catch (SQLException el) {
                    el.printStackTrace();
                }

            }
        });
        btnDelete.setBounds(740, 434, 135, 72);
        frame.getContentPane().add(btnDelete);
    }
}
