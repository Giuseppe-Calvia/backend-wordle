package dto.game;

import dto.tentativo.TentativoDTO;
import lombok.Getter;
import lombok.Setter;
import model.Game;
import model.enumerator.StatoPartita;

import java.util.List;

@Setter @Getter
public class GameResultDTO {

	private Long id;
	private int lunghezzaSelezionataDellaParola;
	private List<TentativoDTO> tentativi;
	private String  parolaSegreta;
	StatoPartita statoPartita;

	public static List<GameResultDTO> fromEntity(List<Game> games) {
		return games.stream()
						.map(GameResultDTO::fromEntity)
						.toList();
	}

	public static GameResultDTO fromEntity(Game game) {
		GameResultDTO dto = new GameResultDTO();
		dto.setId(game.getId());
		dto.setLunghezzaSelezionataDellaParola(game.getLunghezzaSelezionataDellaParola());
		dto.setTentativi(game.getTentativi().stream()
						.map(TentativoDTO::fromEntity).toList());
		dto.setStatoPartita(game.getStatoPartita());

		if(game.getStatoPartita() != StatoPartita.IN_CORSO){
			dto.setParolaSegreta(game.getParola().getValore());
		}

		return dto;
	}
}
