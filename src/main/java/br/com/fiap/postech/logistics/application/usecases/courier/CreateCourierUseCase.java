package br.com.fiap.postech.logistics.application.usecases.courier;

import br.com.fiap.postech.logistics.domain.model.Courier;

public interface CreateCourierUseCase {
    Courier execute(Courier courier);
}
