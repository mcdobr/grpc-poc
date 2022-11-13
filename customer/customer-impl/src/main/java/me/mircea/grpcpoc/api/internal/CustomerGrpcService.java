package me.mircea.grpcpoc.api.internal;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import me.mircea.grpcpoc.customer.BatchDeleteResponse;
import me.mircea.grpcpoc.customer.CustomerContactEdit;
import me.mircea.grpcpoc.customer.CustomerRegistration;
import me.mircea.grpcpoc.customer.CustomerReply;
import me.mircea.grpcpoc.customer.CustomerServiceGrpc;
import me.mircea.grpcpoc.customer.IDRequest;
import me.mircea.grpcpoc.service.CustomerService;
import me.mircea.grpcpoc.service.dto.CustomerDto;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@RequiredArgsConstructor
@GrpcService
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase {
    private final CustomerService customerService;

    @Override
    public void findCustomer(final IDRequest idRequest, final StreamObserver<CustomerReply> responseObserver) {
        var customer = customerService.findById(UUID.fromString(idRequest.getId()));
        final var reply = createReply(customer);
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void findCustomers(Empty request, final StreamObserver<CustomerReply> responseObserver) {
        customerService.findAll()
                .stream()
                .map(this::createReply)
                .forEach(responseObserver::onNext);

        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<IDRequest> findCustomersByIDs(StreamObserver<CustomerReply> responseObserver) {
        return new StreamObserver<IDRequest>() {
            @Override
            public void onNext(IDRequest idRequest) {
                var customer = customerService.findById(UUID.fromString(idRequest.getId()));
                final var reply = createReply(customer);
                responseObserver.onNext(reply);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void createCustomer(CustomerRegistration registrationRequest, StreamObserver<CustomerReply> responseObserver) {
        CustomerDto newCustomer = customerService.create(registrationRequest);
        responseObserver.onNext(createReply(newCustomer));
        responseObserver.onCompleted();
    }


    @Override
    public void editCustomer(CustomerContactEdit request, StreamObserver<CustomerReply> responseObserver) {
        CustomerDto editedCustomer = customerService.edit(request);
        responseObserver.onNext(createReply(editedCustomer));
        responseObserver.onCompleted();
    }

    @Override
    public void deleteCustomer(IDRequest request, StreamObserver<Empty> responseObserver) {
        customerService.deleteByID(request);
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<IDRequest> deleteCustomers(StreamObserver<BatchDeleteResponse> responseObserver) {
        return new StreamObserver<IDRequest>() {
            int deleted = 0;

            @Override
            public void onNext(IDRequest idRequest) {
                customerService.deleteByID(idRequest);
                ++deleted;
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(
                        BatchDeleteResponse.newBuilder()
                                .setTotal(deleted)
                                .build()
                );
                responseObserver.onCompleted();
            }
        };
    }

    private CustomerReply createReply(CustomerDto customer) {
        return CustomerReply.newBuilder()
                .setId(customer.getId().toString())
                .setName(customer.getName())
                .build();
    }
}
