package com.mtgo.exam.deliveryservice.grpc.client;

import com.google.protobuf.Descriptors.*;
import com.google.protobuf.InvalidProtocolBufferException;
import com.mtgo.exam.grpcinterface.OrderRequest;
import com.mtgo.exam.grpcinterface.OrderServiceGrpc;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class DeliveryGrpcClientImpl {

    @GrpcClient("order-service")
    private OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub;

    @GrpcClient("order-service")
    private OrderServiceGrpc.OrderServiceStub orderServiceStub;

    public Map<FieldDescriptor, Object> getOrderById(int orderId) {
        return orderServiceBlockingStub.getOrder(
                OrderRequest.newBuilder()
                        .setOrderId(orderId)
                        .build())
                .getAllFields();
    }

}
