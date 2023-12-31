package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealMapper {
    
    /**
     * 根据分类id查询套餐的数量
     *
     * @param id
     * @return
     */
    @Select ("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId (Long id);
    
    @AutoFill (OperationType.INSERT)
    int insertSetmeal (Setmeal setmeal);
    
    Page<SetmealVO> setmealPageQuery (SetmealPageQueryDTO setmealPageQueryDTO);
    
    @Select ("select * from setmeal where id = #{id}")
    SetmealVO selectBySetmealId (Long id);
    
    @AutoFill (OperationType.UPDATE)
    int updateSetmeal (Setmeal setmeal);
    
    
    /**
     * 根據套餐id查詢所有菜品
     *
     * @param setmealId
     * @return
     */
    //  select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = ?
    @Select ("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{id}")
    List<Dish> selectSetmealDishesBySetmealId (Long setmealId);
    
    
    int deleteSetmealBatch (List<Long> ids);
    
    
    List<Setmeal> list (Setmeal setmeal);
    
    /**
     * 根据套餐id查询菜品选项
     *
     * @param setmealId
     * @return
     */
    @Select ("""
            select sd.name, sd.copies, d.image, d.description
            from setmeal_dish sd
            left join dish d on sd.dish_id = d.id
            where sd.setmeal_id = #{setmealId}
            """)
    List<DishItemVO> getDishItemBySetmealId (Long setmealId);
    
    Integer countByMap (Map map);
}
