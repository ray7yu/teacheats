<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/ray7yu/laundr-portal">
    <img src="ReadmeIMG/logo.png" alt="Logo" width="300" height="100">
  </a>
   <h3 align="center">Teach Eats</h3>
  <p align="center">
  An android application, written in Kotlin, that uses the Clarifai API to identify images of fruits and hosts educational pages geared towards children.
    <br />
    ·
    <a href="https://github.com/ray7yu/laundr-portal/issues">Report Bug</a>
    ·
    <a href="https://github.com/ray7yu/laundr-portal/issues">Request Feature</a>
    .
  </p>
</p>



<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About](#about)
* [Built With](#built-with)
* [Getting Started](#getting-started)
* [Prerequisites](#prerequisites)
* [Usage](#usage)
* [License](#license)
* [Contact](#contact)
* [Acknowledgements](#acknowledgements)

<!-- ABOUT THE PROJECT --> 
## About

### Background

This project was developed as a part of the WiCSE @ UF Shadowing Program during Fall 2020. Under the guidance of a corporate mentor from Traject, I had to develop an application over the course of the semester. </br> </br>
Tasks: 
* Sketch wireframe to plan app structure
* Collaborate with student artist to develop the UI
* Create a timeline for app milestones
* Write reports outlining project progress
* Present project results to mentors and peers
* Research and learn about Kotlin and Android development
* Develop and test my android application
* Maintain time logs of my activities
* Host biweekly meetings with the mentor

### Overview
The goal of the project was to develop a mobile application that is designed to teach children about fruits. The app takes a photo of a fruit, and then sends the image to the Clarifai API. There, an image recognition model will identify the fruit, and then return a label. The app then uses the label to redirect the user to the fruit's respective page, with educational information about that fruit. Educational information will include color, pronunciation, and origin. UI/UX elements, such as vivid palettes and friendly interface, will be emphasized in order to accommodate the attention of young children.

### Built With
* [Kotlin](https://kotlinlang.org/)
* [Android Studio](https://developer.android.com/studio)
* [Clarifai](https://www.clarifai.com/)
* [JUnit](https://junit.org/junit4/)
* [Hamcrest](http://hamcrest.org/)
* [AWS S3](https://aws.amazon.com/s3/)

<!-- GETTING STARTED -->
## Getting Started

1. Install Android Studio.
2. Make Clarifai account, create a project, and make an API Key.
3. Install and Configure NDK and CMake in Android Studio.
   [Tutorial](https://developer.android.com/studio/projects/install-ndk)
4. Create NDK file in order to securely store API keys. 
   [Tutorial](https://medium.com/programming-lite/securing-api-keys-in-android-app-using-ndk-native-development-kit-7aaa6c0176be)

### Prerequisites
1. Android Studio
2. Clarifai Account and API Secret + ID
2. Android Device is required, as the app makes camera intents.

<!-- USAGE EXAMPLES -->
## Usage
 1. Take Picture of Fruit:
 <img src="ReadmeIMG/login.png" alt="Logo" width="600" height="300">
 
 2. After identification, view educational pages about respective fruit:
 <img src="ReadmeIMG/dashboard.png" alt="Logo" width="600" height="300">
  
##  Features
* Child-Friendly UI with hand-drawn images
* Image Recognition and Identification of 25+ Fruits
* Fruit origin images are publicly hosted on AWS S3 bucket
* Sends camera intent to take images
* Alert Dialog and halt app if no internet access
* Asynchronous code to send gRPC calls and load images from URLs
* App saves state if user leaves app
* Educational pages that teaches color, name, and origin for each fruit
* App has option to save image to phone gallery
* Loading photos in app is optimized by scaling the bitmap
* Cache is cleaned upon app reinitialization
* Tests to verify app has proper navigation

<!-- LICENSE -->
## License


<!-- CONTACT -->
## Contact
Raymond Yu (Developer) - wrg7yu@gmail.com 
[![LinkedIn][linkedin-shield]][linkedin-url-raymond]

Alana Jones (Artist) - alanaj829@gmail.com
[![LinkedIn][linkedin-shield]][linkedin-url-alana]


<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [University of Florida WiCSE](https://cise.ufl.edu/dept/ufwicse/)
* [Traject](https://bytraject.com/)


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url-raymond]: https://www.linkedin.com/in/ray7yu/
[linkedin-url-alana]: https://www.linkedin.com/in/alana-jones-329129187/


[issues-shield]: https://img.shields.io/github/issues/laundr-portal.svg?style=flat-square
[issues-url]: https://github.com/ray7yu/laundr-portal/issues
[product-screenshot]: images/screenshot.png
