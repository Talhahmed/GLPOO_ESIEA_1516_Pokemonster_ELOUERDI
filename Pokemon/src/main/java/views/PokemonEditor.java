package views;

import static java.awt.BorderLayout.CENTER;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import common.helpers.CellHelper;
import common.helpers.StringHelper;
import datas.models.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import datas.models.Pokemon;
import datas.models.TypePokemon;
import services.ActionService;

public class PokemonEditor extends JFrame {

	private static final long serialVersionUID = -638731145561555723L;

	private JTable tableau;
	private TableModel modele;
	private JDialog ratioLegendaireFemaleJdialog;
	private JDialog defenseJdialog;
	private JDialog defense2Jdialog;
	private JMenuItem menuModifier;
	private JMenuItem menuSupprimer;

	public PokemonEditor() {
		super();
		setTitle("Liste des Pokemons");
		setPreferredSize(new Dimension(500, 400));
		//setDefaultCloseOperation(EXIT_ON_CLOSE);

		ajoutDuTableau();
		ajoutDuMenu();

		pack();
	}

	private void ajoutDuMenu() {
		final JMenuBar menuBar = new JMenuBar();

		final JMenu menuFichier = new JMenu("Fichier");
		menuBar.add(menuFichier);

		final JMenuItem menuOuvrir = new JMenuItem(new OuvrirAction("Ouvrir"));
		menuFichier.add(menuOuvrir);
		final JMenuItem menuSauver = new JMenuItem(new SauverAction("Sauver"));
		menuFichier.add(menuSauver);
		menuFichier.addSeparator();
		final JMenuItem menuQuitter = new JMenuItem(new QuitterAction("Quitter"));
		menuFichier.add(menuQuitter);

		final JMenu menuEdition = new JMenu("Edition");
		menuBar.add(menuEdition);
		final JMenuItem menuAjouter = new JMenuItem(new AjouterLigneAction());
		menuEdition.add(menuAjouter);
		menuModifier = new JMenuItem(new ModifierLigneAction());
		menuEdition.add(menuModifier);
		menuSupprimer = new JMenuItem(new SupprimerLigneAction());
		menuEdition.add(menuSupprimer);
		activerOuDesactiverMenuEdition();

		final JMenu menuAffichage = new JMenu("Affichage");
		menuBar.add(menuAffichage);

		final JRadioButtonMenuItem menuLegendaire = new JRadioButtonMenuItem(new LegendaireAction("Voir les legendaires seulement"));
		menuAffichage.add(menuLegendaire);
		final JRadioButtonMenuItem menuFemale = new JRadioButtonMenuItem(new FemaleAction("Voir les normales seulement"));
		menuAffichage.add(menuFemale);
		final JRadioButtonMenuItem menuLegendaireAndFemale = new JRadioButtonMenuItem(new LegendaireAndFemaleAction("Voir tout les pokemons"));
		menuAffichage.add(menuLegendaireAndFemale);

		final ButtonGroup groupGenre = new ButtonGroup();
		groupGenre.add(menuLegendaire);
		groupGenre.add(menuFemale);
		groupGenre.add(menuLegendaireAndFemale);
		menuLegendaireAndFemale.setSelected(true);

		final JMenu menuGraphe = new JMenu("Graphe");
		menuBar.add(menuGraphe);
		final JMenuItem menuCamember = new JMenuItem(new CamembertLegendaireFemaleAction());
		menuGraphe.add(menuCamember);
		menuGraphe.addSeparator();
		final JMenuItem menuPoids = new JMenuItem(new PoidsAction());
		menuGraphe.add(menuPoids);
		final JMenuItem menuPoids2 = new JMenuItem(new Poids2Action());
		menuGraphe.add(menuPoids2);

		final JMenu menuPointInterrogration = new JMenu("?");
		menuBar.add(menuPointInterrogration);
		final JMenuItem menuPreferences = new JMenuItem(new PreferencesAction("Préférences"));
		menuPointInterrogration.add(menuPreferences);
		final JMenuItem menuAPropos = new JMenuItem(new PreferencesAction("A propos"));
		menuPointInterrogration.add(menuAPropos);

		setJMenuBar(menuBar);
	}

	private void ajoutDuTableau() {
		modele = new TableModel();
		tableau = new JTable(modele);
		getContentPane().add(new JScrollPane(tableau), CENTER);

		tableau.setDefaultRenderer(TypePokemon.class, new CellHelper());

		tableau.setAutoCreateRowSorter(true);

		final TableRowSorter<javax.swing.table.TableModel> sorter = new TableRowSorter<>(tableau.getModel());
		tableau.setRowSorter(sorter);

		sorter.setSortable(0, false);
		sorter.setComparator(1, new StringHelper());

		final ListSelectionModel listSelectionModel = tableau.getSelectionModel();
		listSelectionModel.addListSelectionListener(new TableauListSelectionListener());
	}

	private class OuvrirAction extends AbstractAction {
		public OuvrirAction(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	private class SauverAction extends AbstractAction {
		public SauverAction(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent e) {
			modele.sauver();
		}
	}

	private class QuitterAction extends AbstractAction {
		public QuitterAction(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	private class LegendaireAction extends AbstractAction {
		public LegendaireAction(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent e) {
			modele.rechercheAvecFiltreSurSexe(TypePokemon.Legendaire);
		}
	}

	private class FemaleAction extends AbstractAction {
		public FemaleAction(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent e) {
			modele.rechercheAvecFiltreSurSexe(TypePokemon.Normale);
		}
	}

	private class LegendaireAndFemaleAction extends AbstractAction {
		public LegendaireAndFemaleAction(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent e) {
			modele.rechercheAvecFiltreSurSexe(null);
		}
	}

	private class AjouterLigneAction extends AbstractAction {
		private static final long serialVersionUID = 7183768497443802311L;

		private AjouterLigneAction() {
			super("Ajouter");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			final ActionService handler = new ActionService(modele);
			ActionDialog popup = new ActionDialog(handler);

			popup.setVisible(true);
		}
	}

	private class ModifierLigneAction extends AbstractAction {

		private static final long serialVersionUID = 7183768497443802311L;

		private ModifierLigneAction() {
			super("Modifier");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			final int[] selection = tableau.getSelectedRows();

			if (selection != null && selection.length == 1) {
				final Pokemon pokemon = modele.getPokemonAt(selection[0]);

				final ActionService handler = new ActionService(modele);
				ActionDialog popup = new ActionDialog(handler, pokemon);

				popup.setVisible(true);
			}
		}
	}

	private class SupprimerLigneAction extends AbstractAction {

		private static final long serialVersionUID = -5556554884674073716L;

		private SupprimerLigneAction() {
			super("Supprimer");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			final int[] selection = tableau.getSelectedRows();
			for (int i = selection.length - 1; i >= 0; i--) {
				modele.deletePokemon(selection[i]);
			}
		}
	}

	private class CamembertLegendaireFemaleAction extends AbstractAction {

		private static final long serialVersionUID = 4515460004284651581L;

		private CamembertLegendaireFemaleAction() {
			super("Camembert N/L");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			final List<Pokemon> pokemons = modele.getPokemons();
			int nbLegendaire = 0;
			for (Pokemon pokemon : pokemons) {
				if (pokemon.getTypePokemon() == TypePokemon.Legendaire) {
					nbLegendaire++;
				}
			}
			final int nbFemele = pokemons.size() - nbLegendaire;

			ratioLegendaireFemaleJdialog = new JDialog();
			ratioLegendaireFemaleJdialog.setTitle("Ratio N/L");

			final DefaultPieDataset pieDataset = new DefaultPieDataset();

			pieDataset.setValue("Normale", nbFemele);
			pieDataset.setValue("Legendaire", nbLegendaire);

			final JFreeChart pieChart = ChartFactory.createPieChart("Ratio N/L", pieDataset, true, false, false);
			final ChartPanel cPanel = new ChartPanel(pieChart);

			ratioLegendaireFemaleJdialog.getContentPane().add(cPanel, CENTER);

			ratioLegendaireFemaleJdialog.pack();
			ratioLegendaireFemaleJdialog.setVisible(true);

		}
	}

	private class PoidsAction extends AbstractAction {

		private static final long serialVersionUID = -2621678352128531798L;

		private PoidsAction() {
			super("Defense");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] trancheNames = { "0-5", "6-10", "11-15", "16-20", "21-25", "26-30", "31+" };

			Map<String, Integer> map = new HashMap<>();
			for (String trancheName : trancheNames) {
				map.put(trancheName, 0);
			}

			final List<Pokemon> pokemons = modele.getPokemons();

			for (Pokemon pokemon : pokemons) {

				String tranche = "0-5";
				if (6 <= pokemon.getDefense()) {
					tranche = "6-10";
				}
				if (11 <= pokemon.getDefense()) {
					tranche = "11-15";
				}
				if (16 <= pokemon.getDefense()) {
					tranche = "16-20";
				}
				if (21 <= pokemon.getDefense()) {
					tranche = "21-25";
				}
				if (26 <= pokemon.getDefense()) {
					tranche = "26-30";
				}
				if (31 <= pokemon.getDefense()) {
					tranche = "31+";
				}

				Integer value = map.get(tranche);
				value++;
				map.put(tranche, value);
			}

			defenseJdialog = new JDialog();
			defenseJdialog.setTitle("Defense");

			final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			for (String tranche : trancheNames) {
				final Integer nb = map.get(tranche);
				dataset.addValue(nb, "Defense", tranche);
			}

			final JFreeChart barChart = ChartFactory.createBarChart("Defense", "Tranches", "Nombre", /**/
					dataset, PlotOrientation.VERTICAL, true, true, false);

			final ChartPanel cPanel = new ChartPanel(barChart);

			defenseJdialog.getContentPane().add(cPanel, CENTER);

			defenseJdialog.pack();
			defenseJdialog.setVisible(true);
		}
	}

	private class Poids2Action extends AbstractAction {

		private static final long serialVersionUID = -2621678352128531798L;

		private Poids2Action() {
			super("Defense N/L");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] trancheNames = { "0-5", "6-10", "11-15", "16-20", "21-25", "26-30", "31+" };

			Map<String, Integer> maleMap = new HashMap<>();
			Map<String, Integer> femaleMap = new HashMap<>();
			for (String trancheName : trancheNames) {
				maleMap.put(trancheName, 0);
				femaleMap.put(trancheName, 0);
			}

			final List<Pokemon> pokemons = modele.getPokemons();

			for (Pokemon pokemon : pokemons) {

				String tranche = "0-5";
				if (6 <= pokemon.getDefense()) {
					tranche = "6-10";
				}
				if (11 <= pokemon.getDefense()) {
					tranche = "11-15";
				}
				if (16 <= pokemon.getDefense()) {
					tranche = "16-20";
				}
				if (21 <= pokemon.getDefense()) {
					tranche = "21-25";
				}
				if (26 <= pokemon.getDefense()) {
					tranche = "26-30";
				}
				if (31 <= pokemon.getDefense()) {
					tranche = "31+";
				}

				final Map<String, Integer> map = (pokemon.getTypePokemon() == TypePokemon.Legendaire) ? maleMap : femaleMap;

				Integer value = map.get(tranche);
				value++;
				map.put(tranche, value);
			}

			defense2Jdialog = new JDialog();
			defense2Jdialog.setTitle("Defense N/L");

			final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			for (String tranche : trancheNames) {
				final Integer nb = maleMap.get(tranche);
				dataset.addValue(nb, "Legendaire", tranche);
			}

			for (String tranche : trancheNames) {
				final Integer nb = femaleMap.get(tranche);
				dataset.addValue(nb, "Normale", tranche);
			}

			final JFreeChart barChart = ChartFactory.createBarChart("Defense", "Tranches", "Nombre", /**/
					dataset, PlotOrientation.VERTICAL, true, true, false);

			final ChartPanel cPanel = new ChartPanel(barChart);

			defense2Jdialog.getContentPane().add(cPanel, CENTER);

			defense2Jdialog.pack();
			defense2Jdialog.setVisible(true);
		}
	}

	private class PreferencesAction extends AbstractAction {
		public PreferencesAction(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	private class AProposAction extends AbstractAction {
		public AProposAction(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	private class TableauListSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(final ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}

			activerOuDesactiverMenuEdition();
		}

	}

	private void activerOuDesactiverMenuEdition() {
		final int[] selection = tableau.getSelectedRows();

		final boolean isSelection = selection != null && selection.length != 0;
		menuSupprimer.setEnabled(isSelection);

		final boolean isUnEtSeulementUnSelection = isSelection && selection.length == 1;
		menuModifier.setEnabled(isUnEtSeulementUnSelection);
	}
}
