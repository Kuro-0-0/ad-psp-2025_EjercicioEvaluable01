package com.salesianostriana.dam.ejercicio_evaluable.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Year;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class MuseoLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ciudad;
    private Year anioApertura;
    private Integer salasExposicion;
    private String descripcionGeneral;
    private String urlOficial;
    private String urlImagen;


}
