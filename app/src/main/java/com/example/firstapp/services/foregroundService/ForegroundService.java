package com.example.firstapp.services.foregroundService;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.firstapp.R;
import com.example.firstapp.api.ApiActivity;

/** @noinspection BusyWait*/
public class ForegroundService extends Service {
    private static final String TAG = "ForegroundService";
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "ForegroundServiceChannel";

    private Handler handler;
    private Thread musicThread;
    private Runnable toastRunnable;
    private boolean isToastShowing = false;
    private volatile boolean isServiceRunning = true;
    private Ringtone notificationSound;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        handler = new Handler();
    }

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (hasPermissions()) {
            startForegroundService();
            return START_STICKY;
        } else {
            // If permissions are not granted, return START_NOT_STICKY
            return START_NOT_STICKY;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForegroundService();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private boolean hasPermissions() {
        // Check if the necessary permissions are granted
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.FOREGROUND_SERVICE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
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
        Intent notificationIntent = new Intent(this, ApiActivity.class);
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

    private void startPlayingMusic() {
        musicThread = new Thread(() -> {
            try {
                Uri defaultRingtoneUri = Settings.System.DEFAULT_RINGTONE_URI;
                notificationSound = RingtoneManager.getRingtone(getApplicationContext(), defaultRingtoneUri);
                showNotification();
                while (isServiceRunning) {
                    notificationSound.play();
                    Thread.sleep(5000); // Sound plays every 5 seconds
                }
            } catch (InterruptedException e) {
                Log.d(TAG, "Music thread interrupted");
            }
        });
        musicThread.start();
        Log.d(TAG, "Music thread started");
    }

    private void stopPlayingMusic() {
        //stopping the thread
        isServiceRunning = false; // Set the flag to stop the music thread
        if (musicThread != null) {
            musicThread.interrupt(); // Interrupt the music thread
            musicThread = null; // Set musicThread to null to indicate it's no longer running
            Log.d(TAG, "Music thread stopped");
        }
        // Ensure the Ringtone is stopped
        if (notificationSound != null) {
            notificationSound.stop();
            notificationSound = null; // Set to null to release resources
        }
    }

    private void startToast() {
        isToastShowing = true;
        toastRunnable = new Runnable() {
            @Override
            public void run() {
                if (isToastShowing) {
                    Toast.makeText(getApplicationContext(), "Thread two is running", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(this, 3000); // Display toast every 3 seconds
                }
            }
        };
        handler.post(toastRunnable);
        Log.d(TAG, "Toast thread started");
    }

    private void stopToast() {
        isToastShowing = false;
        handler.removeCallbacks(toastRunnable);
        Log.d(TAG, "Toast thread stopped");
    }

    private void startForegroundService() {
        startPlayingMusic();
        startToast();
    }

    private void stopForegroundService() {
        stopPlayingMusic();
        stopToast();
        removeNotification();
    }
}

/*
Below is an older implementation doesn't work on api 34 or above
 */

//package com.example.firstapp.services.foregroundService;
//
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Intent;
//import android.media.Ringtone;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Handler;
//import android.os.IBinder;
//import android.provider.Settings;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.core.app.NotificationCompat;
//
//import com.example.firstapp.R;
//import com.example.firstapp.api.ApiActivity;
//
///** @noinspection ALL*/
//public class ForegroundService extends Service {
//    private static final String TAG = "ForegroundService";
//    private static final int NOTIFICATION_ID = 1;
//    private static final String CHANNEL_ID = "ForegroundServiceChannel";
//
//    private Handler handler;
//    private Thread musicThread;
//    private Runnable toastRunnable;
//    private boolean isToastShowing = false;
//    private volatile boolean isServiceRunning = true;
//    private Ringtone notificationSound;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        createNotificationChannel();
//        handler = new Handler();
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        startPlayingMusic();
//        startToast();
//        return START_STICKY;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        stopForegroundService();
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    private void createNotificationChannel() {
//        NotificationChannel serviceChannel = new NotificationChannel(
//                CHANNEL_ID,
//                "Foreground Service Channel",
//                NotificationManager.IMPORTANCE_HIGH
//        );
//        NotificationManager manager = getSystemService(NotificationManager.class);
//        manager.createNotificationChannel(serviceChannel);
//        Log.d(TAG, "Notification channel created");
//    }
//
//    private Notification buildNotification() {
//        Intent notificationIntent = new Intent(this, ApiActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
//
//        return new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setContentTitle("Foreground Service")
//                .setContentText("Music is playing")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentIntent(pendingIntent)
//                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//                .build();
//    }
//
//    private void showNotification() {
//        Notification notification = buildNotification();
//        startForeground(NOTIFICATION_ID, notification);
//    }
//
//    private void removeNotification() {
//        stopForeground(true);
//    }
//
//    private void startPlayingMusic() {
//        musicThread = new Thread(() -> {
//            try {
//                Uri defaultRingtoneUri = Settings.System.DEFAULT_RINGTONE_URI;
//                notificationSound = RingtoneManager.getRingtone(getApplicationContext(), defaultRingtoneUri);
//                showNotification();
//                while (isServiceRunning) {
//                    notificationSound.play();
//                    Thread.sleep(5000); // Sound plays every 5 seconds
//                }
//            } catch (InterruptedException e) {
//                Log.d(TAG, "Music thread interrupted");
//            }
//        });
//        musicThread.start();
//        Log.d(TAG, "Music thread started");
//    }
//
//    private void stopPlayingMusic() {
//
//        //stopping the thread
//        isServiceRunning = false; // Set the flag to stop the music thread
//        if (musicThread != null) {
//            musicThread.interrupt(); // Interrupt the music thread
//            musicThread = null; // Set musicThread to null to indicate it's no longer running
//            Log.d(TAG, "Music thread stopped");
//        }
//        // Ensure the Ringtone is stopped
//        if (notificationSound != null) {
//            notificationSound.stop();
//            notificationSound = null; // Set to null to release resources
//        }
//    }
//
//    private void startToast() {
//        isToastShowing = true;
//        toastRunnable = new Runnable() {
//            @Override
//            public void run() {
//                if (isToastShowing) {
//                    Toast.makeText(getApplicationContext(), "Thread two is running", Toast.LENGTH_SHORT).show();
//                    handler.postDelayed(this, 3000); // Display toast every 3 seconds
//                }
//            }
//        };
//        handler.post(toastRunnable);
//        Log.d(TAG, "Toast thread started");
//    }
//
//    private void stopToast() {
//        isToastShowing = false;
//        handler.removeCallbacks(toastRunnable);
//        Log.d(TAG, "Toast thread stopped");
//    }
//
//    private void stopForegroundService() {
//        stopPlayingMusic();
//        stopToast();
//        removeNotification();
//    }
//}
//
//
