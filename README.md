# Compose With Clean Arch

Sample Android app listing users from the [JSONPlaceholder API](https://jsonplaceholder.typicode.com) with Jetpack Compose and **Clean Architecture** per feature. Each feature splits into **presentation** (View/ViewModel), **domain** (entities, repository contracts, use cases), and **data** (models, mappers, data sources, repository implementations). Koin binds layers at compile time; settings theme updates flow through dedicated use cases and DataStore. Same JSONPlaceholder user list and detail screens as the MVVM sample, with stricter layer boundaries.

## Structure

```mermaid
flowchart TB
  subgraph presentation [presentation]
    UserRoute --> UserViewModel
    SettingRoute --> SettingViewModel
  end
  UserViewModel --> GetAllUsersUseCase
  SettingViewModel --> UpdateThemeUseCase
  subgraph domain [domain]
    GetAllUsersUseCase --> UserRepositoryPort[UserRepository interface]
    UpdateThemeUseCase --> SettingRepositoryPort[SettingRepository interface]
  end
  subgraph data [data]
    UserRepositoryPort --> UserRepositoryImpl
    UserRepositoryImpl --> UserDataSource
    UserDataSource --> HttpService
    SettingRepositoryPort --> SettingRepositoryImpl
    SettingRepositoryImpl --> SettingDataSource
    SettingDataSource --> DataStore
  end
  HttpService --> JSONPlaceholder[JSONPlaceholder API]
```

## Stack

| Technology | Version |
|------------|---------|
| Android Gradle Plugin | 9.4.0 |
| Kotlin | 2.2.10 |
| Compose BOM | 2026.02.01 |
| Koin | 4.2.2 |
| Navigation Compose | 2.9.3 |
| Ktor Client | 3.1.3 |
| DataStore | 1.1.7 |
| compileSdk / targetSdk | 37 |
| minSdk | 29 |
| JVM | 21 |

## Architecture

Feature-based Clean Architecture with Koin for dependency injection.

```
src/
├── common/
│   ├── constants/
│   ├── patterns/
│   └── services/
├── di/
├── design/theme/
├── routes/
└── features/
    ├── users/
    │   ├── domain/
    │   ├── data/
    │   └── presentation/
    └── settings/
        ├── domain/
        ├── data/
        └── presentation/
```

## ScreenShots

| Image 1 | Image 2 | Image 3 |
|----------|----------|----------|
| ![App Screenshot](assets/screenshots/screen-1.png) | ![App Screenshot](assets/screenshots/screen-2.png) | ![App Screenshot](assets/screenshots/screen-3.png) |

| Image 4 | Image 5 | Image 6 |
|----------|----------|----------|
| ![App Screenshot](assets/screenshots/screen-4.png) | ![App Screenshot](assets/screenshots/screen-5.png) | ![App Screenshot](assets/screenshots/screen-6.png) |

## Commits

```
git add . && git commit -m ":rocket: Initial commit." && git push
git add . && git commit -m ":building_construction: Added initial project architecture." && git push
git add . && git commit -m ":building_construction: Update project architecture." && git push
git add . && git commit -m ":memo: Updated project documentation." && git push
git add . && git commit -m ":memo: Updated code documentation." && git push
git add . && git commit -m ":white_check_mark: Added feature xyz." && git push
git add . && git commit -m ":wrench: Fixed xyz usage." && git push
git add . && git commit -m ":heavy_minus_sign: Removed xyz." && git push
git add . && git commit -m ":memo: Adjusted project imports." && git push
git add . && git commit -m ":arrow_up: Updated dependencies." && git push
git add . && git commit -m ":arrow_down: Removed dependencies." && git push
git add . && git commit -m ":wastebasket: Removed unused code." && git push
git add . && git commit -m ":test_tube: Added test functionality xyz." && git push
git add . && git commit -m ":construction_worker: Building in progress." && git push
git add . && git commit -m ":construction_worker: Added CI build system." && git push
```

## License

[MIT License](https://opensource.org/licenses/MIT)

Copyright (c) 2026 William Franco.
