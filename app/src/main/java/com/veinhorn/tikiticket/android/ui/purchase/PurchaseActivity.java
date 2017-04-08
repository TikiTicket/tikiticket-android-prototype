package com.veinhorn.tikiticket.android.ui.purchase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stepstone.stepper.StepperLayout;
import com.veinhorn.tikiticket.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseActivity extends AppCompatActivity {

    @BindView(R.id.purchaseStepper) protected StepperLayout purchaseStepper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        ButterKnife.bind(this);

        setTitle("Шаг 1 из 6 - Маршрут");
        purchaseStepper.setAdapter(new PurchaseStepperAdapter(getSupportFragmentManager(), this));
    }
}
