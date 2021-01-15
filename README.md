# Mastering Selenium WebDriver 3.0, Second Edition

<a href="https://www.packtpub.com/web-development/mastering-selenium-webdriver-30-second-edition?utm_source=GitHub&utm_medium=repository&utm_campaign=9781788299671"><img src="https://d1ldz4te4covpm.cloudfront.net/sites/default/files/imagecache/ppv4_main_book_cover/B07967_New_cover.png" alt="Mastering Selenium WebDriver 3.0 - Second Edition" height="256px" align="right"></a>

This is the code repository for [Mastering Selenium WebDriver 3.0 - Second Edition](https://www.packtpub.com/web-development/mastering-selenium-webdriver-30-second-edition?utm_source=GitHub&utm_medium=repository&utm_campaign=9781788299671), published by Packt.

**Boost the performance and reliability of your automated checks by mastering Selenium WebDriver**

## What is this book about?
The second edition of Mastering Selenium 3.0 WebDriver starts by showing you how to build your own Selenium framework with Maven. You'll then look at how you can solve the difficult problems that you will undoubtedly come across as you start using Selenium in an enterprise environment and learn how to produce the right feedback when failing. Next, you’ll explore common exceptions that you will come across as you use Selenium, the root causes of these exceptions, and how to fix them. Along the way, you’ll use Advanced User Interactions APIs, running any JavaScript you need through Selenium; and learn how to quickly spin up a Selenium Grid using Docker containers. In the concluding chapters, you‘ll work through a series of scenarios that demonstrate how to extend Selenium to work with external libraries and applications so that you can be sure you are using the right tool for the job.

This book covers the following exciting features: 
* Provide fast, useful feedback with screenshots
* Create extensible, well-composed page objects
* Utilize ChromeDriver and GeckoDriver in headless mode
* Leverage the full power of Advanced User Interactions APIs
* Use JavascriptExecutor to execute JavaScript snippets in the browser through Selenium

If you feel this book is for you, get your [copy](https://www.amazon.com/dp/1788299671) today!

<a href="https://www.packtpub.com/?utm_source=github&utm_medium=banner&utm_campaign=GitHubBanner"><img src="https://raw.githubusercontent.com/PacktPublishing/GitHub/master/GitHub.png" 
alt="https://www.packtpub.com/" border="5" /></a>


## Instructions and Navigations
All of the code is organized into folders. For example, Chapter02.

The code will look like the following:
```
public class BasicTest 
{
  private ExpectedCondition<Boolean> pageTitleStartsWith(final
  String searchString) 
  {
    return driver -> driver.getTitle().toLowerCase().
    startsWith(searchString.toLowerCase());
  }
}
```

**Following is what you need for this book:**
If you are a software tester or a developer with working experience in Selenium and competency with Java, who is interested in automation and are looking forward to taking the next step in their learning journey, then this is the book for you.

With the following software and hardware list you can run all code files present in the book (Chapter 1-15).

### Software and Hardware List

| Chapter | Software required                   | OS required                        |
| --------| ------------------------------------| -----------------------------------|
|  All    |Oracle JDK8                           | Windows, Mac OS X, and Linux (Any) |
|         | Maven 3                              | Windows, Mac OS X, and Linux (Any) |
|         | IntelliJ IDEA 2018                   | Windows, Mac OS X, and Linux (Any) |
|         | JMeter                               | Windows, Mac OS X, and Linux (Any) |
|         | Zed Attack Proxy                     | Windows, Mac OS X, and Linux (Any) |
|         | Docker                               | Windows, Mac OS X, and Linux (Any) |
|         | Mozilla Firefox                      | Windows, Mac OS X, and Linux (Any) |
|         |                                      | Windows, Mac OS X, and Linux (Any) |
|         |                                      | Windows, Mac OS X, and Linux (Any) |
|         |                                      | Windows, Mac OS X, and Linux (Any) |
|         | Google Chrome                        | Windows, Mac OS X, and Linux (Any) |
|         |                                      | Windows, Mac OS X, and Linux (Any) |
|         |                                      | Windows, Mac OS X, and Linux (Any) |




### Related products 
* Selenium Framework Design in Data-Driven Testing [[Packt]](https://www.packtpub.com/web-development/selenium-framework-design-data-driven-testing) [[Amazon]](https://www.amazon.in/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574)

* Selenium WebDriver 3 Practical Guide - Second Edition [[Packt]](https://www.packtpub.com/web-development/selenium-webdriver-3-practical-guide-second-edition) [[Amazon]](https://www.amazon.in/Selenium-WebDriver-Practical-Guide-automation-ebook/dp/B07BJKWB1J)

## Get to Know the Author
**Mark Collin**
has been working in the software industry since 2001. He started his career in the financial sector before moving into consultancy. He has an eclectic range of skills and proficiencies, which include test automation, security and penetration testing, and
performance testing. Mark is the creator and maintainer of driver-binary-downloadermaven-plugin, and the Query library used in this book. He is also a core contributor to jmeter-maven-plugin, a tool that allows you to run JMeter tests through Maven. He has also
contributed code to the core Selenium code base.


## Other books by the authors
* [Mastering Selenium WebDriver](https://www.packtpub.com/web-development/mastering-selenium-webdriver?utm_source=GitHub&utm_medium=repository&utm_campaign=9781784394356)

### Suggestions and Feedback
[Click here](https://docs.google.com/forms/d/e/1FAIpQLSdy7dATC6QmEL81FIUuymZ0Wy9vH1jHkvpY57OiMeKGqib_Ow/viewform) if you have any feedback or suggestions.
