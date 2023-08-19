package com.sky.mapper;


import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    
    int insertBatch (List<DishFlavor> flavors);
    
    /**
     * 根據菜品id刪除口味
     *
     * @param dishId
     */
    @Delete ("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId (Long dishId);
}
