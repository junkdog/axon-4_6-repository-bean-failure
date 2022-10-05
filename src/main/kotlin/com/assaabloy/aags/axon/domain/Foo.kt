package com.assaabloy.aags.axon.domain

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy.CREATE_IF_MISSING
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class Foo() {

    @AggregateIdentifier
    var id: String? = null

    @CommandHandler
    @CreationPolicy(CREATE_IF_MISSING)
    fun ensureFoo(command: BootstrapFooCommand): String? {
        if (id == null) {
            apply(FooCreatedEvent(command.id))
        }

        // with 4.4.8: FooCreatedEvent applied, id is set
        // with 4.5:   FooCreatedEvent delayed, id not set
        return id
    }

    @EventSourcingHandler
    fun on(event: FooCreatedEvent) {
        this.id = event.id
    }
}