package topg.converter_currency.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import topg.converter_currency.dto.CurrencyResponseDto;
import topg.converter_currency.dto.CurrencyResponsesDto;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ConverterService {
    private final WebClient webClient;
    private static final String API_KEY = "f5a8b02aa97949252e8f4113f5e7e285"; // Replace with your actual key
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;



    public CurrencyResponseDto convertAmount(String currencyFrom, String currencyTo, double amount) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/convert")
                        .queryParam("access_key", API_KEY)
                        .queryParam("from", currencyFrom.toUpperCase())
                        .queryParam("to", currencyTo.toUpperCase())
                        .queryParam("amount", amount)
                        .build())
                .retrieve()
                .bodyToMono(CurrencyResponseDto.class)
                .block();
    }

    public CurrencyResponsesDto getExchange(String baseCurrency) {
        String cacheKey = "live_rates_" + baseCurrency.toUpperCase();

        // Retrieve from Redis and convert properly
        Object cachedData = redisTemplate.opsForValue().get(cacheKey);
        if (cachedData != null) {
            return objectMapper.convertValue(cachedData, CurrencyResponsesDto.class);
        }

        // Fetch from API if not cached
        CurrencyResponsesDto response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/live")
                        .queryParam("access_key", API_KEY)
                        .queryParam("source", baseCurrency.toUpperCase())
                        .build())
                .retrieve()
                .bodyToMono(CurrencyResponsesDto.class)
                .block();

        // Store in Redis with an expiration time
        redisTemplate.opsForValue().set(cacheKey, response, 10, TimeUnit.MINUTES);

        return response;
    }
    public CurrencyResponseDto getExchangeForParticularDay(LocalDate date) {
        String cacheKey = "historical_rates_" + date.toString();


        CurrencyResponseDto cachedRates = (CurrencyResponseDto) redisTemplate.opsForValue().get(cacheKey);
        if (cachedRates != null) {
            return cachedRates;
        }

        // If not cached, fetch from API
        CurrencyResponseDto response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/historical")
                        .queryParam("access_key", API_KEY)
                        .queryParam("date", date.toString())
                        .build())
                .retrieve()
                .bodyToMono(CurrencyResponseDto.class)
                .block();

        redisTemplate.opsForValue().set(cacheKey, response);

        return response;
    }

}
