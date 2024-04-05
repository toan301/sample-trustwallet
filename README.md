# Sample Mobile Framework

## Introduction:
Sample Mobile Test Framework to demonstrate Page Object Model with integration of Appium, TestNG, SpringBoot, Allure Report.

## Prerequisites
1. Install [Node.js](https://github.com/nodejs/help/wiki/Installation)
2. Set up the [Android SDK](https://developer.android.com/about/versions/14/setup-sdk).
3. Clone the project and navigate to its root directory.

### Steps for running test:
1. [Install Appium](https://appium.io/docs/en/2.0/quickstart/install/) and [UIAutomator2](https://appium.io/docs/en/2.0/quickstart/uiauto2-driver/)
    ```bash
    npm install -g appium
    appium driver install uiautomator2
    appium server -p 4725 -a 127.0.0.1 -pa /wd/hub
    ```
2. [Launch Android emulator](https://developer.android.com/studio/run/emulator-commandline) or using [GennyMotion](https://genymotion.com/)
3. Run Test

   command: ```mvn clean test```

### Config:
1. Appium config: src/test/resources/application-android.properties
 ```
deviceName=
platformVersion=
appActivity=.ui.app.AppActivity
appPackage=com.wallet.crypto.trustapp
app=trust-wallet.apk
platformName=Android
appiumLog=logs/appiumLog
fullReset=true
noReset=false
```

2. TestData src/test/resources/testdata.properties
```
passcode=123456
```
---

---