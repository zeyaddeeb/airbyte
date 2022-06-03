/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.db.flyway;

import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.api.migration.JavaMigration;
import org.flywaydb.core.internal.resolver.MigrationInfoHelper;
import org.flywaydb.core.internal.util.Pair;

// Class is mostly duplicated from org.flywaydb.core.api.migration.BaseJavaMigration
// Flyway has support for BaselineMigration but unfortunately, it is for the enterprise version.
// The base class available has guards in the constructor so that it only works with versioned
// migrations and repeatable migrations so in order to have baseline migration, we need to provide
// our own.
public abstract class AirbyteJavaMigration implements JavaMigration {

  private final MigrationVersion version;
  private final String description;
  private final boolean isBaselineMigration;

  public AirbyteJavaMigration() {
    String shortName = getClass().getSimpleName();
    String prefix = null;

    boolean repeatable = shortName.startsWith("R");
    isBaselineMigration = shortName.startsWith("B");

    if (shortName.startsWith("V") || repeatable || isBaselineMigration) {
      prefix = shortName.substring(0, 1);
    }
    if (prefix == null) {
      throw new FlywayException("Invalid Java-based migration class name: " + getClass().getName() +
          " => ensure it starts with V, R, B" +

          " or implement org.flywaydb.core.api.migration.JavaMigration directly for non-default naming");
    }

    Pair<MigrationVersion, String> info =
        MigrationInfoHelper.extractVersionAndDescription(shortName, prefix, "__", new String[] {""}, repeatable);
    version = info.getLeft();
    description = info.getRight();
  }

  @Override
  public MigrationVersion getVersion() {
    return version;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
  @Override
  public Integer getChecksum() {
    return null;
  }

  @Override
  public boolean isUndo() {
    return false;
  }

  @Override
  public boolean isStateScript() {
    return isBaselineMigration;
  }

  @Override
  public boolean canExecuteInTransaction() {
    return true;
  }

}
