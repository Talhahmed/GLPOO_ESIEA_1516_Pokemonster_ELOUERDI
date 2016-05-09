package services;

import common.exceptions.PokemonException;
import datas.enums.Action;
import datas.models.Pokemon;

public interface IActionService {

	void process(final Action action) throws PokemonException;
	void process(final Action action, final Pokemon pokemon) throws PokemonException;
}
