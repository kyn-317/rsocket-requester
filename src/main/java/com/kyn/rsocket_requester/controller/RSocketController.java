package com.kyn.rsocket_requester.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyn.rsocket_requester.dto.GeminiResponseDto;
import com.kyn.rsocket_requester.service.RSocketClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class RSocketController {

    @Autowired
    private RSocketClientService rSocketService;

    @GetMapping("request-response")
    public Mono<GeminiResponseDto> requestResponse(@RequestParam String message) {
        return this.rSocketService.requestResponse(message);
    }

    @GetMapping(value = "request-response-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<GeminiResponseDto> requestResponseFlux(@RequestParam String message) {
        System.out.println("message: " + message);
        return this.rSocketService.requestResponseFlux(message);
    }
}
