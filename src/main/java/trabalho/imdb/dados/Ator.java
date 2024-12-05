package trabalho.imdb.dados;

public class Ator {
    private String id;
    private String nome;
    private String dataNascimento;
    private String sexo;
    private String biografia;
    private String id_filme;

    public Ator() {
    }

    public Ator(String id, String nome, String dataNascimento, String sexo, String biografia) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.biografia = biografia;
    }

    public String getId_filme() {
        return this.id_filme;
    }

    public void setId_filme(String id_filme) {
        this.id_filme = id_filme;
    }

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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getBiografia() {
        return biografia.substring(0, 100) + "...";
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String toString() {
        return "\nID: " + id + "\nNome: " + nome + "\nData de Nascimento: " + dataNascimento + "\nSexo: " + sexo
                + "\nBiografia: " + getBiografia();
    }
}