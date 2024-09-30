package com.streamit.streaming_service.dtos.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateBankSlipDTO extends CreatePaymentDTO{
    private String payerName;
    private String payerCpf;
}
