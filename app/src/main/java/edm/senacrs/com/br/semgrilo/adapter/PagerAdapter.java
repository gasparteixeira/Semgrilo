package edm.senacrs.com.br.semgrilo.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edm.senacrs.com.br.semgrilo.PostFragment;
import edm.senacrs.com.br.semgrilo.RegisterFragment;
import edm.senacrs.com.br.semgrilo.SearchFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    public int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SearchFragment tab1 = new SearchFragment();
                return tab1;
            case 1:
                PostFragment tab2 = new PostFragment();
                return tab2;
            case 2:
                RegisterFragment tab3 = new RegisterFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
