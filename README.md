# gRPC proof of concept

This application is meant as a proof of concept for gRPC communication in a distributed application.


This is a contrived example of how one might build an ecommerce website.

Services involved:

```
OrderService -> CustomerService
            \-> InventoryService
             \-> Delivery Service
```

where you would have some internal communication between these services.

## Tools
- [Postman](https://www.postman.com/)

You need to create an gRPC API by importing the proto file in `customer-api` module.