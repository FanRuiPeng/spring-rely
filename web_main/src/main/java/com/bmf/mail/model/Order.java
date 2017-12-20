package com.bmf.mail.model;

public class Order {
    private String orderNumber;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public Order setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Order setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public static class Customer {
        private String firstName, lastName, emailAddress;

        public String getFirstName() {
            return firstName;
        }

        public Customer setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public Customer setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public Customer setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }
    }
}
