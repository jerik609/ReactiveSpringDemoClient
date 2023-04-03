package com.example.ReactiveSpringDemoClient.proxy;

import com.example.ReactiveSpringDemoClient.exceptions.NullNameProductException;
import com.example.ReactiveSpringDemoClient.exceptions.ProductRetrieveException;
import com.example.ReactiveSpringDemoClient.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ProductProxy {

    // this will call the endpoint of another application and expose it
    // thus it is a proxy

    // it should consume reactively

    // we will do this via WebClient

    private final WebClient webClient;

    public Flux<Product> getAll() {

        final var defaultProduct = Product.builder().id(0).name("default").build();

        return webClient.get().uri("/product2")
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(Product.class))
                .doOnNext(p -> {
                    if (p.getName() == null) throw new NullNameProductException();
                })
                // error handling

                // NOTE: don't put error handling logic directly here, but put it in different class
                //  and call it here

                // first let's define how to recover on error
                //  we need to provide a flux as an alternative - we could also try to access a different service
                //  instead of providing a static content
                //.onErrorResume(exception -> Flux.just(defaultProduct))
                // but it's better to use this overload, since it allows us to specify the behavior,
                //  because we typically need specific handling
                //.onErrorResume(WebClientRequestException.class, exception -> Flux.just(defaultProduct))
                // and this is even more flexible
                .onErrorResume(e -> e instanceof WebClientRequestException, e -> Flux.just(defaultProduct))
                // also possible, but we have no access to the exception,
                //  which is not ideal, since we can't even log the exception
                //  as for onErrorResume, there are 3 overloads
                .onErrorReturn(e -> e instanceof WebClientRequestException, defaultProduct)
                // here we can map exceptions to different exception - and handle them e.g. by an exceptionhandler
                //  where this exceptionhandler is the standard rest controller advice-backed exception handler
                // useful when we want to treat the technical exceptions which happened here somewhere else (by an existing error handler?)
                .onErrorMap(e -> e instanceof WebClientRequestException, e -> new ProductRetrieveException())

                // e.g. we have an exception and an object on which we failed = and abnormal event, this method
                //  allows us to just skip the event, just drop it (and log it or something)
                .onErrorContinue(e -> e instanceof NullNameProductException, (x, o) -> {
                    System.out.println("Received a null name product: " + ((Product)o).getId());
                } )
                ;
    }

}
