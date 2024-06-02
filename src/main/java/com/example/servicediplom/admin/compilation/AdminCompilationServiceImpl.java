package com.example.servicediplom.admin.compilation;

import com.example.servicediplom.dto.compilation.CompilationDto;
import com.example.servicediplom.dto.compilation.NewCompilationDto;
import com.example.servicediplom.dto.compilation.UpdateCompilationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCompilationServiceImpl implements AdminCompilationService {

    @Override
    public CompilationDto create(NewCompilationDto newCompilationDto) {
        return null;
    }

    @Override
    public void deleteCompilation(Long compId) {

    }

    @Override
    public CompilationDto updateCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        return null;
    }
}
