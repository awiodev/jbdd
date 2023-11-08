package io.github.awiodev.jbdd.restallured;

import static io.restassured.filter.log.LogDetail.ALL;

import io.qameta.allure.Allure;
import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter responsible for attaching request response to allure report
 */
public class AllureResponseLogFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllureResponseLogFilter.class);

    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        Response response = ctx.next(requestSpec, responseSpec);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            try (PrintStream printStream = new PrintStream(outputStream)) {
                StringBuilder sb = new StringBuilder();

                ResponsePrinter.print(response, response, printStream, ALL, true,
                    Collections.emptySet());
                final byte[] responseBody = response.asByteArray();

                sb.append(outputStream);
                if (responseBody != null) {
                    sb.append(System.getProperty("line.separator"));
                    sb.append(new String(responseBody));
                }

                Allure.addAttachment("response", sb.toString());
                response = cloneResponseIfNeeded(response, responseBody);

                return response;
            }
        } catch (IOException e) {
            LOGGER.error("Failed to log response to allure", e);
        }

        return response;

    }

    private Response cloneResponseIfNeeded(Response response, byte[] responseAsString) {
        if (responseAsString != null && response instanceof RestAssuredResponseImpl &&
            !((RestAssuredResponseImpl) response).getHasExpectations()) {
            final Response build =
                new ResponseBuilder().clone(response).setBody(responseAsString).build();
            ((RestAssuredResponseImpl) build).setHasExpectations(true);
            return build;
        }
        return response;
    }
}
