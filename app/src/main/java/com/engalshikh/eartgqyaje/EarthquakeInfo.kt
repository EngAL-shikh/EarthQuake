package com.engalshikh.eartgqyaje

import android.app.Notification
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*


class EarthquakeInfo : Fragment() {
    private lateinit var earthViewModel: EarthViewModel

    private lateinit var earthquakeRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         earthViewModel =
            ViewModelProviders.of(this).get(EarthViewModel::class.java)






    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var e=EarthquakeFetchr()
        val earthquakeLiveData=e.feachData()
        earthquakeLiveData.observe(this, Observer {
            Log.d("test", "Response received: ${it}")
            earthquakeRecyclerView.adapter = EarthquakeAdapter(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
              val view=inflater.inflate(R.layout.fragment_earthquake_info, container, false)
                earthquakeRecyclerView=view.findViewById(R.id.earthquakeRecyclerView)
            earthquakeRecyclerView.layoutManager= LinearLayoutManager(context)

        return view
    }

    companion object {
        fun newInstance() = EarthquakeInfo()
    }
    private  inner class EarthquakeHolder(view: View) : RecyclerView.ViewHolder(view){

        val magButton=view.findViewById(R.id.Earthquake_digree) as TextView
        val titleTextView=view.findViewById(R.id.title) as TextView
        val destanceTextView=view.findViewById(R.id.destance) as TextView
        val dateTextView=view.findViewById(R.id.date) as TextView
        val timeTextView=view.findViewById(R.id.time) as TextView
        val geo=view.findViewById(R.id.card) as CardView
        private var longtude: Double = 0.0
        private var latitude: Double = 0.0
        var  earthquake=Earthquake()

      fun bind(earthquake: Earthquake){
          this.earthquake=earthquake
          magButton.text=earthquake.pro.mag.toString()
          titleTextView.text=earthquake.pro.title
          destanceTextView.text=earthquake.pro.place

          // get getCoordinates

          var coordinates:List<Double> =earthquake.geo.geos
          longtude = coordinates[0]
          latitude = coordinates[1]

          Log.d("long&lat",longtude.toString()+" -" +latitude.toString())
          geo.setOnClickListener {
              val uri = Uri.parse("geo:$latitude,$longtude")
              val intent = Intent(Intent.ACTION_VIEW).apply {
                  data = uri
              }
              requireActivity().startActivity(intent)
          }

          // change mag colore
        when{

            earthquake.pro.mag in 2.0..3.9
            -> magButton.setBackgroundResource(R.drawable.green)

            earthquake.pro.mag in 4.0..4.9
            -> magButton.setBackgroundResource(R.drawable.yallow)

            earthquake.pro.mag in 5.0..5.9
            -> magButton.setBackgroundResource(R.drawable.orang)

            earthquake.pro.mag in 6.0..10.0
            -> magButton.setBackgroundResource(R.drawable.red)

            else -> magButton.setBackgroundResource(R.drawable.white)

        }

          //get Time and convert

          var date =earthquake.pro.time
          val calendar = Calendar.getInstance()
         calendar.time = Date(date)

                  val stringDate = "${calendar.get(Calendar.YEAR)}-" +
                  "${calendar.get(Calendar.MONTH)}-" +
                  "${calendar.get(Calendar.DAY_OF_MONTH)}"

                   val stringTime = "${calendar.get(Calendar.HOUR_OF_DAY)}:" +
                  "${calendar.get(Calendar.MINUTE)}"

          dateTextView.text = stringDate
          timeTextView.text = stringTime

      }



    }



    inner class EarthquakeAdapter(var earthquakes: List<Earthquake>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view: View
            view = layoutInflater.inflate(
                R.layout.earthquake_list,
                parent, false
            )

            return EarthquakeHolder(view)

        }




        override fun getItemCount(): Int {

            return earthquakes.size

        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            val earthquake=earthquakes[position]
            if(holder is EarthquakeHolder)
                holder.bind(earthquake)


        }
    }





}