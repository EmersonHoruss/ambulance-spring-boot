package com.ambulance.ambulance.controllers;

import com.ambulance.ambulance.DTOs.entities.BaseActivatedDTO;
import com.ambulance.ambulance.DTOs.entities.BaseCreateDTO;
import com.ambulance.ambulance.DTOs.entities.BaseShowDTO;
import com.ambulance.ambulance.DTOs.entities.BaseUpdateDTO;
import com.ambulance.ambulance.DTOs.responses.ResponseDTO;
import com.ambulance.ambulance.entities.BaseEntity;
import com.ambulance.ambulance.services.BaseServiceImplementation;
import com.ambulance.ambulance.utils.specification.Specification;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public class BaseControllerImplementation<
    E extends BaseEntity,
    CreateDTO extends BaseCreateDTO<E>,
    ShowDTO extends BaseShowDTO<E>,
    UpdateDTO extends BaseUpdateDTO<E>,
    ActivatedDTO extends BaseActivatedDTO<E>
>{
    @Autowired
    private BaseServiceImplementation<E> service;

    @PostMapping("")
    public ResponseEntity<ResponseDTO> create(
        @Valid @RequestBody CreateDTO createDTO,
        UriComponentsBuilder uriBuilder,
        HttpServletRequest request
    ){
        E entity = service.create(createDTO.asEntity());
        String requestUri = request.getRequestURI() + "/{id}";
        URI uri = uriBuilder.path(requestUri).buildAndExpand(entity.getId()).toUri();
        BaseShowDTO<E> dto = entity.asShowDTO();
        return ResponseEntity.created(uri).body(new ResponseDTO(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> get(@PathVariable Long id){
        E entity = service.get(id);
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO> get(
        @RequestParam(required = false) String query,
        @PageableDefault(size = 10) Pageable pageable
    ){
        Page<E> page = service.get(new Specification<E>(query), pageable);
        List<BaseShowDTO> dtos = page
            .getContent()
            .stream()
            .map(e -> e.asShowDTO())
            .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseDTO(dtos, page, query));
    }

    @PutMapping("")
    public ResponseEntity<ResponseDTO> update(
        @Valid @RequestBody UpdateDTO dto
    ){
        E entity = service.update(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @PatchMapping("")
    public ResponseEntity<ResponseDTO> setActivation(
        @Valid @RequestBody ActivatedDTO dto
    ){
        E entity = service.setActivation(dto.asEntity());
        return ResponseEntity.ok(new ResponseDTO(entity.asShowDTO()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivated(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
