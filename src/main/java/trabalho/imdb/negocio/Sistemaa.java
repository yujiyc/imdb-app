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

public class Sistemaa {
    private Requests requests = new Requests();
    // private List<Filme> listaDeFilmes = new ArrayList<Filme>();
    // private List<Ator> listaAtor = new ArrayList<Ator>();
    private FilmeDAO filmeDAO;
    private AtorDAO atorDAO;

    public static Sistemaa instance = null;

    public static Sistemaa getInstance() {
        if (instance == null) {
            try {
                instance = new Sistemaa("0916");
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

    public Sistemaa(String senha) throws ClassNotFoundException, SQLException, SelectException {
        Conexao.setSenha(senha);
        filmeDAO = FilmeDAO.getInstance();
        atorDAO = AtorDAO.getInstance();
    }

    public List<Filme> listarFilmes(String nome) {
        List<Filme> listaRetorno = new ArrayList<Filme>();

        List<TitleResponse> titles = requests.getTitles(nome);

        for (TitleResponse title : titles) {
            if (title.getTitleType() != null && "movie".contains(title.getTitleType())) {
                Filme filme = new Filme();
                filme.setId(title.getId());
                filme.setNome(title.getTitle());
                listaRetorno.add(filme);
            }
        }
        return listaRetorno;
    }

    // public List<String> listarFilmes(String nome) {
    // List<String> listaRetorno = new ArrayList<String>();
    // String retorno = "";

    // List<TitleResponse> titles = requests.getTitles(nome);

    // for (TitleResponse title : titles) {
    // if (title.getTitleType() != null && "movie".contains(title.getTitleType())) {
    // // List<Filme>
    // // Filme filme = new Filme();
    // // filme.setId(title.getId());
    // // filme.setNome(title.getTitle());
    // retorno = "ID: " + title.getId() + " Titulo: " + title.getTitle() + "\n";
    // listaRetorno.add(retorno);
    // }
    // }
    // return listaRetorno;
    // }

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

    public List<Filme> mostrarFilmes() throws SelectException {
        List<Filme> filmes = new ArrayList<Filme>();
        for (int i = 0; i < filmeDAO.selectAll().size(); i++) {
            Filme fil = new Filme();
            fil.setId(filmeDAO.selectAll().get(i).getId());
            fil.setNome(filmeDAO.selectAll().get(i).getNome());
            fil.setRanking(filmeDAO.selectAll().get(i).getRanking());
            fil.setAno(filmeDAO.selectAll().get(i).getAno());
            fil.setElencoPrincipal(filmeDAO.selectAll().get(i).getElencoPrincipal());
            filmes.add(fil);
        }

        return filmes;
    }

    public List<Ator> mostrarAtores() throws SelectException {
        List<Ator> atores = new ArrayList<Ator>();
        for (int i = 0; i < atorDAO.selectAll().size(); i++) {
            Ator at = new Ator();
            at.setId(atorDAO.selectAll().get(i).getId());
            at.setNome(atorDAO.selectAll().get(i).getNome());
            at.setDataNascimento(atorDAO.selectAll().get(i).getDataNascimento());
            at.setSexo(atorDAO.selectAll().get(i).getSexo());
            at.setBiografia(atorDAO.selectAll().get(i).getBiografia());
            atores.add(at);
        }

        return atores;
    }

    public Filme mostrarFilme(String id) throws SelectException {
        if (!filmeDAO.select(id).getId().isEmpty()) {
            Filme fil = new Filme();
            fil.setId(filmeDAO.select(id).getId());
            fil.setNome(filmeDAO.select(id).getNome());
            fil.setRanking(filmeDAO.select(id).getRanking());
            fil.setAno(filmeDAO.select(id).getAno());
            fil.setElencoPrincipal(filmeDAO.select(id).getElencoPrincipal());
            return fil;
        }

        return null;
    }

    public Ator mostrarAtor(String id) throws SelectException {
        for (int i = 0; i < atorDAO.selectAll().size(); i++) {
            if (id.contains(atorDAO.selectAll().get(i).getId())) {
                Ator ato = new Ator();
                ato.setId(atorDAO.selectAll().get(i).getId());
                ato.setNome(atorDAO.selectAll().get(i).getNome());
                ato.setDataNascimento(atorDAO.selectAll().get(i).getDataNascimento());
                ato.setSexo(atorDAO.selectAll().get(i).getSexo());
                ato.setBiografia(atorDAO.selectAll().get(i).getBiografia());
                return ato;
            }
        }
        return null;
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