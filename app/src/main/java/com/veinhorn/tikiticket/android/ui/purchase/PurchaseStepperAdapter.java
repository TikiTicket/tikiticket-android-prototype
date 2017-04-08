package com.veinhorn.tikiticket.android.ui.purchase;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;

/**
 * Created by veinhorn on 13.3.17.
 */

public class PurchaseStepperAdapter extends AbstractFragmentStepAdapter {
    public PurchaseStepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        SelectRouteFragment fragment = new SelectRouteFragment();

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
