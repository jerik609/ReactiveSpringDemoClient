package com.example.ReactiveSpringDemoClient.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    // mandatory, one must be an "id"
    //@Id
    private long id;

    private String name;

}
