This application should provide a REST API that satisfies the following requirements.

a) returns all users who have actually borrowed at least one book
b) returns all non-terminated users who have not currently borrowed anything
c) returns all users who have borrowed a book on a given date
d) returns all books borrowed by a given user in a given date range
e) returns all available (not borrowed) books

As input there are three csv files containing all users, books and who borrowed what and when

When you run the application, BootstrapData class executes and loads the csv file data into the H2 database which can be accessed through the H2 console. The url and 
credentials for H2 console are defined in the application.yml file
