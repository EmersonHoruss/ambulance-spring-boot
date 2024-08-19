package com.ambulance.ambulance.DTOs.entities;

import com.ambulance.ambulance.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("rawtypes")
@Getter
@Setter
public abstract class BaseShowDTO<E extends BaseEntity> {
    private Long id;
    private Boolean activated;
}
