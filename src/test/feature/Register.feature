Feature: Register feature
  As a customer
  I want to register NopCommerce
  So that I can buy product

  Scenario: check register functionality
    Given customer is on home page
    When customer enter valid register details
      | firstName | lastName | birthdate | birth month | birthYear | emil           | company | password  | repassword |
      | Rajesh    | Patil    | 12        | May         | 1986      | test@yahoo.com | ABC     | password1 | password1  |

    Then user should login successfull


  Scenario: check register functionality for blank data
    Given customer is on home page
    When customer enter blank spaces in manadatory fields

      | firstName | lastName | birthdate | birth month | birthYear | emil           | company | password | repassword |
      | Rajesh    | Patil    |           | May         | 1986      | test@yahoo.com | ABC     |          | password1  |


    Then user should not login successfully

    Scenario: check register functionality for invalid data
      Given customer is on home page
      When customer enter invalid data in manadatory fields

        | firstName | lastName | birthdate | birth month | birthYear | emil           | company | password | repassword |
        | Rajesh    | Patil    |           | May         | 1986      | test@yahoo.com | ABC     |          | password1  |


      Then user should not login successfully



