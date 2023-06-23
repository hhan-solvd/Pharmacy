package com.solvd.app.interfaces;

import com.solvd.app.models.Position;

public interface IPositionDAO extends IBaseDAO<Position> {

    double getSalaryByPositionName(String name);
}
