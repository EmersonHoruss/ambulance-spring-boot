package com.ambulance.ambulance.DTOs.entities;

import com.ambulance.ambulance.entities.BaseEntity;

public interface BaseDTO<E extends BaseEntity> {
    public E asEntity();
}
