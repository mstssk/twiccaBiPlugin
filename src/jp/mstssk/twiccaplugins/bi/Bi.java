package jp.mstssk.twiccaplugins.bi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class Bi extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Intent intent = getIntent();

		if (intent == null) {
			finish();
			return;
		}

		String text = intent.getStringExtra(Intent.EXTRA_TEXT);
		String newText = getString(R.string.bi) + " RT @"
				+ intent.getStringExtra("user_screen_name") + " "
				+ (text != null ? text : "");
		intent.putExtra(Intent.EXTRA_TEXT, newText);
		if (intent.getAction().equals("jp.r246.twicca.ACTION_SHOW_TWEET")) {
			Intent webIntent = new Intent(Intent.ACTION_VIEW);
			webIntent.setData(webIntentUrl(newText, null));
			// intent.getStringExtra("id")
			startActivity(webIntent);
		} else {
			// jp.r246.twicca.ACTION_EDIT_TWEET
			setResult(RESULT_OK, intent);
		}
		finish();
	}

	private Uri webIntentUrl(String tweet, String inReplyToStatusId) {

		StringBuilder sb = new StringBuilder(
				"https://twitter.com/intent/tweet?");
		sb.append("text=");
		sb.append(Uri.encode(tweet));
		if (inReplyToStatusId != null) {
			sb.append("&in_reply_to=");
			sb.append(inReplyToStatusId);
		}
		String url = sb.toString();
		// Log.i("mstssk", url);
		return Uri.parse(url);

	}
}