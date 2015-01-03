Feature: Date conversion

Scenario: Convert a specific date
  Given a user named 'Tim'
  When this user enters the date '2014-12-03 15:20:05' into the time conversion service
  Then the service returns a conversion hint with the message 'hello Tim: the date converted is Wed, Dec 3, 2014'