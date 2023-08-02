package com.ce.spring.sms.controller;

import com.ce.spring.sms.domain.entity.SectionEntity;
import com.ce.spring.sms.domain.request.SectionRequestModel;
import com.ce.spring.sms.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "sectionController")
@RequestMapping("/api/v1/section")
@RequiredArgsConstructor
public class SectionController {

    private static final Logger LOGGER = LogManager.getLogger(SectionController.class);
    private final SectionService sectionService;

    @PostMapping(value="/add")
    public SectionEntity addSection(@RequestBody SectionRequestModel sectionRequestModel){
        LOGGER.info("Section request data received at controller layer for post mapping: {}", sectionRequestModel);
        return sectionService.addSection(sectionRequestModel);
    }

    @PutMapping(value="/update")
    public SectionEntity updateSection(@RequestBody SectionEntity sectionEntity){
        LOGGER.info("Section request data received at controller layer for put mapping: {}", sectionEntity);
        return sectionService.updateSection(sectionEntity);
    }

    @DeleteMapping(value="/delete/{sectionId}")
    public void deleteSection(@PathVariable Long sectionId){
        LOGGER.info("Section request data received at controller layer for delete mapping: {}", sectionId);
        sectionService.deleteSection(sectionId);
    }

    @GetMapping(value="/get/{sectionId}")
    public SectionEntity getSection(@PathVariable Long sectionId){
        LOGGER.info("Section request data received at controller layer for get mapping: {}", sectionId);
        return sectionService.getSection(sectionId).orElse(null);
    }

    @GetMapping(value={"/","/gets"})
    public List<SectionEntity> getSections(){
        LOGGER.info("Request sections at controller layer for get mapping");
        return sectionService.getSections();
    }
}
