package com.kyn.rsocket_requester.service;

import java.net.URI;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;

import com.kyn.rsocket_requester.dto.GeminiResponseDto;

import io.rsocket.transport.netty.client.WebsocketClientTransport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RSocketClientService {
    private final RSocketRequester requester;

    @Autowired
    public RSocketClientService(RSocketRequester.Builder builder) {
        this.requester = builder
                .transport(WebsocketClientTransport
                        .create(URI.create("ws://localhost:8081/rsocket")));
    }

    public Mono<GeminiResponseDto> requestResponse(String message) {
        return this.requester.route("gemini.generate")
                .data(message)
                .retrieveMono(GeminiResponseDto.class);
    }

    public Flux<GeminiResponseDto> requestResponseFlux(String message) {
        return this.requester.route("gemini.stream")
                .data(message)
                .retrieveFlux(GeminiResponseDto.class);
    }
}
