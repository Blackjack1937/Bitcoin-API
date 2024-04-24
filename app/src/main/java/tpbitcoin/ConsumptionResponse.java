import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
