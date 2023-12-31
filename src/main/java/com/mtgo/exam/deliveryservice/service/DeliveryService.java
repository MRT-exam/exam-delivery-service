package com.mtgo.exam.deliveryservice.service;

import com.mtgo.exam.deliveryservice.dto.*;
import com.mtgo.exam.deliveryservice.enums.DeliveryStatus;
import com.mtgo.exam.deliveryservice.graphql.GraphqlRequestBody;
import com.mtgo.exam.deliveryservice.model.CustomerInfo;
import com.mtgo.exam.deliveryservice.model.Delivery;
import com.mtgo.exam.deliveryservice.model.DeliveryItem;
import com.mtgo.exam.deliveryservice.repository.IDeliveryRepository;
import com.mtgo.exam.deliveryservice.utils.GraphqlSchemaReaderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryService implements IDeliveryService{

    private final IDeliveryRepository deliveryRepository;

    private final WebClient.Builder webClientBuilder;
    @Override
    public DeliveryDto updateDeliveryStatus(int deliveryId, DeliveryStatus deliveryStatus) {
        return null;
    }

    @Override
    public Delivery claimOrderForDelivery(int orderId) throws IOException {
        // Call GraphQL getOrderById
        GraphqlRequestBody requestBody = new GraphqlRequestBody();
        final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName("getOrderById");

        requestBody.setQuery(query);
        requestBody.setId(orderId);
        OrderDto orderDto = webClientBuilder.build().post()
                .uri("http://order-service/graphql-order-service")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(OrderDto.class)
                .block();
        log.info(orderDto.getComment());
        log.info(orderDto.getStatus());
        if (!orderDto.getStatus().equals("ACCEPTED")) {
            System.out.println("Order with the id has not been accepted");
        }

        List<DeliveryItem> deliveryItems = orderDto.getOrderLineDtoList().stream()
                .map(this::mapToDeliveryItem)
                .toList();

        CustomerInfo customerInfo = this.mapToCustomerInfo(orderDto.getCustomerInfoDto());

        Delivery delivery = Delivery.builder()
                .id(orderDto.getId())
                .restaurantId(orderDto.getRestaurantId())
                .status(DeliveryStatus.ACTIVE)
                .deliveryDateTime(orderDto.getOrderDateTime())
                .deliveryCompletedDateTime(null)
                .deliveryItems(deliveryItems)
                .comment(orderDto.getComment())
                .customerInfo(customerInfo)
                .build();



        deliveryRepository.save(delivery);

        return delivery;
    }

    @Override
    public List<DeliveryDto> getDeliveriesByStatus(DeliveryStatus deliveryStatus) {
        List<Delivery> deliveries = deliveryRepository.findByStatus(deliveryStatus);
        // TODO: Map to DTOs
        return null;
    }

    private DeliveryItem mapToDeliveryItem(OrderLineDto orderLineDto) {
        DeliveryItem deliveryItem = new DeliveryItem();
        deliveryItem.setItemId(orderLineDto.getItemId());
        deliveryItem.setItemName(orderLineDto.getItemName());
        deliveryItem.setQuantity(orderLineDto.getQuantity());
        return deliveryItem;
    }

    private CustomerInfo mapToCustomerInfo(CustomerInfoDto customerInfoDto) {
        CustomerInfo customerInfo = new CustomerInfo(
                customerInfoDto.getUserId(),
                customerInfoDto.getFirstName(),
                customerInfoDto.getLastName(),
                customerInfoDto.getPhone(),
                customerInfoDto.getAddress()
        );
        return customerInfo;
    }
}
