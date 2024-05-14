# TestMe
Submission for TradeMe tech test. A lightweight native Android app emulating the Trade Me yellow app. Currently hits Trade Me's non-prod environment, 'sandbox'.

## Tech stack
- Kotlin, Coroutines, Flow - async operations/events
- Jetpack Compose - UI
- Compose Navigation (2.8.0-alpha08) - type safe navigation woohoo!
- Kotlin serialization - serialisation for Compose Navigation
- Coil - image loading
- Ktor - http client
- Turbine - testing Kotlin's Flow
- Mockk - mocking

## Architecture
Inspired by SOLID principles, and Google's [recommendations](https://github.com/android/nowinandroid) to facilitate separation of concerns, testability, and maintainability.
- MVVM
- Domain layer (UseCases, Models) - representation of business logic
- Data layer - representation of the network/api
- Repository Pattern (TODO)

## Future improvements
- Dependency injection
- Network/data layer
	- ⚠️ WIP ⚠️ Remote data source (TradeMe API)
	- Local data source / caching
- Move ListingCard into shared UI component package for reuse across features/screens
- Improve list performance
- Fix bottom navigation bug
- Improve security of tokens
- Plenty of room for general enhancements too!
