package com.example.museumtickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //setting back button
        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity2.this, MainActivity.class));
            }
        });

        //setting image
        int toDisplay = R.drawable.ic_launcher_background;
        int text= 0;
        double kPrice=0.0, aPrice=0.0, sPrice=0.0;

        ImageView museumImg= findViewById(R.id.museumImage);
        TextView museumTitle= findViewById(R.id.museumTitle);

        EditText kidsPrice= (EditText) findViewById(R.id.kids);
        EditText adultsPrice= (EditText) findViewById(R.id.adults);
        EditText seniorPrice= (EditText) findViewById(R.id.senior);

        Bundle extras= getIntent().getExtras();
        String museum_name= extras.getString("MUSEUM");

        if(museum_name.compareTo("MOMA") == 0) {
            toDisplay=R.drawable.moma;
            text= R.string.MOMA;
            kPrice=12;
            aPrice=25;
            sPrice=15;
        }
        else if(museum_name.compareTo("MET") == 0) {
                toDisplay=R.drawable.met;
                text= R.string.MET;
                kPrice=14;
                aPrice=30;
                sPrice=17;
        }
        else if(museum_name.compareTo("Gugg") == 0) {
                toDisplay=R.drawable.soloman;
                text= R.string.GUGG;
                kPrice=10;
                aPrice=25;
                sPrice=13;
        }
        else if(museum_name.compareTo("NatHistory") == 0){
                toDisplay = R.drawable.american;
                text= R.string.NatHist;
                kPrice=15;
                aPrice=30;
                sPrice=10;
        }

        museumImg.setImageResource(toDisplay);
        museumTitle.setText(text);
        kidsPrice.setText(Double.toString(kPrice));
        adultsPrice.setText(Double.toString(aPrice));
        seniorPrice.setText(Double.toString(sPrice));

        //setting toast message
        Toast.makeText(this, Integer.toString(R.string.toast_message), Toast.LENGTH_SHORT).show();


        //opening web brower
        museumImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Bundle extras= getIntent().getExtras();
                String museum_name= extras.getString("MUSEUM");
                String url = null;

                if(museum_name.compareTo("MOMA") == 0) {
                    url="https://www.moma.org/";
                }
                else if(museum_name.compareTo("MET") == 0) {
                    url="https://www.metmuseum.org/";
                }
                else if(museum_name.compareTo("Gugg") == 0) {
                    url="https://www.guggenheim.org/";
                }
                else if(museum_name.compareTo("NatHistory") == 0){
                    url="https://www.amnh.org/";
                }

                Intent intent= new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter1);
        spinner3.setAdapter(adapter1);


        Button calc = (Button) findViewById(R.id.calculate);

        EditText ticketPrice= (EditText) findViewById(R.id.ticketPrice);
        EditText salesTax= (EditText) findViewById(R.id.salesTax);
        EditText totalPrice= (EditText) findViewById(R.id.ticketTotal);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calculations and display numbers
                double kid_price=Double.parseDouble(kidsPrice.getText().toString());
                double adult_price=Double.parseDouble(adultsPrice.getText().toString());
                double senior_price=Double.parseDouble(seniorPrice.getText().toString());

                int kids=Integer.parseInt(spinner1.getSelectedItem().toString());
                int adults=Integer.parseInt(spinner3.getSelectedItem().toString());
                int seniors=Integer.parseInt(spinner2.getSelectedItem().toString());

                double ticket_price=0.0, tax=0.0, total=0.0;

                ticket_price= (kids*kid_price)+(adults*adult_price)+(seniors*senior_price);
                tax=0.08875*ticket_price;
                total=ticket_price+tax;

                ticketPrice.setText(String.format("%.2f", ticket_price));
                salesTax.setText(String.format("%.2f", tax));
                totalPrice.setText(String.format("%.2f", total));

            }
        });

    }

}