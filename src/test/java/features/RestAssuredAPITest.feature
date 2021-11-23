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

	@addPlace
	Scenario: Verify if place is being successfully added using AddPlaceAPI
	Given Add place payload
	When user calls "addPlaceAPI" with POST http Request
	Then the API call is success with status code "200"
	And "status" in response body is "OK"
	And "scope" in response body is "APP"