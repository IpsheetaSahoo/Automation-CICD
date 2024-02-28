
@tag
Feature: Purchase a product from the ecommerce webpage
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce page


  @Regression
  Scenario Outline: Positive Test of purchasing the order
    Given logged in with username <name> and password <password>
    When add product <productName> to cart
    And checkout the product <productName> and submit the order
    Then verify message "Thankyou for the order." is displayed on confirmation page

    Examples: 
      | name  									| 		password 	| productName  	|
      | reema.nayak12@gmail.com |     Abcd@1234 | ZARA COAT 3		|	
     
