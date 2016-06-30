package com.doublebrain.mysupportlibrary.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.doublebrain.mysupportlibrary.R;
import com.doublebrain.mysupportlibrary.library.executorWithFeedback.ExecutorWithFeedback;
import com.doublebrain.mysupportlibrary.library.listViewSimplified.abstracts.ListViewSimplified;
import com.doublebrain.mysupportlibrary.models.ListItem;
import com.doublebrain.mysupportlibrary.models.ListViewSimplifiedSample;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentExecutorWithFeedbackSample extends Fragment {

    ListViewSimplified.LVList list;
    ProgressBar progressBar;

    public FragmentExecutorWithFeedbackSample() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ListViewSimplified.LVList<>();

        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ExecutorWithFeedback.stop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_executor_with_feedback_sample, container, false);

        ListViewSimplifiedSample listView = (ListViewSimplifiedSample) rootView.findViewById(R.id.listViewJobsResult);
        listView.init(R.layout.list_item,list);

        Button button = (Button) rootView.findViewById(R.id.buttonDoJob);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                progressBar.setVisibility(View.VISIBLE);
                makeJobs();
            }
        });

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        return rootView;
    }

    private void makeJobs() {

        for (int i = 0; i < 7; i++) {

            final int finalI = i;
            ExecutorWithFeedback.MyJob job = new ExecutorWithFeedback.MyJob() {
                @Override
                protected Bundle doJob() {
                    Bundle bundle = new Bundle();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ListItem listItem = new ListItem("Job #"+String.valueOf(finalI),"");
                    Log.d("JOB",listItem.text1);
                    bundle.putSerializable("result", listItem);

                    return bundle;
                }
            };

            job.setOnCompleteListener(new ExecutorWithFeedback.OnCompleteListener() {
                @Override
                public void onComplete(Bundle result) {
                    if (result.containsKey("result"))
                        list.add(result.get("result"));
                    if (!ExecutorWithFeedback.hasJobs()) progressBar.setVisibility(View.GONE);
                }
            });

            job.execute();

        }
    }

}
