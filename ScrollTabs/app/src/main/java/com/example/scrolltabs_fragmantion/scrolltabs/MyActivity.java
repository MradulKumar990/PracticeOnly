package com.example.scrolltabs_fragmantion.scrolltabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.TextView;


public class MyActivity extends FragmentActivity{

    ViewPager viewPager=null;
    TextView text;
    public static final String TAG_NAME = "TAU";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        viewPager= (ViewPager) findViewById(R.id.pager);
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager, 80));
    }

    class MyAdapter extends FragmentPagerAdapter{

        private int count;

        public MyAdapter(android.support.v4.app.FragmentManager fm, int fragCount) {
            super(fm);
            count = fragCount;
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment=null;
            Bundle bundle = new Bundle();
            if(i % 3 == 0){
                fragment= new FragmentA();
            }else if(i % 3 == 1){
                fragment= new FragmentB();
            }else if(i % 3 == 2){
                fragment= new FragmentC();
            }
            bundle.putString(TAG_NAME, "yoyo " + (i+1));
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "yoyo " + (position+1);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem, menu);


        return super.onCreateOptionsMenu(menu);
    }
}
