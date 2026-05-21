# CarLog - Android Car Management App

CarLog is a comprehensive Android application designed for car owners to track maintenance, repairs, tuning projects, and expenses.

## Features
- **Vehicle Management**: Add, edit, and remove multiple vehicles.
- **Repair Tracking**: Log repairs, services, and tuning updates.
- **Expense Management**: Track costs of parts and labor.
- **History**: View a detailed history of everything done to your car.

## Architecture
The project follows the **MVVM (Model-View-ViewModel)** pattern with a **Repository** layer to ensure a clean separation of concerns and maintainability.

- **UI**: Activity and Fragments using Material Design 3.
- **ViewModel**: Handles UI logic and communicates with the Repository.
- **Repository**: Single source of truth for data, managing Room database.
- **Data (Room)**: Local SQLite database for persistent storage.

## Technologies
- **Language**: Java
- **Database**: Room
- **UI Framework**: Material Design 3
- **Navigation**: Jetpack Navigation Component
- **Architecture**: MVVM + Repository

## How to Run
1. Clone the repository.
2. Open in Android Studio (Ladybug or newer).
3. Sync Gradle and run on an emulator or physical device (Min SDK 24).

## Screenshots
*(Screenshots will be added as development progresses)*

## APK
*(APK link will be provided in final releases)*
