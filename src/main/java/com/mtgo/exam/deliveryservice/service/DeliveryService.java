package com.mtgo.exam.deliveryservice.service;

import com.mtgo.exam.deliveryservice.dto.DeliveryDto;
import com.mtgo.exam.deliveryservice.enums.DeliveryStatus;
import com.mtgo.exam.deliveryservice.model.Delivery;
import com.mtgo.exam.deliveryservice.repository.IDeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryService implements IDeliveryService{

    private final IDeliveryRepository deliveryRepository;
    @Override
    public DeliveryDto updateDeliveryStatus(int deliveryId, DeliveryStatus deliveryStatus) {
        return null;
    }

    @Override
    public DeliveryDto createDelivery(DeliveryDto deliveryDto) {

        return null;
    }
    @Override
    public List<DeliveryDto> getDeliveriesByStatus(DeliveryStatus deliveryStatus) {
        List<Delivery> claimableDeliveries = deliveryRepository.findByDeliveryStatus(deliveryStatus);
        // TODO: Map to DTOs
        return null;
    }
}
