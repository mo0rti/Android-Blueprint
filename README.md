# Android Template Project

## Overview

This template serves as a foundational structure for building Android projects, designed with scalability, maintainability, and ease of use in mind. The architecture follows the MVI pattern, utilizes modularization, and separates concerns across layers.

## Table of Contents

- [Overview](#overview)
- [Table of Contents](#table-of-contents)
- [Module Structure](#module-structure)
  - [Core Modules](#core-modules)
  - [Feature Modules](#feature-modules)
  - [Components Module](#components-module)
- [Getting Started](#getting-started)
- [Contributing](#contributing)

## Modules
![Dependency graph](/docs/images/architecture-overall.png)

### Core Modules

#### `domain` Module
- **Description**: Contains the business logic of the application.
- **Responsibilities**:
  - Business rules and use cases
  - Application wide managers such as session management

#### `data` Module
- **Description**: Manages data transactions and transformations between the domain layer and data sources.
- **Responsibilities**:
  - Data repository implementations

#### `model` Module
- **Description**: Defines domain models used across the application.
- **Responsibilities**:
  - Domain models
  
#### `testing` Module
- **Description**: Provides utilities and frameworks for testing various app components.
- **Responsibilities**:
  - Test utilities
  - Mock implementations
  - Testing frameworks setup

#### `database` Module
- **Description**: Encapsulates all database operations and provides an abstraction layer over the SQLite database.
- **Responsibilities**:
  - Database entities
  - Database schema
  - DAOs (Data Access Objects)
  - Database migration logic

#### `network` Module
- **Description**: Handles all network requests and API communication.
- **Responsibilities**:
  - API service definitions
  - Network response handling
  - Network services
  - Interceptors and network configuration

#### `common` Module
- **Description**: Contains common utilities and shared resources that are used across the application.
- **Responsibilities**:
  - Common utilities
  - Shared resources like constants
  - Extension functions

### Feature Modules

Each feature module encapsulates all the necessary components for a specific application feature.

#### `auth-email` Module
- **Description**: Manages the email-based authentication flow, including sign-in and registration.
- **Responsibilities**:
  - Email login UI screens
  - Email registration logic
  - ViewModel for email authentication

#### `auth-pin` Module
- **Description**: Handles PIN-based user authentication, offering a secure method for user sign-in.
- **Responsibilities**:
  - PIN input UI components
  - ViewModel for PIN verification
  - Integration with authentication services

#### `onboarding` Module
- **Description**: Contains the onboarding flow for new users, introducing them to the app's features.
- **Responsibilities**:
  - Onboarding UI screens
  - User progress tracking through onboarding
  - ViewModel for onboarding logic

#### `account` Module
- **Description**: Includes functionality related to user account management and profile settings.
- **Responsibilities**:
  - User profile UI screens
  - Account settings management
  - ViewModel for account information

#### `welcome` Module
- **Description**: The initial landing screen of the application, showcasing the app's purpose and features.
- **Responsibilities**:
  - Welcome screen UI
  - Navigation to login or registration
  - ViewModel for welcome actions

### Components Module

#### `pinpad` Module
- **Description**: A reusable library for a PIN pad interface, enhancing security for numeric input.
- **Responsibilities**:
  - Customizable PIN pad UI component
  - Input validation and handling
  - Integration with security mechanisms

## Getting Started

1. **Clone the Repository**  
   `git clone https://github.com/mo0rti/android-blueprint-hilt-modularization.git`

2. **Open in Android Studio**
  - Navigate to the project directory and open it in Android Studio.

3. **Change the endpoints**
- Change the `BACKEND_URL`s to your proper location of end points in `secrets.defaults.properties`

4. **Change the application ID**
- Change the `applicationId = "com.mortitech.blueprint.template"` in `app\build.gradle` to your proper app id. If you change the application id, keep in mind that you need to change the firebase json as well and need to config the firebase for your application id. 

5. **Modify the Pipelines**
- Modify the `debug` pipeline in `pipelines\debug-pipeline.yml`. The rest of the pipelines can be modified later based on your needs.

6. **Change the App name and App icon**
- The App name and App icon can be found in the `app` module. To change the splash icon you can replace the `drawable\splash_screen_icon.png`.

7. **Sync Gradle & Build**
  - Sync the project with Gradle and run the build to ensure everything is set up correctly.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests. For major changes or new features, please open an issue first to discuss the proposal.
