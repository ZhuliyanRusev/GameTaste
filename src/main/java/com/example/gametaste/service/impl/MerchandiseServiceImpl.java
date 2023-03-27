package com.example.gametaste.service.impl;

import com.example.gametaste.model.entity.Merchandise;
import com.example.gametaste.model.service.MerchandiseServiceModel;
import com.example.gametaste.model.view.MerchandiseViewModel;
import com.example.gametaste.repository.MerchandiseRepository;
import com.example.gametaste.service.MerchandiseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchandiseServiceImpl implements MerchandiseService {
    private final ModelMapper modelMapper;
    private final MerchandiseRepository merchandiseRepository;

    public MerchandiseServiceImpl(ModelMapper modelMapper, MerchandiseRepository merchandiseRepository) {
        this.modelMapper = modelMapper;
        this.merchandiseRepository = merchandiseRepository;
    }

    @Override
    public MerchandiseServiceModel createMerchandise(MerchandiseServiceModel merchandiseServiceModel) {
        Merchandise merchandise = modelMapper.map(merchandiseServiceModel,Merchandise.class);
        return modelMapper.map(merchandiseRepository.save(merchandise), MerchandiseServiceModel.class);
    }

    @Override
    public List<MerchandiseViewModel> findAllOrderByReleaseDateThenByPrice() {
        return merchandiseRepository
                .findAllByOrderByReleaseDateDescPriceDesc()
                .stream()
                .map(merchandise -> modelMapper.map(merchandise, MerchandiseViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMerchandiseById(Long id) {
        merchandiseRepository.deleteById(id);
    }

    @Override
    public Merchandise findMerchandiseById(Long id) {
        return merchandiseRepository.findById(id).orElse(null);
    }
}
