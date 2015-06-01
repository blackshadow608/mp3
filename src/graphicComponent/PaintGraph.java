package graphicComponent;

/**
 * Created by USER on 18.05.15.
 */


import ads.Realease;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.lang.*;
import java.util.List;

public class PaintGraph extends JPanel
{
    private int valOfDivision;
    private int indent;
    private int lenthY;
    private int lenthX;

    private Realease data;
    private String nameX = "X";
    private String nameY = "Y";
    private JScrollPane scroll;

    public PaintGraph(Realease data)
    {
        valOfDivision = 10;
        indent = 40 ;
        lenthY = 60;
        lenthX = 60;
        setBackground(Color.WHITE);
        this.data = data;
    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);
        List<Integer []> dots = data.getRows();
        for(int currentDot = 0; currentDot < dots.size(); currentDot++){
            if(lenthX / valOfDivision < dots.get(currentDot)[0]){
                lenthX = dots.get(currentDot)[0] * valOfDivision;
            }
            if(lenthY/valOfDivision < dots.get(currentDot)[1]){
                lenthY = (dots.get(currentDot)[1]) *valOfDivision;
            }
        }
        for(int currentDot = 0; currentDot < dots.size(); currentDot++){
            Integer dot[] = dots.get(currentDot);
            g.setColor(Color.BLUE);
        g.fillOval(indent + dot[0]* valOfDivision - 2, indent + lenthY - (dot[1]) * valOfDivision - 2, 5, 5);

            if(currentDot !=0){
                g.setColor(Color.RED);
                g.drawLine(indent + dots.get(currentDot - 1)[0]* valOfDivision + 1, indent + lenthY - ( dots.get(currentDot - 1)[1]) * valOfDivision +1 ,
                        indent +  dots.get(currentDot)[0]* valOfDivision + 1 , indent + lenthY - ( dots.get(currentDot)[1]) * valOfDivision +1);
            }
        }
        g.setColor(Color.BLACK);
        setPreferredSize(new Dimension(lenthX + 2 * indent + 2 * valOfDivision, lenthY + 2 * indent));
        g.drawLine(indent, indent, indent, lenthY + indent);
        g.drawLine( indent, lenthY + indent, indent + lenthX + valOfDivision, lenthY + indent);
        g.drawLine(indent, indent, indent - 7, indent + 10);
        g.drawLine(indent, indent, indent + 7, indent + 10);
        g.drawString(nameX, indent, indent - 5);
        g.drawString(nameY , indent + lenthX + valOfDivision , lenthY + indent + 20);
        g.drawLine(indent + lenthX + valOfDivision, lenthY + indent, indent + lenthX + valOfDivision - 10, lenthY + indent + 7);
        g.drawLine(indent + lenthX + valOfDivision, lenthY + indent, indent + lenthX + valOfDivision - 10, lenthY + indent -7);

        Integer numOfSegmentsX = lenthX / valOfDivision;
        Integer numOfSegmentsY = (lenthY / valOfDivision) - 1;

        for(int segment = 1; segment <= numOfSegmentsY; segment++){
            g.drawLine(indent - 3, lenthY + indent - valOfDivision * segment,
                    indent + 3, lenthY + indent - valOfDivision * segment);
        }
        for(int segment = 1; segment <= numOfSegmentsX; segment++){
            g.drawLine(indent +  valOfDivision * segment, lenthY + indent -3,
                    indent +  valOfDivision * segment, lenthY + indent + 3);
        }

        int index;
        index = 10;
        int indentIn;
        indentIn = indent ;

        for(Integer segment = 0; segment <= numOfSegmentsX; segment+= 5){

            if((segment+1)/index != 0){
                index = index * 10;
                indentIn = indentIn - 4;
            }
            g.drawString(segment.toString(),indentIn +  valOfDivision * segment, lenthY + indent + 15 );
        }

        index = 10;
        indentIn = indent - 15;

        for(Integer segment = 5; segment <= numOfSegmentsY; segment+= 5){

            if((segment+1)/index != 0){
                index = index * 10;
                indentIn = indentIn - 7;
            }
            g.drawString(segment.toString(),indentIn, lenthY + indent - valOfDivision * segment + 5);
        }

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if(e.isControlDown() && e.getPreciseWheelRotation() < 0){
                    valOfDivisionAdd();
                    repaint();
                }
                if(e.isControlDown() && e.getPreciseWheelRotation() > 0){
                    valOfDivisionKick();
                    repaint();}
            }
        });
    }

    private void valOfDivisionKick() {
        if(valOfDivision > 2) valOfDivision--;
    }


public void valOfDivisionAdd(){
    if(valOfDivision < 20) valOfDivision++;
}
    public void setData(Realease data){
        this.data = data;
    }

    public int getValOfDivision() {
        return valOfDivision;
    }
    public void setValOfDivision(int valOfDivision) {
        if(valOfDivision > 2) this.valOfDivision = valOfDivision;
    }

    public void setNameX(String name){
        this.nameX = name;
    }
    public void setNameY(String name){
        this.nameY = name;
    }
}