package topg.converter_currency.dto;


import java.util.Map;

public record CurrencyResponsesDto(
        boolean success,
        long timestamp,
        String source,
        Map<String, Double> quotes
) {}

