package com.ce.spring.sms.service.impl;

import com.amazonaws.services.connect.model.UserNotFoundException;
import com.ce.spring.sms.domain.entity.ParentEntity;
import com.ce.spring.sms.domain.request.ParentRequestModel;
import com.ce.spring.sms.repository.ParentRepository;
import com.ce.spring.sms.service.ParentService;
import com.ce.spring.sms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "parentService")
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private static final Logger LOGGER = LogManager.getLogger(ParentServiceImpl.class);
    private final ParentRepository parentRepository;
    private final UserService userService;

    @Override
    public ParentEntity addParent(ParentRequestModel parentRequestModel) {

        if(!userService.userEnabled(parentRequestModel.getUsername())){
            throw new UserNotFoundException(parentRequestModel.getUsername() + " not found!");
        }

        LOGGER.info("Parent request data received at service layer for adding: {}", parentRequestModel);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ParentEntity parentEntity = parentRepository.save(modelMapper.map(parentRequestModel, ParentEntity.class));
        LOGGER.info("Save parent data after hit database: {}", parentEntity);

        return parentEntity;
    }

    @Override
    public ParentEntity updateParent(ParentEntity parentEntity) {

        if(!userService.userEnabled(parentEntity.getUsername())){
            throw new UserNotFoundException(parentEntity.getUsername() + " not found!");
        }

        LOGGER.info("Parent request data received at service layer for updating: {}", parentEntity);
        ParentEntity updateParentEntity = parentRepository.save(parentEntity);
        LOGGER.info("Update parent data after hit database: {}", updateParentEntity);
        return updateParentEntity;
    }

    @Override
    public void deleteParent(Long parentId) {
        LOGGER.info("Parent request data received at service layer for deleting: {}", parentId);
        parentRepository.deleteById(parentId);
        LOGGER.info("Delete parent data after hit database: {}", parentId);
    }

    @Override
    public Optional<ParentEntity> getParent(Long parentId) {
        LOGGER.info("Parent request data received at service layer for get parent: {}", parentId);
        Optional<ParentEntity> parentEntity =  parentRepository.findById(parentId);
        LOGGER.info("Get parent data after hit database: {}", parentEntity);
        return parentEntity;
    }

    @Override
    public List<ParentEntity> getParents() {
        LOGGER.info("Parent request data received at service layer for get parents");
        return parentRepository.findAll();
    }
}
