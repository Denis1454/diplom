package com.example.servicediplom.admin.compilation;

import com.example.servicediplom.dto.compilation.CompilationDto;
import com.example.servicediplom.dto.compilation.NewCompilationDto;
import com.example.servicediplom.dto.compilation.UpdateCompilationRequest;

public interface AdminCompilationService {

    CompilationDto createCompilation(NewCompilationDto newCompilationDto);

    void deleteCompilation(Long compilationId);

    CompilationDto updateCompilation(Long compilationId, UpdateCompilationRequest updateCompilationRequest);
}
