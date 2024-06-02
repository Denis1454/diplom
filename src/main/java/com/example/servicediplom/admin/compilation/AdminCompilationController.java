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
        return adminCompilationService.create(newCompilationDto);
    }

    @DeleteMapping("/{compId}")
    public void delete(@PathVariable Long compId) {
        adminCompilationService.deleteCompilation(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto update(@PathVariable @Validated Long compId,
                                 @RequestBody @Validated UpdateCompilationRequest updateCompilationRequest) {
        return adminCompilationService.updateCompilation(compId,updateCompilationRequest);
    }
}
