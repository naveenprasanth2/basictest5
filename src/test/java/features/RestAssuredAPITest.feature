@RestAssured
Feature: Test the getcourses api functionality
Background: Initialize the browser
Given I initialize the browsers
Scenario: Verify getCourses functionality is working
And I open the google website
And I enter all the required values
And create a spec builder
Then I get the values from the response
And I validate the values