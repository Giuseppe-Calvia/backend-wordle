package model.enumerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatoPartita implements IEnum{

	IN_CORSO("In corso"),
	VINTA("Vinta"),
	PERSA("Persa");

	private final String descrizione;

	StatoPartita(String descrizione) {this.descrizione = descrizione;}

	@Override
	public String getNome() {return this.name();}

	@Override
	public String getDescrizione() {return this.descrizione;}
}
