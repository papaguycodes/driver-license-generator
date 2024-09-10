# U.S. Driver's License 🪪 Generator and Validator

This web application allows users to generate and validate U.S. driver's licenses using customizable inputs. It also supports generating PDF417 barcodes for the licenses and provides functionalities to download the barcode as an SVG file. The application includes animations, a dark/light mode switch, and state-specific license validation.

## Features

- **Generate U.S. Driver's License Numbers**: Based on user input and selected state.
- **Validate License Numbers**: Ensures the generated license number conforms to state-specific formats.
- **PDF417 Barcode Generation**: Encodes license data into a PDF417 barcode.
- **Download Barcode**: Save the barcode as an SVG file.
- **Light/Dark Mode Toggle**: Switch between light and dark themes for better user experience.
- **Responsive UI/UX**: Smooth interactions and animations for a sleek interface.
- **Offline Functionality**: Service worker support for offline use and faster loading.

## Installation

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/papaguycodes/driver-license-generator.git
    ```

2. **Navigate to the Project Directory**:
    ```bash
    cd driver-license-generator
    ```

3. **Backend Setup**:
   - Navigate to the `backend` directory.
   - Build and run the backend:
     ```bash
     ./gradlew  login run
     ```

4. **Frontend Setup**:
   - Open `frontend/index.html` in a web browser to view the application.

## Dependencies

- **Ktor**: For building the backend server in Kotlin.
- **PDF417.js**: A library for generating PDF417 barcodes.
- **Faker**: For generating fake data in the backend (optional).

## Service Workers

The app uses service workers to cache assets for offline functionality and faster loading. Make sure your browser supports service workers and HTTPS for full functionality.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
