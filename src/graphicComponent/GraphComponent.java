package graphicComponent;

/**
 * Created by USER on 18.05.15.
 */


import ads.Realease;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphComponent extends JPanel
{
    private PaintGraph graphic;
    private JScrollPane scroll;
    private int startX;
    private int startY;

    public GraphComponent(Realease data)
    {
        setSize(600,600);
        setLayout(new BorderLayout());
        graphic = new PaintGraph(data);
        scroll = new JScrollPane(graphic);
        scroll.setPreferredSize(new Dimension(600, 600));
        add(scroll, BorderLayout.CENTER);
        setVisible(true);
        graphic.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    startX = e.getX();
                    startY = e.getY();
                    graphic.repaint();
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        graphic.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
//                if(e.getButton() == MouseEvent.BUTTON1){
                    scroll.getHorizontalScrollBar().setValue(scroll.getHorizontalScrollBar().getValue() + (startX - e.getX()));
                    scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getValue() + (startY - e.getY()));
                    graphic.repaint();
//                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }


    public void setData(Realease data){
        graphic.setData(data);
    }
}
