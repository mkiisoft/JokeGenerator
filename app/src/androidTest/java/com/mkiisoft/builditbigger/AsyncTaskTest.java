package com.mkiisoft.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import static junit.framework.TestCase.assertFalse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mkiisoft.builditbigger.data.JokeAsyncTask;
import com.mkiisoft.builditbigger.ui.MainActivity;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAsyncTaskJoke() throws Exception {

        JokeAsyncTask testAsyncTask = new JokeAsyncTask(activityActivityTestRule.getActivity(), true);

        testAsyncTask.execute();
        String result = testAsyncTask.get();

        assertFalse(TextUtils.isEmpty(result));
    }
}