package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.kulvir72510.cms6.MainActivity.userId;

public class profile extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "TAG";
    Button btn_logout,gbtn_logout,btnEditProfile, btn_pass;
    DatabaseReference databaseReference;
    FirebaseFirestore fStore=FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();

    String Name;
    Uri imageUri;
    String myUrl= "";
    StorageTask uploadTask;
    StorageReference storageReference=storage.getReference();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    TextView tv_email, tv_city, tv_address,tv_phone,tv_name,tv_pos;
    TextView gtv_email, gtv_city, gtv_address,gtv_phone,gtv_name;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    CircleImageView img_dp;
    ImageView imageView8;
    ScrollView scroll;
    ScrollView G_scroll;
    private RecyclerView recyclerView;
    private Adapter PostAdaptar;
    private DatabaseReference mDatabaseRef;
    private List<ModelClass> mUploads;

    @Override
    protected void onStart() {
        super.onStart();

        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference=fStore.collection("users").document(userId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String p = String.valueOf(documentSnapshot.getLong("Phone"));
                    tv_name.setText("Hello! "+documentSnapshot.getString("Full_Name"));
                    tv_pos.setText(documentSnapshot.getString("position"));
                    tv_email.setText(documentSnapshot.getString("Email"));
                    tv_address.setText(documentSnapshot.getString("Address"));
                    tv_city.setText(documentSnapshot.getString("city"));
                    tv_phone.setText(p);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_profile);
        btn_logout = findViewById(R.id.btn_logout);
        tv_address=findViewById(R.id.tv_address);
        tv_city= findViewById(R.id.tv_city);
        tv_email = findViewById(R.id.tv_email);
        tv_phone= findViewById(R.id.tv_phone);
        tv_name = findViewById(R.id.tv_name);
        img_dp = findViewById(R.id.img_dp);
        btn_pass = findViewById(R.id.button2);
        scroll = findViewById(R.id.scroll);
        tv_pos=findViewById(R.id.tv_pos);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        imageView8 = findViewById(R.id.imageView8);
       // G_scroll = findViewById(R.id.G_scroll)


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient= new GoogleApiClient.Builder(this).enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id == R.id.ic_profile){
                    return false;
                }
                else if(id==R.id.ic_add){
                    startActivity(new Intent(profile.this,add.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_graph){
                    startActivity(new Intent(profile.this,graph.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_personadd){
                    startActivity(new Intent(profile.this,personAdd.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_home){
                    startActivity(new Intent(profile.this,home.class));
                    overridePendingTransition(0, 0);
                }
                return true;
            }
        });


        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.signOut();
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if(status.isSuccess()){
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }
                    }
                });
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();



            }
        });

        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference=fStore.collection("users").document(userId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String p = String.valueOf(documentSnapshot.getLong("Phone"));
                    tv_name.setText("Hello! "+documentSnapshot.getString("Full_Name"));
                    Name = documentSnapshot.getString("Full_Name");
                    tv_pos.setText(documentSnapshot.getString("position"));
                    tv_email.setText(documentSnapshot.getString("Email"));
                    tv_address.setText(documentSnapshot.getString("Address"));
                    tv_city.setText(documentSnapshot.getString("city"));
                    tv_phone.setText(p);
                    if (documentSnapshot.getString("imageUri").equals("")){
                        Toast.makeText(getApplicationContext(),"sorry error !!",Toast.LENGTH_LONG).show();

                    }else
                    {
                        storageReference.child(documentSnapshot.getString("imageUri")).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                img_dp.setImageBitmap(Bitmap.createScaledBitmap(bmp, img_dp.getWidth(), img_dp.getHeight(), false));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle any errors
                                Toast.makeText(getApplicationContext(),"sorry error !!",Toast.LENGTH_LONG).show();
                            }
                        });
                    }





                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"sorry error !!",Toast.LENGTH_LONG).show();
            }
        });



        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),EditProfile.class);
                i.putExtra("name",Name);
                i.putExtra("email",tv_email.getText());
                i.putExtra("phone",tv_phone.getText());
                i.putExtra("address",tv_address.getText());
                i.putExtra("city",tv_city.getText());
                startActivity(i);

            }
        });

        btn_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetmail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter your mail to recieve reset password link");
                passwordResetDialog.setView(resetmail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetmail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(profile.this, "Reset link has been sent to your mail", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(profile.this, "Error : LinK not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordResetDialog.create().show();
            }
        });


        recyclerView=findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads=new ArrayList<ModelClass>();
        CollectionReference reff = fStore.collection("users").document(userId).collection("postUrl");
        reff.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    String model=documentSnapshot.getString("Model");
                    String color = documentSnapshot.getString("Color");
                    String gst = documentSnapshot.getString("Gst");
                    String price = documentSnapshot.getString("Price");
                    String rp = documentSnapshot.getString("RoadPrice");
                    String e = documentSnapshot.getString("publisherEmail");
                    String p = documentSnapshot.getString("publisherPhone");
                    mUploads.add(new ModelClass(model,color,gst,price,rp,e,p));
                }
                PostAdaptar=new Adapter(profile.this,mUploads);
                recyclerView.setAdapter(PostAdaptar);
                System.out.println("fetched all data success");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();

            }
        });



    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data != null && data.getData()!=null){
            imageUri= data.getData();
            img_dp.setImageURI(imageUri);
            System.out.println(imageUri+"  kulvir 1");
            uploadPicture();

        }
    }

    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image");
        pd.show();
        final String randomKey = UUID.randomUUID().toString();

        final StorageReference riversRef = storageReference.child("images/"+randomKey);


        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content),"Image Uploaded",Snackbar.LENGTH_LONG).show();
                        userId = fAuth.getCurrentUser().getUid();

                        DocumentReference documentReference= fStore.collection("users").document(userId);
                        Map<String,Object> user= new HashMap<>();
                        user.put("imageUri","images/"+randomKey);
                        documentReference.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "user profile saved image successfully" + userId);
                            }
                        });




                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),"Failed to Upload",Toast.LENGTH_LONG).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent=(100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Percentage "+(int) progressPercent + "%");
            }
        });


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}