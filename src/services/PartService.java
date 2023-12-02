package services;

import models.Part;
import data.DataStorage;

import java.util.List;
import java.util.ArrayList;

public class PartService {
    private DataStorage dataStorage;
    public PartService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }
    public void addPart(Part part) {
        dataStorage.addPart(part);
    }

}
