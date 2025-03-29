package topg.converter_currency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import topg.converter_currency.dto.CurrencyResponseDto;
import topg.converter_currency.dto.CurrencyResponsesDto;
import topg.converter_currency.service.ConverterService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final ConverterService converterService;

    // ✅ Convert a specific amount between two currencies
    @GetMapping("/convert")
    public CurrencyResponseDto convertCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {
        return converterService.convertAmount(from, to, amount);
    }

    // ✅ Get the latest exchange rates for a base currency
    @GetMapping("/latest")
    public CurrencyResponsesDto getLatestRates(@RequestParam String baseCurrency) {
        return converterService.getExchange(baseCurrency);
    }

    @GetMapping("/historical")
    public CurrencyResponseDto getHistoricalRates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return converterService.getExchangeForParticularDay(date);
    }
}
