package com.example.project_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.example.project_3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Button buttonNotify;
    private ActivityMainBinding binding;

    private NotificationManager notificationManager;

    private static final String PRIMARY_CHANNEL_ID =
            "Primary Notification";

    public MainActivity(Button buttonNotify) {
        this.buttonNotify = buttonNotify;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        createChannel();
        Notification notification = createNotification();

        binding.buttonNotify.setOnClickListener(view -> {
            notificationManager.notify(0, notification);
        });
    }

    public void createChannel(){
        if (Build.VERSION.SDK_INT >> Build.VERSION.CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    PRIMARY_CHANNEL_ID, "Primary Notification",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(notificationChannel);
        }

    }

    public Notification createNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
          this, PRIMARY_CHANNEL_ID
        );

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("You`ve been notified!")
                .setContentText("This is your notification text.")
                .setContentIntent(getPendingIntent())
                .addAction(R.drawable.baseline_update_24, "Update", getPendingIntent())
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(
                                BitmapFactory.decodeResource(
                                        getResources(),
                                        R.drawable.image)
                ));
        return builder.build();
    }

    public PendingIntent getPendingIntent(){
        return PendingIntent.getActivities(
                this,
                0,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_IMMUTABLE
        );
    }
}






















