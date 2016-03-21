package ro.pub.cs.systems.eim.lab05.startedserviceactivity.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import ro.pub.cs.systems.eim.lab05.startedserviceactivity.general.Constants;

public class StartedServiceBroadcastReceiver extends BroadcastReceiver {

    private TextView messageTextView;

    // TODO: exercise 8 - default constructor

    public StartedServiceBroadcastReceiver() {

    }

    public StartedServiceBroadcastReceiver(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: exercise 6 - get the action and the extra information from the intent
        // and set the text on the messageTextView
        String action = intent.getAction();
        String data = null;
        switch (action) {
            case Constants.ACTION_STRING: {
                String string = intent.getStringExtra(Constants.DATA);
                data = string;
                break;
            }
            case Constants.ACTION_INTEGER: {
                Integer integer = intent.getIntExtra(Constants.DATA, 0);
                data = integer.toString();
                break;
            }
            case Constants.ACTION_ARRAY_LIST: {
                ArrayList<String> strings = intent.getStringArrayListExtra(Constants.DATA);
                data = strings.toString();
                break;
            }
        }

        if (messageTextView != null) {
            messageTextView.setText(messageTextView.getText().toString() + "\n" + data);
        }

        // TODO: exercise 8 - restart the activity through an intent
        // if the messageTextView is not available
        else {
            Intent startedServiceActivityIntent = new Intent(context, StartedServiceActivity.class);
            startedServiceActivityIntent.putExtra(Constants.MESSAGE, data);
            startedServiceActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(startedServiceActivityIntent);
        }
    }

}
