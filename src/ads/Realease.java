package ads;

import ads.Conveyor;
import ads.ReadData;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import graphicComponent.GraphComponent;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by USER on 30.05.15.
 */
public class Realease {
    private JButton nextStep;
    private JButton start;
    private JButton accept = new JButton("Применить");
    private JTextField stepTime = new JTextField(3);
    private JTextField capacityField = new JTextField(3);
    private int iterator;
    private int helper;
    private int capacity = 4;
    private Integer numOfPair = 0;
    Integer time = 0;
    Integer steper = 2;
    String [][] mas;
    Conveyor conv;
    List<String[]> pairs;
    List<Integer []>timeList;
    private Integer[] dot;
    private StepInfo step1;
    private StepInfo step2;
    private StepInfo step3;
    private StepInfo step4;
    private JTable table;
    private JTable resultTable;
    private DefaultTableModel resultModel;
    private JLabel timeLabel =  new JLabel("Такт: ");
    private DefaultTableModel model;
    private GraphComponent graph;

    public Realease() {
        iterator = 1;
        helper = 0;
        graph = new GraphComponent(this);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         step1 = new StepInfo();
         step2 = new StepInfo();
         step3 = new StepInfo();
         step4 = new StepInfo();
        step1.setBorder(new TitledBorder("Step 1"));
        step2.setBorder(new TitledBorder("Step 2"));
        step3.setBorder(new TitledBorder("Step 3"));
        step4.setBorder(new TitledBorder("Step 4"));
        model = new DefaultTableModel();
        model.addColumn("номер");
        model.addColumn("Число 1");
        model.addColumn("Число 2");
        resultModel = new DefaultTableModel();
        resultModel.addColumn("Номер");
        resultModel.addColumn("Результат");
        resultTable = new JTable(resultModel);
        ReadData data = new ReadData("d:/pairs.txt",capacity);
        pairs = data.getData();
        for(int i = 0; i < pairs.size(); i++){
            String [] currentPair = pairs.get(i);
        }
        for(int i = 1; i < pairs.size(); i++){
            String[] row = new String[3];
            row[0] = Integer.toString(i);
            row[1] = pairs.get(i)[0] + "("+Integer.parseInt(pairs.get(i)[0],2)+")" ;
            row[2] = pairs.get(i)[1] +"("+Integer.parseInt(pairs.get(i)[1],2)+")" ;
            model.addRow(row);
        }
        table = new JTable(model);
        Box mainBox = Box.createHorizontalBox();
        mainBox.add(new JScrollPane(table));
        Box box = Box.createVerticalBox();
        Box settingBox = Box.createHorizontalBox();
        capacityField.setText("4");
        stepTime.setText("2");
        settingBox.add(new JLabel("Время шага: "));
        settingBox.add(stepTime);
        settingBox.add(new JLabel("Разрядность: "));
        settingBox.add(capacityField);
        settingBox.add(accept);
        mainBox.add(box);
        mainBox.add(new JScrollPane(resultTable));
        mainBox.add(graph);
        box.add(settingBox);
        box.add(step1);
        box.add(step2);
        box.add(step3);
        box.add(step4);
        box.add(timeLabel);
        nextStep = new JButton("Следующий швг");
        start = new JButton("Start");
        box.add(nextStep);
        box.add(start);
        frame.add(mainBox);
        frame.setVisible(true);

        frame.pack();


        conv = new Conveyor(capacity);
        java.util.List<String[]> listConv = new ArrayList<String[]>();

        mas = new String[5][];
        timeList = new ArrayList<Integer[]>();
        dot = new Integer[3];
        nextStep.setEnabled(false);
        start.setEnabled(false);
        initListeners();




    }

    public List<Integer []> getRows(){
        return timeList;
    }

    private void initListeners(){
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextStep.setEnabled(true);
                start.setEnabled(true);
                capacity = Integer.parseInt(capacityField.getText());
                steper = Integer.parseInt(stepTime.getText());
            }
        });
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = iterator; i < pairs.size() + 3; i++){
                if(iterator < pairs.size()){
                    if(mas[2] != null ){
                        conv.setPair( mas[2]);
                        conv.fourStep(step4);
                        mas[3] = conv.getResult();
                        numOfPair++;
                        dot = new Integer[3];
                        dot[0] = numOfPair;
                        dot[1] = time + steper;
                        timeList.add(dot);
                        String[] row = new String[3];
                        row[0] = numOfPair.toString();
                        row[1] = Integer.parseInt(mas[3][2],2) +" " + "("+ time +")";
                        resultModel.addRow(row);

                    }
                    if(mas[1] != null  ){
                        conv.setPair(mas[1]);
                        conv.thirdStep(step3);
                        mas[2] = conv.getResult();
                    }
                    if(mas[0] != null  ){
                        conv.setPair(mas[0]);
                        conv.secondStep(step2);
                        mas[1] = conv.getResult();
                    }
                    conv.setPair(pairs.get(iterator));
                    conv.firstStep(step1);
                    mas[0] = conv.getResult();
                    iterator++;
                    time+=steper;
                    timeLabel.setText("Такт: "+time.toString());
                }else{
                    if(helper < 3){
                        if(mas[2] != null ){
                            conv.setPair( mas[2]);
                            conv.fourStep(step4);
                            mas[3] = conv.getResult();
                            numOfPair++;
                            dot = new Integer[3];
                            dot[0] = numOfPair;
                            dot[1] = time + steper;
                            timeList.add(dot);
                            String[] row = new String[3];
                            row[0] = numOfPair.toString();
                            row[1] = Integer.parseInt(mas[3][2],2) +" " + "("+ time +")";
                            resultModel.addRow(row);
                        }
                        if(mas[1] != null  ){
                            conv.setPair(mas[1]);
                            conv.thirdStep(step3);
                            mas[2] = conv.getResult();
                        }
                        if(mas[0] != null  ){
                            conv.setPair(mas[0]);
                            conv.secondStep(step2);
                            mas[1] = conv.getResult();
                        }
                        mas[helper]= null;
                        helper++;
                        time+=steper;
                        timeLabel.setText("Такт: "+time.toString());
                    }
                }
            }
            }

        });
        nextStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(iterator < pairs.size()){
                    if(mas[2] != null ){
                        conv.setPair( mas[2]);
                        conv.fourStep(step4);
                        mas[3] = conv.getResult();
                        numOfPair++;
                        dot = new Integer[3];
                        dot[0] = numOfPair;
                        dot[1] = time + steper;
                        timeList.add(dot);
                        String[] row = new String[3];
                        row[0] = numOfPair.toString();
                        row[1] = Integer.parseInt(mas[3][2],2) +" " + "("+ time +")";
                        resultModel.addRow(row);
                   }
                    if(mas[1] != null  ){
                        conv.setPair(mas[1]);
                        conv.thirdStep(step3);
                        mas[2] = conv.getResult();
                    }
                    if(mas[0] != null  ){
                        conv.setPair(mas[0]);
                        conv.secondStep(step2);
                        mas[1] = conv.getResult();
                    }
                    conv.setPair(pairs.get(iterator));
                    conv.firstStep(step1);
                    mas[0] = conv.getResult();
                    iterator++;
                    time+=steper;
                    timeLabel.setText("Такт: "+time.toString());
                }else{
                    if(helper < 3){
                    if(mas[2] != null ){
                        conv.setPair( mas[2]);
                        conv.fourStep(step4);
                        mas[3] = conv.getResult();
                        numOfPair++;
                        dot = new Integer[3];
                        dot[0] = numOfPair;
                        dot[1] = time + steper;
                        timeList.add(dot);
                        String[] row = new String[3];
                        row[0] = numOfPair.toString();
                        row[1] = Integer.parseInt(mas[3][2],2) +" " + "("+ time +")";
                        resultModel.addRow(row);

                    }
                    if(mas[1] != null  ){
                        conv.setPair(mas[1]);
                        conv.thirdStep(step3);
                        mas[2] = conv.getResult();
                    }
                    if(mas[0] != null  ){
                        conv.setPair(mas[0]);
                        conv.secondStep(step2);
                        mas[1] = conv.getResult();
                    }
                        mas[helper]= null;
                        helper++;
                        time+=steper;
                        timeLabel.setText("Такт: "+time.toString());
                }
                }
            }
        });

    }
}
