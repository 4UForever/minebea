package com.devsenses.minebea.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.devsenses.minebea.R;
import com.devsenses.minebea.activity.MainActivity;
import com.devsenses.minebea.adapter.ListDocumentAdapter;
import com.devsenses.minebea.model.documentmodel.Datum;

/**
 * Created by Administrator on 5/11/2015.
 */
public class ListDocumentFragment extends Fragment implements AdapterView.OnItemClickListener{
    private static String KEY_DOCUMENT = "kayDocument";
    static ListDocumentFragment listDocumentFragment;

    private Button btBack;
    private ListView listDocument;

    private Datum dataDocument;

    public static ListDocumentFragment newInstance(Datum dataDocument){
//        if (listDocumentFragment == null) {
            listDocumentFragment = new ListDocumentFragment();
//        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DOCUMENT, dataDocument);
        listDocumentFragment.setArguments(bundle);

        return listDocumentFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        dataDocument = getArguments().getParcelable(KEY_DOCUMENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_list_document, null);
        initUI(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListDocumentAdapter listDocumentAdapter = new ListDocumentAdapter(getActivity() , dataDocument.getDocuments());
        listDocument.setAdapter(listDocumentAdapter);

    }

    void initUI(View view){
        btBack = (Button)view.findViewById(R.id.back);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getFragmentManager().popBackStack();
            }
        });
        listDocument = (ListView)view.findViewById(R.id.listDocument);
        listDocument.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String urlLoadPdfFile = dataDocument.getDocuments().get(position).getDownloadUrl();
        String pdfName = dataDocument.getDocuments().get(position).getTitle();

            if (!urlLoadPdfFile.trim().isEmpty() && !pdfName.trim().isEmpty())
                ((MainActivity) getActivity()).loadDocument(urlLoadPdfFile, pdfName);
            else
                Toast.makeText(getActivity() , "File not available" , Toast.LENGTH_SHORT).show();
    }


}
