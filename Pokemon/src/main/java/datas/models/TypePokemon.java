package datas.models;

public enum TypePokemon {

	Normale(2, "f"),

	Legendaire(1, "m");

	private final int code;
	private final String lettre;

	TypePokemon(final int code, final String lettre) {
		this.code = code;
		this.lettre = lettre;
	}

	public static TypePokemon valueOfByCode(final int code) {
		switch (code) {
		case 1:
			return Legendaire;

		case 2:
			return Normale;

		default:
			throw new IllegalArgumentException("Le sexe demande n'existe pas.");
		}
	}

	public static TypePokemon valueOfByLettre(final String lettre) {
		
		for(TypePokemon sexe : values()) {
			if (sexe.lettre.equals(lettre)) {
				return sexe;
			}
		}
		
		throw new IllegalArgumentException("Le sexe demande n'existe pas.");
	}

	public boolean isLegendaire() {
		return this == Legendaire;
	}

	public int getCode() {
		return code;
	}
}
