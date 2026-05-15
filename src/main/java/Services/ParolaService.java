package Services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.ebean.Database;
import model.Parola;
import repository.ParolaRepository;

import java.util.List;

@ApplicationScoped
public class ParolaService {

	@Inject
	Database db;

	@Inject
	ParolaRepository parolaRepository;

	public List<Parola> getAll() {
		return parolaRepository.findAll();
	}
}
