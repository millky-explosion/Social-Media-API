package com.example.socialmediaapi.service;

import com.example.socialmediaapi.models.RequestListEntity;
import com.example.socialmediaapi.repository.RequestListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@Service
@RequiredArgsConstructor
public class RequestListService {
    private final RequestListRepository friendListRepository;

    public Mono<RequestListEntity> sendRequestToUser(RequestListEntity friendListEntity) {
        return friendListRepository.save(friendListEntity).doOnSuccess(u -> {
            log.info("In RequestListRepository User has been send request - to user: {} ", u);
        });
    }

    public Mono<Void> deletePost (Long id) {
        return friendListRepository.deleteById(id).doOnSuccess(u -> {
            log.info("Success DELETE in RequestListService : {} ", u);
        });
    }

    public Mono<Void> findByID (Long id) {
        return friendListRepository.deleteById(id).doOnSuccess(u -> {
            log.info("Success DELETE in RequestListService : {} ", u);
        });
    }

    public Flux<RequestListEntity> findAll () {
        return friendListRepository.findAll();
    }

}
