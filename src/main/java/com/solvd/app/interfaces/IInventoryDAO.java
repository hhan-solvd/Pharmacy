package com.solvd.app.interfaces;

import com.solvd.app.models.Inventory;

public interface IInventoryDAO extends IBaseDAO<Inventory> {

    int getInventoryQuantityByID(int id);
}
