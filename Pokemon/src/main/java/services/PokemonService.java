package services;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import repositories.basics.IPokemonCsvRepository;
import repositories.PokemonRepository;
import datas.models.Pokemon;
import datas.models.TypePokemon;

public class PokemonService {

	private IPokemonCsvRepository pokemonRepository;
	private static PokemonService instance;
	private String fileName;
	private LoadingCache<String, List<Pokemon>> pokemonsCache;

	private PokemonService() {
		pokemonRepository = new PokemonRepository();

		final CacheLoader<String, List<Pokemon>> cacheLoader = new CacheLoader<String, List<Pokemon>>() {
			public List<Pokemon> load(final String key) {
				final File file = new File(fileName);
				pokemonRepository.init(file);
				return pokemonRepository.findAll();
			}
		};

		pokemonsCache = CacheBuilder.newBuilder()
				.expireAfterWrite(10, TimeUnit.MINUTES)
				.build(cacheLoader);
	}

	public static synchronized PokemonService getInstance() {
		if (instance == null) {
			instance = new PokemonService();
		}

		return instance;
	}

	public List<Pokemon> findAllPokemons(final String fileName) {
		this.fileName = fileName;

		try {
			return pokemonsCache.get("pokemons");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Pokemon> findAllPokemons(final String fileName, final TypePokemon type) {
		final List<Pokemon> temp = findAllPokemons(fileName);

		if (type != null) {
			final Predicate<Pokemon> typePredicate = pokemon -> pokemon.getTypePokemon() == type;
			return Lists.newArrayList(temp.stream().filter(typePredicate::apply).collect(Collectors.toList()));
		}

		return null;
	}

	public void sauver(final List<Pokemon> pokemons) {
		final String target = fileName.replaceFirst(".csv", "_2.csv");

		final File file = new File(target);

		pokemonRepository.sauver(pokemons, file);
	}
}
