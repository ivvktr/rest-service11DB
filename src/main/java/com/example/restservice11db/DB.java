package com.example.restservice11db;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.sql.*;
import java.time.LocalDateTime;

public class DB {
    private final String url = "jdbc:postgresql://192.168.0.104:5432/postgres";
    private final String login = "postgres";
    private final String password = "postgres";

    public User getUserLogin(String loginget) throws SQLException{
        User user = new User();
        Connection con = DriverManager.getConnection(url, login, password);
        String queryLogin = "SELECT * FROM users JOIN emails ON emails.login = users.login WHERE users.login = '" + loginget +"'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryLogin);
            while (rs.next()) {
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setDate(rs.getString("date"));
            }
            if(user.getLogin()==null){
                throw new Exception();
            }
            rs.close();
            stmt.close();
        }catch (Exception e) {
            System.out.println("Логина не существует");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        con.close();
        return user;
    }

    public User postUser(String loginpost, String passwordpost, String emailpost){

        User user = new User();
        String queryUsers = "INSERT INTO users (login, password, date) VALUES (?, ?, ?)";
        String queryEmails = "INSERT INTO emails (login, email) VALUES (?, ?)";
        try ( Connection con = DriverManager.getConnection(url, login, password);
              PreparedStatement stmtUsr = con.prepareStatement(queryUsers);
              PreparedStatement stmtEml = con.prepareStatement(queryEmails))
        {
            LocalDateTime date = LocalDateTime.now();
            stmtUsr.setString(1, loginpost);
            user.setLogin(loginpost);
            stmtUsr.setString(2, passwordpost);
            stmtUsr.setTimestamp(3, Timestamp.valueOf(date));
            user.setDate(String.valueOf(date));
            stmtUsr.executeUpdate();

            stmtEml.setString(1, loginpost);
            stmtEml.setString(2, emailpost);
            stmtEml.executeUpdate();

        }
        catch (Exception e) {
            System.out.println("Не удалось выполнить вставку");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return user;
    }
}