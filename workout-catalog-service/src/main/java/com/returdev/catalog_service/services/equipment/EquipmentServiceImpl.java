package com.returdev.catalog_service.services.equipment;

import com.returdev.catalog_service.entities.EquipmentEntity;
import com.returdev.catalog_service.repositories.EquipmentRepository;
import com.returdev.utils_library.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public EquipmentEntity saveEquipment(EquipmentEntity equipment) {
        equipment.setId(null);
        return equipmentRepository.save(equipment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EquipmentEntity> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EquipmentEntity getEquipmentById(Long id) {
        return equipmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EquipmentEntity getEquipmentByName(String name) {
        return equipmentRepository.findEquipmentByName(name).orElseThrow(() -> new ResourceNotFoundException(name)); //TODO Add message
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsByName(String name) {
        return equipmentRepository.existsByName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEquipmentById(Long id) {
        equipmentRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEquipment(EquipmentEntity equipment) {
        existsById(equipment.getId());
        equipmentRepository.save(equipment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateEquipment(Long id, String name, String imageUrl) {
        existsById(id);
        if (name != null && !name.isBlank()) {
            return equipmentRepository.updateEquipmentName(id, name) == 1;
        }
        if (imageUrl != null) {
            return equipmentRepository.updateEquipmentImageUrl(id, imageUrl) == 1;
        }
        return false;
    }

    /**
     * Checks if an equipment entity with the given ID exists.
     *
     * @param id the ID of the equipment entity
     * @throws EntityNotFoundException if no equipment entity with the specified ID is found
     */
    private void existsById(Long id) {
        if (!equipmentRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
    }
}