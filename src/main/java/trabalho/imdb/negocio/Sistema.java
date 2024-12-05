package trabalho.imdb.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trabalho.imdb.api.Requests;
import trabalho.imdb.api.dados.ActorResponse;
import trabalho.imdb.api.dados.ComplementResponse;
import trabalho.imdb.api.dados.TitleResponse;
import trabalho.imdb.dados.Ator;
import trabalho.imdb.dados.Filme;
import trabalho.imdb.exception.InsertException;
import trabalho.imdb.exception.SelectException;
import trabalho.imdb.persistencia.AtorDAO;
import trabalho.imdb.persistencia.Conexao;
import trabalho.imdb.persistencia.FilmeDAO;

public class Sistema {
    private Requests requests = new Requests();
    // private List<Filme> listaDeFilmes = new ArrayList<Filme>();
    // private List<Ator> listaAtor = new ArrayList<Ator>();
    private FilmeDAO filmeDAO;
    private AtorDAO atorDAO;

    public static Sistema instance = null;

    public static Sistema getInstance() {
        if (instance == null) {
            try {
                instance = new Sistema("0916");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SelectException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Sistema(String senha) throws ClassNotFoundException, SQLException, SelectException {
        Conexao.setSenha(senha);
        filmeDAO = FilmeDAO.getInstance();
        atorDAO = AtorDAO.getInstance();
    }

    public String listarFilmes(String nome) {
        String retorno = "";

        List<TitleResponse> titles = requests.getTitles(nome);

        for (TitleResponse title : titles) {
            if (title.getTitleType() != null && "movie".contains(title.getTitleType())) {
                retorno += "ID: " + title.getId() + " Titulo: " + title.getTitle() + "\n";
            }
        }
        return retorno;
    }

    public void cadastrarFilme(String id, String nome) throws InsertException, SelectException {
        List<TitleResponse> titles = requests.getTitles(nome);
        TitleResponse temp = new TitleResponse();
        for (TitleResponse title : titles) {
            if (id.contains(title.getId())) {
                temp = title;
            }
        }

        String novoId = novoId(id, 7, 16);
        List<ComplementResponse> complements = requests.getComplement(nome);
        ComplementResponse comp = new ComplementResponse();
        for (ComplementResponse complement : complements) {
            if (novoId.contains(complement.getId())) {
                comp = complement;
            }
        }

        Filme filme = new Filme();
        filme.setId(temp.getId());
        filme.setNome(temp.getTitle());
        filme.setAno(temp.getYear());
        filme.setRanking(comp.getRank());

        List<Ator> tempAtor = new ArrayList<Ator>();
        for (int i = 0; i < temp.getPrincipals().size(); i++) {
            Ator ator = new Ator();
            ator.setId_filme(id);
            ator.setNome(temp.getPrincipals().get(i).getName());
            ator.setId(temp.getPrincipals().get(i).getId());
            ActorResponse actor = requests.getActor(novoId(temp.getPrincipals().get(i).getId(), 6, 15));
            ator.setDataNascimento(actor.getBirthDate());
            ator.setSexo(actor.getGender());
            ator.setBiografia(actor.getBio().get(0).getText());
            tempAtor.add(ator);
        }

        filme.setElencoPrincipal(tempAtor);
        filmeDAO.insert(filme);
    }

    public void cadastrarAtor(String nome) throws InsertException, SelectException {
        List<ComplementResponse> complements = requests.getComplement(nome);
        ComplementResponse temp = new ComplementResponse();
        temp = complements.get(0);
        String id = temp.getId();

        ActorResponse actor = requests.getActor(id);
        Ator ator = new Ator();
        ator.setNome(actor.getName());
        ator.setId(id);
        ator.setDataNascimento(actor.getBirthDate());
        ator.setSexo(actor.getGender());
        ator.setBiografia(actor.getBio().get(0).getText());

        // listaAtor.add(ator);
        atorDAO.insert(ator);
    }

    public String mostrarFilmes() throws SelectException {
        String retorno = "";
        for (int i = 0; i < filmeDAO.selectAll().size(); i++) {
            retorno += filmeDAO.selectAll().get(i).toString();
        }

        return retorno;
    }

    public String mostrarAtores() throws SelectException {
        String retorno = "";
        for (int i = 0; i < atorDAO.selectAll().size(); i++) {
            retorno += atorDAO.selectAll().get(i).toString();
        }

        return retorno;
    }

    public String mostrarFilme(String id) throws SelectException {
        if (!filmeDAO.select(id).getId().isEmpty()) {
            return filmeDAO.select(id).toString();
        }

        return "";
    }

    public String mostrarAtor(String id) throws SelectException {
        String retorno = "Nenhum ator encontrado";
        for (int i = 0; i < atorDAO.selectAll().size(); i++) {
            if (id.contains(atorDAO.selectAll().get(i).getId())) {
                retorno = atorDAO.selectAll().get(i).toString();
            }
        }

        return retorno;
    }

    public String novoId(String id, int n, int m) {
        String novoId = "";
        for (int j = 0; j < id.length(); j++) {
            if (j >= n && j < m) {
                novoId += id.charAt(j);
            }
        }

        return novoId;
    }
}