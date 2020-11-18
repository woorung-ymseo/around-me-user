package com.around.me.user.api.v1.user.controller;

import com.around.me.user.api.v1.user.service.UserService;
import com.around.me.user.core.annoitation.version.RestMappingV1;
import com.around.me.user.core.domain.User;
import com.around.me.user.core.dto.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * User 관련 API Controller
 */
@Api(tags = "회")
@Slf4j
@RequiredArgsConstructor
@RestMappingV1
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "결제 승인")
    @GetMapping(value = "/user")
    Response<User> payApproval(/*@RequestHeader(name = "Authorization", required = true) String authorization,*/
                               /*@ApiIgnore Errors errors*/) {

        User user = userService.findUserId();

        return Response.ok(user);
    }
}
