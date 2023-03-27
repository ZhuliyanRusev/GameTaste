package com.example.gametaste.service;

import com.example.gametaste.model.entity.Merchandise;
import com.example.gametaste.model.service.MerchandiseServiceModel;
import com.example.gametaste.model.view.MerchandiseViewModel;

import java.util.List;

public interface MerchandiseService {
    MerchandiseServiceModel createMerchandise(MerchandiseServiceModel merchandiseServiceModel);

    List<MerchandiseViewModel> findAllOrderByReleaseDateThenByPrice();

    void deleteMerchandiseById(Long id);

    Merchandise findMerchandiseById(Long id);
}
