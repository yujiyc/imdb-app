package trabalho.imdb.api.dados;

import java.util.List;

public class TitleResponse {

    private String id;
    private String title;
    private String year;
    private String titleType;
    private List<ActorResponse> principals;

    public TitleResponse() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitleType() {
        return this.titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public List<ActorResponse> getPrincipals() {
        return this.principals;
    }

    public void setPrincipals(List<ActorResponse> principals) {
        this.principals = principals;
    }

}
