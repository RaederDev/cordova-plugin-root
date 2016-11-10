package technology.raeder.root;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import eu.chainfire.libsuperuser.Shell;

public class Root extends CordovaPlugin {

    private final String CALL_RUN = "run";
    private final String CALL_CHECK = "isAvailable";

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals(CALL_CHECK)) {
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    final Boolean res = Shell.SU.available();
                    callbackContext.success(res.toString());
                }
            });
            return true;
        }

        if(action.equals(CALL_RUN)) {
            final String command = args.getString(0);
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    if(command == null || command.equals("")) {
                        callbackContext.error("Invalid command supplied");
                        return;
                    }
                    final List<String> result = Shell.SU.run(command);
                    if(result == null) {
                        callbackContext.error("Command did not execute successfully or root access denied");
                    } else {
                        callbackContext.success(new JSONArray(result));
                    }
                }
            });
            return true;
        }

        return false;
    }

}
