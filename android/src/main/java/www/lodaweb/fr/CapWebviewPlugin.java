package www.lodaweb.fr;

import android.util.Log;

public class CapWebviewPlugin {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}
