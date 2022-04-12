package com.mThree.ui;

import com.mThree.dto.Dvd;

import java.util.ArrayList;
import java.util.List;

public class DvdLibraryView {

    UserIO io;

    public DvdLibraryView (UserIO io)
    {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List of Dvds");
        io.print("2. Enter New Dvd");
        io.print("3. View Dvd");
        io.print("4. Remove Dvd");
        io.print("5. Update Dvd");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices",1,6);

    }

    public Dvd getNewDvdInfo(){

        String dvdTitle = io.readString("Enter DVD title.");
        String releaseDate = io.readString("Enter DVD release date.");
        String mpaaRating = io.readString("Enter DVD MPAA rating.");
        String directorName = io.readString("Enter director's name.");
        String studio = io.readString("Enter studio.");
        String userRating = io.readString("Enter DVD rating or comment.");

        Dvd currentDvd = new Dvd();
        currentDvd.setTitle(dvdTitle);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setDirectorName(directorName);
        currentDvd.setMPAARating(mpaaRating);
        currentDvd.setStudio(studio);
        currentDvd.setRating(userRating);

        return currentDvd;
    }

    public Dvd updateNewDvdInfo(Dvd prevDvd){

        String dvdTitle = io.readString("Enter DVD title.");
        String releaseDate = io.readString("Enter DVD release date.");
        String mpaaRating = io.readString("Enter DVD MPAA rating.");
        String directorName = io.readString("Enter director's name.");
        String studio = io.readString("Enter studio.");
        String userRating = io.readString("Enter DVD rating or comment.");

        if(dvdTitle.isEmpty()){dvdTitle = prevDvd.getTitle();}
        if(releaseDate.isEmpty()){releaseDate = prevDvd.getReleaseDate();}
        if(mpaaRating.isEmpty()){mpaaRating = prevDvd.getMPAARating();}
        if(directorName.isEmpty()){directorName = prevDvd.getDirectorName();}
        if(studio.isEmpty()){studio = prevDvd.getStudio();}
        if(userRating.isEmpty()){userRating = prevDvd.getRating();}

        prevDvd.setTitle(dvdTitle);
        prevDvd.setReleaseDate(releaseDate);
        prevDvd.setDirectorName(directorName);
        prevDvd.setMPAARating(mpaaRating);
        prevDvd.setStudio(studio);
        prevDvd.setRating(userRating);

        return prevDvd;
    }



    public void displayDvdList(ArrayList<Dvd> dvdList)
    {
        for(Dvd dvd : dvdList)
        {
            String dvdInfo = String.format("#%s \t %s \t %s \t %s \t %s \t %s \t %s",
                    dvd.getsNo(),
                    dvd.getTitle(),
                    dvd.getReleaseDate(),
                    dvd.getDirectorName(),
                    dvd.getMPAARating(),
                    dvd.getStudio(),
                    dvd.getRating());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDvd(ArrayList<Dvd> dvdList) {
       if(dvdList != null){
            for(Dvd dvd : dvdList)
            {
                String dvdInfo = String.format("#%s \t %s \t %s \t %s \t %s \t %s \t %s",
                        dvd.getsNo(),
                        dvd.getTitle(),
                        dvd.getReleaseDate(),
                        dvd.getDirectorName(),
                        dvd.getMPAARating(),
                        dvd.getStudio(),
                        dvd.getRating());
                io.print(dvdInfo); }
        }
        else
        {
            io.print("No such Dvd found.");
        }
        io.readString("Please hit enter to continue.");
    }


        public void displayRemoveResult(Dvd dvdRecord) {
        if(dvdRecord != null){
            io.print("Dvd successfully removed.");
        }else{
            io.print("No such Dvd found. Try with full dvd name");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayUpdateResult(Dvd dvdRecord) {
        if(dvdRecord != null){
            io.print("Dvd successfully Updated.");
        }else{
            io.print("No such Dvd found. Try with full dvd name");
        }
        io.readString("Please hit enter to continue.");
    }


    //Get User Choices
    public String getDvdTitleChoice() { return io.readString("Please enter Dvd title.");}
    public String getFullDvdTitleChoice() { return io.readString("Please enter full Dvd title.");}
    public int getDvdSnoChoice() { return io.readInt("Please enter Dvd S.no.");}
    public int getRedoChoice() { return io.readInt("Please enter 1 to continue, or any other value to go back to Main Menu");}
    // Display Banners
    public void displayCreateDvdBanner(){io.print("===========||Create Dvd||===========");}
    public void displayCreateSuccessBanner(){io.readString("===========||Success! Create Dvd||===========");}
    public void displayDisplayAllBanner() { io.print("=== Display All Dvds ===");}
    public void displayDisplayDvdBanner () { io.print("=== Display Dvd ==="); }
    public void displayUpdateDvdBanner () { io.print("=== Update Dvd ==="); }
    public void displayRemoveDvdBanner () { io.print("=== Remove Dvd ==="); }
    public void displayExitBanner() { io.print("Good Bye!!!");}
    public void displayUnknownCommandBanner() { io.print("Unknown Command!!!"); }
    public void displayDisplayErrorBanner (String msg) { io.print("=== Error ==="); io.print(msg); }



}
