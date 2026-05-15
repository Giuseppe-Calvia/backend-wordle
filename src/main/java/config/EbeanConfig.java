package config;

import io.agroal.api.AgroalDataSource;
import io.ebean.Database;
import io.ebean.config.DatabaseConfig;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

public class EbeanConfig {

	@ApplicationScoped
	@Startup
	@Produces
	public Database createDb(AgroalDataSource source) {
		DatabaseConfig dbConfig = new DatabaseConfig();
		dbConfig.setDataSource(source);
		dbConfig.setDefaultServer(true);
		dbConfig.addPackage("model");
		dbConfig.setCurrentUserProvider(() -> "system");
		return dbConfig.build();
	}
}
