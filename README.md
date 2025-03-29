# Currency Converter Web App

## Overview
This web application provides real-time currency conversion and exchange rates. Users can select a base currency and fetch the latest exchange rates for various currencies. The frontend is built using **HTML, CSS, and JavaScript**, while the backend is powered by a **Spring Boot API** that fetches currency data.

## Features
✅ Convert a specific amount between two currencies  
✅ Fetch the latest exchange rates for a base currency  
✅ Display exchange rates in a user-friendly format  
✅ Clean and responsive UI using modern styling  

## Tech Stack
- **Frontend:** HTML, CSS, JavaScript (Fetch API)
- **Backend:** Spring Boot (Java) with RESTful API

## API Endpoints
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/api/currency/convert?from={currency}&to={currency}&amount={value}` | Convert an amount from one currency to another |
| `GET` | `/api/currency/latest?baseCurrency={currency}` | Get latest exchange rates for the base currency |
| `GET` | `/api/currency/historical?date={YYYY-MM-DD}` | Get historical exchange rates for a specific date |

## Setup Instructions
1. **Clone the Repository**
   ```sh
   git clone https://github.com/your-repo/currency-converter.git
   cd currency-converter
   ```

2. **Run the Backend**
   - Navigate to the backend folder
   - Start the Spring Boot server
   ```sh
   mvn spring-boot:run
   ```
   - The server should run on `http://localhost:8080`

3. **Run the Frontend**
   - Open `index.html` in a browser
   - Make sure the backend is running

## Usage
1. Select a base currency from the dropdown menu.
2. Click the "Get Exchange Rates" button.
3. The latest exchange rates will be displayed.

## Example API Response (Latest Exchange Rates)
```json
{
    "success": true,
    "timestamp": 1743278464,
    "source": "EUR",
    "quotes": {
        "EURAED": 3.998302,
        "EURAFN": 76.426194,
        "EURALL": 99.362051
    }
}
```

## Notes
- Ensure the backend is running before using the frontend.
- If encountering CORS issues, configure CORS settings in the Spring Boot backend.
- API requires an internet connection to fetch live exchange rates.

## Future Improvements
- Add support for more currencies.
- Implement historical exchange rate charts.
- Optimize UI with animations and better user experience.

---
Developed by **Falade Ayomide** 🚀

