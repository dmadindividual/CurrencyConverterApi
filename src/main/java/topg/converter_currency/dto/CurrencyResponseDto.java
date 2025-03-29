package topg.converter_currency.dto;

public record CurrencyResponseDto(
        boolean success,
        Query query,
        Info info,
        double result
) {
    public record Query(String from, String to, double amount) {}
    public record Info(long timestamp, double quote) {}
}
