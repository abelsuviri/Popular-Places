package com.abelsuviri.viewmodel.mock;

import com.abelsuviri.data.model.ResponseModel;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class MockResponse {
    public static final String mockJson = "{" +
            "\"response\": {" +
            "\"headerLocation\": \"Málaga\"," +
            "\"groups\": [{" +
            "\"items\": [{" +
            "\"venue\": {" +
            "\"id\": \"4e2aa4ce6284b96d7b7f176b\"," +
            "\"name\": \"Alcazaba de Málaga\"," +
            "\"contact\": {}," +
            "\"location\": {" +
            "\"address\": \"C. Alcazabilla, 2\"," +
            "\"lat\": 36.72107608581298," +
            "\"lng\": -4.415988922119141," +
            "\"labeledLatLngs\": [{" +
            "\"label\": \"display\"," +
            "\"lat\": 36.72107608581298," +
            "\"lng\": -4.415988922119141" +
            "}]," +
            "\"postalCode\": \"29015\"," +
            "\"cc\": \"ES\"," +
            "\"city\": \"Málaga\"," +
            "\"state\": \"Andalucía\"," +
            "\"country\": \"España\"," +
            "\"formattedAddress\": [\"C. Alcazabilla, 2\", \"29015 Málaga Andalucía\", \"España\"]" +
            "}," +
            "\"categories\": [{" +
            "\"id\": \"4deefb944765f83613cdba6e\"," +
            "\"name\": \"Lugar histórico\"," +
            "\"pluralName\": \"Lugares históricos\"," +
            "\"shortName\": \"Lugar histórico\"," +
            "\"icon\": {" +
            "\"prefix\": \"https:\\/\\/ss3.4sqi.net\\/img\\/categories_v2\\/arts_entertainment\\/historicsite_\"," +
            "\"suffix\": \".png\"" +
            "}," +
            "\"primary\": true" +
            "}]" +
            "}" +
            "}]" +
            "}]" +
            "}" +
            "}";

    public static final Response<ResponseModel> mockNonExistingPlace = Response.error(400, ResponseBody.create(MediaType.parse("application/json"), "{}"));
}
