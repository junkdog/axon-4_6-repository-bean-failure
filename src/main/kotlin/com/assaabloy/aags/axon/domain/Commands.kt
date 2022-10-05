package com.assaabloy.aags.axon.domain

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class BootstrapFooCommand(
    @TargetAggregateIdentifier val id: String
)