Feature: data can be retrieved when there is a feature and multiple scenarios
  Scenario: client makes call to GET /api/steps
    Given a feature file is loaded with a multiple scenarios
    When the client calls /api/steps
    Then the client receives status code of 200
    And the client receives response data

  Scenario: client makes call to GET /api/steps
    Given a feature file is loaded with a multiple scenarios
    When the client calls /api/steps
    Then the client receives status code of 200
    And the client receives data for nodes
    And the client receives data for edges