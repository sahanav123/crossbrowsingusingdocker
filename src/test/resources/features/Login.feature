Feature: Login Functionality

@validlogin
Scenario: verify Login with valid credentials 
Given User enters the valid username and valid password
When User clicks on the Login button
Then User Should navigate myaccount page

@Invalidlogin
Scenario:  Verify Login with Invalid Credentilas
Given User enters the Invalid username and Invalid password
When User clicks on the Login button
Then User should see warning message