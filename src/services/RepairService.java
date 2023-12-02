package services;

import data.DataStorage;
import models.Repair;

import java.util.Date;
import java.util.List;

public class RepairService {
    private DataStorage dataStorage;
    public RepairService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }
    public void addRepair(Repair repair) {
        dataStorage.addRepair(repair);
        dataStorage.saveRepairsToTextFile();
    }
    public List<Repair> getAllRepairs() {
        return dataStorage.getRepairs();
    }
    public void deleteRepair(Repair repair) {
        dataStorage.getRepairs().remove(repair);
        dataStorage.saveRepairsToTextFile();
    }
    public Repair getRepairById(int repairId) {
        for (Repair repair : dataStorage.getRepairs()) {
            if (repair.getId() == repairId) {
                return repair;
            }
        }
        return null;
    }
    public void updateRepair(Repair updatedRepair) {
        List<Repair> repairs = dataStorage.getRepairs();
        for (int i = 0; i < repairs.size(); i++) {
            Repair repair = repairs.get(i);
            if (repair.getId() == updatedRepair.getId()) {
                repairs.set(i, updatedRepair);
                dataStorage.saveRepairsToTextFile();
                break;
            }
        }
    }
}
