package com.example.e450c.bosszhiping.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.e450c.bosszhiping.R;
import com.example.e450c.bosszhiping.adapter.CompanyAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class CompanyFragment extends Fragment {

    @InjectView(R.id.company_rv)
     RecyclerView companyRecycle;

    @InjectView(R.id.company_srfl)
    SwipeRefreshLayout companySwipRefreshLayout;

    boolean isLoading;
    private List<Map<String, Object>> data = new ArrayList<>();
    private CompanyAdapter adapter;
    private Handler handler = new Handler();


    public CompanyFragment() {

    }


    public static CompanyFragment newInstance() {
        CompanyFragment fragment = new CompanyFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initView() {




//        companySwipRefreshLayout.setColorSchemeResources(getActivity().getResources().getColor(R.color.blueStatus));
        companySwipRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    companySwipRefreshLayout.setRefreshing(true);
                }
            });

        companySwipRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            data.clear();
                            getData();
                        }
                    }, 2000);
                }
            });
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        companyRecycle.setLayoutManager(layoutManager);
        adapter = new CompanyAdapter(getActivity(),data);
        companyRecycle.setAdapter(adapter);
        companyRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    Log.d("test", "StateChanged = " + newState);


                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    Log.d("test", "onScrolled");

                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                        Log.d("test", "loading executed");

                        boolean isRefreshing = companySwipRefreshLayout.isRefreshing();
                        if (isRefreshing) {
                            adapter.notifyItemRemoved(adapter.getItemCount());
                            return;
                        }
                        if (!isLoading) {
                            isLoading = true;
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getData();
                                    Log.d("test", "load more completed");
                                    isLoading = false;
                                }
                            }, 1000);
                        }
                    }
                }
            });

            //添加点击事件
            adapter.setOnItemClickListener(new CompanyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Log.d("test", "item position = " + position);
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });
        }


    public void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 1500);



    }

    private void getData() {

        for (int i = 0; i < 6; i++) {
            Map<String, Object> map = new HashMap<>();
            data.add(map);
        }
        adapter.notifyDataSetChanged();
        companySwipRefreshLayout.setRefreshing(false);
        adapter.notifyItemRemoved(adapter.getItemCount());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company, container, false);

        ButterKnife.inject(this,view);
        initView();
        initData();
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
