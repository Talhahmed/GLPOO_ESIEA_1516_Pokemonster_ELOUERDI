package views;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;

public class Battle extends JFrame {

	String[][] mat = new String[23][23];

	public Battle(){
		setTitle("Battle");
		setLocationRelativeTo(null);
		setResizable(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

		setContentPane(buildContentPane());
	}

	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);

		FileReader fileReader = null;
		BufferedReader tampon = null;

		String separator = ";";

		try {
			fileReader = new FileReader("src/main/resources/eupoke.csv");
			tampon = new BufferedReader(fileReader);

			panel.setPreferredSize(new Dimension(512, 450));

			int i=0;

			while (true) {
				String ligne = tampon.readLine();
				if (ligne == null)
				break;

				String[] cellule = ligne.split(separator);

				mat[0][i]=cellule[54];

				mat[1][i]=cellule[4];

				mat[2][i]=cellule[5];

				i++;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (tampon != null) {
					tampon.close();
				}

				if (fileReader != null) {
					fileReader.close();
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}

		int x = new Random().nextInt(22) + 1;
		int x2 = new Random().nextInt(22) + 1;

		JLabel label = new JLabel(get_matrice(0, x));
		label.setForeground(Color.white);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(label);

		JLabel label2 = new JLabel("Level " + get_matrice(1, x));
		label2.setForeground(Color.white);
		panel.add(label2);

		JLabel label6 = new JLabel("Exp. " + get_matrice(2, x));
		label6.setForeground(Color.white);
		panel.add(label6);

		panel.add(new JLabel(new ImageIcon("src/main/resources/battle.png")));

		JLabel label3 = new JLabel(get_matrice(0, x2));
		label3.setFont(new Font("Arial", Font.BOLD, 20));
		label3.setForeground(Color.white);
		panel.add(label3);

		JLabel label4 = new JLabel("Level " + get_matrice(1, x2));
		label4.setForeground(Color.white);
		panel.add(label4);

		JLabel label5 = new JLabel("Exp. " + get_matrice(2, x2));
		label5.setForeground(Color.white);
		panel.add(label5);

		String winner = battle(x, x2) ? get_matrice(0, x) : get_matrice(0, x2);

		JButton fight = new JButton();
		fight.setBorderPainted(false);
		fight.setIcon(new ImageIcon("src/main/resources/buttons/fight.png"));
		fight.setFocusPainted(false);
		fight.setContentAreaFilled(false);
		fight.addActionListener(e -> fight(winner));
		panel.add(fight);

		return panel;
	}

	private void fight(String pokemon) {
		JFrame frameFight = new JFrame("And the winner is ...");
		frameFight.setSize(400, 300);

		JPanel panelFight = new JPanel();
		panelFight.setBackground(Color.ORANGE);
		panelFight.add(new JLabel(new ImageIcon("src/main/resources/winner.png")));

		JLabel label = new JLabel(pokemon);
		Font font = new Font("Arial", Font.BOLD, 30);
		label.setFont(font);
		panelFight.add(label);

		frameFight.getContentPane().add(panelFight);
		frameFight.setVisible(true);
		frameFight.setResizable(false);
		frameFight.setLocationRelativeTo(null);
	}

	private boolean battle(int x, int x2) {
		int A1 = Integer.parseInt(get_matrice(1, x));
		int A2 = Integer.parseInt(get_matrice(1, x2));

		int vie1 = 100;
		int vie2 = 100;

		do{
			vie2 -= A1;

			vie1 -=  A2;
		} while(vie1 > 1 || vie2 > 1);

		return vie1 > vie2;
	}

	private String get_matrice(int i, int j ) {
		return mat[i][j];
	}
}