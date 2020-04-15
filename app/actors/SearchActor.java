package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.Materializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.SearchAPIResponse;
import models.SearchDto;

import java.util.concurrent.CompletionStage;

public class SearchActor extends AbstractActor {
    private ActorRef guardian;

    public SearchActor(ActorRef guardian) {
        this.guardian = guardian;
    }

    public static Props init(ActorRef actorRef) {
        return Props.create(SearchActor.class, () -> new SearchActor(actorRef));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(JsonNode.class, this::processMessage)
                .build();
    }

    private void processMessage(JsonNode jsonNode) {
        ObjectMapper mapper1 = new ObjectMapper();
        SearchDto searchModel = mapper1.convertValue(jsonNode, SearchDto.class);
        CompletionStage<HttpResponse> responseFuture = this.callRestApi(searchModel.getSearchParam(),searchModel.getSearchType());
        responseFuture.thenCompose(this::consumeHttpResponse)
                .thenAccept(SearchResult -> {
                    System.out.println("DATA : " + SearchResult);
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode json = mapper.convertValue(SearchResult, JsonNode.class);
                    this.guardian.tell(json, getSelf());
                });
    }

    private CompletionStage<SearchAPIResponse> consumeHttpResponse(HttpResponse httpResponse) {
        Materializer materializer = Materializer.matFromSystem(getContext().getSystem());
        return Jackson.unmarshaller(SearchAPIResponse.class)
                .unmarshal(httpResponse.entity(), materializer)
                .thenApply(messageModel -> {
                    this.discardEntity(httpResponse, materializer);
                    return messageModel;
                });
    }

    private void discardEntity(HttpResponse httpResponse, Materializer materializer) {
        httpResponse.discardEntityBytes(materializer)
                .completionStage()
                .whenComplete((done, ex) -> System.out.println("Discarded"));
    }

    private CompletionStage<HttpResponse> callRestApi(String searchType, String searchParam) {
        return Http.get(getContext().getSystem()).singleRequest(HttpRequest.create("http://localhost:9000/api/search?searchType=" + searchType + "&movieOrMulti=" + searchParam));
    }
}
