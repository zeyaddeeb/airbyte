/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.db.instance.jobs.migrations;

import static org.jooq.impl.DSL.primaryKey;

import io.airbyte.db.flyway.AirbyteJavaMigration;
import java.time.OffsetDateTime;
import java.util.Arrays;
import org.flywaydb.core.api.migration.Context;
import org.jooq.Catalog;
import org.jooq.DSLContext;
import org.jooq.EnumType;
import org.jooq.Field;
import org.jooq.JSONB;
import org.jooq.Schema;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.SchemaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Provides a baseline for Jobs table.
public class B0_29_1_001__CreateInitialJobsTables extends AirbyteJavaMigration {

  private static final Logger LOGGER = LoggerFactory.getLogger(B0_29_1_001__CreateInitialJobsTables.class);

  @Override
  public void migrate(final Context context) throws Exception {
    LOGGER.info("Running migration: {}", this.getClass().getSimpleName());

    // Warning: please do not use any jOOQ generated code to write a migration.
    // As database schema changes, the generated jOOQ code can be deprecated. So
    // old migration may not compile if there is any generated code.
    final DSLContext ctx = DSL.using(context.getConnection());

    createEnums(ctx);
    createAirbyteMetadataTable(ctx);
    createJobsTable(ctx);
    createAttemptsTable(ctx);
  }

  private static void createEnums(final DSLContext ctx) {
    ctx.createType(JobStatus.NAME)
        .asEnum(Arrays.stream(JobStatus.values()).toList())
        .execute();

    ctx.createType(AttemptStatus.NAME)
        .asEnum(Arrays.stream(AttemptStatus.values()).toList())
        .execute();

    ctx.createType(JobConfigType.NAME)
        .asEnum(Arrays.stream(JobConfigType.values()).toList())
        .execute();
  }

  private static void createAirbyteMetadataTable(final DSLContext ctx) {
    final Field<String> key = DSL.field("key", SQLDataType.VARCHAR(255));
    final Field<String> value = DSL.field("value", SQLDataType.VARCHAR(255));
    ctx.createTableIfNotExists("airbyte_metadata")
        .columns(key, value)
        .constraints(primaryKey(key))
        .execute();
  }

  private static void createJobsTable(final DSLContext ctx) {
    final Field<Long> id = DSL.field("id", SQLDataType.BIGINT.identity(true));
    final Field<JobConfigType> configType = DSL.field("config_type", SQLDataType.VARCHAR.asEnumDataType(JobConfigType.class));
    final Field<String> scope = DSL.field("SCOPE", SQLDataType.VARCHAR(255));
    final Field<JSONB> config = DSL.field("config", SQLDataType.JSONB);
    final Field<JobStatus> status = DSL.field("status", SQLDataType.VARCHAR.asEnumDataType(JobStatus.class));
    final Field<OffsetDateTime> startedAt = DSL.field("started_at", SQLDataType.TIMESTAMPWITHTIMEZONE);
    final Field<OffsetDateTime> createdAt = DSL.field("created_at", SQLDataType.TIMESTAMPWITHTIMEZONE);
    final Field<OffsetDateTime> updatedAt = DSL.field("updated_at", SQLDataType.TIMESTAMPWITHTIMEZONE);

    // FIXME: For some unknown reason, it seems that the Types from createEnums are found by the create
    // table calls. These create statements are a not so great workaround
    ctx.execute("""
                  CREATE
                    TYPE JOB_STATUS AS ENUM(
                        'pending',
                        'running',
                        'incomplete',
                        'failed',
                        'succeeded',
                        'cancelled'
                      );
                  CREATE
                    TYPE JOB_CONFIG_TYPE AS ENUM(
                        'check_connection_source',
                        'check_connection_destination',
                        'discover_schema',
                        'get_spec',
                        'sync',
                        'reset_connection'
                      );
                """);

    ctx.createTableIfNotExists("jobs")
        .columns(
            id,
            configType,
            scope,
            config,
            status,
            startedAt,
            createdAt,
            updatedAt)
        .constraints(primaryKey(id))
        .execute();
  }

  private static void createAttemptsTable(final DSLContext ctx) {
    final Field<Long> id = DSL.field("id", SQLDataType.BIGINT.identity(true));
    final Field<Long> jobId = DSL.field("job_id", SQLDataType.BIGINT);
    final Field<Integer> attemptNumber = DSL.field("attempt_number", SQLDataType.INTEGER);
    final Field<String> logPath = DSL.field("log_path", SQLDataType.VARCHAR(255));
    final Field<JSONB> output = DSL.field("OUTPUT", SQLDataType.JSONB);
    final Field<AttemptStatus> status = DSL.field("status", SQLDataType.VARCHAR.asEnumDataType(AttemptStatus.class));
    final Field<OffsetDateTime> createdAt = DSL.field("created_at", SQLDataType.TIMESTAMPWITHTIMEZONE);
    final Field<OffsetDateTime> updatedAt = DSL.field("updated_at", SQLDataType.TIMESTAMPWITHTIMEZONE);
    final Field<OffsetDateTime> endedAt = DSL.field("ended_at", SQLDataType.TIMESTAMPWITHTIMEZONE);

    // FIXME: For some unknown reason, it seems that the Types from createEnums are found by the create
    // table calls. These create statements are a not so great workaround
    ctx.execute("""
                CREATE
                  TYPE ATTEMPT_STATUS AS ENUM(
                      'running',
                      'failed',
                      'succeeded'
                    );
                """);

    ctx.createTableIfNotExists("attempts")
        .columns(
            id,
            jobId,
            attemptNumber,
            logPath,
            output,
            status,
            createdAt,
            updatedAt,
            endedAt)
        .constraints(primaryKey(id))
        .execute();

    ctx.createUniqueIndex("job_attempt_idx")
        .on("attempts", jobId.getName(), attemptNumber.getName())
        .execute();
  }

  public enum JobStatus implements EnumType {

    pending("pending"),
    running("running"),
    incomplete("incomplete"),
    failed("failed"),
    succeeded("succeeded"),
    cancelled("cancelled");

    public final static String NAME = "JOB_STATUS";

    private final String literal;

    JobStatus(final String literal) {
      this.literal = literal;
    }

    @Override
    public String getLiteral() {
      return literal;
    }

    @Override
    public String getName() {
      return NAME;
    }

    @Override
    public Catalog getCatalog() {
      return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
      return new SchemaImpl(DSL.name("public"));
    }

  }

  public enum AttemptStatus implements EnumType {

    running("running"),
    failed("failed"),
    succeeded("succeeded");

    public final static String NAME = "ATTEMPT_STATUS";

    private final String literal;

    AttemptStatus(final String literal) {
      this.literal = literal;
    }

    @Override
    public String getLiteral() {
      return literal;
    }

    @Override
    public String getName() {
      return NAME;
    }

    @Override
    public Catalog getCatalog() {
      return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
      return new SchemaImpl(DSL.name("public"));
    }

  }

  public enum JobConfigType implements EnumType {

    CHECK_CONNECTION_SOURCE("check_connection_source"),
    CHECK_CONNECTION_DESTINATION("check_connection_destination"),
    DISCOVER_SCHEMA("discover_schema"),
    GET_SPEC("get_spec"),
    SYNC("sync"),
    RESET_CONNECTION("reset_connection");

    public final static String NAME = "JOB_CONFIG_TYPE";

    private final String literal;

    JobConfigType(final String literal) {
      this.literal = literal;
    }

    @Override
    public String getLiteral() {
      return literal;
    }

    @Override
    public String getName() {
      return NAME;
    }

    @Override
    public Catalog getCatalog() {
      return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
      return new SchemaImpl(DSL.name("public"));
    }

  }

}
