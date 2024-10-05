package com.base.services.service;

import com.base.services.dto.UsersDto;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SampleService {
    Mono<UsersDto> getUser(UUID id);
}
