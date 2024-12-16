package org.code.Exercice2.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurDAOImpl implements IProfesseurDAO {

    private final String url = "jdbc:mysql://localhost:3306/professeurs_db";
    private final String user = "root";
    private final String password = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


    public List<Professeur> getAllProfesseurs() {
        List<Professeur> professeurs = new ArrayList<>();
        String query = "SELECT * FROM professeurs";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                professeurs.add(new Professeur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("specialite")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professeurs;
    }


    public Professeur getProfesseurById(int id) {
        String query = "SELECT * FROM professeurs WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Professeur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("specialite")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean addProfesseur(Professeur professeur) {
        String query = "INSERT INTO professeurs (id, nom, specialite) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, professeur.getId());
            pstmt.setString(2, professeur.getNom());
            pstmt.setString(3, professeur.getSpecialite());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateProfesseur(Professeur professeur) {
        String query = "UPDATE professeurs SET nom = ?, specialite = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, professeur.getNom());
            pstmt.setString(2, professeur.getSpecialite());
            pstmt.setInt(3, professeur.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean deleteProfesseur(int id) {
        String query = "DELETE FROM professeurs WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

