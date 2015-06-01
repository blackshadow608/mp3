package ads;

import javax.swing.*;

/**
 * Created by USER on 30.05.15.
 */
public class StepInfo extends JPanel {
    private JTextField enterData = new JTextField(20);



    private JTextField sdvig = new JTextField(10);
    private JTextField plus = new JTextField(10);
    private JLabel enterLabel = new JLabel("Вход:");
    private JLabel sdvigLabel = new JLabel("-->");
    private JLabel result = new JLabel("Результат");



    private JLabel plusLabel = new JLabel("+");

    public StepInfo(){
        result.setVisible(false);
        Box mainBox = Box.createHorizontalBox();
        Box labelBox = Box.createVerticalBox();
        Box fieldBox = Box.createVerticalBox();
        labelBox.add(enterLabel);
        labelBox.add(Box.createVerticalStrut(10));
        labelBox.add(sdvigLabel);
        labelBox.add(Box.createVerticalStrut(10));
        labelBox.add(plusLabel);
        labelBox.add(Box.createVerticalStrut(10));
        fieldBox.add(enterData);
        fieldBox.add(Box.createVerticalStrut(10));
        fieldBox.add(sdvig);
        fieldBox.add(Box.createVerticalStrut(10));
        fieldBox.add(plus);
        fieldBox.add(Box.createVerticalStrut(10));
        fieldBox.add(result);
        fieldBox.add(Box.createVerticalStrut(10));
        mainBox.add(labelBox);
        mainBox.add(fieldBox);
        add(mainBox);

    }
    public void setFields(String data, String sdvigText, String plusData){
        enterData.setText(data);
        sdvig.setText(sdvigText);
        plus.setText(plusData);
    }
    public void setEnterData(String data){
        enterData.setText(data);
    }
    public void setSdvig(String sdvig) {
        this.sdvig.setText(sdvig);
    }
    public void setPlus(String plusLabel) {
        this.plus.setText(plusLabel);
    }
    public void setVisibleResult(boolean flag){
        result.setVisible(flag);
    }
    public void setResult(String text){
        result.setText("Результат: "+ text + "("+Integer.parseInt(text,2)+")");
    }
}
