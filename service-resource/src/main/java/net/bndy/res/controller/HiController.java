package net.bndy.res.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Api(tags = "Hi")
@RestController
@RequestMapping(value = "/api/hi")
public class HiController {

    @ApiOperation(value = "Hello world")
    @GetMapping()
    public String hi() {
        return "Hello World!";
    }

    @ApiOperation(value = "Who am I?")
    @GetMapping(value = "who")
    public Object who(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaims();
    }

    @ApiOperation(value = "No privilege")
    @GetMapping(value = "data")
    public String data() {
        return "You should not see this response since no 'data' scope granted";
    }

}
