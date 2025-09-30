# KMP Pokémon Workshop

## Overview

This workshop demonstrates how to migrate existing iOS and Android Pokémon applications to a shared Kotlin Multiplatform Mobile (KMP) codebase.
It provides a step-by-step guide for converting platform-specific code to shared Kotlin code that can be used across both platforms.

## Project Structure

- **ios/** - Original iOS Pokémon application
- **android/** - Original Android Pokémon application
- **shared/** - KMP shared module containing:
    - Common business logic
    - Pokémon data models
    - Repository implementation
    - Shared resources (images and data)

## Prerequisites

- Android Studio or IntelliJ IDEA
- Xcode (for iOS development)
- JDK 11 or newer
- Kotlin 2.2 or newer

## Workshop Goals

- Learn KMP project structure and configuration
- Understand how to share code between iOS and Android
- Migrate platform-specific data models to common Kotlin code
- Implement shared business logic in Kotlin
- Access shared code from both platforms

## Step-by-Step and final version
The step-by-step and final version of the workshop can be found here:
https://github.com/happn-app/workshops/pull/5

## Key Benefits of KMP

- Reduce code duplication
- Maintain a single source of truth
- Improve development efficiency
- Share business logic while keeping platform-specific UI