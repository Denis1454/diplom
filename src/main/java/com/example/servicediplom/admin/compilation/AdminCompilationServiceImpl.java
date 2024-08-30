package com.example.servicediplom.admin.compilation;

import com.example.servicediplom.dto.compilation.CompilationDto;
import com.example.servicediplom.dto.compilation.NewCompilationDto;
import com.example.servicediplom.dto.compilation.UpdateCompilationRequest;
import com.example.servicediplom.entities.Compilation;
import com.example.servicediplom.exception.NotFoundException;
import com.example.servicediplom.mapper.CompilationMapperDto;
import com.example.servicediplom.repository.CompilationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCompilationServiceImpl implements AdminCompilationService {
    private static final Logger log = LoggerFactory.getLogger(AdminCompilationServiceImpl.class);
    private final String ERR_MESSAGE_COMPILATION_NOT_FOUND = "Подборки с id=%s не существует";
    private final CompilationRepository compilationRepository;
    private final CompilationMapperDto compilationMapper;

    @Override
    public CompilationDto createCompilation(NewCompilationDto newCompilationDto) {
        log.info(String.format("Начало операции createCompilation, %s ", newCompilationDto));
        Compilation save = compilationRepository.save(compilationMapper.toCompilation(newCompilationDto));
        CompilationDto compilationDto = compilationMapper.toDto(save);
        log.info("Конец операции createCompilation" + compilationDto);
        return compilationDto;
    }

    @Override
    public void deleteCompilation(Long compilationId) {
        log.info(String.format("Начало операции deleteCompilation, compilationId = %s ", compilationId));
        if (compilationRepository.existsById(compilationId)) {
            compilationRepository.deleteById(compilationId);
        } else {
            throw new NotFoundException(String.format(ERR_MESSAGE_COMPILATION_NOT_FOUND, compilationId));
        }
    }

    @Override
    public CompilationDto updateCompilation(Long compilationId, UpdateCompilationRequest updateCompilationRequest) {
        log.info(String.format("Начало операции createCompilation, compilationId = %s, %s ", compilationId, updateCompilationRequest));
        Compilation compilation = findCompilationById(compilationId);
        compilationMapper.updateCompilationDto(updateCompilationRequest, compilation);
        Compilation save = compilationRepository.save(compilation);
        CompilationDto compilationDto = compilationMapper.toDto(save);
        log.info("Конец операции updateCompilation" + compilationDto);
        return compilationDto;
    }

    private Compilation findCompilationById(Long compId) {
        return compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_COMPILATION_NOT_FOUND, compId)));
    }
}
