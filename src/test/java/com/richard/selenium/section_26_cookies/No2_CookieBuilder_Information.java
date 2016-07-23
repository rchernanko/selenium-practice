package com.richard.selenium.section_26_cookies;

import org.openqa.selenium.Cookie;

public class No2_CookieBuilder_Information {

    /*

    You can create a cookie by using the Cookie constructor:

    Cookie cookie = new Cookie(...).

    In this constructor, you can specify things like the cookie name, value, domain, path, expiry etc

    In terms of what these things are, have a look at the readme - these are all the columns in e.g chrome dev tools
    when you go to view a cookie within resources

    There is another way of creating a cookie and that is by using the Cookie Builder.

    One might argue that a builder pattern helps to make your code more readable, read here to find out more:
    https://www.javacodegeeks.com/2013/01/the-builder-pattern-in-practice.html

    See an example of cookie builder below:

     */

    public void createCookieUsingBuilder() {

        //Below is one way to create a cookie using the cookie builder - note the build() at the end:

        Cookie aNewCookie = new Cookie.Builder("myCookie", "myValue").build();

        //Here is another way - this allows me to build up my cookie a bit more slowly and it also makes it a little more
        //readable

        Cookie.Builder anotherNewCookie = new Cookie.Builder("myCookie", "myValue");

        anotherNewCookie.isSecure(true);

        anotherNewCookie.build();

        //I could also do it this way:

        Cookie.Builder yetAnotherNewCookie = new Cookie.Builder("myCookie", "myValue").
                                                isSecure(true).
                                                path("this_path").
                                                domain("this_domain");

        Cookie cookie = yetAnotherNewCookie.build();

    }
}
