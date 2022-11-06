package me.mircea.grpcpoc.api.internal;

import io.grpc.stub.StreamObserver;
import me.mircea.grpcpoc.common.CustomerReply;
import me.mircea.grpcpoc.common.CustomerServiceGrpc;
import me.mircea.grpcpoc.common.IDRequest;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase {
    public void findCustomer(final IDRequest id, final StreamObserver<CustomerReply> responseObserver) {
        final var reply = CustomerReply.newBuilder()
                .setId(id.getId())
                .setName("Mock #1")
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
