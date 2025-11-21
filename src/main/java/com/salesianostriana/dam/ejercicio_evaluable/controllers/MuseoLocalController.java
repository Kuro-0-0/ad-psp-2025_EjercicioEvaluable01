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
    @Operation(description = "Obtener un listado con todos los museos.")
    public ResponseEntity<List<MuseumResponseDto>> obtenerMuseos() {
        return ResponseEntity.ok(service.getAll().stream().map(MuseumResponseDto::to).toList());
    }

    @PostMapping()
    public ResponseEntity<MuseumResponseDto> crearMuseo(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Contenido mediante el cual se creara un nuevo museo.",
                    content = @Content(
                            mediaType = "application/JSON",
                            schema = @Schema(implementation = MuseumRequestDto.class),
                            examples = @ExampleObject(
                                    value = """
                                            """
                            )
                    )
            )
            @RequestBody MuseumRequestDto museoDTO
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(MuseumResponseDto.to(service.crearMuseo(museoDTO.from())));
    }

}
