# MavenWeather - an application for displaying temperature and location based on IP address
MavenWeather is an app, which uses the user's IP address in order to obtain the current temperature in that location and the location itself, and automatically
opens a Google Maps tab with the user's location. The application is built with Java and Maven, using threads. It utilizes APIs to obtain the above-mentined information.
When the app is launched, a server stars running, waiting for users to join. A timer is automatically initiated, which will update the location and tempature every single hour.
