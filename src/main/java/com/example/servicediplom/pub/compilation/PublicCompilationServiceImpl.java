package com.example.servicediplom.pub.compilation;

import com.example.servicediplom.dto.compilation.CompilationDto;
import com.example.servicediplom.entities.Compilation;
import com.example.servicediplom.exception.NotFoundException;
import com.example.servicediplom.mapper.CompilationMapperDto;
import com.example.servicediplom.repository.CompilationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicCompilationServiceImpl implements PublicCompilationService {
    private static final Logger log = LoggerFactory.getLogger(PublicCompilationServiceImpl.class);
    private final String ERR_MESSAGE_COMPILATION_NOT_FOUND = "Подборки с id=%s не существует";
    private final CompilationRepository compilationRepository;
    private final CompilationMapperDto compilationMapperDto;

    @Override
    public List<CompilationDto> getCompilationOfEvents(boolean pinned, Integer from, Integer size) {
        log.info("Начала операции getCompilationOfEvents");
        Pageable pageable = PageRequest.of(from, size);
        Page<Compilation> compilations = compilationRepository.findCompilationsByPinned(pinned, pageable);
        List<CompilationDto> compilationDto = compilationMapperDto.toListDto(compilations);
        log.info("Конец операции getCompilationOfEvents" + compilationDto);
        return compilationDto;
    }

    @Override
    public CompilationDto getCompilationById(Long compId) {
        log.info(String.format("Начала операции getCompilationOfEvents, compId = %s", compId));
        Compilation compilation = compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_COMPILATION_NOT_FOUND, compId)));
        CompilationDto compilationDto = compilationMapperDto.toDto(compilation);
        log.info("Конец операции getCompilationById" + compilationDto);
        return compilationDto;
    }
}
