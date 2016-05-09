package datas.models;

import java.util.List;
import common.helpers.DefenseHelper;
import common.helpers.TypeHelper;
import fr.ybonnel.csvengine.annotation.CsvColumn;
import fr.ybonnel.csvengine.annotation.CsvFile;

@CsvFile(separator = ";")
public class SimplePokemon implements Pokemon {

	private static final long serialVersionUID = -1225454238084424608L;

	@CsvColumn(value = "nom", order = 0)
	private String nom;

	@CsvColumn(value = "Nom complet", order = 1)
	private String nomComplet;

	@CsvColumn(value = "type", adapter = TypeHelper.class, order = 2)
	private TypePokemon type;

	@CsvColumn(value = "boule_2", adapter = DefenseHelper.class, order = 4)
	private Double boule_2;
	
	@CsvColumn(value = "boule_1", adapter = DefenseHelper.class, order = 3)
	private Double boule_1;

	public SimplePokemon() {
	}

	public SimplePokemon(final String nom) {
		this.nom = nom;
	}

	public SimplePokemon(final String nom, final TypePokemon type) {
		this(nom);
		this.type = type;
	}

	public SimplePokemon(String nom, String nomComplet, TypePokemon type,  List<String> couleurs, Double boule_2,Double boule_1) {
		this(nom, type);
		this.nomComplet = nomComplet;
		this.boule_2 = boule_2;
		this.boule_1 = boule_1;
	}

	@Override
	public String toString() {
		return "SimplePokemon [Attaque=" + nom + "]";
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomComplet() {
		return nomComplet;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}

	public TypePokemon getTypePokemon() {
		return type;
	}

	public void setTypePokemon(TypePokemon type) {
		this.type = type;
	}

		public Double getDefense() {
		return boule_2;
	}

	public void setDefense(Double boule_2) {
		this.boule_2 = boule_2;
	}
	
	public Double getAttaque() {
		return boule_1;
	}

	public void setAttaque(Double boule_1) {
		this.boule_1 = boule_1;
	}

	@Override
	public String toString2() {
		return null;
	}
}
