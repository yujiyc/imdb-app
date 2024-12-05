package trabalho.imdb.api.dados;

import java.util.List;

public class TitleBaseResponse {

    private List<TitleResponse> results;

    public TitleBaseResponse() {
    }

    public List<TitleResponse> getResults() {
        return this.results;
    }

    public void setResults(List<TitleResponse> results) {
        this.results = results;
    }
}