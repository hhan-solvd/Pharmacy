package com.solvd.app.interfaces;

import com.solvd.app.models.DrugCategory;

public interface IDrugCategoryDAO extends IBaseDAO<DrugCategory> {

    DrugCategory getDrugCategoryByName(String name);
}
