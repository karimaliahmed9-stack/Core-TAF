package Core.Apis;

import Core.DataReaderManager.PropertyReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class Buildre {
    private static final String baseUri = PropertyReader.getProperty("BaseURI");

    private Buildre() {
    }

    public static RequestSpecification GetUserManagementSpecification(Map<String, ?> formparam) {
        return new RequestSpecBuilder().setBaseUri(baseUri)
                .setContentType(ContentType.URLENC)
                .addFormParams(formparam)
                .build();
    }


}