package dto;

import lombok.Getter;
import lombok.Setter;
import model.RisultatoLettera;
import model.enumerator.StatoLettera;

@Setter
@Getter
public class RisultatoLetteraDTO {

	private Long id;
	private String lettera;
	StatoLettera stato;

	public static RisultatoLetteraDTO fromEntity(RisultatoLettera risultato) {
		RisultatoLetteraDTO dto = new RisultatoLetteraDTO();
		dto.setLettera(risultato.getLettera());
		dto.setStato(risultato.getStato());
		return dto;
	}
}
