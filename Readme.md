# Selenium Udemy

This is me messing around with Selenium.

### Useful info:

1) HtmlUnitDriver used to be in main Selenium dependency but has since been split. Hence why I've had to add it as a dependency in my pom.xml.

2) If using Firefox Version 45 or below, I can use FirefoxDriver(). However, if I am testing on Firefox Version 46 or above, I need to use another driver entitled 'Marionette'

![FireFoxDriver vs Marionette](readme_images/firefoxDriver_vs_Marionette.png)

And here is how I could set up my code:

![Marionette](readme_images/marionette_settings.png)

### Chromedriver and other drivers

If not specified, when I try to run tests that require ChromeDriver, (something) will look to my PATH for chromedriver.

When I use e.g. homebrew to install chromedriver, I don't have to specify (within my test repo) where chromedriver is stored - brew has handled that for me already. Yay.

But if I'm not using brew, I can specify where chromedriver is within my actual tests, for example:

![Example from chromedriver site](readme_images/chromedriver_example.png)

In this instance, despite me using brew on my laptop, I have used the maven surefire plugin and am setting a system property in my pom.xml file:

![My pom file](readme_images/pom_1.png)

![My pom file](readme_images/pom_2.png)

And the advantage of this is that I don't have to keep specifying chromedriver at the beginning of every individual test.

### Interrogation

Be careful with driver.getPageSource():

The source code that is downloaded is not exactly the same as the file that is on the actual server.

Different browsers will tailor the page source ever so slightly different:

![Be careful with get page source](readme_images/get_page_source_interrogation.png)

### findElement(By...) examples:

Below are some examples of the .findElement(By...) interrogation methods

1) .findElement(By.name)

![findElement(By.name)](readme_images/findElementByName.png)

2) .findElement(By.cssSelector)

![findElement(By.cssSelector)](readme_images/findElementByCssSelector.png)

3) .findElement(By.id)

![findElement(By.id)](readme_images/findElementByIdTest.png)

### CSS selectors - Introduction section and useful screenshots

1) Match attribute

![css_selectors_match_attribute](readme_images/css_selectors_match_attribute.png)

2) Match tag and id combined

![css_selectors_match_tag_and_id_combined](readme_images/css_selectors_match_tag_and_id_combined.png)

3) Match tag and class name combined

![css_selectors_match_tag_and_class_name_combined](readme_images/css_selectors_match_tag_and_class_name_combined.png)

4) Match tag

![css_selectors_match_tag](readme_images/css_selectors_match_tag.png)

5) CSS attribute matching - some possible options:

![css_selectors_attribute_matching](readme_images/css_selectors_attribute_matching.png)

6) CSS attribute matching - example 1:

![css_selectors_attribute_matching](readme_images/css_selectors_attribute_matching_example_1.png)

7) CSS attribute matching - example 2:

![css_selectors_attribute_matching](readme_images/css_selectors_attribute_matching_example_2.png)

8) CSS attribute matching - match any div tag which contains 'Div' in its class name (a partial match):

![css_selectors_partial_match_match_any_div_tag_which_contains_div_in_class_name](readme_images/css_selectors_partial_match_match_any_div_tag_which_contains_div_in_class_name.png)

9) CSS attribute matching - match any div tag where class name ends with 'Div':

![css_selectors_match_any_div_tag_where_class_name_ends_with_div](readme_images/css_selectors_match_any_div_tag_where_class_name_ends_with_div.png)

10) CSS attribute matching - match any div tag where the class name starts with 'spe':

![css_selectors_match_any_div_tag_where_class_name_starts_with_spe](readme_images/css_selectors_match_any_div_tag_where_class_name_starts_with_spe.png)

11) CSS attribute matching - match any div tags that has class name as 'nestedDiv' AND name as 'nestedDiv':

![css_selectors_match_any_div_tag_that_has_class_name_as_nestedDiv_and_name_as_nestedDiv](readme_images/css_selectors_match_any_div_tag_that_has_class_name_as_nestedDiv_and_name_as_nestedDiv.png)

12) CSS attribute matching - match any div tags that has class name as 'nestedDiv' OR any div tag that has an 'a' in its name:

![css_selectors_match_any_div_tag_that_has_class_name_as_nestedDiv_or_any_div_tag_that_has_a_in_its_name](readme_images/css_selectors_match_any_div_tag_that_has_class_name_as_nestedDiv_or_any_div_tag_that_has_a_in_its_name.png)