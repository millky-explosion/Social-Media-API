package com.example.socialmediaapi.controllers;

import com.example.socialmediaapi.dto.UserDto;
import com.example.socialmediaapi.mappers.UserMapper;
import com.example.socialmediaapi.models.RequestListEntity;
import com.example.socialmediaapi.security.CustomPrincipal;
import com.example.socialmediaapi.service.RequestListService;
import com.example.socialmediaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class RequestListRestController {
     private final UserService userService;
     private final RequestListService friendListService;
     private final UserMapper userMapper;


     @GetMapping("/{id}")
     public Mono<UserDto> findById (@PathVariable(value = "id") Long id) {
         return userService.getUserById(id).map(userMapper::map);
     }



     @PostMapping("/{id}/send_request")
    public Mono<RequestListEntity> sendRequest (@PathVariable(value = "id") Long id,
                                                Authentication authentication) {
         CustomPrincipal customPrincipal = (CustomPrincipal) authentication.getPrincipal();

         RequestListEntity requestFriendList = new RequestListEntity();
         requestFriendList.setUser_id(id);
         requestFriendList.setFriend_request_id(customPrincipal.getId());

         return friendListService.sendRequestToUser(requestFriendList);
     }
}
