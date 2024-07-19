package com.example.ordini.services;

import com.example.ordini.converters.CarrelloConverter;
import com.example.ordini.entities.Carrello;
import com.example.ordini.entities.Ordine;
import com.example.ordini.exceptions.BusinessException;
import com.example.ordini.exceptions.EntityNotFoundException;
import com.example.ordini.kafka.OrderConfirmation;
import com.example.ordini.kafka.OrderProducer;
import com.example.ordini.prodotto.ProdottoClient;
import com.example.ordini.prodotto.ProdottoPurchaseRequest;
import com.example.ordini.repositories.CarrelloRepository;
import com.example.ordini.requests.CarrelloRequest;
import com.example.ordini.responses.CarrelloResponse;
import com.example.ordini.utente.UtenteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrelloService {

    @Autowired
    private CarrelloRepository carrelloRepository;
    @Autowired
    private CarrelloConverter carrelloConverter;
    @Autowired
    private UtenteClient utenteClient;
    @Autowired
    private ProdottoClient prodottoClient;
    @Autowired
    private OrdineService ordineService;
    @Autowired
    private OrderProducer orderProducer;

    public Carrello getCarrelloById(Long id) {
        return carrelloRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Carrello"));
    }

    public CarrelloResponse getCarrelloResponseById(Long id) {
        Carrello carrello = carrelloRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Carrello"));
        return carrelloConverter.mapToCarrelloResponse(carrello);
    }

    public CarrelloResponse createCarrello(CarrelloRequest carrelloRequest) {
        var utente = utenteClient.findUtenteById(carrelloRequest.getIdUtente());
        if (utente.isEmpty()) {
            throw new BusinessException("L'utente con id: "+carrelloRequest.getIdUtente()+" non esiste! Impossibile effettuare l'ordine.");
        }
        // il totale lo dà il front-end
        prodottoClient.purchaseProdotti(carrelloRequest.getProdottoPurchaseRequests());


        Carrello carrello = carrelloRepository.saveAndFlush(carrelloConverter.mapToCarrello(carrelloRequest));

        List<Ordine> ordineList = new ArrayList<>();
        for (ProdottoPurchaseRequest pRequest : carrelloRequest.getProdottoPurchaseRequests()) {
            Ordine ordine = ordineService.saveOrdine(Ordine.builder()
                            .carrello(carrello)
                            .idProdotto(pRequest.getIdProdotto())
                            .quantita(pRequest.getQuantita())
                    .build());
            ordineList.add(ordine);
        }
        carrello.setOrdineList(ordineList);
        carrelloRepository.saveAndFlush(carrello);
        //TODO processare il pagamento

        orderProducer.sendOrderConfirmation(
                OrderConfirmation.builder()
                        .idOrdine(carrello.getId())
                        .idUtente(carrello.getIdUtente())
                        .totalAmount(carrello.getTotalAmount())
                        .prodotti(carrelloRequest.getProdottoPurchaseRequests())
                        .build());

        return carrelloConverter.mapToCarrelloResponse(carrello);
    }
}
