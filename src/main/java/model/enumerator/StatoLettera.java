package model.enumerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatoLettera implements IEnum{

	CORRETTA("Corretta"),
	PRESENTE("Presente"),
	ASSENTE("Assente");

	private final String descrizione;

	StatoLettera(String descrizione) {this.descrizione = descrizione;}

	@Override
	public String getNome() {return this.name();}

	@Override
	public String getDescrizione() {return this.descrizione;}
}
