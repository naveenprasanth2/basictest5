@Regression
Feature: Testing the login functionality of the application
Background: Initialize the browser
Given I initialize the browser

Scenario Outline: Tesing the RahulShetty Acacdemy website
Given I open the RahulShetty website
And I click on the loginButton
And I enter the credentials "<userid>" and "<password>"
When I click on login button
Then System throws error

Examples:
|userid				|password					|
|abc@gmail.com|test							|
|def@gmail.com|test122					|
