package com.example.servicediplom.pub.compilation;

import com.example.servicediplom.dto.compilation.CompilationDto;

import java.util.List;

public interface PublicCompilationService {


    List<CompilationDto> getCompilationOfEvents(boolean pinned, Integer from, Integer size);

    CompilationDto getCompilationById(Long compId);
}
