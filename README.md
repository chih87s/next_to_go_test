# next_to_go_test

## Description
Created for Entain's Android challenge. Please note that this application has been tested on Android devices.

## Project Structure
The project follows the MVVM (Model-View-ViewModel) architecture.

## Process
1. **Read Task Documentation**: Understand the requirements and objectives.
2. **Check Response Data Structure**: Use Postman to inspect the structure of the response data.
3. **Design User Interface and Project Architecture**: Plan the UI and decide on the project architecture.
4. **Set Up Gradle File**: Configure the `build.gradle` file and use `buildSrc` to manage third-party libraries.
5. **Develop UIs**: Start developing the user interfaces.
6. **Implement Network Services and Dependency Injection**: Set up network services and incorporate dependency injection.
7. **Write Unit Tests**: Develop unit tests to ensure code quality and functionality.

## Technologies Used

- **Programming Languages**:
  - Kotlin

- **Frameworks and Libraries**:
  - Jetpack Compose

- **Tools and Platforms**:
  - Postman

- **Other Skills**:
  - RESTful API design
  - Unit testing
  - Kotlin coroutines
  - Dependency injection

## Future Improvements

1. **Error Handling**:
   - The application should handle errors from network requests. If the app sends a request to the server and the network is lost, it should display a dialog to show the error. After the network is reconnected, the page should be able to reload.

2. **Page Reload on Background to Foreground Transition**:
   - The app should send a request to reload the page when it transitions from the background to the foreground.

3. **UI Enhancements**:
   - Add more UI animations to make page refreshes smoother.

```bash
# Clone the repository
git clone https://github.com/yourusername/next_to_go_test.git

# Navigate to the project directory
cd next_to_go_test

# Open the project in Android Studio
# Make sure you have the necessary SDKs installed
