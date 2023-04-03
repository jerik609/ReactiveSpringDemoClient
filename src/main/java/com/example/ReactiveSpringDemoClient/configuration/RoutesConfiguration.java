package com.example.ReactiveSpringDemoClient.configuration;

import com.example.ReactiveSpringDemoClient.handlers.ProductHandler;
import com.example.ReactiveSpringDemoClient.model.Product;
import com.example.ReactiveSpringDemoClient.proxy.ProductProxy;
import com.example.ReactiveSpringDemoClient.services.ProductService;
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

    private final ProductHandler productHandler;

    @Bean
    public RouterFunction<ServerResponse> productRoutes() {
        return route()
                .GET("/proxiedProducts", productHandler::handle)
                .build();
    }

}
