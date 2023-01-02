package mohammad.karimi.mohammadmahdi.gholamreza.dunifooddatabase

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mohammad.karimi.mohammadmahdi.gholamreza.dunifooddatabase.room.Food
import mohammad.karimi.mohammadmahdi.gholamreza.dunifooddatabase.databinding.ActivityMainBinding
import mohammad.karimi.mohammadmahdi.gholamreza.dunifooddatabase.databinding.DialogAddNewItemBinding
import mohammad.karimi.mohammadmahdi.gholamreza.dunifooddatabase.databinding.DialogDeleteItemBinding
import mohammad.karimi.mohammadmahdi.gholamreza.dunifooddatabase.databinding.DialogUpdateItemBinding
import mohammad.karimi.mohammadmahdi.gholamreza.dunifooddatabase.room.foodDao
import mohammad.karimi.mohammadmahdi.gholamreza.dunifooddatabase.room.mydatabase
import kotlin.math.log

const val URL_IMAG  =    "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food"
class MainActivity : AppCompatActivity(), foodadapter.Foodevents {
    lateinit var binding: ActivityMainBinding
    lateinit var myadapter: foodadapter
    lateinit var foodDao: foodDao
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        foodDao = mydatabase.getdatabase(this).foodDao

        binding.edtSearch.editText!!.addTextChangedListener {edittextinput ->
             searchondatabase(edittextinput!!.toString())

        }

        binding.btnMain.setOnClickListener {
            addfood()
        }

        val sharedPreferences = getSharedPreferences("food", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("first_run", true)) {


            firsrun()
            sharedPreferences.edit().putBoolean("first_run", false).apply()


        }

        showalldata()

       binding.btnRemoveallfood.setOnClickListener{
           removealldata()
       }




    }







    fun searchondatabase(edittextinput : String){
        if (edittextinput!!.isNotEmpty()){

            val searchdata = foodDao.searchFood(edittextinput)
            myadapter.setdata(ArrayList(searchdata))



        }else{
            val data = foodDao.getallfood()
            myadapter.setdata(ArrayList(data))


        }

    }

    private fun removealldata() {

        foodDao.deletealldata()
        showalldata()



    }

    private fun firsrun() {

       val foodList = listOf<Food>(
           Food(
               txtSubject  =       "Hamburger",
               txtPrice =          "15",
               txtDistance =         "3",
               txtCity =         "Isfahan, Iran",
               urlImage =          "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
               numOfRating =        20,
               rating =          4.5f

           ),
           Food(
               txtSubject  =   "Grilled fish",
               txtPrice =      "20",
               txtDistance =   "2.1",
               txtCity =       "Tehran, Iran",
               urlImage =      "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
               numOfRating =   10,
               rating =        4f
           ),
           Food(
             txtSubject  =   "Lasania",
             txtPrice =      "40",
             txtDistance =   "1.4",
             txtCity =       "Isfahan, Iran",
             urlImage =      "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
             numOfRating =   30,
             rating =        2f

           ),
           Food(
              txtSubject  =   "pizza",
              txtPrice =      "10",
              txtDistance =   "2.5",
              txtCity =       "Zahedan, Iran",
              urlImage =      "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
              numOfRating =   80,
              rating =        1.5f

           ),
           Food(
             txtSubject  =    "Sushi",
             txtPrice =       "20",
             txtDistance =    "3.2",
             txtCity =        "Mashhad, Iran",
             urlImage =       "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
             numOfRating =    200,
             rating =         3f

           ),
           Food(
              txtSubject  =   "Roasted Fish",
              txtPrice =      "40",
              txtDistance =   "3.7",
              txtCity =       "Jolfa, Iran",
              urlImage =      "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
              numOfRating =   50,
              rating =        3.5f

           ),
           Food(
               txtSubject  =   "Fried chicken",
               txtPrice =      "70",
               txtDistance =   "3.5",
               txtCity =       "NewYork, USA",
               urlImage =      "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
               numOfRating =   70,
               rating =        2.5f

           ),
           Food(
               txtSubject  =   "Vegetable salad",
               txtPrice =      "12",
               txtDistance =   "3.6",
               txtCity =       "Berlin, Germany",
               urlImage =      "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
               numOfRating =   40,
               rating =        4.5f

           ),
           Food(
               txtSubject  =   "Grilled chicken",
               txtPrice =      "10",
               txtDistance =   "3.7",
               txtCity =       "Beijing, China",
               urlImage =      "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
               numOfRating =   15,
               rating =        5f

           ),
           Food(
               txtSubject  =   "Baryooni",
               txtPrice =      "16",
               txtDistance =   "10",
               txtCity =       "Ilam, Iran",
               urlImage =      "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
               numOfRating =   28,
               rating =        4.5f

           ),
           Food(
               txtSubject  =   "Ghorme Sabzi",
               txtPrice =      "11.5",
               txtDistance =   "7.5",
               txtCity =       "Karaj, Iran",
               urlImage =      "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
               numOfRating =   27,
               rating =        5f

           ),
           Food(
              txtSubject  =   "Rice",
              txtPrice =      "12.5",
              txtDistance =   "2.4",
              txtCity =       "Shiraz, Iran",
              urlImage =      "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
              numOfRating =   35,
              rating =        2.5f

           ),
       )

        foodDao.insertallfood(foodList)





    }


    private fun showalldata() {
        val fooddata = foodDao.getallfood()
        myadapter = foodadapter(ArrayList(fooddata), this)
        binding.recycelerMain.adapter = myadapter
        binding.recycelerMain.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }



    override fun onfoodlongcliced(oldfood: Food, position: Int, itemView: View) {

        val namefood = oldfood.txtSubject
        val view = DialogDeleteItemBinding.inflate(layoutInflater)
        view.textView.text = "Delete $namefood anyway?"
        val dialog = AlertDialog.Builder(this).create()
        dialog.setView(view.root)
        dialog.setCancelable(true)
        dialog.show()
        dialog.setOnDismissListener {
            itemView.setBackgroundColor(ContextCompat.getColor(this , R.color.white))

        }
        itemView.setBackgroundColor(ContextCompat.getColor(this , R.color.blue))

        view.dialogBtnDeleteCancel.setOnClickListener {
            dialog.dismiss()
        }

        view.dialogBtnDeleteSure.setOnClickListener {
             myadapter.removefood(oldfood , position)
            foodDao.delete(oldfood)
            dialog.dismiss()


        }



    }

    override fun onfoodcliced(food: Food) {
           val view = DialogUpdateItemBinding.inflate(layoutInflater)
         val dialog = AlertDialog.Builder(this).create()
         dialog.setView(view.root)
         dialog.setCancelable(true)
         dialog.show()

         view.dialogEdtFoodCity.setText(food.txtCity)
         view.dialogEdtFoodPrice.setText(food.txtPrice)
         view.dialogEdtFoodDistance.setText(food.txtDistance)
         view.dialogEdtNameFood.setText(food.txtSubject)
         view.dialogUpdateBtnDone.setOnClickListener {

             if (
                 view.dialogEdtNameFood.length() > 0 &&
                 view.dialogEdtFoodCity.length() > 0 &&
                 view.dialogEdtFoodPrice.length() > 0 &&
                 view.dialogEdtFoodDistance.length() > 0
             ) {
                 val txtName = view.dialogEdtNameFood.text.toString()
                 val txtPrice = view.dialogEdtFoodPrice.text.toString()
                 val txtDistance = view.dialogEdtFoodDistance.text.toString()
                 val txtCity = view.dialogEdtFoodCity.text.toString()


                 val newfood = Food(txtName , txtPrice , txtDistance , txtCity , food.urlImage , food.numOfRating , food.rating , food.id)


                 foodDao.update(newfood)
                 myadapter.refreshfood(ArrayList(foodDao.getallfood()))

                 dialog.dismiss()
             }else Toast.makeText(this, "لطفا همه مقادیر را وارد کنید :)", Toast.LENGTH_SHORT).show()


         }
        view.dialogUpdateBtnCancel.setOnClickListener {

            dialog.dismiss()

        }




    }
    fun addfood(){

        val dialog = AlertDialog.Builder(this).create()

        val dialogBinding = DialogAddNewItemBinding.inflate(layoutInflater)
        dialog.setView(dialogBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        dialogBinding.dialogBtnDone.setOnClickListener {
            if (
                dialogBinding.dialogEdtNameFood.length() > 0 &&
                dialogBinding.dialogEdtFoodCity.length() > 0 &&
                dialogBinding.dialogEdtFoodPrice.length() > 0 &&
                dialogBinding.dialogEdtFoodDistance.length() > 0
            ) {

                val txtName = dialogBinding.dialogEdtNameFood.text.toString()
                val txtPrice = dialogBinding.dialogEdtFoodPrice.text.toString()
                val txtDistance = dialogBinding.dialogEdtFoodDistance.text.toString()
                val txtCity = dialogBinding.dialogEdtFoodCity.text.toString()
                val txtRatingNumber: Int = (1..150).random()
                val ratingBarStar: Float = (1..5).random().toFloat()
                val randomForUrl = (1 .. 12).random()
                val urlPic= URL_IMAG + randomForUrl.toString() + ".jpg"


                val newFood = Food(
                    txtName,
                    txtPrice,
                    txtDistance,
                    txtCity,
                    urlPic,
                    txtRatingNumber,
                    ratingBarStar

                )
                foodDao.insertfood(newFood)
                myadapter.refreshfood(ArrayList(foodDao.getallfood()))



                // myadapter.addfood(newFood )
                dialog.dismiss()
              binding.recycelerMain.scrollToPosition(myadapter.data.size - 1)



            } else {

                Toast.makeText(this, "لطفا همه مقادیر را وارد کنید :)", Toast.LENGTH_SHORT)
                    .show()

            }


        }
    }

 }



