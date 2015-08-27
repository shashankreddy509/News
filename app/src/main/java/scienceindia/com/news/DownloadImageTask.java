package scienceindia.com.news;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by shashankreddy509 on 8/27/15.
 * This calls is used to download the image from a given url and displays the image
 * in the image view which it has been passed to this class
 */
class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    final ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String imageUrl = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(imageUrl).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        if(result!=null)
            bmImage.setImageBitmap(result);
    }
}
