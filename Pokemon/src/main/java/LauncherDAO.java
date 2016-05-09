import java.io.File;
import java.util.List;
import org.apache.log4j.Logger;
import repositories.basics.IPokemonCsvRepository;
import repositories.PokemonRepository;
import repositories.basics.IPokemonRepository;
import datas.models.Pokemon;

public class LauncherDAO {

	private static final Logger LOGGER = Logger.getLogger(LauncherDAO.class);

	final static String FILE_NAME = "src/main/resources/eupoke_2.csv";
	final static File FILE = new File(FILE_NAME);

	public static void main(String[] args) {
		LOGGER.debug("Projet : DEBUT");

		doFindChiensCsvEngine();

		LOGGER.debug("Projet : FIN");

	}

	private static void doWork(final IPokemonRepository dao) {
		final List<Pokemon> pokemons = dao.findAll();

		final int nombreDePokemons = pokemons.size();
		LOGGER.debug("Nombre de Pokemon : " + nombreDePokemons);

		
	}

	private static void doFindChiensCsvEngine() {
		LOGGER.debug("Recherche avec CSV Engine");

		final IPokemonCsvRepository dao = new PokemonRepository();
		dao.init(FILE);

		doWork(dao);
	}

}
