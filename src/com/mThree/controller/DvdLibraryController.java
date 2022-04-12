package com.mThree.controller;

import com.mThree.dao.DvdLibraryDao;
import com.mThree.dao.DvdLibraryDaoException;
import com.mThree.dao.DvdLibraryDaoImpl;
import com.mThree.dto.Dvd;
import com.mThree.ui.DvdLibraryView;
import java.util.ArrayList;


public class DvdLibraryController {

    private DvdLibraryView view;
    private DvdLibraryDao dao;


    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view)
    {
        this.dao = dao;
        this.view = view;
    }

    public void run()
    {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        createDvd();
                        break;
                    case 3:
                        viewDvd();
                        break;
                    case 4:
                        removeDvd();
                        break;
                    case 5:
                        updateDvd();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();

                } // end switch
            }// end while
            view.displayExitBanner();

        } catch (DvdLibraryDaoException e)
        {
            view.displayDisplayErrorBanner("Exception Error");
        }
    }

    private int getMenuSelection() {

        return view.printMenuAndGetSelection();
    }


    private void listDvds() throws DvdLibraryDaoException{
        view.displayDisplayAllBanner();
        ArrayList<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);

    }
    private void createDvd() throws DvdLibraryDaoException{
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
        if(view.getRedoChoice() == 1) {createDvd();}
    }
    private void viewDvd() throws DvdLibraryDaoException{
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        ArrayList<Dvd> dvd = dao.getDvd(dvdTitle);
        view.displayDvd(dvd);
    }
    private void removeDvd() throws DvdLibraryDaoException{
        view.displayRemoveDvdBanner();
        String dvdTitle = view.getFullDvdTitleChoice();
        ArrayList<Dvd> dvd = dao.getDvd(dvdTitle);
        if(dvd.isEmpty()){ view.displayRemoveResult(null);}
        else{ view.displayDvd(dvd);
        int dvdSno = view.getDvdSnoChoice();
        Dvd removeDvd = dao.removeDvd(dvd, dvdSno);
        view.displayRemoveResult(removeDvd);}
        if(view.getRedoChoice() == 1) {removeDvd();}
    }
    private void updateDvd() throws DvdLibraryDaoException{
        view.displayUpdateDvdBanner();
        String dvdTitle = view.getFullDvdTitleChoice();
        ArrayList<Dvd> dvd = dao.getDvd(dvdTitle);
        if(dvd.isEmpty()){ view.displayUpdateResult(null);}
        else{view.displayDvd(dvd);
        int dvdSno = view.getDvdSnoChoice();
        Dvd updatedDvd = dao.getSingleDvd(dvdTitle, dvdSno);
        view.updateNewDvdInfo(updatedDvd);
        dao.saveUpdate(updatedDvd);
        view.displayUpdateResult(updatedDvd);}
        if(view.getRedoChoice() == 1) {updateDvd();}
    }
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
}
