package com.devsenses.minebea.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.devsenses.minebea.R;
import com.devsenses.minebea.activity.MainActivity;
import com.devsenses.minebea.adapter.GridDocumentAdapter;
import com.devsenses.minebea.model.documentmodel.DocumenModel;

/**
 * Created by Administrator on 5/11/2015.
 */
public class GridMemuDocumentFragment extends Fragment implements AdapterView.OnItemClickListener{
    static GridMemuDocumentFragment listDocumentMunuFragment;
    private static String KEY_DATA = "ketData";

    private GridView gridViewDocument;
    private DocumenModel documenModel;
    public static GridMemuDocumentFragment newInstance(DocumenModel documenModel){
       // if (listDocumentMunuFragment == null)
            listDocumentMunuFragment = new GridMemuDocumentFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelable(KEY_DATA ,documenModel );
            listDocumentMunuFragment.setArguments(bundle);

        return listDocumentMunuFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        documenModel = getArguments().getParcelable(KEY_DATA);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_grid_menu_document, null);
        initUI(view);
        return view;
    }
    void initUI(View view){
        gridViewDocument = (GridView)view.findViewById(R.id.gridViewDocument);
        gridViewDocument.setNumColumns(2);
        gridViewDocument.setOnItemClickListener(this);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initGridDocument();
    }

    void initGridDocument(){
//        gridDocumentAdapter = new GridDocumentAdapter(MainActivity.this );
        gridViewDocument.setAdapter(new GridDocumentAdapter(getActivity(),documenModel.getData()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       if (documenModel.getData().get(position).getDocuments().size()>0){
//           FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//               fragmentTransaction.replace(R.id.fragmentDocument, ListDocumentFragment.newInstance(documenModel.getData().get(position)));
//               fragmentTransaction.commit();
          ((MainActivity)getActivity()).showListDocument(documenModel.getData().get(position));
           //Toast.makeText(getActivity() , documenModel.getData().get(position).getDocuments().get(0).getTitle() , Toast.LENGTH_SHORT).show();;
       }
       else{
           Toast.makeText(getActivity() , "Not have Document!" , Toast.LENGTH_SHORT).show();
       }
    }
}
