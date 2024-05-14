# TestMe
Submission for TradeMe tech test. A lightweight native Android app emulating the Trade Me yellow app. Currently hits Trade Me's non-prod environment, 'sandbox', which seems to be filled with dirty data.

![Screenshot_20240514_153011_TestMe](https://github.com/MikeyStewart/TestMe/assets/22163261/e3a972de-037e-46ea-aa8e-ec263c79e7cd)

## Installation steps
0. (If you don't have them already) you can quickly generate a consumer key and secret for sandbox [here](https://www.tmsandbox.co.nz/MyTradeMe/Api/RegisterNewApplication.aspx?)
1. Clone this repo
2. Add the following to your `local.properties` file, then rebuild & run the app!
```
consumerKey="<your_key>"
consumerSecret="<your_secret>"
```

## Tech stack
- Kotlin, Coroutines, Flow - async operations/events
- Jetpack Compose - UI
- Compose Navigation (2.8.0-alpha08) - type safe navigation woohoo!
- Kotlin serialization - serialisation for Compose Navigation
- Coil - image loading
- Ktor - network client
- Turbine - testing Kotlin's Flow
- Mockk - mocking

## Architecture
Inspired by SOLID principles, and Google's [recommendations](https://github.com/android/nowinandroid) to facilitate separation of concerns, testability, and maintainability.
- MVVM
- Domain layer (UseCases, Models) - representation of business logic
- Data layer - representation of the network/api
- Repository Pattern (TODO)

![Screenshot 2024-05-14 at 4 00 30 PM](https://github.com/MikeyStewart/TestMe/assets/22163261/db090319-3b4d-4eff-8115-cb400829257c)

## Future improvements
- Dependency injection
- Network/data layer
	- ⚠️ WIP ⚠️ Remote data source (TradeMe API)
	- Local data source / caching
- Shared UI component package for reuse across features/screens
- Implement Watchlist feature
- Implement My Trade Me feature
- Implement Listing Detail screen
- Improve list performance
- Fix bottom navigation bug
- Improve security of tokens
