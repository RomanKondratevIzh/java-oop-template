package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[]{};


    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {

        int countBooks = 0;
        SchoolBook[] booksArr;

        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                countBooks++;
            }
        }

        if (countBooks == 0) {
            return new SchoolBook[]{};
        }

        booksArr = new SchoolBook[countBooks];
        countBooks = 0;
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                booksArr[countBooks] = schoolBook;
                countBooks++;
            }
        }
        return booksArr;
    }

    @Override
    public boolean removeByName(String name) {
            int i = 0;
        if (findByName(name).length != 0){
            SchoolBook[] booksArr = new SchoolBook[count() - findByName(name).length];
            for (SchoolBook bookInArray : schoolBooks){
                if (bookInArray.getName() != name){
                    booksArr[i] = bookInArray;
                    i++;
                }
            }
            schoolBooks = booksArr;
            return true;
        } else return false;

    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
