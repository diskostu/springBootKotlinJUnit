package de.diskostu.learn.`spring-boot-thenewboston`.datasource

class BankAlreadyExistsException(accountNumber: String) :
    IllegalArgumentException("The bank with the accountNumber $accountNumber already exists.")
