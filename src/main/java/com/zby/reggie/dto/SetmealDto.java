package com.zby.reggie.dto;

import com.zby.reggie.entity.Setmeal;
import com.zby.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
