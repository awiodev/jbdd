package io.github.awiodev.jbdd.restallured;

import static io.restassured.filter.log.LogDetail.ALL;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter responsible for attaching request to allure report
 */
public class AllureRequestLogFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllureRequestLogFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            try (PrintStream printStream = new PrintStream(outputStream)) {
                String uri = requestSpec.getURI();

                uri = UrlDecoder.urlDecode(uri,
                    Charset.forName(
                        requestSpec.getConfig().getEncoderConfig().defaultQueryParameterCharset()),
                    true);

                RequestPrinter
                    .print(requestSpec, requestSpec.getMethod(), uri, ALL, Collections.emptySet(),
                        printStream,
                        true);

                String key = requestSpec.getMethod() + " " + uri;

                Allure.addAttachment(key, outputStream.toString());
            }
        } catch (IOException e) {
            LOGGER.error("Failed to log request to allure", e);
        }

        return ctx.next(requestSpec, responseSpec);
    }
}
