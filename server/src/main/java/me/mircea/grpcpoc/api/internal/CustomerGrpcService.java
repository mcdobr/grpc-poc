package me.mircea.grpcpoc.api.internal;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import me.mircea.grpcpoc.common.CustomerReply;
import me.mircea.grpcpoc.common.CustomerServiceGrpc;
import me.mircea.grpcpoc.common.IDRequest;
import me.mircea.grpcpoc.service.CustomerService;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@RequiredArgsConstructor
@GrpcService
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase {
    private final CustomerService customerService;

    public void findCustomer(final IDRequest idRequest, final StreamObserver<CustomerReply> responseObserver) {
        var customer = customerService.findById(UUID.fromString(idRequest.getId()));


        final var reply = CustomerReply.newBuilder()
                .setId(idRequest.getId())
                .setName(customer.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
