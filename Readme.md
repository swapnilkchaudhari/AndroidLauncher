JIO Tesseract Assignment (2020-Oct-10)
============================================================

Task 1
============================================================
Create an application that can replace the home launcher in your smartphone.
The project should be split into two modules:
 SDK - All query functions for all data required in the UI/App.
 App - UI implementation for listing and launching applications.

SDK
------------
Launcher SDK will return all the launcher apps list which are currently installed on the phone. Here
are the main features of the SDK: 
1. List of apps containing following information - App name, Package name, Icon, Main Activity class
name, Version code, and Version name.
2. The list should be in ascending order based on app-name
3. Notify when app installs/uninstalls.

App
------------
UI implementation for listing and launching applications
1. List application data in a recycler view.
2. Launch the application when clicked on the app icon/list item. 
3. Add a search bar on top which will filter the application list based on the name.

Task 2
============================================================
Create an SDK using AIDL, which will return the phone orientation data. 
Ref: https://developer.android.com/guide/components/aidl
1. Use Sensor.TYPE_ROTATION_VECTOR. 
2. The sensor should provide the data at an interval of 8ms.
3. Multiple applications/clients can connect to service using the SDK a time to get the orientation
data.  
4. Create SDK sample application implementation showing IMU data in a Textview.

Other Requirements
 The SDK main class should use the Singleton Design pattern. 
 You can use java or kotlin language for the above tasks. 
 Finally need to push your code on GitHub or bitbucket and share the link with us.