package com.example.ReactiveSpringDemoClient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    // mandatory, one must be an "id"
    //@Id
    private long id;

    private String name;

}
