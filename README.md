# TestMe
Submission for TradeMe tech test. A lightweight native Android app emulating the Trade Me yellow app. Currently doesn't hit the TM API but instead has randomly generated data accompanied by images of cute foxes.

![Screenshot_20240509_220935_TestMe](https://github.com/MikeyStewart/TestMe/assets/22163261/fdd737dd-49b7-4041-8e2f-f8ba16aa394d)

## Tech stack
- Kotlin, Coroutines, Flow - async operations/events
- Jetpack Compose - UI
- Compose Navigation (2.8.0-alpha08) - type safe navigation woohoo!
- Kotlin serialization - serialisation for Compose Navigation
- Coil - image loading
- Turbine - testing Kotlin's Flow
- Mockk - mocking

## Architecture
Inspired by Clean Architecture, SOLID principles, and Google's recommendations to facilitate separation of concerns, testability, and maintainability.
- MVVM
- UseCases - representing business logic
- Respository Pattern (TODO)

## Future improvements
- Dependency injection
- Network/data layer
	- Remote data source (TradeMe API)
	- Caching
