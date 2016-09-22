Feature: End to End for menu selection
  As a customer
  I want to select product
  So that I can get it to my home

  Scenario: menu selection for clothes
    Given customer is on home page
    When customer enter valid register details
      | firstName | lastName | birthdate | birth month | birthYear | emil           | company | password  | repassword |
      | Rajesh    | Patil    | 12        | May         | 1986      | test@yahoo.com | ABC     | password1 | password1  |

    Then user should login successfull
    When customer select fourth product  in list
    Then customer see same product details in shopping cart
    When  user select product  menu with "Clothing"
    Then user select the result it should  be added
    And selected product is same in shopping cart