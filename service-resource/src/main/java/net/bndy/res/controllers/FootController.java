package net.bndy.res.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.bndy.res.entities.Foo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Foo")
@RestController
@RequestMapping(value = "/api/foos")
public class FootController {

    @ApiOperation(value = "Get all items")
    @GetMapping(value = "")
    public Foo findAll() {
        Foo foo = new Foo();
        foo.setId("all");
        return  foo;
    }

    @ApiOperation(value = "Get one item")
    @GetMapping(value = "/{id}")
    public Foo findOne(@PathVariable String id) {
        Foo foo = new Foo();
        foo.setId(id);
        return  foo;
    }
}
