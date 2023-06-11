Feature: Admin privileges

    Scenario: An admin user connects to the API and try to get the vendors list
        Given His admin rights
        When He tries to get the vendors list
        Then He should have a success response

    Scenario: A normal user connects to the API and try to get the vendors list
        Given His normal rights
        When He tries to get the vendors list
        Then He should have a forbidden response
