package trabalho.imdb.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import trabalho.imdb.dados.Ator;
import trabalho.imdb.exception.DeleteException;
import trabalho.imdb.exception.InsertException;
import trabalho.imdb.exception.SelectException;
import trabalho.imdb.exception.UpdateException;

public class AtorDAO {
    private static AtorDAO instance = null;

    // private PreparedStatement newId;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement selectAll;
    // private PreparedStatement selectAllByIdFilme;

    public static AtorDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
        if (instance == null) {
            instance = new AtorDAO();
        }
        return instance;
    }

    private AtorDAO() throws ClassNotFoundException, SQLException, SelectException {
        Connection conexao = Conexao.getConexao();
        insert = conexao.prepareStatement("insert into ator values (?, ?, ?, ?, ?, ?)");
        delete = conexao.prepareStatement("delete from ator where id_filme = ?");
        update = conexao.prepareStatement(
                "update ator set nome = ?, dataNascimento = ?, sexo = ?, bio = ?, where id_filme = ?");
        select = conexao.prepareStatement("select * from ator where id_filme = ?");
        selectAll = conexao.prepareStatement("select * from ator");
        // selectAllByIdFilme = conexao.prepareStatement("select * from ator where
        // id_filme = ?");
        // newId = conexao.prepareStatement("select nextval ('id_ator')");
    }

    // private String newId() throws SelectException {
    // try {
    // ResultSet rs = newId.executeQuery();
    // if (rs.next()) {
    // return rs.getString(1);
    // }
    // } catch (SQLException e) {
    // System.err.println(e);
    // throw new SelectException("Erro ao buscar novo id da tabela ator");
    // }
    // return null;
    // }

    public void insert(Ator ator) throws InsertException, SelectException {
        try {
            insert.setString(1, ator.getId());
            insert.setString(2, ator.getNome());
            insert.setString(3, ator.getDataNascimento());
            insert.setString(4, ator.getSexo());
            insert.setString(5, ator.getBiografia());
            insert.setString(6, ator.getId_filme());
            insert.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            throw new InsertException("Erro ao inserir ator");
        }
    }

    public void delete(Ator ator) throws DeleteException {
        try {
            delete.setString(1, ator.getId());
            delete.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteException("Erro ao deletar ator");
        }
    }

    public void update(Ator ator) throws UpdateException {
        try {
            update.setString(1, ator.getNome());
            update.setString(2, ator.getDataNascimento());
            update.setString(3, ator.getSexo());
            update.setString(4, ator.getBiografia());
            update.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateException("Erro ao atualizar ator");
        }
    }

    public Ator select(String id_filme) throws SelectException {
        try {
            select.setString(1, id_filme);
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                Ator ator = new Ator();
                String id = rs.getString(1);
                ator.setId(id);
                String nome = rs.getString(2);
                ator.setNome(nome);
                String data = rs.getString(3);
                ator.setDataNascimento(data);
                String sexo = rs.getString(4);
                ator.setSexo(sexo);
                String bio = rs.getString(5);
                ator.setBiografia(bio);
                return ator;
            }
        } catch (Exception e) {
            throw new SelectException("Erro ao buscar ator");
        }
        return null;
    }

    public List<Ator> selectAll() throws SelectException {
        List<Ator> atores = new LinkedList<Ator>();
        try {
            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                Ator ator = new Ator();
                String id = rs.getString(1);
                ator.setId(id);
                String nome = rs.getString(2);
                ator.setNome(nome);
                String data = rs.getString(3);
                ator.setDataNascimento(data);
                String sexo = rs.getString(4);
                ator.setSexo(sexo);
                String bio = rs.getString(5);
                ator.setBiografia(bio);
                atores.add(ator);
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar atores");
        }
        return atores;
    }

    public List<Ator> selectAllByIdFilme(String id_filme) throws SelectException {
        List<Ator> atores = new LinkedList<Ator>();
        try {
            select.setString(1, id_filme);
            ResultSet rs = select.executeQuery();
            while (rs.next()) {
                Ator ator = new Ator();
                String id = rs.getString(1);
                ator.setId(id);
                String nome = rs.getString(2);
                ator.setNome(nome);
                String data = rs.getString(3);
                ator.setDataNascimento(data);
                String sexo = rs.getString(4);
                ator.setSexo(sexo);
                String bio = rs.getString(5);
                ator.setBiografia(bio);
                atores.add(ator);
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar atores");
        }
        return atores;
    }
}