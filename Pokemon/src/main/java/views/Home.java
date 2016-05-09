package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame("Pokémon Battle Simulation");
	JPanel panel = new JPanel();
	JButton button1 = new JButton();
	JButton button2 = new JButton();
	JButton button3 = new JButton();
	JButton button4 = new JButton();
	JOptionPane optionPane = new JOptionPane();

	public Home() {
		frame.setSize(603, 500);
        panel.setBackground(Color.ORANGE);
		panel.add(new JLabel(new ImageIcon("src/main/resources/pokeworld.jpg")));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);

        button1.setBorderPainted(false);
        button1.setIcon(new ImageIcon("src/main/resources/buttons/play.png"));
        button1.setFocusPainted(false);
        button1.setContentAreaFilled(false);

        button2.setBorderPainted(false);
        button2.setIcon(new ImageIcon("src/main/resources/buttons/quit.png"));
        button2.setFocusPainted(false);
        button2.setContentAreaFilled(false);

        button3.setBorderPainted(false);
        button3.setIcon(new ImageIcon("src/main/resources/buttons/help.png"));
        button3.setFocusPainted(false);
        button3.setContentAreaFilled(false);

        button4.setBorderPainted(false);
        button4.setIcon(new ImageIcon("src/main/resources/buttons/facts.png"));
        button4.setFocusPainted(false);
        button4.setContentAreaFilled(false);

		panel.add(button1);
		button1.addActionListener(this);
		panel.add(button3);
		button3.addActionListener(this);
		panel.add(button2);
		button2.addActionListener(this);
		panel.add(button4);
		button4.addActionListener(this);

		frame.getContentPane().add(panel);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			Battle battle = new Battle();
            
            battle.pack();
    		battle.setLocationRelativeTo(null);
    		battle.setVisible(true);
    		battle.pack();
		}
		
		if (e.getSource() == button2) {
			if(JOptionPane.showConfirmDialog(null, "Are you sure to quit ?", "Yes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			else {
				return;
			}
		}
		
		if (e.getSource() == button3) {
			JFrame frameHelp = new JFrame("Help");
            frameHelp.setSize(500, 550);

			JPanel panelHelp = new JPanel();
            panelHelp.setSize(500, 550);
            panelHelp.setBackground(Color.ORANGE);
			panelHelp.add(new JLabel(new ImageIcon("src/main/resources/help.png")));

			frameHelp.getContentPane().add(panelHelp);
			frameHelp.setVisible(true);
			frameHelp.setResizable(false);
            frameHelp.setLocationRelativeTo(null);
		}
		
		if (e.getSource() == button4) {
			final JFrame f = new PokemonEditor();

 		    f.setLocationRelativeTo(null);
 		    f.setResizable(false);
            f.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
