package com.suncaption.kpoptubemy.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.suncaption.kpoptubemy.fragment.Fragment1;
import com.suncaption.kpoptubemy.fragment.FragmentNew;

public class TabPagerAdapter extends FragmentStatePagerAdapter {


        private int mPageCount;



        public TabPagerAdapter(FragmentManager fm, int pageCount) {

            super(fm);

            this.mPageCount = pageCount;

        }



        @Override

        public Fragment getItem(int position) {
            Fragment fragment = (Fragment) null;
            switch (position) {

                case 0:

                    fragment = new FragmentNew();

                    break;



                case 1:

                    //GameFragment gameFragment = new GameFragment();

                    break;



                case 2:

                    //MovieFragment movieFragment = new MovieFragment();

                   break;



                case 3:

                    //BookFragment bookFragment = new BookFragment();

                    break;



                case 4:

                    //NewsFragment newsFragment = new NewsFragment();

                    break;



                default:

                    return null;

            }

            return fragment == null ? new Fragment1() : fragment;

        }



        @Override

        public int getCount() {

            return mPageCount;

        }
}
