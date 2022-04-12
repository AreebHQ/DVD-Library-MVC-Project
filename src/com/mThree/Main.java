package com.mThree;

import com.mThree.controller.DvdLibraryController;
import com.mThree.dao.DvdLibraryDao;
import com.mThree.dao.DvdLibraryDaoException;
import com.mThree.dao.DvdLibraryDaoImpl;
import com.mThree.ui.DvdLibraryView;
import com.mThree.ui.UserIO;
import com.mThree.ui.UserIOConsoleImpl;

public class Main {

    public static void main(String[] args) {
	// write your code here

        UserIO myIO = new UserIOConsoleImpl();
        DvdLibraryView myView = new DvdLibraryView(myIO);
        DvdLibraryDao myDao = null;
        try { myDao = new DvdLibraryDaoImpl(); }
        catch (DvdLibraryDaoException e) { e.printStackTrace(); }
        DvdLibraryController controller = new DvdLibraryController(myDao,myView);
        controller.run();
    }
}
