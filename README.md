## NoteList - Android Notes App
NoteList is a simple, fast Android application for creating and managing personal notes. It focuses on reliability, clean UI, and smooth offline-first usage so you can capture ideas anytime.

### Features
- **Create, edit, delete notes**: Quick, frictionless note management.
- **Search and filter**: Find notes instantly by title or content.
- **Sorting**: Order notes by date created, last edited, or title.
- **Offline-first**: Notes are stored locally on the device.
- **Light/Dark theme**: Respects system theme for comfortable reading.


### Tech stack
+ **Language**: Kotlin.
+ **UI framework**: Jetpack Compose.
+ **Architecture**: MVVM with Repository pattern.
+ **Async**: Kotlin Coroutines (structured concurrency for background work and smooth UI).
+ **State**: AndroidX Lifecycle ViewModel + reactive streams (LiveData/Flow).
+ **Navigation**: AndroidX Navigation.
+ **Dependency Injection**: Dagger 2 with modules and component scope management.
+ **Persistence**: Room with DAOs and migrations.


### Coroutines usage
* **Main-thread safety**: Runs I/O and long-running tasks off the UI thread to keep interactions smooth.
* **Structured concurrency**: Uses viewModelScope to automatically cancel ongoing work when screens are closed.
* **Cold streams**: Combines with LiveData/Flow for reactive updates to the UI without manual threading.


### Architecture
**MVVM** - ViewModel holds UI state and business logic; the Repository abstracts data sources; the UI layer observes state and renders it. *UI, domain logic, and data layers* are clearly separated to improve testability and maintainability.


### Dependencies
- **Kotlin Coroutines**: core, android for concurrency;
- **Jetpack Compose** for UI;
- **AndroidX Lifecycle** components;
- **Navigation Compose** for screen navigation;
- **Room**: runtime, ktx, compiler (KAPT) for SQLite persistence with Flow/suspend support;
- **Dagger 2**: dagger, compiler (KAPT) for DI.


### Installation
1. Clone the repository.
2. Open in Android Studio.
3. Sync Gradle dependencies.
4. Run on device or emulator.


### Screenshots
Below you can see the main interface and functionality of the NoteList app:

| Notes List | Note Details |
|------------| -------------|
| <img src="https://github.com/user-attachments/assets/2dd1a2ea-0dd5-4164-a6eb-e5828cb94a3f" width="250"/> | <img src="https://github.com/user-attachments/assets/53146e77-bf16-4373-a5b2-b155b40292d4" width="250"/> | 
| *Notes list with sorting*. | *Note view with color picker*. |


| Delete Confirmation | Undo Snackbar |
|---------------------|---------------|
| <img src="https://github.com/user-attachments/assets/5dcf982f-fe14-4210-b8e0-3b1109884f88" width="250"/> | <img src="https://github.com/user-attachments/assets/00aa706a-cf24-4619-8cee-96115d167438" width="250"/> |
| *Confirm delete dialog*. | *"Note deleted" with Undo*. |


### License
Personal project for educational and portfolio purposes.
