package com.solvd.app.daofactories;

import com.solvd.app.interfaces.IBaseDAO;
import com.solvd.app.mybatis.*;

public class MyBatisFactory extends AbstractDAOFactory {

    @Override
    public IBaseDAO<?> getDAO(String modelName) {
        return switch (modelName.toLowerCase()) {
            case "customer" -> new CustomerDAO();
            case "doctor" -> new DoctorDAO();
            case "drugcategory" -> new DrugCategoryDAO();
            case "drug" -> new DrugDAO();
            case "inventory" -> new InventoryDAO();
            case "manufacturer" -> new ManufacturerDAO();
            case "person" -> new PersonDAO();
            case "pharmacy" -> new PharmacyDAO();
            case "position" -> new PositionDAO();
            case "prescription" -> new PrescriptionDAO();
            case "prescriptionitem" -> new PrescriptionItemDAO();
            case "specialty" -> new SpecialtyDAO();
            case "staff" -> new StaffDAO();
            case "supplier" -> new SupplierDAO();
            default -> throw new IllegalArgumentException("Invalid model name: " + modelName);
        };
    }
}
