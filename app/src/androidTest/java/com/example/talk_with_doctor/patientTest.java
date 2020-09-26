package com.example.talk_with_doctor;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class patientTest {

    @Rule
    public ActivityTestRule<homePatient> mActivityTestRule = new ActivityTestRule<homePatient>(homePatient.class);
    private homePatient mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void  testLaunch() {
        View view = mActivity.findViewById(R.id.txtPatient);
        assertNotNull(view);
    }

    @After
    public void tearDown() {
        mActivity = null;
    }
}
