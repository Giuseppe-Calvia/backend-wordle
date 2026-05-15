package Services;

import dto.game.GameRequestDTO;
import dto.game.GameResultDTO;
import dto.tentativo.TentativoRequestDTO;
import io.ebean.Database;
import io.ebean.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Game;
import model.Parola;
import model.RisultatoLettera;
import model.Tentativo;
import model.enumerator.StatoLettera;
import model.enumerator.StatoPartita;
import repository.ParolaRepository;
import java.util.List;

@ApplicationScoped
public class GameService {

	@Inject
	Database db;

	@Inject
	ParolaRepository parolaRepository;

	public GameResultDTO startGame(GameRequestDTO request) {
		try(Transaction tx = db.beginTransaction()) {
			Parola parola = parolaRepository.findRandom((request.getLunghezzaSelezionataDellaParola()));

			Game game = request.toEntity();
			game.setParola(parola);
			game.setStatoPartita(StatoPartita.IN_CORSO);
			game.insert();

			tx.commit();
			return GameResultDTO.fromEntity(game);
		}
	}

	public List<GameResultDTO> getAllGames() {
		return GameResultDTO.fromEntity(db.find(Game.class).findList());
	}

	public Game getGame(Long id) {
		return db.find(Game.class, id);
	}

	public GameResultDTO nextTentativo(Long id, TentativoRequestDTO request) {
		Game game = getGame(id);
		if(!isEditable(game)){
			throw new jakarta.ws.rs.BadRequestException("Partita già terminata con stato: " + game.getStatoPartita());
		}
		try(Transaction tx = db.beginTransaction()) {

			List<RisultatoLettera> risultati = confrontoParole(
							request.getTentativo(),
							game.getParola().getValore()
			);

			Tentativo tentativo =  new Tentativo();
			tentativo.setGame(game);
			tentativo.setNumeroTentativo(game.getTentativi().size() + 1);
			tentativo.setRisultati(risultati);
			tentativo.insert();

			game.getTentativi().add(tentativo);

			boolean vinto = risultati.stream()
											.allMatch(r -> r.getStato() == StatoLettera.CORRETTA);
			if(vinto) {
				game.setStatoPartita(StatoPartita.VINTA);
			} else if (game.getTentativi().size() >= game.getTentativiMassimi()) {
				game.setStatoPartita(StatoPartita.PERSA);
			}

			game.update();
			tx.commit();

			GameResultDTO dto = GameResultDTO.fromEntity(game);
			if(game.getStatoPartita() != StatoPartita.IN_CORSO) {
				dto.setParolaSegreta(game.getParola().getValore());
			}
			return dto;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private boolean isEditable(Game game) {
		return game.getStatoPartita() == StatoPartita.IN_CORSO;
	}

	private List<RisultatoLettera> confrontoParole(String tentativo, String segreta) {
		int lunghezza = segreta.length();
		RisultatoLettera[] risultato = new RisultatoLettera[lunghezza];
		boolean[] segretaUsata = new boolean[lunghezza];
		boolean[] tentativoUsato = new boolean[lunghezza];

		for(int i = 0; i < lunghezza; i++) {
			if(tentativo.charAt(i) == segreta.charAt(i)) {
				risultato[i] = creaRisultato(tentativo.charAt(i), StatoLettera.CORRETTA);
				segretaUsata[i] = true;
				tentativoUsato[i] = true;
			}
		}

		for(int i = 0; i < lunghezza; i++) {
			if(tentativoUsato[i]) continue;

			boolean trovata = false;
			for(int j = 0; j < lunghezza; j++) {
				if (!segretaUsata[j] && tentativo.charAt(i) == segreta.charAt(j)) {
					risultato[i]  = creaRisultato(tentativo.charAt(i), StatoLettera.PRESENTE);
					segretaUsata[j] = true;
					trovata = true;
					break;
				}
			}

			if(!trovata) {
				risultato[i] = creaRisultato(tentativo.charAt(i), StatoLettera.ASSENTE);
			}
		}

		return List.of(risultato);
	}

	private RisultatoLettera creaRisultato(char lettera, StatoLettera stato) {
		RisultatoLettera risultato = new RisultatoLettera();
		risultato.setLettera(String.valueOf(lettera));
		risultato.setStato(stato);
		return risultato;
	}
}
