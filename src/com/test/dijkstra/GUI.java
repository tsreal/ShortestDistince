package com.test.dijkstra;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.Set;

import static com.test.dijkstra.BuildData.model;


public class GUI {
    private JPanel jpanel1;
    private JComboBox comboBox_startLineNum;
    private JComboBox comboBox_startStationName;
    private JComboBox comboBox_endLineNum;
    private JComboBox comboBox_endStationName;
    private JLabel headLine;
    private JLabel lineNum;
    private JLabel StationName;
    private JLabel label_startPoint;
    private JLabel label_endPoint;

    private JLabel label_outPut;
    private JButton button_Search;
    private JButton button_Input;
    private JPanel jPanel2;
    private JScrollPane scroll;
    private JTextArea textArea_Result;

    private JLabel Label_results;


    public GUI(){





        String line = (String) getLineNum()[0];
        //为下拉菜单添加内容
        comboBox_startStationName.setModel(new DefaultComboBoxModel(getStationName(line)));
        comboBox_startLineNum.setModel(new DefaultComboBoxModel(getLineNum()));
        comboBox_startLineNum.addItemListener(new ItemListener() {
                                                  @Override
                                                  public void itemStateChanged(ItemEvent e) {
                                                      itemChange01();
                                                  }
                                              }

        );
        comboBox_endStationName.setModel(new DefaultComboBoxModel(getStationName(line)));
        comboBox_endLineNum.setModel(new DefaultComboBoxModel(getLineNum()));
        comboBox_endLineNum.addItemListener(new ItemListener() {
                                                  @Override
                                                  public void itemStateChanged(ItemEvent e) {
                                                      itemChange02();
                                                  }
                                              }

        );


        button_Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String start=(String)comboBox_startStationName.getSelectedItem();
                String end=(String)comboBox_endStationName.getSelectedItem();

                Subway sw = new Subway();
//        sw.calculate(new Station("南京站"), new Station("奥体东站"));
                sw.calculate(new Station(start), new Station(end));
                textArea_Result.setText(sw.getOutput());


            }
        });
        button_Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JFrame jFrame = new JFrame("南京地铁信息查询工具");
                jFrame.setContentPane(new GUI01().panel01);
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.pack();
                jFrame.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("南京地铁信息查询工具");
        frame.setContentPane(new GUI().jpanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



    }


    public Object[] getLineNum() {
        Map<String, String[]> map = model;
        Set<String> set = map.keySet(); // 获取Map集合中的键，并以Set集合返回
        return set.toArray(); // 返回获取的地铁线信息
    }


    public String[] getStationName(String selectLine) {
        Map<String, String[]> map = model; // 获取地铁各线保存到Map中
        return map.get(selectLine); // 返回指定地铁线中的站点信息
    }

    private void itemChange01() {
        String selectLineNum = (String) comboBox_startLineNum.getSelectedItem();
        comboBox_startStationName.removeAllItems(); // 清空市/县列表
        String[] arrStation = getStationName(selectLineNum); // 获取市/县
        comboBox_startStationName.setModel(new DefaultComboBoxModel(arrStation)); // 重新添加市/县列表的值
    }
    private void itemChange02() {
        String selectLineNum = (String) comboBox_endLineNum.getSelectedItem();
        comboBox_endStationName.removeAllItems(); // 清空市/县列表
        String[] arrStation = getStationName(selectLineNum); // 获取市/县
        comboBox_endStationName.setModel(new DefaultComboBoxModel(arrStation)); // 重新添加市/县列表的值
    }



}
