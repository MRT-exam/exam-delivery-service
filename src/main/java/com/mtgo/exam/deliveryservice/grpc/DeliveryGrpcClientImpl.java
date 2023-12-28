package com.mtgo.exam.deliveryservice.grpc;

import com.google.protobuf.Descriptors.*;
import com.mtgo.exam.grpcinterface.OrderRequest;
import com.mtgo.exam.grpcinterface.OrderServiceGrpc.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;

public class DeliveryGrpcClientImpl {

    /*
    @GrpcClient("order-service")
    private OrderServiceBlockingStub orderServiceBlockingStub;

    public Map<FieldDescriptor, Object> getOrderById(int orderId) {
        return orderServiceBlockingStub.getOrder(
                        OrderRequest.newBuilder()
                                .setOrderId(orderId)
                                .build())
                .getAllFields();
    }
     */
}

