package com.example.servicediplom.mapper;

import com.example.servicediplom.dto.compilation.CompilationDto;
import com.example.servicediplom.dto.compilation.NewCompilationDto;
import com.example.servicediplom.dto.compilation.UpdateCompilationRequest;
import com.example.servicediplom.entities.Compilation;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompilationMapperDto {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCompilationDto(UpdateCompilationRequest dto, @MappingTarget Compilation compilation);
    List<CompilationDto> toListDto(Page<Compilation> compilations);
    Compilation toCompilation(NewCompilationDto newCompilationDto);
    CompilationDto toDto(Compilation compilation);
}
