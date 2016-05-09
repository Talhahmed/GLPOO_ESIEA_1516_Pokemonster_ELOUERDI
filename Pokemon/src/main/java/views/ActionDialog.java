package views;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.SwingConstants.RIGHT;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import common.exceptions.PokemonException;
import datas.enums.Action;
import datas.models.Pokemon;
import datas.models.SimplePokemon;
import datas.models.TypePokemon;
import services.IActionService;

public class ActionDialog extends JDialog {

	private services.IActionService IActionService;
	private JTextField nom = new JTextField(15);
	private JTextField nomJaponais = new JTextField(15);
	private JComboBox type = new JComboBox();
	private JTextField boule_2 = new JTextField(15);
	private JTextField boule_1 = new JTextField(15);
	private JButton ajouterButton = new JButton(new AjouterAction("Ajouter"));
	private JButton modifierButton = new JButton(new ModifierAction("Modifier"));
	private JButton annulerButton = new JButton(new AnnulerAction("Annuler"));
	private static final int widthLabel = 100;
	private static final int widthChamp = 200;
	private static final int heightLigne = 20;
	private static final int espace = 5;
	private int y = 0;
	private JPanel formPanel = new JPanel(null);
	private JPanel buttonPanel = new JPanel();
	private Pokemon pokemon;

	public ActionDialog(final IActionService IActionService) {
		this(IActionService, null);
	}

	public ActionDialog(final IActionService IActionService, final Pokemon pokemon) {
		super();

		this.IActionService = IActionService;
		this.pokemon = pokemon;

		if (pokemon == null) {
			setTitle("Ajouter un pokemon");
		} else {
			setTitle("Modifier un pokemon");
		}

		setModal(true);

		addFormulaire();
		preremplirFormulaire();

		addBoutons();

		pack();
	}

	private void addBoutons() {
		buttonPanel.add(pokemon == null ? ajouterButton : modifierButton);

		buttonPanel.add(annulerButton);
		add(buttonPanel, SOUTH);
	}

	private void addFormulaire() {
		ajouter("Nom :", nom);
		ajouter("Nom Japonais :", nomJaponais);

		// Sexes
		type.addItem(TypePokemon.Legendaire);
		type.addItem(TypePokemon.Normale);

		type.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				final TypePokemon type = (TypePokemon) value;
				setText((type == TypePokemon.Legendaire) ? "Legendaire" : "Normale");
				return this;
			}

		});

		ajouter("Type :", type);

		ajouter("Defense :", boule_2);
		ajouter("Attaque :", boule_1);

		formPanel.setPreferredSize(new Dimension(3 * espace + widthLabel + widthChamp, y + espace));

		add(formPanel, CENTER);
	}

	private void preremplirFormulaire() {
		if (pokemon == null) {
			return;
		}

		nom.setText(pokemon.getNom());
		nomJaponais.setText(pokemon.getNomComplet());

		boule_2.setText(pokemon.getDefense().toString());
		
		boule_1.setText(pokemon.getAttaque().toString());
		
		type.setSelectedItem(pokemon.getTypePokemon());
	}

	private void ajouter(final String label, final JComponent champ) {
		y += espace;

		final JLabel l = new JLabel(label);
		l.setBounds(espace, y, widthLabel, heightLigne);
		l.setHorizontalAlignment(RIGHT);

		champ.setBounds(2 * espace + widthLabel, y, widthChamp, heightLigne);

		y += heightLigne;

		formPanel.add(l);
		formPanel.add(champ);
	}

	private class AjouterAction extends AbstractAction {
		public AjouterAction(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent event) {
			final SimplePokemon pokemon = new SimplePokemon();
            remplir(pokemon);

			try {
				IActionService.process(Action.CREER, pokemon);
			}
            catch (PokemonException e) {
				e.printStackTrace();
			}

			ActionDialog.this.closePopup();
		}
	}

	private class ModifierAction extends AbstractAction {
		public ModifierAction(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent event) {
			if (pokemon instanceof SimplePokemon) {
                remplir((SimplePokemon) pokemon);

                try {
                    IActionService.process(Action.MODIFIER, pokemon);
                }
                catch (PokemonException e) {
                    e.printStackTrace();
                }

                ActionDialog.this.closePopup();
            }
		}
	}

	private void remplir(final SimplePokemon pokemon) {

		pokemon.setNom(nom.getText());
		pokemon.setNomComplet(nomJaponais.getText());

		try {
			String temp = boule_2.getText();
			String temp2 = boule_1.getText();

			temp = temp.replace(',', '.');
			temp = temp.replaceAll(" ", "");

			if (!temp.isEmpty()) {
                pokemon.setDefense(Double.valueOf(temp));
                pokemon.setAttaque(Double.valueOf(temp2));
			}
		} catch (NullPointerException
                | NumberFormatException e) {
			e.printStackTrace();
		}

		pokemon.setTypePokemon((TypePokemon) type.getSelectedItem());
	}

	private class AnnulerAction extends AbstractAction {
		public AnnulerAction(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent e) {
			ActionDialog.this.closePopup();
		}
	}

	private void closePopup() {
		dispose();
	}
}
