Feature: check notebook product selection feature
  As a customer
  I want to buy product
  So that I can get product delivary

  Scenario: Check shopping cart for notebook
    Given customer is on home page
    When customer enter valid register details
      | firstName | lastName | birthdate | birth month | birthYear | emil           | company | password  | repassword |
      | Rajesh    | Patil    | 12        | May         | 1986      | test@yahoo.com | ABC     | password1 | password1  |

    Then user should login successfull
    When customer select fourth product  in list
    Then customer see same product details in shopping cart


  Scenario: check menu selection for notebook

    Given customer is on home page
    When customer enter valid register details
      | firstName | lastName | birthdate | birth month | birthYear | emil           | company | password  | repassword |
      | Rajesh    | Patil    | 12        | May         | 1986      | test@yahoo.com | ABC     | password1 | password1  |

    Then user should login successfull
    And he select product with "Notebook" from menu
    When user select the second result it sjould be added


  Scenario:check complete functionality of buying Notebook

    Given customer is on home page
    When customer enter valid register details
      | firstName | lastName | birthdate | birth month | birthYear | emil           | company | password  | repassword |
      | Rajesh    | Patil    | 12        | May         | 1986      | test@yahoo.com | ABC     | password1 | password1  |

    Then user should login successfull
    And he select product with "Notebook" from menu
    And user select the fifth product it should  be added
    Then selected product is same as in  shopping cart
    When user enter checkout details as
      | city   | Address1   | Zip    | phoneNumber |
      | London | 96 Romford | IG11EL | 0987654345  |
    Then he proceed payment information
      | cardholderName | cardNumber   | month | year | cardCode |
      | Rajesh         | 876553468778 | 8     | 2018 | 675      |


  Scenario:Check compete functionality of buying Notebook with invalid checkout detail data

    Given customer is on home page
    When customer enter valid register details
      | firstName | lastName | birthdate | birth month | birthYear | emil           | company | password  | repassword |
      | Rajesh    | Patil    | 12        | May         | 1986      | test@yahoo.com | ABC     | password1 | password1  |

    Then user should login successfull
    And he select product with "Notebook" from menu
    And user select the fifth product it should  be added
    Then selected product is same as in  shopping cart
    When user enter checkout details as
      | city | Address1   | Zip    | phoneNumber |
      |      | 96 Romford | IG11EL |             |
    Then customer get error message enter required field

  @smoke
  Scenario:check complete functionality of buying Notebook with invalid payment information

    Given customer is on home page
    When customer enter valid register details
      | firstName | lastName | birthdate | birth month | birthYear | emil           | company | password  | repassword |
      | Rajesh    | Patil    | 12        | May         | 1986      | test@yahoo.com | ABC     | password1 | password1  |

    Then user should login successfull
    And he select product with "Notebook" from menu
    And user select the fifth product it should  be added
    Then selected product is same as in  shopping cart
    When user enter checkout details as
      | city   | Address1   | Zip    | phoneNumber |
      | London | 96 Romford | IG11EL | 0987654345  |
    Then he proceed payment information
      | cardholderName | cardNumber | month | year | cardCode |
      | Rajesh         |            | 8     | 2018 | 675      |
    Then customer get error message of invalid card number
