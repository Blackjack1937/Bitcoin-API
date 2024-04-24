package tpbitcoin;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class HourlyConsumption {
    private final long value; // en MWh

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public HourlyConsumption(long value, LocalDateTime startTime, LocalDateTime endTime) {
        this.value = value;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Retourne la consommation en kWh
    public long getConsumption() {
        return value * 1000;
    }

    public static long getFinnishConsumptionLast24h(String apiKey) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusDays(1);

        HourlyConsumption consumption = getFinnishConsumption(startTime, endTime, apiKey);
        return consumption.getConsumption(); // Retourne directement la consommation en kWh
    }

    public static HourlyConsumption getFinnishConsumption(LocalDateTime startTime, LocalDateTime endTime, String apiKey) {
        String response = queryFinnishAPI(startTime, endTime, apiKey);

        Gson gson = new Gson();
        ConsumptionResponse consumptionResponse = gson.fromJson(response, ConsumptionResponse.class);

        // Convertit la valeur en MWh à kWh
        long consumptionInKWh = (long) (consumptionResponse.getValue() * 1000);
        return new HourlyConsumption(consumptionInKWh, startTime, endTime);
    }

    private static String queryFinnishAPI(LocalDateTime startTime, LocalDateTime endTime, String apiKey) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String datasetId = "124"; // ID de l'ensemble de données pour la consommation d'énergie
        String url = String.format("https://data.fingrid.fi/api/datasets/" + datasetId + "/data/latest",
                datasetId, startTime.format(formatter), endTime.format(formatter));

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-api-key", apiKey)
                .GET() // Méthode GET
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Getters pour startTime et endTime si nécessaire
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public class ConsumptionResponse {
        private long datasetId;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private double value;

        // Constructeur par défaut
        public ConsumptionResponse() {
        }

        // Getters et Setters
        public long getDatasetId() {
            return datasetId;
        }

        public void setDatasetId(long datasetId) {
            this.datasetId = datasetId;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_DATE_TIME);
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = LocalDateTime.parse(endTime, DateTimeFormatter.ISO_DATE_TIME);
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "ConsumptionResponse{" +
                    "datasetId=" + datasetId +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", value=" + value +
                    '}';
        }
    }

}
