package com.kavinschool.pattern.examples;

import java.util.StringJoiner;

public class Dog {
    private String name;
    private int age;
    private String color;
    private String size;

    private Dog(final Builder builder) {
        name = builder.name;
        age = builder.age;
        color = builder.color;
        size = builder.size;
    }


    /**
     * {@code Dog} builder static inner class.
     */
    public static final class Builder {
        private String name;
        private int age;
        private String color;
        private String size;

        public Builder() {
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder withName(final String val) {
            name = val;
            return this;
        }

        /**
         * Sets the {@code age} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code age} to set
         * @return a reference to this Builder
         */
        public Builder withAge(final int val) {
            age = val;
            return this;
        }

        /**
         * Sets the {@code color} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code color} to set
         * @return a reference to this Builder
         */
        public Builder withColor(final String val) {
            color = val;
            return this;
        }

        /**
         * Sets the {@code size} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code size} to set
         * @return a reference to this Builder
         */
        public Builder withSize(final String val) {
            size = val;
            return this;
        }

        /**
         * Returns a {@code Dog} built from the parameters previously set.
         *
         * @return a {@code Dog} built with parameters of this {@code Dog.Builder}
         */
        public Dog build() {
            return new Dog(this);
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Dog.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("age=" + age)
                .add("color='" + color + "'")
                .add("size='" + size + "'")
                .toString();
    }
}
