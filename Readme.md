# Selenium Udemy

This is me messing around with Selenium.

### Useful info:

1) HtmlUnitDriver used to be in main Selenium dependency but has since been split. Hence why I've had to add it as a dependency in my pom.xml.

2) If using Firefox Version 45 or below, I can use FirefoxDriver(). However, if I am testing on Firefox Version 46 or above, I need to use another driver entitled 'Marionette'

![FireFoxDriver vs Marionette](readme_images/firefoxDriver_vs_Marionette.png)