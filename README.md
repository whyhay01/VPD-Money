# VPD Money Assessment

Welcome to this assessment. 
# Getting started
Clone this repository and build the project

# Login Screen
![login screen](https://github.com/whyhay01/VPD-Money/blob/main/loginScreen.png)

Kindly login with any of these three credentials to home page
1. email: testing01@gmail.com password: 123456789
2. email: testing101@gmail.com password: 1234567890
3. email: testing@gmail.com password: qwertyui

![Home Page](https://github.com/whyhay01/VPD-Money/blob/main/homeScreen.png)
# Home Page
The home page has two CTAs
1. My Accounts: This is where you find list of user accounts and their details. On a click on any account, you will go to the transfer screen where you can transfer from the selected account to a new account.
2. My Transaction: Here you find list of transfer transactions

# About the project
This project is implemented using the Android MVVM clean architecture.
With the ui package holding all ui related component, the domain holding the app usecases and the data holding the app implementation

The app graceful handle errors including connectivity during login, account and amount verification during transfers

The project adopt hilt for dependency injection, Room for data persistence, firebase auth for authentication and other useful jetpack library.

* Walk with me to explore some of the screens of the app

![transfer screen](https://github.com/whyhay01/VPD-Money/blob/main/transferScreen.png)

![Account screen](https://github.com/whyhay01/VPD-Money/blob/main/accountScreen.png)

![Confirm Transfer](https://github.com/whyhay01/VPD-Money/blob/main/confirmTransfer.png)

![Transaction Screen](https://github.com/whyhay01/VPD-Money/blob/main/transactionScreen.png)
![Loading Transfer Screen](https://github.com/whyhay01/VPD-Money/blob/main/loadingTransfer.png)
![transfer success](https://github.com/whyhay01/VPD-Money/blob/main/transferSuccess.png)



