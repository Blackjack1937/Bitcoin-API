package tpbitcoin;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FingridAPI {

    private static final String API_KEY = "5149cba6ecdd4fff983747c0237699be"; //

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        String datasetId = "124"; // ID de l'ensemble de données pour la consommation d'énergie
        String url = "https://data.fingrid.fi/api/datasets/" + datasetId + "/data/latest?";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-api-key", API_KEY)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Réponse: " + response.statusCode());
            System.out.println("Données: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
