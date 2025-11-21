package com.salesianostriana.dam.ejercicio_evaluable.controllers;

import com.salesianostriana.dam.ejercicio_evaluable.models.DTO.MuseumRequestDto;
import com.salesianostriana.dam.ejercicio_evaluable.models.DTO.MuseumResponseDto;
import com.salesianostriana.dam.ejercicio_evaluable.services.MuseoLocalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/museum")
@Tag(name = "Controlador de museos", description = "Controlador de operaciones para museos locales.")
@RequiredArgsConstructor
public class MuseoLocalController {

    private final MuseoLocalService service;

    @GetMapping
    @ApiResponse(
            responseCode = "404",
            description = "API Response en caso de no encontrar ningún monumento en la BD",
            content = @Content(
                    mediaType = "application/JSON",
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = {
                            @ExampleObject(
                                    value = """
                                            {
                                              "type": "about:blank",
                                              "title": "Museos no encontrados.",
                                              "status": 404,
                                              "detail": "No se ha encontrado ningún museo.",
                                              "instance": "/museum"
                                            }
                                            """
                            )
                    }
            )
    )
    @ApiResponse(
            responseCode = "200",
            description = "Museos obtenidos correctamente",
            content = @Content(
                    mediaType = "application/JSON",
                    schema = @Schema(implementation = MuseumResponseDto.class),
                    examples = @ExampleObject(
                            value = """
                                    [
                                      {
                                        "id": 1,
                                        "nombre": "British Museum",
                                        "ciudad": "Londres",
                                        "anioApertura": "1759",
                                        "salasExposicion": 1500,
                                        "descripcionGeneral": "Aquí debería poner algo largo, pero no me da tiempo",
                                        "urlOficial": "https://www.britishmuseum.org/",
                                        "urlImagen": "https://upload.wikimedia.org/wikipedia/commons/3/3a/British_Museum_from_NE_2.JPG"
                                      }
                                    ]
                                    """
                    )
            )
    )
    @Operation(description = "Obtener un listado con todos los museos.", summary = "Obtener todos los museos")
    public ResponseEntity<List<MuseumResponseDto>> obtenerMuseos() {
        return ResponseEntity.ok(service.getAll().stream().map(MuseumResponseDto::to).toList());
    }

    @PostMapping()
    @Operation(description = "Solicitud para crear un Museo", summary = "Crear un museo")
    @ApiResponse(
            responseCode = "201",
            description = "Museo generado correctamente",
            content = @Content(
                    mediaType = "application/JSON",
                    schema = @Schema(implementation = MuseumResponseDto.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                      "id": 1,
                                      "nombre": "British Museum",
                                      "ciudad": "Londres",
                                      "anioApertura": "1759",
                                      "salasExposicion": 1500,
                                      "descripcionGeneral": "Aquí debería poner algo largo, pero no me da tiempo",
                                      "urlOficial": "https://www.britishmuseum.org/",
                                      "urlImagen": "https://upload.wikimedia.org/wikipedia/commons/3/3a/British_Museum_from_NE_2.JPG"
                                    }
                                    """
                    )
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Has insertado algun dato invalido.",
            content = @Content(
                    mediaType = "application/JSON",
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = {
                            @ExampleObject(
                                    value = """
                                            {
                                              "type": "about:blank",
                                              "title": "Datos incorrectos enviados en el museo.",
                                              "status": 400,
                                              "detail": "El nombre no debe ser vacio.",
                                              "instance": "/museum"
                                            }
                                            """
                            )
                    }
            )
    )
    public ResponseEntity<MuseumResponseDto> crearMuseo(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Contenido mediante el cual se creara un nuevo museo.",
                    content = @Content(
                            mediaType = "application/JSON",
                            schema = @Schema(implementation = MuseumRequestDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "nombre": "British Museum",
                                              "ciudad": "Londres",
                                              "anioApertura": "1759",
                                              "salasExposicion": 1500,
                                              "descripcionGeneral": "Aquí debería poner algo largo, pero no me da tiempo",
                                              "urlOficial": "https://www.britishmuseum.org/",
                                              "urlImagen": "https://upload.wikimedia.org/wikipedia/commons/3/3a/British_Museum_from_NE_2.JPG"
                                            }
                                            """
                            )
                    )
            )
            @RequestBody MuseumRequestDto museoDTO
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(MuseumResponseDto.to(service.crearMuseo(museoDTO.from())));
    }

}
