package com.kavinschool.pattern.examples;

public class CreateUserDemo {
    public static void main(String[] args) {
        User user1 = new User("Kangs", "Pass");
        System.out.println("user1 = " + user1);
        User user2 = new User("John", "Doe", "johndoe1", "john123");
        System.out.println("user2 = " + user2);
        User user3 = new User("Kavin", "Kangs","KavinKangs1", "Kavin1234", "kangs@kavinschool.com",true);
        System.out.println("user3 = " + user3);
    }
}
