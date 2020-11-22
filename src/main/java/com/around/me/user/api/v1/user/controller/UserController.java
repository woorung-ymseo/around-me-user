package com.around.me.user.api.v1.user.controller;

import com.around.me.user.api.v1.user.service.UserService;
import com.around.me.user.core.annoitation.version.RestMappingV1;
import com.around.me.user.core.domain.User;
import com.around.me.user.core.dto.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * User 관련 API Controller
 */
@Api(tags = "회원")
@Slf4j
@RequiredArgsConstructor
@RestMappingV1
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원정보 조회 test")
    @GetMapping(value = "/user")
    Response<User> userInfoTest(/*@RequestHeader(name = "Authorization", required = true) String authorization,*/
                              /*@ApiIgnore Errors errors*/) {

        User user = userService.findUserId();

        return Response.ok(user);
    }

    @ApiOperation(value = "약관 테스트 조회")
    @GetMapping(value = "/user/term")
    Response<String> userTermInfoTest(/*@RequestHeader(name = "Authorization", required = true) String authorization,*/
                              /*@ApiIgnore Errors errors*/) {

        Response<String> str = userService.term();

        return str;
    }
}
