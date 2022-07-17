# Android Chat Application
## Table of Contents
1. Introduction
2. Setup and User Guide
3. Contributors
4. Notes

***

# 1. Introduction

In this project we created android chat application.
The app allows the user to send texts messages to multiply contacts.

![android login](https://user-images.githubusercontent.com/92301625/179402613-5444689c-8016-4cd7-a247-5bc20350c43b.png)

***


# 2. Setup and User Guide
## 2.1 Setup

* For this project you need to clone 2 repositories: <br />
  * This android app:
  ```bash
  git clone https://github.com/noaziv55/android_application.git
  ```
  * Web API server:
  ```bash
  git clone https://github.com/noaziv55/web_development.git
  ```
* Setup the web API server according to the readme file in it's repository (part 4.1 - Setup)
* For running this android app after cloning repos:<br />
  * First, run the web API server <br />
  * Then for the android application:
    * Clean project and build. <br/>
    [if dependencies error occures, change a dependency version in `app/build.gradle` and build (it will make it resync)]
    * Create an emulator if you don't have one yet.
    * Run this android chat app on the emulator.
    
default server is set to: `http://10.0.2.2:7000`

## 2.2 User Guide

### How to enter the chat:
* To enter with a new user please click on the registration link below the fields.
* After you register:
  *  Click on the login link below.
  *  Fill again the required fileds to log in.

### How to use the chat:
* To add new chat:
  * Because we didn't finish the api communication you will need to add some new users to the app first according to the previous steps. 
  * Click on the "plus" icon on the bottom right.
  * Enter a contact by filling the required fields: the contact username and his server address.
* Sending messages: click on the "Enter your text here" bar, write your text and press on the send botton.

***

# 3. Contributors

* [Noam Gini](https://github.com/NoamGini)
* [Noa Ziv](https://github.com/noaziv55)

***

# 4. Notes

* By the approval of the teaching staff we uploded the missing files so you could run and check our android application.

***
