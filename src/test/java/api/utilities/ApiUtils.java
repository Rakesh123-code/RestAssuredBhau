package api.utilities;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.restassured.response.Response;
import org.awaitility.Awaitility;

public class ApiUtils {
    
    public static Response retryApiCall(Callable<Response> apiCall, int maxRetries, int waitTimeMs) {
        return Awaitility.await()
            .atMost(60, TimeUnit.SECONDS)  // Maximum total wait time (e.g., 60 sec)
            .pollInterval(3, TimeUnit.SECONDS)  // Retry every 3 sec instead of waiting full 9 sec
            .ignoreExceptions()  // Ignore temporary failures
            .until(apiCall, response -> {
                System.out.println("🔄 Retrying... Response Code: " + response.getStatusCode());
                response.then().log().all(); 
                return response.getStatusCode() == 200;  // Stop retry when response is 200
            });
    }
}
