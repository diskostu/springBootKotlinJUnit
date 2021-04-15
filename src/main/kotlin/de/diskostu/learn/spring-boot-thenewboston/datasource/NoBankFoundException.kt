package de.diskostu.learn.`spring-boot-thenewboston`.datasource


class NoBankFoundException(accountNumber: String) :
    NoSuchElementException("No bank data found for accountNumber $accountNumber")
