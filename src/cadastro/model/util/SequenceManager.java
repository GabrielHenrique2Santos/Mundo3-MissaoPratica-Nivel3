/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model.util;

/**
 *
 * @author Gabriel
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {


    public static int getValue(String nomeSequencia) {
        String sql = "SELECT nextval(?) as next_value";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nomeSequencia);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("next_value");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}