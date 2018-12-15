Feature: Google Searching
  As a web surfer, I want to search Google, so that I can learn new things.

  Scenario: Simple Google search
    Given a web browser is on the Google page
    When the search phrase "panda" is entered
    Then results for "panda" are shown
    And the related results include "Panda Express"
    But the related results do not include "pandemonium"

  Scenario: Simple Google search with different search
    Given a web browser is on the Google page
    When the search phrase "panda" is entered
    Then results for "panda2" are shown

  Scenario: Simple Google search with different not included
    Given a web browser is on the Google page
    When the search phrase "panda" is entered
    Then results for "panda" are shown
    And the related results include "Kungfu Panda"
    But the related results do not include "pandemonium"

  Scenario: Simple Google search not on Google
    Given a web browser is not on the Google page
    Then there is no search input
