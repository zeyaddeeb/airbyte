/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.db.instance.configs.migrations;

import static org.jooq.impl.DSL.primaryKey;

import io.airbyte.db.flyway.AirbyteJavaMigration;
import java.time.OffsetDateTime;
import org.flywaydb.core.api.migration.Context;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.JSONB;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: update migration description in the class name
public class B0_29_1_001__CreateInitialConfigsTables extends AirbyteJavaMigration {

  private static final Logger LOGGER = LoggerFactory.getLogger(B0_29_1_001__CreateInitialConfigsTables.class);

  private static final String AIRBYTE_CONFIGS_TABLE_NAME = "airbyte_configs";

  @Override
  public void migrate(final Context context) throws Exception {
    LOGGER.info("Running migration: {}", this.getClass().getSimpleName());

    // Warning: please do not use any jOOQ generated code to write a migration.
    // As database schema changes, the generated jOOQ code can be deprecated. So
    // old migration may not compile if there is any generated code.
    final DSLContext ctx = DSL.using(context.getConnection());

    createAirbyteConfigsTable(ctx);
  }

  private static void createAirbyteConfigsTable(final DSLContext ctx) {
    final Field<Long> id = DSL.field("id", SQLDataType.BIGINT.identity(true));
    final Field<String> configId = DSL.field("config_id", SQLDataType.VARCHAR(36).nullable(false));
    final Field<String> configType = DSL.field("config_type", SQLDataType.VARCHAR(60).nullable(false));
    final Field<JSONB> configBlob = DSL.field("config_blob", SQLDataType.JSONB.nullable(false));
    final Field<OffsetDateTime> createdAt = DSL.field("created_at", SQLDataType.TIMESTAMPWITHTIMEZONE.nullable(false));
    final Field<OffsetDateTime> updatedAt = DSL.field("updated_at", SQLDataType.TIMESTAMPWITHTIMEZONE.nullable(false));

    ctx.createTableIfNotExists(AIRBYTE_CONFIGS_TABLE_NAME)
        .columns(
            id,
            configId,
            configType,
            configBlob,
            createdAt,
            updatedAt)
        .constraints(primaryKey(id))
        .execute();
    ctx.createUniqueIndexIfNotExists("airbyte_configs_type_id_idx")
        .on(AIRBYTE_CONFIGS_TABLE_NAME, configType.getName(), configId.getName())
        .execute();
    ctx.createIndexIfNotExists("airbyte_configs_id_idx")
        .on(AIRBYTE_CONFIGS_TABLE_NAME, configId.getName())
        .execute();
  }

}
