package com.solvd.app.interfaces;

import com.solvd.app.models.Specialty;

public interface ISpecialtyDAO extends IBaseDAO<Specialty> {

    Specialty getSpecialtyByName(String name);
}
