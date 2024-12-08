package com.example.iman_efficientrestaurantbookingmanagementmobileapp;

import com.google.firebase.database.Exclude;

public class UserBooking {
    public String bookName;
    public String bookPhone;
    public String bookDate;
    public String bookTime;
    public String bookTable;
    public String bookHotelName;

    public String mKey;

    public UserBooking() {
    }

    public UserBooking(String bookName, String bookPhone, String bookDate, String bookTime, String bookTable, String bookHotelName) {
        this.bookName = bookName;
        this.bookPhone = bookPhone;
        this.bookDate = bookDate;
        this.bookTime = bookTime;
        this.bookTable = bookTable;
        this.bookHotelName = bookHotelName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPhone() {
        return bookPhone;
    }

    public void setBookPhone(String bookPhone) {
        this.bookPhone = bookPhone;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public String getBookTable() {
        return bookTable;
    }

    public void setBookTable(String bookTable) {
        this.bookTable = bookTable;
    }

    public String getBookHotelName() {
        return bookHotelName;
    }

    public void setBookHotelName(String bookHotelName) {
        this.bookHotelName = bookHotelName;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String Key) {
        mKey = Key;
    }
}
