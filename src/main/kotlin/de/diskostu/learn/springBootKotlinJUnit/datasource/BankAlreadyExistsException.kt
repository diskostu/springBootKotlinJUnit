package de.diskostu.learn.springBootKotlinJUnit.datasource

class BankAlreadyExistsException(accountNumber: String) :
    IllegalArgumentException("The bank with the accountNumber $accountNumber already exists.")
