package dto.tentativo;

import dto.RisultatoLetteraDTO;
import lombok.Getter;
import lombok.Setter;
import model.Tentativo;

import java.util.List;

@Setter @Getter
public class TentativoDTO {

	private Long id;
	private int numeroTentativo;
	private List<RisultatoLetteraDTO> risultati;

	public static TentativoDTO fromEntity(Tentativo tentativo) {
		TentativoDTO dto = new TentativoDTO();
		dto.setId(tentativo.getId());
		dto.setNumeroTentativo(tentativo.getNumeroTentativo());
		dto.setRisultati(tentativo.getRisultati().stream()
						.map(RisultatoLetteraDTO::fromEntity).toList());
		return dto;
	}
}
