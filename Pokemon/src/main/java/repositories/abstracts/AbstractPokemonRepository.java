package repositories.abstracts;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import datas.models.Pokemon;
import repositories.basics.IPokemonCsvRepository;

public abstract class AbstractPokemonRepository implements IPokemonCsvRepository {

	private static final Logger LOGGER = Logger.getLogger(AbstractPokemonRepository.class);

	protected File file;
	protected List<Pokemon> pokemons;
	protected Map<String, Pokemon> pokemonMapByNom;
	protected List<String> entetes;

	protected abstract void reloadPokemons();

	@Override
	public void init(File file) {
		LOGGER.debug("init");
		this.file = file;

		reloadPokemons();
	}

	@Override
	public List<Pokemon> findAll() {
		LOGGER.debug("findAll");

		if (pokemons == null) {
			throw new IllegalStateException("La liste n'a pas encore ete initialisee...");
		}

		return pokemons;
	}

	@Override
	public Pokemon find(final String nom) {

		if (nom == null || nom.isEmpty()) {
			throw new IllegalArgumentException("Le nom ne peut pas etre vide.");
		}

		if (pokemons == null) {
			throw new IllegalStateException("La liste n'a pas encore ete initialisee...");
		}

		return pokemonMapByNom.get(nom);
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public List<String> getEntetes() {
		return entetes;
	}
}
