# Multithreaded HTTP Web Server in Java

A lightweight, custom multithreaded HTTP/1.1 web server built from scratch in Java using `ServerSocket` and multi-threading. It handles concurrent client requests, serves static assets, supports custom GET/POST API endpoints, and is ready for Docker deployment.

## Features
- **Multithreaded Architecture:** Handles multiple concurrent client connections efficiently using Java Threads.
- **Static File Serving:** Serves HTML pages, images (`.jpg`), and custom error pages (`404.html`, `500.html`).
- **GET & POST Request Handling:** Supports custom API endpoint routing and URL-encoded form submissions (saved to `post_data.txt`).
- **Docker Ready:** Includes a multi-stage `Dockerfile` for lightweight container deployment.
- **Unit Tested:** Comes with JUnit tests (`HttpServerGetTest`) to verify server responses.

## Tech Stack
- **Language:** Java 17
- **Build Tool:** Maven
- **Libraries:** Gson, JUnit 4 & JUnit 5
- **Containerization:** Docker

## Project Structure
```text
src/
 ├── main/
 │    ├── java/webserver/
 │    │    ├── api/          # Endpoint registry and route definitions
 │    │    ├── handler/      # Request routing, GET & POST handlers
 │    │    ├── util/         # HTTP response builder, MIME types, status codes
 │    │    └── Server.java   # Main server entry point (Port 8888)
 │    └── resources/         # Static HTML files, images, and error pages
 └── test/java/              # Automated JUnit tests
```

## How to Run Locally
1. Clone the repository:
   ```bash
   git clone https://github.com
   ```
2. Open the project in IntelliJ IDEA and let Maven download the dependencies.
3. Run `Server.java` (starts on port `8888`).
4. Open your browser and visit:
   - `http://localhost:8888/test.html`
   - `http://localhost:8888/url-encoded-form.html`

## Run with Docker
```bash
docker build -t java-webserver .
docker run -d -p 8888:8888 java-webserver
```
