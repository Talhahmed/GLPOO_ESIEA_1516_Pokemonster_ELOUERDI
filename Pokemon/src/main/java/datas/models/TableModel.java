package datas.models;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import datas.models.Pokemon;
import datas.models.TypePokemon;
import services.PokemonService;

public class TableModel extends AbstractTableModel {

	private static final long serialVersionUID = -139320534128196933L;

	private final String[] entetes;
	private List<Pokemon> pokemons;
	final private static String POKEMONS_FILE_NAME = "src/main/resources/eupoke.csv";
	private PokemonService pokemonService = PokemonService.getInstance();
	private TypePokemon selectedType = TypePokemon.Normale;

	public TableModel() {
		super();

		chercherPokemons();

		entetes = new String[] { "Nom", "Nom Japonais", "Type de pokemon", "Defense", "Attaque" };
	}

	private void chercherPokemons() {
		pokemons = pokemonService.findAllPokemons(POKEMONS_FILE_NAME, selectedType);
	}

	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	@Override
	public int getRowCount() {
		return pokemons.size();
	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		final Pokemon pokemon = pokemons.get(rowIndex);

		switch (columnIndex) {
			case 0:
				return pokemon.getNom();

			case 1:
				return pokemon.getNomComplet();

			case 2:
				return pokemon.getTypePokemon();

			case 3:
				return pokemon.getDefense();

			case 4:
				return pokemon.getAttaque();

			default:
				throw new IllegalArgumentException("Le numero de colonne indiqué n'est pas valide.");
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
		case 1:
			return String.class;

		case 2:
			return TypePokemon.class;
		
		default:
			return Object.class;
		}
	}

	public Pokemon getPokemonAt(final int rowIndex) {
		return pokemons.get(rowIndex);
	}

	public void addPokemon(final Pokemon pokemon) {
		pokemons.add(pokemon);

		final int position = pokemons.size() - 1;
		fireTableRowsInserted(position, position);
	}

	public void modifyPokemon(final Pokemon pokemon) {
		fireTableDataChanged();
	}

	public void deletePokemon(final int rowIndex) {
		pokemons.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public List<Pokemon> getPokemons() {
		return pokemons;
	}

	public TypePokemon getSelectedTypePokemon() {
		return selectedType;
	}

	public void setSelectedTypePokemon(TypePokemon selectedType) {
		this.selectedType = selectedType;
	}

	public void rechercheAvecFiltreSurSexe(final TypePokemon sexe) {
		setSelectedTypePokemon(sexe);
		chercherPokemons();
		fireTableDataChanged();
	}

	public void sauver() {
		pokemonService.sauver(pokemons);
	}
}
