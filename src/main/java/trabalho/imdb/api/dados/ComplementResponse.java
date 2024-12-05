package trabalho.imdb.api.dados;

public class ComplementResponse {
    private String id;
    private String name;
    private String rank;
    private String s;
    private String l;

    public String getL() {
        return this.l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getS() {
        return this.s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}
