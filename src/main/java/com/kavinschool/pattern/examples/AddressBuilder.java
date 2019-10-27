package com.kavinschool.pattern.examples;

public class AddressBuilder {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    public AddressBuilder setAddressLine1(final String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public AddressBuilder setAddressLine2(final String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public AddressBuilder setCity(final String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder setState(final String state) {
        this.state = state;
        return this;
    }

    public AddressBuilder setZipCode(final String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public AddressBuilder setCountry(final String country) {
        this.country = country;
        return this;
    }

    public Address createAddress() {
        return new Address(addressLine1, addressLine2, city, state, zipCode, country);
    }
}