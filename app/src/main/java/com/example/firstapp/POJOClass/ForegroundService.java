package com.example.firstapp.POJOClass;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.firstapp.R;

public class ForegroundService extends Service {
    private static final String TAG = "ForegroundService";
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "ForegroundServiceChannel";

    private Thread soundThread;
    private Ringtone notificationSound;
    private boolean isPlaying = false;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isPlaying) {
            startSound();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSound();
        removeNotification();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        NotificationChannel serviceChannel = new NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_HIGH
        );
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(serviceChannel);
        Log.d(TAG, "Notification channel created");
    }

    private Notification buildNotification() {
        Intent notificationIntent = new Intent(this, ForegroundService.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText("Music is playing")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .build();
    }

    private void showNotification() {
        Notification notification = buildNotification();
        startForeground(NOTIFICATION_ID, notification);
    }

    private void removeNotification() {
        stopForeground(true);
    }

    private void startSound() {
        soundThread = new Thread(() -> {
            try {
                isPlaying = true;
                Uri defaultRingtoneUri = Settings.System.DEFAULT_RINGTONE_URI;
                notificationSound = RingtoneManager.getRingtone(getApplicationContext(), defaultRingtoneUri);
                showNotification();
                while (isPlaying) {
                    notificationSound.play();
                    Thread.sleep(5000); // Sound plays every 5 seconds
                }
            } catch (InterruptedException e) {
                Log.d(TAG, "Sound thread interrupted");
                isPlaying = false;
            }
        });
        soundThread.start();
        Log.d(TAG, "Sound thread started");
    }

    private void stopSound() {
        Log.d(TAG, "Stopping sound");
        if (soundThread != null) {
            soundThread.interrupt(); // Interrupt the sound thread
            soundThread = null; // Set soundThread to null to indicate it's no longer running
            Log.d(TAG, "Sound thread interrupted");
        }
        if (notificationSound != null) {
            notificationSound.stop(); // Stop the notification sound
            notificationSound = null; // Set the notificationSound to null to release the reference
            Log.d(TAG, "Notification sound stopped");
        }
        removeNotification();
        isPlaying = false;
    }

}
