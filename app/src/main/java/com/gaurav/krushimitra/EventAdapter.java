package com.gaurav.krushimitra;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class EventAdapter extends FragmentStatePagerAdapter {
    int counter ;
    public EventAdapter(FragmentManager fm,int counter) {
        super(fm);
        this.counter=counter;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                CultipsStages cultipsStages=new CultipsStages();
                return cultipsStages;
            case 1:
                CultipsWeeks cultipsWeeks=new CultipsWeeks();
                return cultipsWeeks;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return counter;
    }
}