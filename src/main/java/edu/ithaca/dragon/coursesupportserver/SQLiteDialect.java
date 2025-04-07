package edu.ithaca.dragon.coursesupportserver;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super();
        registerColumnType(java.sql.Types.VARCHAR, "text");
        registerColumnType(java.sql.Types.INTEGER, "integer");
        registerColumnType(java.sql.Types.BIGINT, "integer");
        registerColumnType(java.sql.Types.BOOLEAN, "integer");
        registerColumnType(java.sql.Types.DOUBLE, "real");
        registerColumnType(java.sql.Types.FLOAT, "real");
        registerColumnType(java.sql.Types.DECIMAL, "real");
        registerColumnType(java.sql.Types.NUMERIC, "real");

        // Register SQLite-specific functions
        registerFunction("concat", new StandardSQLFunction("concat", StringType.INSTANCE));
    }

    @Override
    public boolean dropConstraints() {
        return false; // SQLite does not support dropping constraints
    }

    @Override
    public boolean hasAlterTable() {
        return false; // SQLite has limited ALTER TABLE support
    }

    @Override
    public String getAddColumnString() {
        return "add column"; // SQLite supports adding columns in a limited way
    }

    @Override
    public String getDropForeignKeyString() {
        throw new UnsupportedOperationException("SQLite does not support dropping foreign keys");
    }

    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true; // SQLite supports "IF EXISTS" before table names
    }

    @Override
    public boolean supportsSequences() {
        return false; // SQLite does not support sequences
    }
}