package trabalho.imdb.api.dados;

import java.util.List;

public class ActorResponse {

    private String id;
    private String name;
    private String birthDate;
    private String gender;
    private List<MiniBio> miniBios;
    // private List<List<String>> bio = new ArrayList<List<String>>();
    // private String miniBios;

    public ActorResponse() {
    }

    public List<MiniBio> getBio() {
        return this.miniBios;
    }

    // public String getBio() {
    // String retorno = bio.get(0).getText();
    // return retorno;
    // }

    public void setBio(List<MiniBio> miniBios) {
        this.miniBios = miniBios;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
