package mohammad.karimi.mohammadmahdi.gholamreza.dunifooddatabase.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_food"  )

data class Food(


    val txtSubject :String ,

    val txtPrice :String ,

    val txtDistance :String ,

    val txtCity :String ,

 //   @ColumnInfo(name = "url")
    val urlImage :String ,


    val numOfRating :Int ,

    val rating :Float,


    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
)