package services;

import models.Repair;

import java.util.List;

public class RepairManagementService {
    private List<Repair> repairs;
    public RepairManagementService(List<Repair> repairs) {
        this.repairs = repairs;
    }
        public void addRepair(Repair repair) {
        repairs.add(repair);
    }
    public void updateRepair(Repair updatedRepair) {
        for (int i = 0; i < repairs.size(); i++) {
            Repair repair = repairs.get(i);
            if (repair.getId() == updatedRepair.getId()) {
                repairs.set(i, updatedRepair);
                return;
            }
        }
    }
    public void deleteRepair(Repair repair) {
        for (int i = 0; i < repairs.size(); i++) {
            Repair existingRepair = repairs.get(i);
            if (existingRepair.getId() == repair.getId()) {
                repairs.remove(i);
                return;
            }
        }
    }
    public List<Repair> getAllRepairs() {
        return repairs;
    }
    public Repair getRepairById(int repairId) {
        for (Repair repair : repairs) {
            if (repair.getId() == repairId) {
                return repair;
            }
        }
        return null;
    }
}
