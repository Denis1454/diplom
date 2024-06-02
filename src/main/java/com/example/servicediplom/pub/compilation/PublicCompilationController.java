package com.example.servicediplom.pub.compilation;

import com.example.servicediplom.dto.compilation.CompilationDto;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/compilations")
@RequiredArgsConstructor
public class PublicCompilationController {

    private final PublicCompilationService publicCompilationService;

    @GetMapping()
    public List<CompilationDto> getCompilationOfEvents(@RequestParam boolean pinned,
                                                       @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
                                                       @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return publicCompilationService.getCompilationOfEvents(pinned,from,size);
    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilationById(@PathVariable Long compId) {
        return publicCompilationService.getCompilationById(compId);
    }
}
