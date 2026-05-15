package repository;

import io.ebean.Database;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Parola;

import java.util.List;

@ApplicationScoped
public class ParolaRepository {

	@Inject
	Database db;

	public List<Parola> findAll() {
		return db.find(Parola.class).findList();
	}

	public Parola findRandom(int lunghezza){
		return db.find(Parola.class)
						.where()
						.eq("lunghezza", lunghezza)
						.orderBy("random()")
						.setMaxRows(1)
						.findOne();
	}
}
