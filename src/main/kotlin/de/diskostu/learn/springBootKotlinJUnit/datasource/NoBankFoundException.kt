package de.diskostu.learn.springBootKotlinJUnit.datasource

class NoBankFoundException(accountNumber: String) :
    NoSuchElementException("No bank data found for accountNumber $accountNumber")
