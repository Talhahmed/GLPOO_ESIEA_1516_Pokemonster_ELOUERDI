package repositories.basics;

import java.util.List;
import datas.models.Pokemon;

public interface IPokemonRepository {

	List<Pokemon> findAll();
	Pokemon find(final String nom);
}
