package com.mThree.dao;

import com.mThree.dto.Dvd;

import java.io.*;
import java.util.*;

public class DvdLibraryDaoImpl implements DvdLibraryDao {

    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    Map<String,ArrayList<Dvd>> dvds = new HashMap<>();
    int counter = 000;

    public DvdLibraryDaoImpl() throws DvdLibraryDaoException{
        //load all data as soon as application starts
        loadLibrary();
    }

    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException{
        //setting dvd id counter
        dvd.setsNo(++counter);
        //adding dvd into map - if title already exist then add into the list
        if(dvds.containsKey(title))
        {
            dvds.get(title).add(dvd);
        } else {
            // if title doesnt not exist then create a new list and add list into map
            ArrayList<Dvd> newDvd = new ArrayList<>();
            newDvd.add(dvd);
            dvds.put(title,newDvd);
        }
        //saving dvds data to file
        writeLibrary();
       return dvd;
    }

    @Override
    public  ArrayList<Dvd> getAllDvds()  {
        //get all dvd object into a list and return the list of dvd objects
        ArrayList<Dvd> dvdList = new ArrayList<>();
        for(Map.Entry<String,ArrayList<Dvd>> entry : dvds.entrySet())
        {
            for(int i=0; i<entry.getValue().size(); i++)
            {
                dvdList.add(entry.getValue().get(i));
            }
        }
        return dvdList;
    }

    @Override
    public ArrayList<Dvd> getDvd(String title) throws DvdLibraryDaoException{
        // itterate through map vales to get the dvd object
        ArrayList<Dvd> dvdResult = new ArrayList<>();
        ArrayList<String> dvdKeys = new ArrayList<>(dvds.keySet());
        for(int i=0; i<dvdKeys.size(); i++)
        {
            if(dvdKeys.get(i).contains(title))
            {
                ArrayList<Dvd> dvdlist = new ArrayList<>(dvds.get(dvdKeys.get(i)));
                for(Dvd obj : dvdlist)
                {
                    dvdResult.add(obj);
                }
            }
        }
        return dvdResult;
    }

    @Override
    public Dvd removeDvd(ArrayList<Dvd> dvdList, int sNo) throws DvdLibraryDaoException{
        //remove the dvd object from list
        for(Dvd obj : dvdList)
        {
            if(obj.getsNo()==sNo)
            {
                  if(dvds.get(obj.getTitle()).remove(obj))
                  {
                      //update file
                      writeLibrary();
                       return obj;
                  }
            }
        }
        //update file
        writeLibrary();
        return null;
    }

    @Override
    public Dvd getSingleDvd(String title, int sNo) throws DvdLibraryDaoException {
       //get single dvd object from dvd list
        if(dvds.containsKey(title))
        {
            for(int i=0; i<dvds.get(title).size(); i++)
            {
                if(dvds.get(title).get(i).getsNo() == sNo)
                {
                    Dvd currentDvd = dvds.get(title).get(i);
                    return currentDvd;
                }
            }

        }
        //update file
        writeLibrary();
        return null;
    }
    @Override
    public void saveUpdate(Dvd dvd) throws DvdLibraryDaoException
    {
        writeLibrary();
    }

    private void loadLibrary() throws DvdLibraryDaoException
    {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e)
        {
            throw new DvdLibraryDaoException( "Could not load library data", e);
        }

        String currentLine;
        Dvd currentDvd;

        while (scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            if(!currentLine.isEmpty())
            {
                currentDvd = unmarshallDvd(currentLine);
                setDvdIdCounter(currentDvd);
                if(dvds.containsKey(currentDvd.getTitle()))
                {
                    dvds.get(currentDvd.getTitle()).add(currentDvd);
                } else {
                    ArrayList<Dvd> newDvd = new ArrayList<>();
                    newDvd.add(currentDvd);
                    dvds.put(currentDvd.getTitle(),newDvd);
                }
            }
        }
        scanner.close();
    }

    private void setDvdIdCounter(Dvd currentDvd) {
        counter = Math.max(counter,currentDvd.getsNo());
    }

    private String marshallDvd (Dvd dvd)
    {
        String dvdAsText = dvd.getsNo()+DELIMITER;
        dvdAsText+=dvd.getTitle()+DELIMITER;
        dvdAsText+=dvd.getReleaseDate()+DELIMITER;
        dvdAsText+=dvd.getDirectorName()+DELIMITER;
        dvdAsText+=dvd.getMPAARating()+DELIMITER;
        dvdAsText+=dvd.getStudio()+DELIMITER;
        dvdAsText+=dvd.getRating();

        return dvdAsText;
    }


    private Dvd unmarshallDvd(String dvdAsText) {
        String []dvdToken = dvdAsText.split(DELIMITER);

        Dvd dvdFromFile = new Dvd();
        dvdFromFile.setsNo(Integer.parseInt(dvdToken[0]));
        dvdFromFile.setTitle(dvdToken[1]);
        dvdFromFile.setReleaseDate(dvdToken[2]);
        dvdFromFile.setDirectorName(dvdToken[3]);
        dvdFromFile.setMPAARating(dvdToken[4]);
        dvdFromFile.setStudio(dvdToken[5]);
        dvdFromFile.setRating(dvdToken[6]);

        return dvdFromFile;

    }

    private void writeLibrary() throws DvdLibraryDaoException
    {
        PrintWriter out;

        try{
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e)
        {
            throw new DvdLibraryDaoException("Could not save Dvd data", e);
        }

        String dvdAsText;
        for(Map.Entry<String , ArrayList<Dvd>> entry : dvds.entrySet())
        {
            ArrayList<Dvd> dvdList = new ArrayList<>(entry.getValue());
            for(Dvd dvd : dvdList)
            {
                dvdAsText = marshallDvd(dvd);
                out.println(dvdAsText);
                out.flush();
            }
        }
        out.close();
    }


}
