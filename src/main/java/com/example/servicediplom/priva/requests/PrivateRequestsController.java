package com.example.servicediplom.priva.requests;

import com.example.servicediplom.dto.event.ParticipationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/requests")
@RequiredArgsConstructor
public class PrivateRequestsController {

    private final PrivateRequestsService privateRequestsService;

    @GetMapping()
    public List<ParticipationRequestDto> getRequestsByRequesterId(@PathVariable Long userId) {
        return privateRequestsService.getRequestsByRequesterId(userId);
    }

    @PostMapping()
    public ParticipationRequestDto createRequest(@PathVariable Long userId, @RequestParam Long eventId) {
        return privateRequestsService.createRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelingYourRequestToParticipateInEvent(@PathVariable Long userId,
                                        @PathVariable Long requestId) {
        return privateRequestsService.changeStatusToCancel(userId, requestId);
    }
}
