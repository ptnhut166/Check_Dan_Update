package com.example.salon.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.salon.Helper.NavigationManager;
import com.example.salon.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Booking_sel_schedule extends AppCompatActivity {

    ImageButton back;
    RelativeLayout schedule0, schedule1, schedule2, schedule3;
    TextView time0, time1, time2, time3;
    user_class user_class;
    FirebaseUser user = user_class.mAuth.getCurrentUser();
    String userID = user.getUid();
    private void setScheduleOnClickListener(final RelativeLayout schedule, final TextView timeTextview) {
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện hành động khi nhấn vào schedule ở đây
                // Thêm logic kiểm tra sự lựa chọn nếu cần
                schedule.setBackgroundColor(getResources().getColor(R.color.grey));
                String time = timeTextview.getText().toString();
                user_class user_class = new user_class("", "", time, "", "","");
                // Sử dụng thông tin name và address ở đây (ví dụ: hiển thị hoặc xử lý thông tin)
                // Ví dụ:
                Toast.makeText(getApplicationContext(), "Selected Time: " + time, Toast.LENGTH_SHORT).show();


                FirebaseDatabase database = user_class.Database.getInstance();

                if (userID != null ) {

                    DatabaseReference bookingRef = database.getReference().child("userID").child(userID).child("InfoBooking").child("Time");
                    bookingRef.setValue(time);
                }
                Intent intent = new Intent(Booking_sel_schedule.this, booking_sel_staff.class);
                intent.putExtra("booking_info", user_class);
                startActivity(intent);
                finish(); // Đóng activity hiện tại nếu cần

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_sel_schedule);
        time0 = findViewById(R.id.tv_time_0);
        time1 = findViewById(R.id.tv_time_1);
        time2 = findViewById(R.id.tv_time_2);
        time3 = findViewById(R.id.tv_time_3);
        schedule0 = findViewById(R.id.schedule_0);
        schedule1 = findViewById(R.id.schedule_1);
        schedule2 = findViewById(R.id.schedule_2);
        schedule3 = findViewById(R.id.schedule_3);

        setScheduleOnClickListener(schedule0, time0);
        setScheduleOnClickListener(schedule1, time1);
        setScheduleOnClickListener(schedule2, time2);
        setScheduleOnClickListener(schedule3, time3);
        Intent intent = getIntent();
        user_class user_class = (user_class) intent.getSerializableExtra("booking_info");
// hoặc sử dụng getParcelableExtra nếu bạn đã sử dụng Parcelable


        back = findViewById(R.id.back_schedule);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Thực hiện hành động khi nhấn vào imageButton ở đây
                Intent intent = new Intent(Booking_sel_schedule.this, Booking_sel_locale.class);
                startActivity(intent);
                finish(); // Đóng activity hiện tại nếu cần
            }
        });
    }
}
