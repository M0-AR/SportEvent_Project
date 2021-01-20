package com.example.sportevent_test;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.utilities.Logic;
import com.example.sportevent.utilities.SampleData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.*;

/*****************************************************
 *  The test here is according to the requirements.  *
 *  The Events list should appear in ascending order *
 *****************************************************/
// junit.EventListDateTest
@RunWith(AndroidJUnit4.class)
public class EventListDateTest{

    @Before
    public void initData() {
        /* The test here is according to the requirements.
           The Events list should appear in ascending order
         */
        SampleData.initFakeData();
    }

    @Test
    public void useAppContext() {
        // Shuffle event list
        Collections.shuffle(SampleData.signUpEventList);
        // Sort event list according to the closet date to today's date
        // 2020/11/15 is the closet date or the event who has this date
        // will start first.
        Logic.sortEventsByClosestDateToToday(SampleData.signUpEventList);
        // Check for the events' date in list have ascending order
        for (int i = 0; i < SampleData.signUpEventList.size(); i++) {
            assertEquals(new Date(2020 , 11 , 15+i), SampleData.signUpEventList.get(i).getJoinStartDate());
        }
    }


}