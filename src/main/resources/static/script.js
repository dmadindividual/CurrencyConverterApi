document.addEventListener("DOMContentLoaded", () => {
    const fromCurrency = document.getElementById("fromCurrency");
    const toCurrency = document.getElementById("toCurrency");
    const amount = document.getElementById("amount");
    const convertBtn = document.getElementById("convertBtn");
    const result = document.getElementById("result");

    const apiUrl = "http://localhost:8080/api/currency"; // ✅ Correct API base URL

    // ✅ Load currency options dynamically
    async function loadCurrencies() {
        try {
            let response = await fetch(`${apiUrl}/latest?baseCurrency=USD`); // Default base currency
            let data = await response.json();

            Object.keys(data.quotes).forEach((currency) => {
                let option1 = new Option(currency, currency);
                let option2 = new Option(currency, currency);
                fromCurrency.add(option1);
                toCurrency.add(option2);
            });
        } catch (error) {
            console.error("Error loading currencies:", error);
        }
    }

    // ✅ Convert currency
    convertBtn.addEventListener("click", async () => {
        let from = fromCurrency.value;
        let to = toCurrency.value;
        let amt = amount.value;

        if (!from || !to || amt <= 0) {
            result.innerText = "Please enter valid details.";
            return;
        }

        try {
            let response = await fetch(`${apiUrl}/convert?from=${from}&to=${to}&amount=${amt}`);
            let data = await response.json();

            result.innerText = `${amt} ${from} = ${data.convertedAmount} ${to}`;
        } catch (error) {
            result.innerText = "Conversion failed.";
            console.error("Error converting:", error);
        }
    });

    loadCurrencies();
});
