package com.example.iman_efficientrestaurantbookingmanagementmobileapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectHotel extends AppCompatActivity {

    GridView gridView;
    String[] names={"Bait Al Bahr", "Beach Pavilion Restaurant", "Caramel Restaurant and Lounge Muscat",
            "Come Prima", "John Barry Bar", "Levels RoofTop", "Mosaic (Oman)", "Mumtaz Mahal", "Qureshi Bab Al Hind",
            "Asado South American Steakhouse"};
    int[] images= {R.drawable.bait, R.drawable.beach_pavilion, R.drawable.caramel, R.drawable.come,
            R.drawable.john_bary, R.drawable.level_rooftop, R.drawable.mosaic, R.drawable.mumtaz,
            R.drawable.qureshi, R.drawable.asado};

    String [] address= {"Shangri-La Al Husn Resort & Spa, Bandar Jissah", "Al Bustan Palace, Al Bustan",
            "Royal Opera House, Shati Al Qurum", "Crowne Plaza Hotel, Qurm", "Grand Hyatt Hotel, Hayy As Saruj",
            "A, Street 10, Muscat, Oman", "Crown Plaza Hotel OCEC, Muscat Hills", "Shati Al Qurum",
            "Hormuz Grand Hotel, Muscat Hills", "40 way, Muscat 112, Oman"};

    String [] dineType= {"SeaFood Fine Dinning", "Sea Food Fine Dinning", "International Bar", "Italian Fine Dinning",
            "European Bar", "International", "Asian Fine Dinning", "Indian Fine Dinning", "Indian Fine Dinning",
            "Steakhouse Fine Dinning"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hotel);

        gridView = (GridView) findViewById(R.id.gridView2);

        SelectHotel.CustomAdapter customAdapter = new SelectHotel.CustomAdapter(names, images, address, dineType, this);
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = names[position];
                int selectedImage = images[position];
                String selectedAddress = address[position];
                String selectedType = dineType[position];
                Intent intent = new Intent(SelectHotel.this, BookHotel.class);
                intent.putExtra("name2", selectedName);
                intent.putExtra("image2", selectedImage);
                intent.putExtra("address2", selectedAddress);
                intent.putExtra("dineType", selectedType);
                startActivity(intent);
                finish();
            }
        });

    }

    public class CustomAdapter extends BaseAdapter {

        private String[] imageNames;
        private int[] imagesPhoto;

        private String[] imageAddress;
        private String[] imageType;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(String[] imageNames, int[] imagesPhoto, String[] imageAddress, String[] imageType, Context context) {
            this.imageNames = imageNames;
            this.imagesPhoto = imagesPhoto;
            this.imageAddress= imageAddress;
            this.imageType= imageType;
            this.context = context;
            this.layoutInflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagesPhoto.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=layoutInflater.inflate(R.layout.row_item2, parent, false);
            }

            TextView tvName= convertView.findViewById(R.id.pic2_name);
            ImageView imageView= convertView.findViewById(R.id.image2_view);
            TextView tAddress= convertView.findViewById(R.id.pic2_address);
            TextView tType= convertView.findViewById(R.id.pic2_type);
            Button tButton= convertView.findViewById(R.id.pic2_button);

            tvName.setText(imageNames[position]);
            imageView.setImageResource(imagesPhoto[position]);
            tAddress.setText(imageAddress[position]);
            tType.setText(imageType[position]);
            tButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String h_name= tvName.getText().toString();
                    Intent intent = new Intent(SelectHotel.this, BookHotel.class);
                    intent.putExtra("Restaurant_name", h_name);
                    startActivity(intent);
                    finish();
                }
            });


            return convertView;
        }
    }

    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        super.onBackPressed();
        startActivity(new Intent(SelectHotel.this, HomePage.class));
        finish();
    }
}