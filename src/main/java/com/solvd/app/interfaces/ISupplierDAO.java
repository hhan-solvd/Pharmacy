package com.solvd.app.interfaces;

import com.solvd.app.models.Supplier;

public interface ISupplierDAO extends IBaseDAO<Supplier> {

    Supplier getSupplierByAddress(String address);
}
