package trabalho.imdb.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Connection conexao = null;
    private static String senha;

    public Conexao() {
    }

    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        if (conexao == null) {
            String url = "jdbc:postgresql://localhost:5432/travalho";
            String username = "postgres";
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, username, senha);
        }
        return conexao;
    }

    public static void setSenha(String senha) {
        Conexao.senha = senha;
    }
}
