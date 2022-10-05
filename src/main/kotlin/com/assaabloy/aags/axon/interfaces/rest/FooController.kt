package com.assaabloy.aags.axon.interfaces.rest

import com.assaabloy.aags.axon.domain.BootstrapFooCommand
import com.assaabloy.aags.axon.domain.Foo
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.modelling.command.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID.randomUUID

@RestController
class FooController(
    val commandGateway: CommandGateway,
) {

    @Autowired
    lateinit var userRepository: Repository<Foo>

    @PostMapping("/foo", consumes = ["application/json"])
    fun createFoo(): ResponseEntity<Any> {
        val command = BootstrapFooCommand(randomUUID().toString())
        return ResponseEntity.ok(commandGateway.sendAndWait<String>(command))
    }
}
