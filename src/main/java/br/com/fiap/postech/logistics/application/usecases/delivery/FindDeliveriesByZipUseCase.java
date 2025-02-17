package br.com.fiap.postech.logistics.application.usecases.delivery;

import br.com.fiap.postech.logistics.domain.model.Delivery;

import java.util.List;

public interface FindDeliveriesByZipUseCase {
    List<Delivery> execute(String zip);
}
