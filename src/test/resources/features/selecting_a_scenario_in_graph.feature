Feature: steps are highlighted when a scenario is selected in the graph
  Scenario: user clicks on a scenario
    Given a feature file is loaded with multiple scenarios
    When the user selects a scenario
    Then the nodes are highlighted
    And the edges are highlighted
    And the non-selected nodes turn grey

  Scenario: user clicks out of a selected scenario
    Given graph with a selected scenario
    When the user deselects a scenario
    Then the nodes are highlighted as when initialised