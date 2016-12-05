package model.Pokemon;

import java.io.Serializable;

public enum PokemonType implements Serializable {
	Pikachu, Charmander, MewTwo, Magmar, Caterpie, Geodude, Eevee, Doduo,
	Krabby, Drowzee;
	
	@Override
	public String toString(){
		return name();
	}
}
