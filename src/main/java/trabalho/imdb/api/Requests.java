package trabalho.imdb.api;

import java.util.List;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import trabalho.imdb.api.dados.ActorResponse;
// import trabalho.imdb.api.dados.ActorBaseResponse;
// import trabalho.imdb.api.dados.ActorResponse;
import trabalho.imdb.api.dados.ComplementBaseResponse;
import trabalho.imdb.api.dados.ComplementResponse;
import trabalho.imdb.api.dados.TitleBaseResponse;
import trabalho.imdb.api.dados.TitleResponse;

public class Requests {

    public Requests() {
    }

    public List<TitleResponse> getTitles(String searchTitle) {
        HttpResponse<TitleBaseResponse> response = Unirest
                .get("https://imdb8.p.rapidapi.com/title/find?q=" + searchTitle)
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "c654f5c5f1mshccd6115ddad9be9p16d011jsn3baed4b5c822")
                .asObject(TitleBaseResponse.class);

        return response.getBody().getResults();
    }

    public List<ComplementResponse> getComplement(String searchTitle) {
        HttpResponse<ComplementBaseResponse> response = Unirest
                .get("https://imdb8.p.rapidapi.com/auto-complete?q=" + searchTitle)
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "c654f5c5f1mshccd6115ddad9be9p16d011jsn3baed4b5c822")
                .asObject(ComplementBaseResponse.class);
        return response.getBody().geD();
    }

    public ActorResponse getActor(String id) {
        HttpResponse<ActorResponse> response = Unirest
                .get("https://imdb8.p.rapidapi.com/actors/get-bio?nconst=" + id)
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "c654f5c5f1mshccd6115ddad9be9p16d011jsn3baed4b5c822")
                .asObject(ActorResponse.class);
        return response.getBody();
    }

    // public List<TitleResponse> getTitles(String searchTitle) {
    //     HttpResponse<TitleBaseResponse> response = Unirest
    //             .get("https://imdb8.p.rapidapi.com/title/find?q=" + searchTitle)
    //             .header("x-rapidapi-key", "a6e1b0ae56msh6d66c2023e282ebp163574jsn4cb2521a2f65")
    //             .header("x-rapidapi-host", "imdb8.p.rapidapi.com").asObject(TitleBaseResponse.class);

    //     return response.getBody().getResults();
    // }

    // public List<ComplementResponse> getComplement(String searchTitle) {
    //     HttpResponse<ComplementBaseResponse> response = Unirest
    //             .get("https://imdb8.p.rapidapi.com/auto-complete?q=" + searchTitle)
    //             .header("x-rapidapi-key", "a6e1b0ae56msh6d66c2023e282ebp163574jsn4cb2521a2f65")
    //             .header("x-rapidapi-host", "imdb8.p.rapidapi.com").asObject(ComplementBaseResponse.class);
    //     return response.getBody().geD();
    // }

    // public ActorResponse getActor(String id) {
    //     HttpResponse<ActorResponse> response = Unirest.get("https://imdb8.p.rapidapi.com/actors/get-bio?nconst=" + id)
    //             .header("x-rapidapi-key", "a6e1b0ae56msh6d66c2023e282ebp163574jsn4cb2521a2f65")
    //             .header("x-rapidapi-host", "imdb8.p.rapidapi.com").asObject(ActorResponse.class);
    //     return response.getBody();
    // }
}
