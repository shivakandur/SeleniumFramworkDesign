
@tag
Feature: purchase the order from ecommerce file
  I want to use this template for my feature file

  @Background
  Given I landed on ecommerce page

  @tag2
  Scenario Outline: Positive test of Submitting the order
    Given i logged in with username <name> and password <password>
    When I added product <productName> to cart
    and checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name                   | password    | product       |
      | mahesh.shiva@gmail.com |  Mahesh@123 | ZARA COAT 3   |
     
