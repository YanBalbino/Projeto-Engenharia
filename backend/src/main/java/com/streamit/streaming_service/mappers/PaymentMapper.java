package com.streamit.streaming_service.mappers;

import com.streamit.streaming_service.dtos.payment.ReturnPaymentDTO;
import com.streamit.streaming_service.model.PaymentModel;

public class PaymentMapper {

    public static ReturnPaymentDTO toDto(PaymentModel model) {
        if (model == null) {
            return null;
        }

        ReturnPaymentDTO dto = new ReturnPaymentDTO();
        dto.setId(model.getId());
        dto.setMetodoPagamento(model.getMetodoPagamento());
        dto.setValor(model.getValor());
        dto.setDataPagamento(model.getDataPagamento());
        return dto;
    }

    public static PaymentModel toModel(ReturnPaymentDTO dto) {
        if (dto == null) {
            return null;
        }

        PaymentModel model = new PaymentModel();
        model.setId(dto.getId());
        model.setMetodoPagamento(dto.getMetodoPagamento());
        model.setValor(dto.getValor());
        model.setDataPagamento(dto.getDataPagamento());
        return model;
    }
}
