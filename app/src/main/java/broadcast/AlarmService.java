package broadcast;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.precioso.group_7_final_project.R;

public class AlarmService extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("","ALARM-----------ALARM-----------ALARM");
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.animal_bgm);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("","ALARM111-----------ALARM111-----------ALARM111");
        return null;
    }
}
