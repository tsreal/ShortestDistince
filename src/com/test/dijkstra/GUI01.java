package com.test.dijkstra;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI01 {


    JPanel panel01;
    private JLabel headLine_01;
    private JPanel JPanel2_01;
    private JLabel label_startPoint_01;
    private JLabel label_endPoint_01;
    private JTextField textField_startpoint;
    private JTextField textField_endpoint;
    private JButton button_search01;
    private JLabel label_outPut;
    private JTextArea textArea_01;

    public GUI01(){

        button_search01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField_startpoint.getText().length()==0 && textField_endpoint.getText().length()==0) {
                    textArea_01.setText("输入框不能为空");
                } else {
                    String start = textField_startpoint.getText();
                    String end = textField_endpoint.getText();
                    Subway sw = new Subway();
                    sw.calculate(new Station(start), new Station(end));
                    textArea_01.setText(sw.getOutput());


                }

            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI01");
        frame.setContentPane(new GUI01().panel01);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}



