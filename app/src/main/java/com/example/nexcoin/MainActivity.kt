package com.example.nexcoin
//6bfeace1-fceb-4188-8415-1cec0692b59d API KEY
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.nexcoin.databinding.ActivityMainBinding
import java.lang.invoke.MethodHandleInfo
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter
    private lateinit var data:ArrayList<Modal>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data=ArrayList<Modal>()
        apidata
        rvAdapter=RvAdapter(this,data)
        binding.Rv.layoutManager=LinearLayoutManager(this)
        binding.Rv.adapter=rvAdapter

        binding.search.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val filterdata=ArrayList<Modal>()
                for(item in data) {
                    if(item.name.lowercase(Locale.getDefault()).contains(s.toString().lowercase(Locale.getDefault()))) {
                        filterdata.add(item)
                    }

                }
                if(filterdata.isEmpty()) {
                    Toast.makeText(this@MainActivity,"No Data Available",Toast.LENGTH_SHORT).show()
                }
                else{
                    rvAdapter.changeData(filterdata)
                }
            }

        })


    }

    val apidata:Unit
        get() {
            val url="https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest"
            val queue=Volley.newRequestQueue(this)
            val jsonObjectRequest: JsonObjectRequest=

                @SuppressLint("NotifyDataSetChanged")
                object:JsonObjectRequest(Method.GET,url,null,Response.Listener {
                    response ->
                    binding.progressBar2.isVisible=false
                    try{
                        val dataArray=response.getJSONArray("data")
                        for(i in 0 until dataArray.length()) {
                            val dataObject=dataArray.getJSONObject(i)
                            val symbol=dataObject.getString("symbol")
                            val name=dataObject.getString("name")
                            val qoute=dataObject.getJSONObject("quote")
                            val USD=qoute.getJSONObject("USD")
                            val price=String.format("$ "+"%.2f",USD.getDouble("price"))

                            data.add(Modal(name,symbol,price))
                        }
                        rvAdapter.notifyDataSetChanged()
                    }
                    catch(e:Exception){
                        Toast.makeText(this,"Error Fetching Data",Toast.LENGTH_SHORT).show()
                    }

                } ,Response.ErrorListener{
                    Toast.makeText(this,"Error Fetching Data",Toast.LENGTH_SHORT).show()
                })

                {
                    override fun getHeaders(): Map<String, String> {
                        //return super.getHeaders()
                        val headers=HashMap<String,String>()
                        headers["X-CMC_PRO_API_KEY"]="6bfeace1-fceb-4188-8415-1cec0692b59d"
                        return headers
                        //X-CMC_PRO_API_KEY
                    }
                }
            queue.add(jsonObjectRequest)
        }
}