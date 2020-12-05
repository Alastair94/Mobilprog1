package hu.bead.dinosaurs;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import hu.bead.dinosaurs.dao.DinoDatabase;
import hu.bead.dinosaurs.model.Dino;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DinoPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class DinoPageFragment extends Fragment {

    Dino dino;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DinoPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DinoPageFragment newInstance(String param1, String param2) {
        DinoPageFragment fragment = new DinoPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DinoPageFragment() {
        // Required empty public constructor
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

        View view = inflater.inflate(R.layout.fragment_dino_page, container, false);

        Bundle bundle = getArguments();
        if(bundle != null){
            TextView dataName = view.findViewById(R.id.nameTxt);
            TextView dataDiet = view.findViewById(R.id.dataDiet);
            TextView dataWeight = view.findViewById(R.id.dataWeight);
            TextView dataHeight = view.findViewById(R.id.dataHeight);
            TextView dataLength = view.findViewById(R.id.dataLength);
            ImageView imageView = (ImageView) view.findViewById(R.id.mainImageView);

            dino = (Dino)bundle.getSerializable("dino");

            dataName.setText(dino.getName());
            switch(dino.getDiet().toString()){
                case "HERBIVORE":
                    dataDiet.setText(R.string.herbivore);
                    break;
                default:
                    dataDiet.setText(R.string.carnivore);
                break;
            }
            dataWeight.setText(String.valueOf(dino.getWeight()));
            dataHeight.setText(String.valueOf(dino.getHeight()));
            dataLength.setText(String.valueOf(dino.getLength()));
            imageView.setImageResource(dino.getImage());
        }
        return view;
    }
}