package common.helpers;

import datas.models.TypePokemon;
import fr.ybonnel.csvengine.adapter.AdapterCsv;

public class TypeHelper extends AdapterCsv<TypePokemon> {

	@Override
	public TypePokemon parse(String chaine) {
		return TypePokemon.valueOfByCode(Integer.parseInt(chaine));
	}

	@Override
	public String toString(TypePokemon sexe) {
		return sexe.isLegendaire() ? "1" : "2";
	}
}
