package edu.colostate.cs.cs414.method_men.jungle.server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.HandleConsumer;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import spark.utils.IOUtils;

import javax.management.Query;


public final class SqlUtils {
    private static final String CSU_DB = "jdbc:mysql://faure.cs.colostate.edu/connorbc";
    private static final String PI_DB = "jdbc:mysql://jungle.marcelfiore.com/jungle";
    private static final String LOCAL_DB = "jdbc:mysql://localhost/test";
    private static final List<DbSpecification> PLACES_TO_CHECK = Arrays.asList(
            new DbSpecification(CSU_DB, "connorbc", "824229092"),
            new DbSpecification(CSU_DB, System.getenv("CSU_NAME"), System.getenv("CSU_ID")),
            new DbSpecification(CSU_DB, System.getProperty("username"),
                    System.getProperty("password")),
            new DbSpecification(LOCAL_DB, "", ""),
            new DbSpecification(PI_DB, "jungle", "MethodMen1")
    );
    private static Pattern DELIM = Pattern.compile("(;(\\r)?\\n)|(--\\n)");
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        // Fail after 1 seconds
        DriverManager.setLoginTimeout(1);
    }
    private static Jdbi INSTANCE = findJdbi();


    private SqlUtils() {}

    public static Jdbi getJdbi() {
        return INSTANCE;
    }
    /**
     * Gets a Jdbi instance based on the current environment.
     *
     * @return jdbi instance or null if a database can not be found
     */
    private static Jdbi findJdbi() {
        Jdbi jdbi = searchPlaces();
        // Maybe we're running a proxy and didn't configure it right
        if (jdbi == null) {
            System.out.println("Configuring a socks proxy and trying again.");
            System.setProperty("socksProxyHost", "localhost");
            System.setProperty("socksProxyPort", "9008");
            jdbi = searchPlaces();
        }
        //if (jdbi != null ) {
        //    jdbi.registerRowMapper(new Place.Mapper());
        //}
        return jdbi;
    }

    private static Jdbi searchPlaces() {
        return PLACES_TO_CHECK.stream()
                .filter(DbSpecification::canConnect)
                .findAny()
                .map(DbSpecification::create)
                .orElse(null);
    }

    private static boolean canConnect(String url, String username, String password) {
        System.out.print("Trying to connect to " + url + " with "+username+":"+password+"...");
        try (Connection c = DriverManager.getConnection(url, username, password)) {
            System.out.println(" Success.");
            return true;
        } catch (SQLException e) {
            System.out.println(" Failed.");
            //e.printStackTrace();
            return false;
        }
    }


    /**
     * Creates a HandleConsumer that will execute the specified sql files. While this method
     * may not fully support MariaDB's dialect, it does fully support the output of Mariadb
     * dumps.
     *
     * @param files the files to execute in order
     * @return a HandleConsumer which may be passed to {@link Jdbi#useHandle(HandleConsumer)}.
     */
    public static HandleConsumer<IOException> executeFiles(String... files) {
        return handle -> {
            for (String file : files) {
                doExecute(file, handle);
            }
        };
    }

    private static void doExecute(String file, Handle handle) throws IOException {
        try (Statement statement = handle.getConnection().createStatement()) {
            for (String sql : readCommands(file)) {
                statement.addBatch(sql);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readCommands(String path) throws IOException {
        try (InputStream in = SqlUtils.class.getClassLoader().getResourceAsStream(path)) {
            return DELIM.splitAsStream(IOUtils.toString(in))
                    .filter(s -> !s.trim().isEmpty())
                    .collect(Collectors.toList());
        }
    }

    private static class DbSpecification {
        private final String url;
        private final String username;
        private final String password;

        private DbSpecification(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        boolean canConnect() {
            return url != null && username != null && password != null
                    && SqlUtils.canConnect(url, username, password);
        }

        Jdbi create() {
            Jdbi jdbi = Jdbi.create(url, username, password);
            jdbi.installPlugin(new SqlObjectPlugin());
            return jdbi;
        }
    }

}
