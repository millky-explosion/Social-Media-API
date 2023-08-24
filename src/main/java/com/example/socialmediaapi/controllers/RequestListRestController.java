package com.example.socialmediaapi.controllers;

import com.example.socialmediaapi.dto.UserDto;
import com.example.socialmediaapi.mappers.UserMapper;
import com.example.socialmediaapi.models.RequestListEntity;
import com.example.socialmediaapi.security.CustomPrincipal;
import com.example.socialmediaapi.service.RequestListService;
import com.example.socialmediaapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "RequestListRestController", description = "Контроллер для работы с заявками")
public class RequestListRestController {
     private final UserService userService;
     private final RequestListService friendListService;
     private final UserMapper userMapper;

    @Operation(summary = "Вывод заявки в друзья по /{id} ")
    @GetMapping("/{id}")
     public Mono<UserDto> findById (@PathVariable(value = "id") Long id) {
         return userService.getUserById(id).map(userMapper::map);
     }


    @Operation(summary = "Отправление реквеста в друзья пользователю ")
    @PostMapping("/{id}/send-request")
    public Mono<RequestListEntity> sendRequest (@PathVariable(value = "id") Long id,
                                                Authentication authentication) {
         CustomPrincipal customPrincipal = (CustomPrincipal) authentication.getPrincipal();

         RequestListEntity requestFriendList = new RequestListEntity();
         requestFriendList.setUser_id(id);
         requestFriendList.setFriend_request_id(customPrincipal.getId());

         return friendListService.sendRequestToUser(requestFriendList);
     }
}
