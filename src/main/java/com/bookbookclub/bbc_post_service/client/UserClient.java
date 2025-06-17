package com.bookbookclub.bbc_post_service.client;

import com.bookbookclub.common.dto.UserSummaryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-service", url = "${bbc-user-service.url}")
public interface UserClient {

    @GetMapping("/internal/users/{userId}")
    UserSummaryResponse getUserInfo(@PathVariable Long userId);

    @GetMapping("/internal/users")
    List<UserSummaryResponse> getUsersByIds(@RequestParam("ids") List<Long> userIds);

}