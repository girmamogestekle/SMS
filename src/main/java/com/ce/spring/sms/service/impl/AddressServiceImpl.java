package com.ce.spring.sms.service.impl;

import com.ce.spring.sms.domain.entity.AddressEntity;
import com.ce.spring.sms.domain.request.AddressRequestModel;
import com.ce.spring.sms.repository.AddressRepository;
import com.ce.spring.sms.service.AddressService;
import com.ce.spring.sms.utility.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "addressService")
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private static final Logger LOGGER = LogManager.getLogger(AddressServiceImpl.class);
    private final AddressRepository addressRepository;

    @Override
    public AddressEntity addAddress(AddressRequestModel addressRequestModel) {
        LOGGER.info("AddressService: addAddress request payload {} ", Mapper.mapToJsonString(addressRequestModel));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        AddressEntity addressEntity = addressRepository.save(modelMapper.map(addressRequestModel, AddressEntity.class));
        LOGGER.info("AddressService: addAddress response {} ", Mapper.mapToJsonString(addressEntity));

        return addressEntity;
    }

    @Override
    public AddressEntity updateAddress(AddressEntity addressEntity) {
        LOGGER.info("AddressService: updateAddress request payload {} ", Mapper.mapToJsonString(addressEntity));
        AddressEntity updateAddressEntity = addressRepository.save(addressEntity);
        LOGGER.info("AddressService: updateAddress response {} ", Mapper.mapToJsonString(updateAddressEntity));
        return updateAddressEntity;
    }

    @Override
    public void deleteAddress(Long addressId) {
        LOGGER.info("AddressService: deleteAddress delete address by addressId {} ", addressId);
        addressRepository.deleteById(addressId);
        LOGGER.info("AddressService: deleteAddress deleted");
    }

    @Override
    public Optional<AddressEntity> getAddress(Long addressId) {
        LOGGER.info("AddressService: getAddress fetch address by addressId {} ", addressId);
        Optional<AddressEntity> addressEntity = addressRepository.findById(addressId);
        LOGGER.info("AddressService: getAddress response {} ", Mapper.mapToJsonString(addressEntity));
        return addressEntity;
    }

    @Override
    public List<AddressEntity> getAddresses() {
        LOGGER.info("AddressService: getAddresses fetch all addresses");
        List<AddressEntity> addressList = addressRepository.findAll();
        LOGGER.info("AddressService: getAddresses response {} ", Mapper.mapToJsonString(addressList));
        return addressList;
    }
}
