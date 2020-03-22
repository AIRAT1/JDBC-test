package de.db.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

//@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
            String dbUrl = properties.getProperty("db.url");
            String dbUser = properties.getProperty("db.user");
            String dbPassword = properties.getProperty("db.password");
            String dbDriverClassName = properties.getProperty("db.driverClassName");
            Class.forName(dbDriverClassName);
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/addUser.jsp").forward(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String firstName = req.getParameter("first_name");
//        String last_name = req.getParameter("last_name");
//        try {
////            'temp','temp'); DROP TABLE fix_car; SELECT ('temp', 'hello');
////            Statement statement = connection.createStatement();
////            String sqlInsert = "INSERT INTO fix_user(first_name, last_name) " +
////                    "VALUES ('" + firstName + "', '" + last_name + "');";
////            System.out.println(sqlInsert);
////            statement.execute(sqlInsert);
//            PreparedStatement statement = connection.prepareStatement("INSERT INTO fix_user (first_name, last_name) VALUES (?, ?)");
//            statement.setString(1, firstName);
//            statement.setString(2, last_name);
//            statement.execute();
//        } catch (SQLException e) {
//            throw new IllegalStateException(e);
//        }
//    }
}
