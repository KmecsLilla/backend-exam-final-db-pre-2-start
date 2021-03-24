package com.codecool.database;


import java.sql.*;

public class RadioCharts {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public RadioCharts(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public String getMostPlayedSong() {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "SELECT song FROM music_broadcast GROUP BY song ORDER BY SUM(times_aired) DESC LIMIT 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getMostActiveArtist() {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "SELECT artist FROM music_broadcast GROUP BY artist ORDER BY COUNT(DISTINCT song) DESC LIMIT 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
