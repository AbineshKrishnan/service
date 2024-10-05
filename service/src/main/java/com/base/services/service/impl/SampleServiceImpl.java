package com.base.services.service.impl;

import com.base.services.constants.MessageKeyConstants;
import com.base.services.dto.UsersDto;
import com.base.services.exception.ResourceNotFoundException;
import com.base.services.persistence.repository.UsersRepository;
import com.base.services.service.SampleService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class SampleServiceImpl implements SampleService {

    private final UsersRepository usersRepository;

    public SampleServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Mono<UsersDto> getUser(UUID id) {
        return usersRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(MessageKeyConstants.USER_NOT_FOUND)))
                .map(users -> {
                    UsersDto usersDto = new UsersDto();
                    usersDto.setId(users.getId());
                    usersDto.setUserName(users.getUserName());
                    return usersDto;
                });
    }
}
