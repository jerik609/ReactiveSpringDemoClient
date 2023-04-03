Counterpart - a reactive client to the ReactiveSpringDemo application

This application proxies the endpoint of the other app - it re-exposes it on a different endpoint/port.

Arch:

Routes -> ProductHandler -> ProductService -> ProductProxy

- routes - defines the endpoint - does not build the respose
- productHandler - handles the request (builds response), but does not provide data
- productService - provides the data (may transform data according to our business logic), but does not perform the actual data read from "external storage"
- productProxy - gets the data from remote location

Uses webclient to provide data reactively

The most valuable things in reactive are: 
- a better usage of resources (not that many idle threads with reactive architecture)
- another benefit is event driven communication, decoupling consumer from producer of data

