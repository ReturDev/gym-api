package com.returdev.catalog_service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;


/**
 * Represents an equipment entity in the system.
 * This entity is mapped to the 'equipments' table and contains details related to the equipment,
 * including its name and image URL.
 */
@Entity
@Table(name = "equipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 25, message = "{validation.size.message}")
    @NotNull(message = "{validation.not_null_required.message}")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @URL(message = "{validation.url.message}")
    @NotNull(message = "{validation.not_null_required.message}")
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

}
