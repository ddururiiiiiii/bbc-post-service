package com.bookbookclub.bbc_post_service.global.client;

import com.bookbookclub.common.dto.UserSummaryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "userClient", url = "${bbc-user-service.url}")
public interface UserClient {

    @GetMapping("/api/internal/users/{userId}")
    UserSummaryResponse getUserById(@PathVariable Long userId);

    @GetMapping("/api/internal/users")
    List<UserSummaryResponse> getUsersByIds(@RequestParam List<Long> userIds);
}
