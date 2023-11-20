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

public class PessoaFisicaDAO {

    // Método para obter uma pessoa física pelo ID
    public pessoaFisica getPessoa(int id) {
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaFisica ON Pessoa.id = PessoaFisica.id WHERE Pessoa.id = ?";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return criarPessoaFisica(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Método para obter todas as pessoas físicas
    public List<pessoaFisica> getPessoas() {
        List<pessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa INNER JOIN PessoaFisica ON Pessoa.id = PessoaFisica.id";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                pessoas.add(criarPessoaFisica(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoas;
    }

    // Método para incluir uma pessoa física
    public void incluir(pessoaFisica pessoa) {
        String sqlPessoa = "INSERT INTO Pessoa (id, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaFisica = "INSERT INTO PessoaFisica (id, cpf) VALUES (?, ?)";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa);
             PreparedStatement preparedStatementPessoaFisica = connection.prepareStatement(sqlPessoaFisica)) {

            int id = SequenceManager.getValue("pessoa_seq"); // Obtém o próximo ID da sequência

            preparedStatementPessoa.setInt(1, id);
            preparedStatementPessoa.setString(2, pessoa.getNome());
            preparedStatementPessoa.setString(3, pessoa.getLogradouro());
            preparedStatementPessoa.setString(4, pessoa.getCidade());
            preparedStatementPessoa.setString(5, pessoa.getEstado());
            preparedStatementPessoa.setString(6, pessoa.getTelefone());
            preparedStatementPessoa.setString(7, pessoa.getEmail());

            preparedStatementPessoaFisica.setInt(1, id);
            preparedStatementPessoaFisica.setString(2, pessoa.getCpf());

            // Executa as inserções
            preparedStatementPessoa.executeUpdate();
            preparedStatementPessoaFisica.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para alterar uma pessoa física
    public void alterar(pessoaFisica pessoa) {
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";
        String sqlPessoaFisica = "UPDATE PessoaFisica SET cpf = ? WHERE id = ?";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa);
             PreparedStatement preparedStatementPessoaFisica = connection.prepareStatement(sqlPessoaFisica)) {

            preparedStatementPessoa.setString(1, pessoa.getNome());
            preparedStatementPessoa.setString(2, pessoa.getLogradouro());
            preparedStatementPessoa.setString(3, pessoa.getCidade());
            preparedStatementPessoa.setString(4, pessoa.getEstado());
            preparedStatementPessoa.setString(5, pessoa.getTelefone());
            preparedStatementPessoa.setString(6, pessoa.getEmail());
            preparedStatementPessoa.setInt(7, pessoa.getId());

            preparedStatementPessoaFisica.setString(1, pessoa.getCpf());
            preparedStatementPessoaFisica.setInt(2, pessoa.getId());

            // Executa as atualizações
            preparedStatementPessoa.executeUpdate();
            preparedStatementPessoaFisica.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir uma pessoa física
    public void excluir(int id) {
        String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";
        String sqlPessoaFisica = "DELETE FROM PessoaFisica WHERE id = ?";

        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa);
             PreparedStatement preparedStatementPessoaFisica = connection.prepareStatement(sqlPessoaFisica)) {

            preparedStatementPessoa.setInt(1, id);
            preparedStatementPessoaFisica.setInt(1, id);

            // Executa as exclusões
            preparedStatementPessoa.executeUpdate();
            preparedStatementPessoaFisica.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método privado para criar uma pessoa física a partir de um ResultSet
    private pessoaFisica criarPessoaFisica(ResultSet resultSet) throws SQLException {
        return new pessoaFisica(
                resultSet.getInt("id"),
                resultSet.getString("nome"),
                resultSet.getString("logradouro"),
                resultSet.getString("cidade"),
                resultSet.getString("estado"),
                resultSet.getString("telefone"),
                resultSet.getString("email"),
                resultSet.getString("cpf")
        );
    }
}