package com.example.servicediplom.pub.event;

import com.example.servicediplom.dto.event.EventFullDto;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
public class PublicEventController {

    private final PublicEventService publicEventService;

    @PostMapping()
    public List<EventFullDto> getEventsTheAbilityFilter(@RequestParam String text,
                                          @RequestParam List<Integer> categories,
                                          @RequestParam boolean paid,
                                          @RequestParam String rangeStart,
                                          @RequestParam String rangeEnd,
                                          @RequestParam boolean onlyAvailable,
                                          @RequestParam String sort,
                                          @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
                                          @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return publicEventService.getEventsTheAbilityFilter(text,categories,paid,rangeStart,rangeEnd,onlyAvailable,sort,from,size);
    }

    @PatchMapping("/{id}")
    public EventFullDto getInformationEventById(@PathVariable @Validated Long id) {
        return publicEventService.getInformationEventById(id);
    }
}
