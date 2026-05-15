package dto.game;

import lombok.Getter;
import lombok.Setter;
import model.Game;

@Getter
@Setter
public class GameRequestDTO {

	private int lunghezzaSelezionataDellaParola;
	private int  tentativiMassimi;

	public Game toEntity() {
		Game game = new Game();
		return updateEntity(game);
	}

	public Game updateEntity(Game game) {
		game.setLunghezzaSelezionataDellaParola(this.lunghezzaSelezionataDellaParola);
		game.setTentativiMassimi(this.tentativiMassimi);
		return game;
	}
}
