package com.example.servicediplom.admin.compilation;

import com.example.servicediplom.dto.compilation.CompilationDto;
import com.example.servicediplom.dto.compilation.NewCompilationDto;
import com.example.servicediplom.dto.compilation.UpdateCompilationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/compilations")
@RequiredArgsConstructor
public class AdminCompilationController {

    private final AdminCompilationService adminCompilationService;

    @PostMapping()
    public CompilationDto create(@RequestBody @Validated NewCompilationDto newCompilationDto) {
        return adminCompilationService.createCompilation(newCompilationDto);
    }

    @DeleteMapping("/{compId}")
    public void delete(@PathVariable(name = "compId") Long compilationId) {
        adminCompilationService.deleteCompilation(compilationId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto update(@PathVariable(name = "compId") Long compilationId,
                                 @RequestBody @Validated UpdateCompilationRequest updateCompilationRequest) {
        return adminCompilationService.updateCompilation(compilationId, updateCompilationRequest);
    }
}
