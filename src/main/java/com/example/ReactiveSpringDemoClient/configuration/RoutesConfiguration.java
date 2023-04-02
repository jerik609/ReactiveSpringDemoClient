package com.example.ReactiveSpringDemoClient.configuration;

import com.example.ReactiveSpringDemoClient.model.Product;
import com.example.ReactiveSpringDemoClient.proxy.ProductProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@RequiredArgsConstructor
public class RoutesConfiguration {

    private final ProductProxy productProxy;

    @Bean
    public RouterFunction<ServerResponse> productRoutes() {
        return route()
                .GET("/proxiedProducts", request ->
                                ok()
                                .contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(productProxy.getAll(), Product.class)
                        )
                .build();
    }

}
