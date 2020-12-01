package com.example.justkotlin


/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat

/**
 * This app displays an order form to order coffee.
 */
open class MainActivity: AppCompatActivity() {
    var quantity:Int=2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //this method is called when increment button is called
    fun increment(view: View) {
        if(quantity==100)
        {  Toast.makeText(this,"you can't have more than 100 coffees",Toast.LENGTH_SHORT).show()
            return;
        }
        else{
        quantity = quantity + 1
        display(quantity)
        }
    }
    /**
     * This method is called when the order button is clicked.
     */
    fun submitOrder(view: View) {
        var n1:Int=5
        var namefield=findViewById<View>(R.id.name_field) as EditText
        var name=namefield.getText().toString() //we are calling getText method on namefield object which return type is editable so we are calling another method toString which return a string
        var whippedcreamcheckbox=findViewById<View>(R.id.whipped_cream_checkbox) as CheckBox
        var haswhippedcream=whippedcreamcheckbox.isChecked()
        var choclatecheckbox=findViewById<View>(R.id.chocolate_checkbox) as CheckBox
        var haschoclate = choclatecheckbox.isChecked()
        if(haswhippedcream and haschoclate){
           n1 = n1+3
        }else if(haschoclate){
            n1=n1+2
        } else if(haswhippedcream){
            n1=n1+1
        }
        else{
            n1
        }
        var price:Int = calculatePrice(quantity,n1)
        var priceMessage = createOrderSummary(name,quantity,haswhippedcream,haschoclate)+ price
        priceMessage= priceMessage + "\nThank you !"

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // only email apps should handle this
                //for intent object we are calling a method putextra
                putExtra(Intent.EXTRA_SUBJECT, "just kotlin order for "+ name)//for adding subject to the email we have add Extra_subject
                putExtra(Intent.EXTRA_TEXT,priceMessage)// for adding the body to the email we have to add extra_text
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
    }
    fun createOrderSummary(name:String,num:Int,haswhippedcream:Boolean,haschoclate:Boolean):String{
       return "Name = "+ name +"\nAdd whipped cream?"+ haswhippedcream +"\nAdd choclate?"+haschoclate+"\nquantity = "+ quantity +"\nTotal = $ "
    }
    //this method is called when decrement button is called
    fun decrement(view: View) {
        if(quantity==1)
        { Toast.makeText(this,"you can't have less than 1 coffee",Toast.LENGTH_SHORT).show()
            return;
        }
        quantity = quantity - 1
        display(quantity)
    }
    fun calculatePrice(num :Int,num2:Int):Int{
        return num*num2
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private fun display(number: Int) {
        val quantityTextView = findViewById(R.id.quantity_text_view) as TextView
        quantityTextView.setText("" + number)
    }



}