package com.example.servicediplom.admin.compilation;

import com.example.servicediplom.dto.compilation.CompilationDto;
import com.example.servicediplom.dto.compilation.NewCompilationDto;
import com.example.servicediplom.dto.compilation.UpdateCompilationRequest;

public interface AdminCompilationService {

    CompilationDto create(NewCompilationDto newCompilationDto);

    void deleteCompilation(Long compId);

    CompilationDto updateCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest);
}
