package com.ambulance.ambulance.services;

import com.ambulance.ambulance.entities.BaseEntity;
import com.ambulance.ambulance.repositories.BaseRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public abstract class BaseServiceImplementation<E extends BaseEntity> {
    @Autowired
    protected BaseRepository<E> baseRepository;

    @Transactional
    public E create(E entity){
        return baseRepository.save(entity);
    }

    public E get(Long id){
        return baseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("not found"));
    }

    public Page<E> get(Specification<E> specification, Pageable pageable){
        return baseRepository.findAll(specification, pageable);
    }

    @Transactional
    public E update(E entity){
        E foundEntity = baseRepository.getReferenceById(entity.getId());
        if(!foundEntity.getActivated()){
            throw new RuntimeException("The entity should be active to make changes");
        }
        entity.setActivated(foundEntity.getActivated());
        return baseRepository.save(entity);
    }

    @Transactional
    public E setActivation(E entity){
        E foundEntity = baseRepository.getReferenceById(entity.getId());
        foundEntity.setActivated(entity.getActivated());
        return baseRepository.save(foundEntity);
    }

    @Transactional
    public void delete(Long id){
        E entity = baseRepository.getReferenceById(id);
        baseRepository.delete(entity);
    }
}
