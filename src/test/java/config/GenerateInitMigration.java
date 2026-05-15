package config;

import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;

import java.io.IOException;

public class GenerateInitMigration {

//	mvn test-compile exec:java -Dexec.mainClass="config.GenerateInitMigration" -Dexec.classpathScope="test"
	public static void main(String[] args) throws IOException {
		DbMigration dbMigration = DbMigration.create();
		dbMigration.setPathToResources("src/main/resources");
		dbMigration.addPlatform(Platform.POSTGRES);
		dbMigration.setVersion("V1.0");
		dbMigration.setName("schema");
		dbMigration.setMigrationPath("dbinit");
		dbMigration.generateInitMigration();
	}
}