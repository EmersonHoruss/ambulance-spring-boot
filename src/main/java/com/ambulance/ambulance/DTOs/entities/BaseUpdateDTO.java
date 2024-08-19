package com.ambulance.ambulance.DTOs.entities;

import com.ambulance.ambulance.entities.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("rawtypes")
@Getter
@Setter
public abstract class BaseUpdateDTO<E extends BaseEntity> implements BaseDTO<E> {
    @NotNull
    private Long id;
}
