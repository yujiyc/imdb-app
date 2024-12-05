package trabalho.imdb.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import trabalho.imdb.dados.Ator;
import trabalho.imdb.dados.Filme;
import trabalho.imdb.exception.DeleteException;
import trabalho.imdb.exception.InsertException;
import trabalho.imdb.exception.SelectException;
import trabalho.imdb.exception.UpdateException;

public class FilmeDAO {
    private static FilmeDAO instance = null;
    private static AtorDAO atorDAO = null;
    // private static List<AtorDAO> atorDAO = new ArrayList<AtorDAO>();

    // private PreparedStatement newId;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement selectAll;

    private FilmeDAO() throws SQLException, ClassNotFoundException, SelectException {
        Connection conexao = Conexao.getConexao();

        insert = conexao.prepareStatement("insert into filme values (?, ?, ?, ?)");
        delete = conexao.prepareStatement("delete from filme where id = ?");
        update = conexao.prepareStatement("update filme set nome = ?, ranking = ?, ano = ? where id = ?");
        select = conexao.prepareStatement("select * from filme where id = ?");
        selectAll = conexao.prepareStatement("select * from filme");
        // newId = conexao.prepareStatement("select nextval('id_filme')");

        atorDAO = AtorDAO.getInstance();
    }

    public static FilmeDAO getInstance() throws SQLException, ClassNotFoundException, SelectException {
        if (instance == null) {
            instance = new FilmeDAO();
        }
        return instance;
    }

    // private String newId() throws SelectException {
    // try {
    // ResultSet rs = newId.executeQuery();
    // if (rs.next()) {
    // return rs.getString(1);
    // }
    // } catch (SQLException e) {
    // System.err.println(e);
    // throw new SelectException("Nao foi possivel gerar um novo id");
    // }
    // return null;
    // }

    public void insert(Filme filme) throws InsertException, SelectException {
        try {
            insert.setString(1, filme.getId());
            insert.setString(2, filme.getNome());
            insert.setString(3, filme.getRanking());
            insert.setString(4, filme.getAno());
            insert.executeUpdate();
            if (filme.getElencoPrincipal() != null) {
                for (int i = 0; i < 3; i++) {
                    filme.getElencoPrincipal().get(i).setId_filme(filme.getId());
                    Ator ator = new Ator();
                    ator = filme.getElencoPrincipal().get(i);

                    atorDAO.insert(ator);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
            throw new InsertException("Erro ao inserir filme");
        }
    }

    public void delete(Filme filme) throws DeleteException {
        try {
            for (int i = 0; i < 3; i++) {
                if (filme.getElencoPrincipal().get(i) != null) {
                    atorDAO.delete(filme.getElencoPrincipal().get(i));
                }
            }
            delete.setString(1, filme.getId());
            delete.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteException("Erro ao deletar filme");
        }
    }

    public void update(Filme filme) throws UpdateException {
        try {
            for (int i = 0; i < 3; i++) {
                atorDAO.update(filme.getElencoPrincipal().get(i));
            }
            update.setString(1, filme.getNome());
            update.setString(2, filme.getRanking());
            update.setString(3, filme.getAno());
            update.setString(4, filme.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateException("Erro ao atualizar filme");
        }
    }

    public Filme select(String id_filme) throws SelectException {
        Filme filme = null;
        try {
            select.setString(1, id_filme);
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                filme = new Filme();
                filme.setId(rs.getString("id"));
                filme.setNome(rs.getString("nome"));
                filme.setRanking(rs.getString("ranking"));
                filme.setAno(rs.getString("ano"));

                List<Ator> atores = new ArrayList<Ator>();
                atores = atorDAO.selectAllByIdFilme(filme.getId());
                filme.setElencoPrincipal(atores);
            }
            return filme;
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar filme");
        }
    }

    public List<Filme> selectAll() throws SelectException {
        List<Filme> filmes = new LinkedList<Filme>();
        try {
            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                Filme filme = new Filme();
                filme.setId(rs.getString("id"));
                filme.setNome(rs.getString("nome"));
                filme.setRanking(rs.getString("ranking"));
                filme.setAno(rs.getString("ano"));

                List<Ator> atores = new ArrayList<Ator>();
                atores = atorDAO.selectAllByIdFilme(filme.getId());
                filme.setElencoPrincipal(atores);

                filmes.add(filme);
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar filme");
        }
        return filmes;
    }
}