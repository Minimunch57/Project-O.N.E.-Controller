<p align="center">
    <img align="center" src="https://raw.githubusercontent.com/Minimunch57/Project-O.N.E.-Controller/main/src/main/java/club/minimunch57/images/icon.png">
    <br>
    <h1 align="center">Project O.N.E. - Controller</h1>
</p>
<p align="center">
    A Java application that allows for remote control of the Project O.N.E. system.
    <br><br>
    <img src="https://img.shields.io/badge/Project%20O.N.E.-3D556B?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAAoLQ9TAAAAFVBMVEUAAAArO0rA6+s0SVw5T2M9VWv///8cYhYEAAAAAXRSTlMAQObYZgAAAFBJREFUGNOFj0EKwEAMAnU0//9yDy3bZXuoOQhDEJV8a3OwSYMBy9iEtgR7AaYESzIOhRlKJJl8QJnnmgOQv48tdIYV6g8wocNb7Kh+jjvnXykdAi0mh4iNAAAAAElFTkSuQmCC">
    <img src="https://img.shields.io/badge/designed for-windows-blue?style=flat&logo=windows">
    <img src="https://img.shields.io/badge/version-1.3.1-blue" alt="Controller Application Version">
    <img src="https://img.shields.io/badge/language-java-F58219?logo=oracle">
    <a target="_blank" href="https://github.com/Minimunch57/Project-O.N.E.-Controller/blob/main/LICENSE"><img src="https://img.shields.io/badge/license-GPL%203.0-yellow" alt="GPL License v3.0"></a>
</p>

### Description
This application is a part of  __Project O.N.E.__

It allows for quick and easy control of the Project O.N.E. system by communicating with the Project O.N.E. server.
The application stays in the system tray, and all functions are done through the tray icon.

### Features
- Send and receive commands, messages, and broadcasts to/from the Project O.N.E. server and system.
- Quickly unlock the door by triple-clicking the tray icon.
- A small tray menu that displays with a right-click. Quickly unlock/lock the door, view the about, open the GUI, or exit the application.
- A hidden, compact GUI with quick command buttons and a small terminal.

### Images
- Tray Icon

>![trayIcon](https://github.com/Minimunch57/Project-O.N.E.-Controller/assets/43156167/29042fef-179c-4795-8b1b-5de945da909e)

- Tray Menu

>![trayMenu](https://github.com/Minimunch57/Project-O.N.E.-Controller/assets/43156167/01454a7a-4b83-4c9d-ad46-583f3ac7014b)

- GUI

>![GUI](https://github.com/Minimunch57/Project-O.N.E.-Controller/assets/43156167/7a1b9e4d-826b-40b1-940f-31f5f3087b47)

__The components of the GUI break down as follows:__
- Open Button
    - Unlocks the door by retracting the solenoid, allowing for the door to be opened.
- Close Button
    - Locks the door by releasing the solenoid, keeping the door closed.
- Unlock Button
    - Unlocks the Project O.N.E. system, allowing for anyone to open the door.
- Lock Button
    - Locks the Project O.N.E. system, thereby requiring biometric identity verification to open the door.
- Console/Terminal Functionality with a Text Window, Command Line Entry, and an Enter Button

Clicking anywhere outside of the GUI after it is opened will hide it.
This helps to make the GUI feel snappy, convenient, and easy-to-use.
It can be re-opened using the `Open Interface` button in the Tray Menu.

Please note that the images provided above may not be representative of the current product, as they may not be updated with each new version.

*For more information regarding how this application interfaces with the rest of the project, please reference the Project O.N.E. information repository.*

### Additional Information
The code for Project O.N.E. is currently designed to work with the one system that is currently in existence.
This means that, although this code and the design models are publicly available, nobody else will be able to fully deploy the project without making the necessary changes.
I plan to make adjustments throughout the subprojects to allow for this in the future.
Once completed, anyone will be able to deploy a Project O.N.E. system that can be controlled by this application.

The application connects to the Project O.N.E. server using an authentication token.
The token is pulled from the system environment variable `PROJECT_ONE_CONTROLLER`.
However, as described in the previous paragraph, none of this has any public use at the moment.
Once the server allows for it, this token will likely be used to connect to a specific Project O.N.E. system.
