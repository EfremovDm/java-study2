package ru.efremovdm.lesson8.server;

import java.sql.*;

class AuthService {

    private Connection connection;
    private Statement stmt;
    private PreparedStatement psFindNick;
    private PreparedStatement psUserRegister;

    void connect() throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
        checkTable();
        psFindNick = connection.prepareStatement("SELECT nick FROM users WHERE login = ? AND password = ?;");
        psUserRegister = connection.prepareStatement("INSERT INTO users (login, password, nick) VALUES (?, ?, ?)");
        // userRegistration("login4", "pass4", "nick4");
        testUsers();
    }

    void checkTable(){
        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS users (\n" +
                    "    id       INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    login    TEXT    UNIQUE,\n" +
                    "    password INTEGER,\n" +
                    "    nick     TEXT    UNIQUE\n" +
                    ");\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void testUsers() throws SQLException {
        stmt.execute("DELETE FROM users;");
        for (int i = 1; i < 20; i++) {
            userRegistration("login" + i, "pass" + i, "nick" + i);
        }
    }

    boolean userRegistration(String login, String pass, String nick) throws SQLException{
        try {
            psUserRegister.setString(1, login);
            int passHash = pass.hashCode();
            psUserRegister.setInt(2, passHash);
            psUserRegister.setString(3, nick);
            return psUserRegister.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new SQLException("Ошибка регистрации пользователя");
        }
    }

    String getNickByLoginAndPass(String login, String pass) {
        try {
            psFindNick.setString(1, login);
            int passHash = pass.hashCode();
            psFindNick.setInt(2, passHash);
            ResultSet rs = psFindNick.executeQuery();
            if(rs.next()){
                return rs.getString("nick");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    boolean absentNickReg(String Nick)throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE nick = \"" + Nick + "\";");
        return rs.next();
    }

    boolean absentLoginReg( String Login) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE login = \"" + Login + "\";");
        return rs.next();
    }

    void disconnect(){
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
