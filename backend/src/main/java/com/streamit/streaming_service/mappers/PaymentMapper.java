package com.streamit.streaming_service.mappers;

import com.streamit.streaming_service.dtos.CreatePaymentDTO;
import com.streamit.streaming_service.model.PaymentModel;
import com.streamit.streaming_service.model.UserModel;

public class PaymentMapper {

    public static PaymentModel toModel(CreatePaymentDTO dto, UserModel user) {
        if (dto == null) {
            throw new IllegalArgumentException("CreatePaymentDTO não pode ser nulo.");
        }
        
        PaymentModel payment = new PaymentModel();
        payment.setUser(user);
        payment.setMetodoPagamento(dto.getMetodoPagamento());
        payment.setDataPagamento(dto.getDataPagamento());
        payment.setValor(dto.getValor());
        
        return payment;
    }

    public static CreatePaymentDTO toDTO(PaymentModel payment) {
        if (payment == null) {
            throw new IllegalArgumentException("PaymentModel não pode ser nulo.");
        }
        
        CreatePaymentDTO dto = new CreatePaymentDTO();
        dto.setUserId(payment.getUser().getId());
        dto.setMetodoPagamento(payment.getMetodoPagamento());
        dto.setDataPagamento(payment.getDataPagamento());
        dto.setValor(payment.getValor());
        
        return dto;
    }
}
