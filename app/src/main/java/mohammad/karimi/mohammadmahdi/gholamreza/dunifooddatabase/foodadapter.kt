package mohammad.karimi.mohammadmahdi.gholamreza.dunifooddatabase

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mohammad.karimi.mohammadmahdi.gholamreza.dunifooddatabase.room.Food

class foodadapter(val data: ArrayList<Food>, private val foodevent: Foodevents) :
    RecyclerView.Adapter<foodadapter.foodviewholder>() {


    inner class foodviewholder(itemView: View, val context: Context) :
        RecyclerView.ViewHolder(itemView) {

        val imgMain = itemView.findViewById<ImageView>(R.id.item_img_main)
        val txtSubject = itemView.findViewById<TextView>(R.id.item_txt_subject)
        val txtCity = itemView.findViewById<TextView>(R.id.item_txt_city)
        val txtPrice = itemView.findViewById<TextView>(R.id.item_txt_price)
        val txtDistance = itemView.findViewById<TextView>(R.id.item_txt_distance)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.item_rating_main)
        val txtRating = itemView.findViewById<TextView>(R.id.item_txt_rating)

        @SuppressLint("SetTextI18n")
        fun binddata(position: Int) {


            txtSubject.text = data[position].txtSubject
            txtCity.text = data[position].txtCity
            txtPrice.text = "$" + data[position].txtPrice + " vip"
            txtDistance.text = data[position].txtDistance + " miles from you"
            ratingBar.rating = data[position].rating
            txtRating.text = "(" + data[position].numOfRating.toString() + " Ratings)"

            Glide.with(context).load(data[position].urlImage)
                .into(imgMain)

            itemView.setOnLongClickListener {
                foodevent.onfoodlongcliced(data[adapterPosition], adapterPosition, itemView)
                true
            }

            itemView.setOnClickListener() {
                foodevent.onfoodcliced(data[adapterPosition])
                Toast.makeText(context, data[adapterPosition].id.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): foodviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return foodviewholder(view, parent.context)
    }


    override fun onBindViewHolder(holder: foodviewholder, position: Int) {

        holder.binddata(position)


    }


    override fun getItemCount(): Int {

        return data.size

    }

    fun refreshfood(listfood: ArrayList<Food>) {

        data.clear()
        data.addAll(listfood)
        notifyDataSetChanged()


    }
    /* fun addfood(newfood: Food) {
         data.add( data.size, newfood)
         notifyItemInserted(data.size)
     }*/

    fun removefood(oldfood: Food, position: Int) {
        data.remove(oldfood)
        notifyItemRemoved(position)

    }

    /* fun updatfood(food: Food, position: Int){

         data.set(position , food)
        notifyItemChanged(position)
     }*/

    fun setdata(newlist: ArrayList<Food>) {
        data.clear()
        data.addAll(newlist)
        notifyDataSetChanged()


    }


    interface Foodevents {






        fun onfoodlongcliced(oldfood: Food, position: Int, itemView: View) {}

        fun onfoodcliced(food: Food) {}


    }
}