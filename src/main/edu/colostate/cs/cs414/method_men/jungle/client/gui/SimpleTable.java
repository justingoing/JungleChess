package edu.colostate.cs.cs414.method_men.jungle.client.gui;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SimpleTable extends JFrame {

    JTable table;
    JScrollPane jsp;
    TableModel model;

    SimpleTable(Object[][] rows, String[] columns){
        model = new DefaultTableModel(rows, columns);
        table = new JTable(model);
        jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    }

    //This shows a simple table so that you can see what the values do.
    //May help illuminate what this wacky code does if you see it in action.
    public static void main(String args[]){
        //Make a JFrame to house the example table in.
        JFrame jf = new JFrame();

        //Create some rows and columns
        Object rows[][] = {{"A", "B", "C"}, {"D", "E", "F"}};
        String columns[] = {"First", "Second", "Third"};

        //Call the constructor
        SimpleTable st = new SimpleTable(rows, columns);

        //Set up some JFrame stuff, and add the table to the frame.
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(new BorderLayout());
        jf.setSize(300, 200);
        jf.add(st.jsp, BorderLayout.CENTER);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
