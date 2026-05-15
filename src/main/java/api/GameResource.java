package api;

import Services.GameService;
import dto.game.GameRequestDTO;
import dto.game.GameResultDTO;
import dto.tentativo.TentativoRequestDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import model.Game;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Tag(name = "WordleGame")
@Path("wordleGame/Game")
@Slf4j
public class GameResource {

	@Inject
	GameService gameService;

	@GET
	@Operation(summary = "Restituisce tutte le partite",
					description = "API per la restituzione di tutte le partite ")
	public List<GameResultDTO> getAllGames() {
		return gameService.getAllGames();
	}

	@POST
	public GameResultDTO startGame(@RequestBody GameRequestDTO request) {
		return gameService.startGame(request);
	}

	@PUT
	@Path("{idGame}")
	public GameResultDTO updateGame(@PathParam("idGame") Long idGame, @RequestBody TentativoRequestDTO request) {
		return gameService.nextTentativo(idGame, request);
	}
}
