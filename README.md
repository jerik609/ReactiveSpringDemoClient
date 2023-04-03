counterpart (client) to the ReactiveSpringDemo server application

Arch:

Routes -> ProductHandler -> ProductService -> ProductProxy

- routes - defines the endpoint - does not build the respose
- productHandler - handles the request (builds response), but does not provide data
- productService - provides the data (may transform data according to our business logic), but does not perform the actual data read from "external storage"
- productProxy - gets the data from remote location