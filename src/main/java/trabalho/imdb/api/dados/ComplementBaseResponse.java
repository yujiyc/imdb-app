package trabalho.imdb.api.dados;

import java.util.List;

public class ComplementBaseResponse {

    private List<ComplementResponse> d;

    public ComplementBaseResponse() {
    }

    public List<ComplementResponse> geD() {
        return this.d;
    }

    public void setResults(List<ComplementResponse> d) {
        this.d = d;
    }
}