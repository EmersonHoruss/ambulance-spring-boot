package com.ambulance.ambulance.DTOs.entities;

import com.ambulance.ambulance.entities.BaseEntity;

@SuppressWarnings("rawtypes")
public interface BaseDTO<E extends BaseEntity> {
    public E asEntity();
}
