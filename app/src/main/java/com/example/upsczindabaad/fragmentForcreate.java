package com.example.upsczindabaad;

import android.app.AlertDialog;
import android.content.ClipboardManager;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.constraintlayout.widget.Placeholder;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentForcreate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentForcreate extends BottomSheetDialogFragment {
    EditText groupname;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int s=6;

    public fragmentForcreate() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmentForcreate.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmentForcreate newInstance(String param1, String param2) {
        fragmentForcreate fragment = new fragmentForcreate();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_forcreate, container, false);


        ImageView cgprofilepic=v.findViewById(R.id.cgprofpic);
       //groupname= v.findViewById(R.id.cggrupname);
        groupname=v.findViewById(R.id.cggrupname);
        v.findViewById(R.id.cgprofpic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        v.findViewById(R.id.creategrup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                String uid=user.getUid();
                String invitecode=getrandomstring(s);
                String edtgrup=groupname.getText().toString();

                DatabaseReference refw= FirebaseDatabase.getInstance().getReference("grup").child(invitecode);

                    modelgrupparticipants modelgroup=new modelgrupparticipants();
                    modelgroup.setUid(uid);
                    refw.child("members").setValue(modelgroup);
                    modelgrupdetails modelgrupdetails=new modelgrupdetails();
                    modelgrupdetails.setGroupname(edtgrup);
                    modelgrupdetails.setGruppic("notuploadeimg");
                    refw.child("groupdetails").setValue(modelgrupdetails);
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Successfully Created!!")
                        .setMessage("Referal Code is '"+invitecode+"'")
                        .setPositiveButton("copy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                               // ClipboardManager clipboard=(ClipboardManager)

                            }
                        })
                        .setNegativeButton("share", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
              AlertDialog alertDialog=builder.create();
              alertDialog.show();
            }
        });
        return v;

    }


    public static String getrandomstring(int i){
        final String chaaracters="abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJkl";
        StringBuilder result=new StringBuilder();
        while(i>0){
            Random rand=new Random();
            result.append(chaaracters.charAt(rand.nextInt(chaaracters.length())));
            i--;
        }
        return result.toString();
    }
}