package com.graphql.example.configurations;

import graphql.scalars.ExtendedScalars;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
@RequiredArgsConstructor
class GraphQLConfiguration {

    private final LocalDateScalarConfiguration localDateScalar;

    /**
     * This bean to register the custom Scalar or the Scalar definer in the extended library
     */
    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> {
            wiringBuilder.scalar(localDateScalar.localDateScalar());
            wiringBuilder.scalar(ExtendedScalars.Date);
            wiringBuilder.scalar(ExtendedScalars.LocalTime);
            wiringBuilder.scalar(ExtendedScalars.GraphQLLong);
        };
    }

}
