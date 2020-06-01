# Distance Calculator

Read customers from a file and filter and display that are with in the given coordinates using MVVM, Dagger, RxJava, etc.

# Project Structure

Project uses MVVM approach. Dagger2 is used for dependency injection, RxJava for reactive programming and other libraries. I
t has two modules **app** and **data**.

###### :app
Handles all the presentaion logic. It receieves data from **data** and draws on the views. It contains ViewModels and 
others android components.

###### :data
**data** reads the customers from file and passes it to presentaion. It also moldes the data so that views 
can use it. This module contains the distance [calculation](https://en.wikipedia.org/wiki/Great-circle_distance)  logic too.

# Requirements
- Android SDK 21 or above
- Android Studio 4
- Kotlin 

# How to run project?
- Clone project 
- Open Android Studio ([download](https://developer.android.com/studio/))
- Go to **File -> Open** navigate to the project folder and click **Open**  
- Once the gradle sync is finished, click **Run** (ctrl + R) either using emulator or android device

Please make sure that you have **customers.txt** in **assets** folder of **data** module. Otherwise app will throw an error.

|Initial Screen|Guest List|
|------------|-------------|
|![screenshot-1590970553371](https://user-images.githubusercontent.com/45944138/83366300-d324c880-a3b6-11ea-9488-f4e4908b853a.jpg)|![screenshot-1590970461983](https://user-images.githubusercontent.com/45944138/83366252-7aedc680-a3b6-11ea-8fdf-c9b9fdc6878f.jpg)|


|Error 1|Error 2|
|------------|-------------|
|![screenshot-1590970648318](https://user-images.githubusercontent.com/45944138/83366324-f3ed1e00-a3b6-11ea-8f2f-8642c127fce5.jpg)|![screenshot-1590970612546](https://user-images.githubusercontent.com/45944138/83366346-23038f80-a3b7-11ea-8ea7-06b7953f3bde.jpg)|

|Error 3|
|------------|
<img src="https://user-images.githubusercontent.com/45944138/83366425-a8873f80-a3b7-11ea-9f6f-a45fe529e04d.jpg" height="750" width="405">




