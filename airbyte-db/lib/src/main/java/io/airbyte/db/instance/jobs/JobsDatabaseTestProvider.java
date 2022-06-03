/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.db.instance.jobs;

import io.airbyte.commons.resources.MoreResources;
import io.airbyte.db.Database;
import io.airbyte.db.factory.DatabaseCheckFactory;
import io.airbyte.db.init.DatabaseInitializationException;
import io.airbyte.db.instance.DatabaseConstants;
import io.airbyte.db.instance.FlywayDatabaseMigrator;
import io.airbyte.db.instance.test.TestDatabaseProvider;
import java.io.IOException;
import org.flywaydb.core.Flyway;
import org.jooq.DSLContext;

public class JobsDatabaseTestProvider implements TestDatabaseProvider {

  private final DSLContext dslContext;
  private final Flyway flyway;

  public JobsDatabaseTestProvider(final DSLContext dslContext, final Flyway flyway) {
    this.dslContext = dslContext;
    this.flyway = flyway;
  }

  @Override
  public Database create(final boolean runMigration) throws IOException, DatabaseInitializationException {
    final String initialSchema = MoreResources.readResource(DatabaseConstants.JOBS_SCHEMA_PATH);
    DatabaseCheckFactory.createJobsDatabaseInitializer(dslContext, DatabaseConstants.DEFAULT_CONNECTION_TIMEOUT_MS, initialSchema).initialize();

    final Database jobsDatabase = new Database(dslContext);

    final FlywayDatabaseMigrator migrator = new JobsDatabaseMigrator(
        jobsDatabase, flyway);
    if (runMigration) {
      migrator.migrate();
    } else {
      // Applying the baseline migration to ensure we have a base state to work with
      // It was previously a pre-flyway script so most test assumes those tables would exist even before
      // migrations were applied
      migrator.migrate("0_29_1_001");
    }

    return jobsDatabase;
  }

}
