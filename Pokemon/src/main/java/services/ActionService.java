package services;

import common.exceptions.PokemonException;
import datas.enums.Action;
import datas.models.Pokemon;
import datas.models.TableModel;

public class ActionService implements IActionService {

	private TableModel modele;

	public ActionService(TableModel modele) {
		this.modele = modele;
	}

	@Override
	public void process(final Action action) throws PokemonException {
		process(action, null);
	}

	@Override
	public void process(final Action action, final Pokemon pokemon) throws PokemonException {
		switch (action) {
			case MODIFIER:
				modifier(pokemon);
				break;

			case CREER:
				ajouter(pokemon);
				break;

			case SUPPRIMER:
				supprimer(pokemon);
				break;

			case ANNULER:
			default:
				annuler();
		}
	}

	private void ajouter(final Pokemon pokemon) throws PokemonException {
		modele.addPokemon(pokemon);
	}

	private void modifier(final Pokemon pokemon) throws PokemonException {
		modele.modifyPokemon(pokemon);
	}

	private void annuler() {
	}

	private void supprimer(final Pokemon pokemon) {
		throw new UnsupportedOperationException("Cette fonction n est pas encore disponible");
	}
}
