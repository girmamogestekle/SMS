package com.ce.spring.sms.controller;

import com.ce.spring.sms.domain.entity.AddressEntity;
import com.ce.spring.sms.domain.request.AddressRequestModel;
import com.ce.spring.sms.service.AddressService;
import com.ce.spring.sms.utility.Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "addressController")
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private static final Logger LOGGER = LogManager.getLogger(AddressController.class);
    private final AddressService addressService;

    @PostMapping(value = "/add")
    public AddressEntity addAddress(@RequestBody AddressRequestModel addressRequestModel){
        LOGGER.info("AddressController: addAddress request payload {} ", Mapper.mapToJsonString(addressRequestModel));
        AddressEntity addressEntity = addressService.addAddress(addressRequestModel);
        LOGGER.info("AddressController: addAddress response {} ", Mapper.mapToJsonString(addressEntity));
        return addressEntity;
    }

    @PutMapping(value = "/update")
    public AddressEntity updateAddress(@RequestBody AddressEntity addressEntity){
        LOGGER.info("AddressController: updateAddress request payload {} ", Mapper.mapToJsonString(addressEntity));
        AddressEntity updateAddressEntity = addressService.updateAddress(addressEntity);
        LOGGER.info("AddressController: updateAddress response {} ", Mapper.mapToJsonString(updateAddressEntity));
        return updateAddressEntity;
    }

    @DeleteMapping(value = "/delete/{addressId}")
    public void deleteAddress(@PathVariable Long addressId){
        LOGGER.info("AddressController: deleteAddress delete address by addressId {} ", addressId);
        addressService.deleteAddress(addressId);
        LOGGER.info("AddressController: deleteAddress deleted");
    }

    @GetMapping(value = "/get/{addressId}")
    public AddressEntity getAddress(@PathVariable Long addressId){
        LOGGER.info("AddressController: getAddress fetch address by addressId {} ", addressId);
        AddressEntity addressEntity = addressService.getAddress(addressId).orElse(null);
        LOGGER.info("AddressController: getAddress response {} ", Mapper.mapToJsonString(addressEntity));
        return addressEntity;
    }

    @GetMapping(value = {"/","/gets"})
    public List<AddressEntity> getAddresses(){
        LOGGER.info("AddressController: getAddresses fetch all addresses ");
        List<AddressEntity> addressEntityList = addressService.getAddresses();
        LOGGER.info("AddressController: getAddresses response {} ", Mapper.mapToJsonString(addressEntityList));
        return addressEntityList;
    }
}
