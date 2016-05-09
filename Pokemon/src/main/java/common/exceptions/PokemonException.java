package common.exceptions;

public class PokemonException extends Exception {

	public PokemonException() {
		super();
	}

	public PokemonException(String message, Throwable cause) {
		super(message, cause);

	}

	public PokemonException(String message) {
		super(message);

	}

	public PokemonException(Throwable cause) {
		super(cause);

	}
}
