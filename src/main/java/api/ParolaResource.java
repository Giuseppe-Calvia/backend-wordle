package api;

import Services.ParolaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import model.Parola;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Tag(name = "WordleGame")
@Path("wordleGame/Parola")
@Slf4j
public class ParolaResource {

	@Inject
	ParolaService parolaService;

	@GET
	public List<Parola> getAllParola() {
		return parolaService.getAll();
	}
}
