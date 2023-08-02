package com.ce.spring.sms.controller;

import com.ce.spring.sms.domain.entity.ParentEntity;
import com.ce.spring.sms.domain.request.ParentRequestModel;
import com.ce.spring.sms.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "parentController")
@RequestMapping("/api/v1/parent")
@RequiredArgsConstructor
public class ParentController {

    private static final Logger LOGGER = LogManager.getLogger(ParentController.class);
    private final ParentService parentService;

    @PostMapping(value = "/add")
    public ParentEntity addParent(@RequestBody ParentRequestModel parentRequestModel){
        LOGGER.info("Parent request data received at controller layer for post mapping: {}", parentRequestModel);
        return parentService.addParent(parentRequestModel);
    }

    @PutMapping(value="/update")
    public ParentEntity updateParent(@RequestBody ParentEntity parentEntity){
        LOGGER.info("Parent request data received at controller layer for put mapping: {}", parentEntity);
        return parentService.updateParent(parentEntity);
    }

    @DeleteMapping(value = "/delete/{parentId}")
    public void deleteParent(@PathVariable Long parentId){
        LOGGER.info("Parent request data received at controller layer for delete mapping: {}", parentId);
        parentService.deleteParent(parentId);
    }

    @GetMapping(value = "/get/{parentId}")
    public ParentEntity getParent(@PathVariable Long parentId){
        LOGGER.info("Parent request data received at controller layer for get mapping: {}", parentId);
        return parentService.getParent(parentId).orElse(null);
    }

    @GetMapping(value = {"/","/gets"})
    public List<ParentEntity> getParents(){
        LOGGER.info("Request parents at controller layer for get mapping");
        return parentService.getParents();
    }
}
