package com.gaurav.krushimitra;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.RequestQueue;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;


public class DiagnosisActivity extends AppCompatActivity {
    Button selectButton;
    ImageView imageView;
    TextView textView,textView1;
    LinearLayout linearLayout;
    FirebaseFirestore firebaseFirestore;
    private RequestQueue requestQueue;
    public int RESULT_LOAD_IMAGE = 1;


    String ans="";
    protected Interpreter tflite;

    private MappedByteBuffer loadModelFile(Activity activity) throws IOException {
        AssetFileDescriptor fileDescriptor = activity.getAssets().openFd("disease_model.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        if (Build.VERSION.SDK_INT >= 23) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }

        selectButton = findViewById(R.id.selectButton);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView1=findViewById(R.id.textView1);

        firebaseFirestore=FirebaseFirestore.getInstance();
        linearLayout=findViewById(R.id.linear1);
        textView.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);


        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, ""), 100);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_CANCELED) {
            if (requestCode == 100) {
                Uri imageUri = data.getData();
                try {
                    linearLayout.setVisibility(View.VISIBLE);
                    textView.setText("");
                    textView1.setText("");
                    final Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageView.setImageBitmap(bitmap);
//                    imageView.setImageURI(imageUri);
                    imageView.setVisibility(View.VISIBLE);
                    Log.e("IMAGE PATH", getRealPathFromURI(imageUri));
                    File imageFile = new File(getRealPathFromURI(imageUri));
                    compute(imageFile,textView);
                    textView.setVisibility(View.VISIBLE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
    void compute(File file, final TextView tv) throws IOException {

        String url = new String("https://corn-dis-eff.herokuapp.com/predict");


        //OkHttpClient client = new OkHttpClient();
        post(url, file, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Something went wrong
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseStr = response.body().string();
                    Log.e("OP",responseStr);
                    try {
                        final JSONObject jsonObject=new JSONObject(responseStr);
                        ans= (String) jsonObject.get("disease_name");
                        Log.e("NAME", ans);

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                firebaseFirestore.collection("Remedies")
                                        .get()
                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                                for(DocumentSnapshot d: lst)
                                                {
                                                    try {
                                                        if(d.getId().equals(jsonObject.get("disease_name")))
                                                        {
                                                            Log.e("COME INSIDE  ", "SUCCESSFULLY INSIDE" );
                                                            String cr=d.getString("remedy");
                                                            cr=cr.replaceAll("\\\\n","\n");
                                                            textView1.setText(cr);
                                                            textView1.setTextSize(20);
                                                            break;
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(DiagnosisActivity.this, "Error ! Check your Internet Connection", Toast.LENGTH_SHORT).show();
                                            }
                                        });
//                                    textView1.setText();
                                // Stuff that updates the UI
                                try {
                                    tv.setText("Predicted " + jsonObject.get("disease_name"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                tv.setTextSize(20);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ans=responseStr;
                    // Do what you want to do with the response.
                } else {
                    // Request not successful
                }

            }
        });
    }
    Call post(String url,File file, Callback callback) throws IOException {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .build();
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/jpeg");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "logo-square.png",
                        RequestBody.create(MEDIA_TYPE_PNG, file))
                .build();

        Request req = new Request.Builder().url(url).post(requestBody).build();

        Call call = client.newCall(req);
        call.enqueue(callback);
        return call;
    }
    public String getRealPathFromURI(Uri contentUri)
    {
        String[] proj = { MediaStore.Audio.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}