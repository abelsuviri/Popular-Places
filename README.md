# Four Squares Popular Places

This app allows the user to enter a city/town to check the popular places in the area.

This app has three screens.

In the first screen the user has to type the name of a city/town and choose one category from the dropdown list. When all the results are retrieved from the server the app will navigate automatically to the second screen.
At the second screen the user will see a list with all the places for the search. When the user clicks on one of the items of the lists then the third screen will be opened, displaying the place address.

### How to build the project

This project is built in Android Studio 3.3 Canay 2, gradle plugin 3.3.0-alpha02 and gradle 4.8. The target SDK is 28, so you may need to download all the required dependencies to be able to build the project. 

To use the app you have to enter your APP_ID and APP_KEY in the viewmodel module gradle. There are two build configuration fields where you have to enter those keys.

### How to run the tests

This project has unit and instrumentation tests. 

The unit test is covering the view model and data (97% lines coverage), and it is located in the viewmodel package. To run it just right click on the MainViewModelTest class and click on 'run'.

The intrumentation test is located in the app package. To run it you need a device or emulator. You have to right click on the SearchCitySteps class and click on 'run'.
