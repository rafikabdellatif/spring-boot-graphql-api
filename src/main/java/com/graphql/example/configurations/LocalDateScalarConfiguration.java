package com.graphql.example.configurations;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Configuration(proxyBeanMethods = false)
public class LocalDateScalarConfiguration {

    @Bean
    public GraphQLScalarType localDateScalar() {
        return GraphQLScalarType.newScalar()
                .name("LocalDate")
                .description("Java 8 LocalDate as scalar.")
                .coercing(new LocalDateCoercing())
                .build();
    }

    private static class LocalDateCoercing implements Coercing<LocalDate, String> {
        @Override
        public String serialize(
                final Object dataFetcherResult,
                final GraphQLContext graphQLContext,
                final Locale locale) {
            if (dataFetcherResult instanceof LocalDate) {
                return dataFetcherResult.toString();
            } else {
                throw new CoercingSerializeException("Expected a LocalDate object.");
            }
        }

        @Override
        public LocalDate parseValue(
                final Object input,
                GraphQLContext graphQLContext,
                Locale locale) {
            try {
                if (input instanceof String) {
                    return LocalDate.parse((String) input);
                } else {
                    throw new CoercingParseValueException("Expected a String");
                }
            } catch (DateTimeParseException e) {
                throw new CoercingParseValueException(String.format("Not a valid date: '%s'.", input), e
                );
            }
        }

        @Override
        public LocalDate parseLiteral(
                final Value<?> input,
                final CoercedVariables variables,
                GraphQLContext graphQLContext,
                Locale locale) {
            if (input instanceof StringValue) {
                try {
                    return LocalDate.parse(((StringValue) input).getValue());
                } catch (DateTimeParseException e) {
                    throw new CoercingParseLiteralException(e);
                }
            } else {
                throw new CoercingParseLiteralException("Expected a StringValue.");
            }
        }
    }

}
