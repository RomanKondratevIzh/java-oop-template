package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = {};



    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            authors = Arrays.copyOf(authors, authors.length + 1);
            authors[authors.length - 1] = author;
            return true;
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author authorInArray : authors){
         if (authorInArray.getName().equals(name) && authorInArray.getLastName().equals(lastname)){
             return authorInArray;
         }
    }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        int i = 0;
        if (findByFullName(author.getName(), author.getLastName()) != null){
            Author[] authorsArr = new Author[authors.length - 1];
            for (Author authorInArray : authors){
                if (author.getLastName() != authorInArray.getLastName() || author.getName() != authorInArray.getName()){
                    authorsArr[i] = authorInArray;
                    i++;
                }
            }
            authors = authorsArr;
            return true;
        } else return false;

    }

    @Override
    public int count() {
        return authors.length;
    }


}
