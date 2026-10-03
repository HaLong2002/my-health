# MyHealth

MyHealth is a personal Android application for health and wellness management.

The project is built with modern Android development tools and focuses on managing health-related information, recipes, and grocery lists.

#### 🚧 Work in Progress
This project is currently under active development.

## Features

### Home
- Feature structure and navigation setup

### Recipes
- Feature structure and navigation setup

### Grocery Lists
- Display grocery lists
- Create, edit and delete grocery lists by name on the Grocery Lists screen

### Account
- Feature structure and navigation setup

## Screenshots

![Home](readme/screenshots/home.png)

<p align="center">
  <img src="readme/screenshots/home.png" width="200" alt="">
  <img src="readme/screenshots/recipes.png" width="200" alt="">
  <img src="readme/screenshots/groceries.png" width="200" alt="">
  <img src="readme/screenshots/grocery_list_deletion.png" width="200" alt="">
  <img src="readme/screenshots/account.png" width="200" alt="">
</p>

## Tech Stack

- **Kotlin** - Primary programming language
- **Jetpack Compose** - Declarative UI toolkit
- **Material 3** - UI components and theming
- **Navigation 3** - App navigation
- **MVVM** - Presentation architecture
- **Room** - Local database
- **Hilt** - Dependency injection
- **Coroutines** - Asynchronous programming
- **Flow** - Reactive data streams

## Architecture

The project follows a modular architecture with shared `core` modules and feature-based organization.

```
MyHealth
├── app
│   ├── account
│   ├── groceries
│   ├── home
│   └── recipes
│
└── core
    ├── common
    ├── database
    ├── data
    ├── designsystem
    ├── model
    └── navigation
```

The `app` module contains feature-specific UI and logic, while the `core` modules provide shared functionality across features.

## Project Status

MyHealth is an ongoing personal project

Current development focuses on:
- Building the structure, navigation and features for Health

## Purpose

This project is built as a personal learning project to explore modern Android development and apply concepts such as Jetpack Compose, Navigation 3, MVVM, Room, Hilt, Coroutines, Flow and modular architecture.