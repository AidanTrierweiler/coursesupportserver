package edu.ithaca.dragon.coursesupportserver;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.hibernate.dialect.identity.IdentityColumnSupport;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super();
        registerColumnType(java.sql.Types.VARCHAR, "text");
        registerColumnType(java.sql.Types.INTEGER, "integer");
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new IdentityColumnSupportImpl() {
            @Override
            public boolean supportsIdentityColumns() {
                return true;
            }

            @Override
            public String getIdentitySelectString(String table, String column, int type) {
                return "select last_insert_rowid()"; // SQLite's way of getting the last inserted ID
            }

            @Override
            public String getIdentityColumnString(int type) {
                return "integer primary key autoincrement"; // Ensure AUTOINCREMENT is included
            }
        };
    }

    @Override
    public boolean supportsSequences() {
        return false; // SQLite does not support sequences
    }
}