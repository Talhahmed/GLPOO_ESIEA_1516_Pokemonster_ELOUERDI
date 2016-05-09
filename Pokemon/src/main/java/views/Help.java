package views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help extends JFrame implements ActionListener {

    JPanel pan = new JPanel();
    private JButton retour = new JButton("<");

    public Help(){

        this.setTitle("Aide");
        this.setSize(600, 400);

        this.setContentPane(new AfficheImage("src/main/resources/im.png"));

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        retour.setBackground(Color.cyan);
        retour.setBounds(380,410, 60, 40);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        pan.add(retour);

        setContentPane(pan);

        retour.addActionListener(this);

        this.setVisible(true);
    }

    class AfficheImage extends JPanel {
        Image eau;

        AfficheImage(String s) {
            eau = getToolkit().getImage(s);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(eau, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if((JButton)e.getSource() == retour) {
            this.dispose();
            GUIItem menu= new GUIItem();
        }
    }
}