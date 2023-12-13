package broadcast;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.JobIntentService;

public class AlarmHandler extends JobIntentService {
    private static final int JOB_ID = 1000;

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, AlarmHandler.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(Intent intent) {
        Log.d("AlarmHandler", "------0000----Alarm triggered");

        Intent serviceIntent = new Intent(getApplicationContext(), AlarmService.class);
        startService(serviceIntent);
    }
}
