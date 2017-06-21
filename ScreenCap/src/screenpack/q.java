package screenpack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class q extends JFrame
{
    public static void main(String[] args)
    {
        q window = new q();
        window.setVisible(true);
        window.setSize(1024, 800);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container cont = window.getContentPane();
        cont.setLayout(new GridLayout(2,2));
        r panel = new r();
        JPanel BPanel = new JPanel();
        cont.add(panel);
        cont.add(BPanel);
        BPanel.setBackground(Color.blue);
        JButton button1,button2;
        button1 = new JButton("Rect");
        button2 = new JButton("Oval");
        BPanel.add(button1);
        BPanel.add(button2);
        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                r.SWITCH = 2;
            }
        });
        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                r.SWITCH = 3;
            }
        });
    }
}