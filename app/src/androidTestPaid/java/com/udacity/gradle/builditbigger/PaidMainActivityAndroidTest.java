package com.udacity.gradle.builditbigger;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

/**
 * Created by Guanqing on 2015/9/29.
 */
public class PaidMainActivityAndroidTest extends ActivityInstrumentationTestCase2 {


    private MainActivity mActivity;
    private Button mButton;

    public PaidMainActivityAndroidTest(){
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        mActivity = (MainActivity) getActivity();
        mButton = (Button) mActivity.findViewById(R.id.joke_telling_button);
    }

    @MediumTest
    public void testCases(){
        assertNotNull(mActivity);
        assertNull(mActivity.getJoke());

        TouchUtils.clickView(this, mButton);
        String joke1 = mActivity.getJoke();
        assertNotNull(joke1);
    }
}
