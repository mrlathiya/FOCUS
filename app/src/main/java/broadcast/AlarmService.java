package broadcast;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.precioso.group_7_final_project.R;
import com.precioso.group_7_final_project.StopActivity;

public class AlarmService extends Service {
    private static final int NOTIFICATION_ID = 1;
    private MediaPlayer mediaPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("AlarmHandler", "------11111----Alarm START");

        // Create the notification channel if API level is 26 or higher
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "007",
                    "Alarm Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        // Build the notification
        Intent stopIntent = new Intent(this, StopActivity.class);
        PendingIntent stopPendingIntent = PendingIntent.getActivity(this, 0, stopIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, "007")
                .setContentTitle("Alarm is playing")
                .setContentText("Tap to stop the alarm")
                .setSmallIcon(R.drawable.baseline_category_24)
                .setContentIntent(stopPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        // Start the foreground service with the notification
        startForeground(NOTIFICATION_ID, notification);

        // Start the alarm sound
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.animal_bgm);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}
