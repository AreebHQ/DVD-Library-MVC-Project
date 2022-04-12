package com.mThree.dao;

import com.mThree.dto.Dvd;
import java.util.ArrayList;


public interface DvdLibraryDao {
    Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException;
    ArrayList<Dvd> getAllDvds() throws DvdLibraryDaoException;
    ArrayList<Dvd>  getDvd(String title) throws DvdLibraryDaoException;
    Dvd removeDvd(ArrayList<Dvd>  dvd, int sno)throws DvdLibraryDaoException;
    Dvd getSingleDvd(String title, int sno) throws DvdLibraryDaoException;
    void saveUpdate(Dvd dvd)  throws DvdLibraryDaoException;
}
