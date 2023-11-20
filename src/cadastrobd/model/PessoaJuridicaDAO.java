/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Gabriel
 */
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {

    // Método para obter uma pessoa jurídica pelo ID
    public pessoaJuridica getPessoa(int id) {
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaJuridica ON Pessoa.id = PessoaJuridica.id WHERE Pessoa.id = ?";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return criarPessoaJuridica(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Método para obter todas as pessoas jurídicas
    public List<pessoaJuridica> getPessoas() {
        List<pessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaJuridica ON Pessoa.id = PessoaJuridica.id";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                pessoas.add(criarPessoaJuridica(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoas