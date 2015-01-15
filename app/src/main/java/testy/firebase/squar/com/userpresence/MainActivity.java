package testy.firebase.squar.com.userpresence;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity {

  private Firebase mRef;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Firebase.setAndroidContext(this);
    mRef = new Firebase("http://mychat-beta.firebaseio.com/").child("mychat").child("presences").child("testy");
    setContentView(R.layout.activity_main);
  }

  @Override
  protected void onStart() {
    super.onStart();
    comeOnline();
  }

  @Override
  protected void onStop() {
    super.onStop();
    comeOffline();
  }

  private void comeOnline() {
    mRef.goOnline();
    mRef.onDisconnect().setValue(getOfflineValue());
    mRef.setValue(getOnlineValue());
  }

  private void comeOffline() {
    mRef.goOffline();
  }

  private Map<String, Object> getOfflineValue() {
    Map<String, Object> map = new HashMap<>();
    map.put("status", "offline");
    map.put("lastSeen", ServerValue.TIMESTAMP);
    return map;
  }

  private Map<String, Object> getOnlineValue() {
    Map<String, Object> map = new HashMap<>();
    map.put("status", "online");
    return map;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
