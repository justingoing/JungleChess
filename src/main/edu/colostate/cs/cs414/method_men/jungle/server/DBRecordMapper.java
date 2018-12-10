package edu.colostate.cs.cs414.method_men.jungle.server;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Custom mapper
 */
public class DBRecordMapper implements RowMapper<DBRecord> {

        @Override
        public DBRecord map(ResultSet rs, StatementContext ctx) throws SQLException {
            Long ID = rs.getLong(1);
            String string1 = rs.getString(2);
            String string2 = rs.getString(3);
            String string3 = rs.getString(4);
            String string4 = rs.getString(5);
            return new DBRecord(ID, string1, string2, string3, string4);
        }

}
