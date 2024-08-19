package com.ambulance.ambulance.DTOs.entities;

import com.ambulance.ambulance.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("rawtypes")
@Getter
@Setter
public abstract class BaseCreateDTO<E extends BaseEntity> implements BaseDTO<E> {
}
