package com.example.ordini.requests;

import com.example.ordini.prodotto.ProdottoPurchaseRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarrelloRequest {

    private Long idUtente;
    private Double totalAmount;
    private List<ProdottoPurchaseRequest> prodottoPurchaseRequests;

}
