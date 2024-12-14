# NexCoin

NexCoin is an Android application designed to track cryptocurrency prices in real-time. It allows users to view the latest market data for a wide range of cryptocurrencies. The app provides an easy-to-use interface to display current price data, market trends, and more.

## Features
- Real-time cryptocurrency price tracking.
- Support for multiple cryptocurrencies.
- Easy-to-use and intuitive user interface.
- Display of detailed market data including price, change, and percentage change.
- Fetches data from reliable APIs to ensure accuracy.
  
## Technologies Used
- **Android** (Java/Kotlin)
- **API** (Cryptocurrency data provider)
- **Retrofit** for API requests
- **JSON** for data parsing

## Dependencies
To run the application, you will need to add the following dependencies in your build.gradle files:

implementation 'com.squareup.retrofit2:retrofit:2.x.x'
implementation 'com.squareup.retrofit2:converter-gson:2.x.x'
implementation 'com.google.code.gson:gson:2.x.x'
implementation 'com.android.support:appcompat-v7:28.x.x'

## Acknowledgments
Thanks to CoinGecko API for providing reliable cryptocurrency data.
Android and Kotlin communities for providing learning resources.
