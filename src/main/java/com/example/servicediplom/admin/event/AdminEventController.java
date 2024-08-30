package com.example.servicediplom.admin.event;

import com.example.servicediplom.dto.event.EventFullDto;
import com.example.servicediplom.dto.event.UpdateEventAdminRequest;
import com.example.servicediplom.entities.enums.State;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/events")
@RequiredArgsConstructor
public class AdminEventController {
    private final AdminEventService adminEventService;

    @GetMapping()
    public List<EventFullDto> searchEvent(@RequestParam List<Long> users,
                                          @RequestParam List<State> states,
                                          @RequestParam List<Long> categories,
                                          @RequestParam String rangeStart,
                                          @RequestParam String rangeEnd,
                                          @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
                                          @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return adminEventService.searchEvent(users,states,categories,rangeStart,rangeEnd,from,size);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto update(@PathVariable @Validated Long eventId, @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        return adminEventService.updateAdminEvent(eventId,updateEventAdminRequest);
    }
}
