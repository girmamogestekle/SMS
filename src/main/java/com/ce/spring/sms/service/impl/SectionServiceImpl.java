package com.ce.spring.sms.service.impl;

import com.ce.spring.sms.domain.entity.SectionEntity;
import com.ce.spring.sms.domain.request.SectionRequestModel;
import com.ce.spring.sms.repository.SectionRepository;
import com.ce.spring.sms.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "sectionService")
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private static final Logger LOGGER = LogManager.getLogger(ParentServiceImpl.class);
    private final SectionRepository sectionRepository;

    @Override
    public SectionEntity addSection(SectionRequestModel sectionRequestModel) {
        LOGGER.info("Section request data received at service layer for adding: {}", sectionRequestModel);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SectionEntity sectionEntity = sectionRepository.save(modelMapper.map(sectionRequestModel, SectionEntity.class));
        LOGGER.info("Save section data after hit database: {}", sectionEntity);

        return sectionEntity;
    }

    @Override
    public SectionEntity updateSection(SectionEntity sectionEntity) {
        LOGGER.info("Section request data received at service layer for updating: {}", sectionEntity);
        SectionEntity updateSectionEntity = sectionRepository.save(sectionEntity);
        LOGGER.info("Update section data after hit database: {}", updateSectionEntity);
        return updateSectionEntity;
    }

    @Override
    public void deleteSection(Long sectionId) {
        LOGGER.info("Section request data received at service layer for deleting: {}", sectionId);
        sectionRepository.deleteById(sectionId);
        LOGGER.info("Delete section data after hit database: {}", sectionId);
    }

    @Override
    public Optional<SectionEntity> getSection(Long sectionId) {
        LOGGER.info("Section request data received at service layer for get section: {}", sectionId);
        Optional<SectionEntity> sectionEntity = sectionRepository.findById(sectionId);
        LOGGER.info("Get section data after hit database: {}", sectionEntity);
        return sectionEntity;
    }

    @Override
    public List<SectionEntity> getSections() {
        LOGGER.info("Section request data received at service layer for get sections");
        return sectionRepository.findAll();
    }
}
