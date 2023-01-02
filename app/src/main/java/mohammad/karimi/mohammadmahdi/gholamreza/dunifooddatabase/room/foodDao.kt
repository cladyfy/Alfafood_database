package mohammad.karimi.mohammadmahdi.gholamreza.dunifooddatabase.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface foodDao {

    @Insert
    fun insertfood(food: Food)

    @Insert
    fun insertallfood(data: List<Food>)



    @Update
    fun update(food: Food)


    @Delete
    fun delete(food: Food)

    @Query("select * from table_food  where txtSubject like '%'|| :sarching || '%' ")
    fun searchFood(sarching: String): List<Food>


    @Query("delete from table_food ")
    fun deletealldata()


    @Query("SELECT * FROM table_food  ")
    fun getallfood(): List<Food>




}