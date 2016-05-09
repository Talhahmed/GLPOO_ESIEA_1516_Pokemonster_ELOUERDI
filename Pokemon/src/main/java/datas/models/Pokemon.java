package datas.models;

import java.io.Serializable;

public interface Pokemon extends Serializable {

	String getNom();
	String getNomComplet();
	TypePokemon getTypePokemon();
	Double getDefense();
	Double getAttaque();
	String toString2();

}
