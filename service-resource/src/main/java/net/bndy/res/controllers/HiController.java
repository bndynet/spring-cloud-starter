package net.bndy.res.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Hi")
@RestController
@RequestMapping(value = "/api/hi")
public class HiController {

    @ApiOperation(value = "Hello world")
    @GetMapping()
    public String hi() {
        return "Hello World!";
    }
}
