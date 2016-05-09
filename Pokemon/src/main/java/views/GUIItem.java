package views;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIItem extends JFrame implements ActionListener {

       JPanel pan = new JPanel();
       private JButton Pokemon = new JButton("Pokemon");
       private JButton Aide = new JButton("Aide");
       private JButton Quit = new JButton("Quitter");
       private JButton Stat = new JButton("Statistiques");

       public GUIItem(){
    	   
           pan.setLayout(null);
         
           this.setTitle("Menu");
           this.setSize(500, 400);
          
           this.setIconImage(new ImageIcon("help.png").getImage());

           this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           this.setLocationRelativeTo(null);
 
           Pokemon.setBackground(Color.cyan);
           Pokemon.setBounds(200,20, 10, 50);
           
           Aide.setBackground(Color.cyan);
           Aide.setBounds(200,100, 10,50);
           
           Stat.setBackground(Color.cyan);
           Stat.setBounds(200,180, 10,50);
           
           Quit.setBackground(Color.cyan);
           Quit.setBounds(200,250, 10,50);

          pan.setBackground(Color.PINK);

          pan.add(Pokemon);   
          pan.add(Aide);  
          pan.add(Quit); 
          pan.add(Stat);
                            
        setContentPane(pan);

        Pokemon.addActionListener(this);
        Aide.addActionListener(this);
        Quit.addActionListener(this);
        Stat.addActionListener(this);
        setResizable(false);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if((JButton)e.getSource() == Pokemon) {
            this.dispose();

            Battle fen = new Battle();
            fen.pack();
            fen.setLocationRelativeTo(null);
            fen.setVisible(true);
            fen.pack();
        }
        else if (((JButton)e.getSource() == Quit)){
            this.dispose();
        }
        else if (((JButton)e.getSource() == Aide)){
            this.dispose();
            Help fenn = new Help();

            fenn.setVisible(true);
        }
        else if (((JButton)e.getSource() == Stat)){
            this.dispose();

            final JFrame f = new PokemonEditor();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        }
   }
}