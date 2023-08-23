package com.example.socialmediaapi.repository;

import com.example.socialmediaapi.models.RequestListEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface RequestListRepository extends R2dbcRepository <RequestListEntity, Long> {
    Mono<RequestListEntity> findById (Long id);
    @Query(value = "SELECT u.id FROM requestlist u" +
            " WHERE u.user_id=:user_id AND u.friend_request_id=:friend_request_id")
    Mono<RequestListEntity> findAllById (Long user_id, Long friend_request_id);


}
