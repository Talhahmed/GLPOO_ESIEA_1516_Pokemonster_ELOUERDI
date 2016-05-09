package repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import datas.models.Pokemon;
import datas.models.SimplePokemon;
import fr.ybonnel.csvengine.CsvEngine;
import fr.ybonnel.csvengine.factory.AbstractCsvReader;
import fr.ybonnel.csvengine.factory.DefaultCsvManagerFactory;
import fr.ybonnel.csvengine.factory.OpenCsvReader;
import fr.ybonnel.csvengine.model.Result;
import repositories.abstracts.AbstractPokemonRepository;

public class PokemonRepository extends AbstractPokemonRepository {

	private void setEngineFactory(final CsvEngine engine) {
		engine.setFactory(new DefaultCsvManagerFactory() {
			@Override
			public AbstractCsvReader createReaderCsv(Reader reader, char separator) {
				return new OpenCsvReader(reader, separator) {
					@Override
					public String[] readLine() throws IOException {
						String[] nextLine = super.readLine();
						if (isLineAComment(nextLine)) {
							nextLine = readLine();
						}
						return nextLine;
					}

					private boolean isLineAComment(String[] line) {
						return line != null && line.length > 0 && line[0].startsWith("#");
					}
				};
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void reloadPokemons() {

		try {
			final CsvEngine engine = new CsvEngine(SimplePokemon.class);
			setEngineFactory(engine);

			final FileInputStream fis = new FileInputStream(file);

			final Result<SimplePokemon> resultat = engine.parseInputStream(fis, SimplePokemon.class);

			final List<? extends Pokemon> dogs = resultat.getObjects();
			pokemons = (List<Pokemon>) dogs;
			pokemonMapByNom = new HashMap<String, Pokemon>(pokemons.size());
			for (Pokemon pokemon : pokemons) {
				pokemonMapByNom.put(pokemon.getNom(), pokemon);
			}

			entetes = engine.getColumnNames(SimplePokemon.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sauver(final List<Pokemon> pokemons, final File file) {
		final List<SimplePokemon> simplePokemons = Lists.transform(pokemons, new Function<Pokemon, SimplePokemon>() {
			public SimplePokemon apply(final Pokemon pokemon) {
				return (SimplePokemon) pokemon;
			}
		});

		doSauver(simplePokemons, file);
	}

	private void doSauver(final List<SimplePokemon> pokemons, final File file) {
		final CsvEngine engine = new CsvEngine(SimplePokemon.class);

		try {
			engine.writeFile(new FileWriter(file), pokemons, SimplePokemon.class);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}