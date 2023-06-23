package com.solvd.app.interfaces;

import com.solvd.app.models.Prescription;
import com.solvd.app.models.PrescriptionItem;

import java.util.List;

public interface IPrescriptionItemDAO extends IBaseDAO<PrescriptionItem> {

    List<PrescriptionItem> getItemsByPrescription(Prescription prescription);
}
