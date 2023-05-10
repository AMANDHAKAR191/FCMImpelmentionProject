package com.aman.fcmimplementationproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aman.fcmimplementationproject.fcmservice.FCMNotificationSender;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    Button buttonSendNotification;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSendNotification = findViewById(R.id.button_send_notification);

        FirebaseMessaging.getInstance().subscribeToTopic("UserName");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId = getString(R.string.default_notification_channel_id);
            String channelName = "Fcm notifications";
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }

//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                        if (!task.isSuccessful()) {
//                            return;
//                        }
//                        // Get new Instance ID token
//                        token = task.getResult().getToken();
//                        System.out.println("token: " + token);
//                        Log.e("TOKEN", token);
//
//
//
//                    }
//                });
        buttonSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FCMNotificationSender notificationSender =
                        new FCMNotificationSender("/topics/" + "UserName", "FCMessage test", "this is a test notification, Please ignore this notification.\nthis is a test notification, Please ignore this notification", MainActivity.this, MainActivity.this);
                notificationSender.sendNotification();
                Toast.makeText(MainActivity.this, "Notification Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
