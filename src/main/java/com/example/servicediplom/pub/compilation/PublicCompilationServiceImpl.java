package com.example.servicediplom.pub.compilation;

import com.example.servicediplom.dto.compilation.CompilationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicCompilationServiceImpl implements PublicCompilationService {


    @Override
    public List<CompilationDto> getCompilationOfEvents(boolean pinned, Integer from, Integer size) {
        return null;
    }

    @Override
    public CompilationDto getCompilationById(Long compId) {
        return null;
    }
}
