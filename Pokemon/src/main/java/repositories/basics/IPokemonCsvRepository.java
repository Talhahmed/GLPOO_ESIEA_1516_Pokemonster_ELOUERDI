package repositories.basics;

import java.io.File;
import java.util.List;
import datas.models.Pokemon;

public interface IPokemonCsvRepository extends IPokemonRepository {

	void init(File file);
	File getFile();
	List<String> getEntetes();
	void sauver(final List<Pokemon> chiens, final File file);
}
