package com.booklisting.br.controller;

import com.booklisting.br.pojo.Book;
import com.booklisting.br.util.QueryUtils;

import java.util.List;

/**
 * Created by gilsonjuniorpro on 4/30/17.
 */

public class BookController {

    public static List<Book> listBooks(String title) {
        return QueryUtils.fetchBookData(title);
    }

}
