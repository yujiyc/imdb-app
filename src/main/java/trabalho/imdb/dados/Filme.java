package trabalho.imdb.dados;

import java.util.ArrayList;
import java.util.List;

public class Filme {
    private String id;
    private String nome;
    private String ranking;
    private String ano;
    private List<Ator> elencoPrincipal = new ArrayList<Ator>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public List<Ator> getElencoPrincipal() {
        return elencoPrincipal;
    }

    public void setElencoPrincipal(List<Ator> elencoPrincipal) {
        this.elencoPrincipal = elencoPrincipal;
    }

    private String mostrarElenco() {
        String retorno = "";
        for (int i = 0; i < elencoPrincipal.size(); i++) {
            retorno += elencoPrincipal.get(i).toString();
        }
        return retorno;
    }

    public String toString() {
        return "Nome: " + nome + "\nID: " + id + "\nRanking: " + ranking + "\nAno: " + ano + "\nElenco Principal\n"
                + mostrarElenco();
    }
}