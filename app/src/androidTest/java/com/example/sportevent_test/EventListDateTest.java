package com.example.sportevent_test;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.utilities.Constants;
import com.example.sportevent.utilities.SampleData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
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
    ArrayList<Event> joinedEventList;

    @Before
    public void initData() {
        /* The test here is according to the requirements.
           The Events list should appear in ascending order
         */
        SampleData.initData();
        joinedEventList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            new Event(i, 50.0, SampleData.images.get(i), i +" : ", "description","https://www.google.com/maps/dir/Copenhagen/Spain/@47.7297451,-4.5837011,5z/data=!3m1!4b1!4m14!4m13!1m5!1m1!1s0x4652533c5c803d23:0x4dd7edde69467b8!2m2!1d12.5683372!2d55.6760968!1m5!1m1!1s0xc42e3783261bc8b:0xa6ec2c940768a3ec!2m2!1d-3.74922!2d40.463667!3e1"
                    , Arrays.asList("@gmail.com:"+i, "@gmail.com:"+i+1, "@gmail.com:"+i+2),Arrays.asList("@gmail.com:"+i, "@gmail.com:"+i+1, "@gmail.com:"+i+2),
                    new Date(2020 , 11 , 15 + i), // JoinStartDate
                    new Date(2020, 11, 25 + i) ,  // JoinEndDate
                    new Date(2021 , 11 , 15 + i), // RaceStartDate
                    new Date(2021, 11, 25 + i)); // RaceEndDate
        }

    }

    @Test
    public void useAppContext() {
        // Shuffle event list
        Collections.shuffle(joinedEventList);
        // Sort event list according to the closet date to today's date
        // 2020/11/15 is the closet date or the event who has this date
        // will start first.
        sortEventsByClosestDateToToday(joinedEventList);
        // Check for the events' date in list have ascending order
        for (int i = 0; i < joinedEventList.size(); i++) {
            assertEquals(new Date(2020 , 11 , 15+i), joinedEventList.get(i).getJoinStartDate());
        }
    }

    public ArrayList<Event> sortEventsByClosestDateToToday(ArrayList<Event> events){
        ArrayList<Event> sortedEvents = events;
        for (int i = 0; i < events.size(); i++) {
            Event min = events.get(i);
            int index = i;
            for (int j = i + 1; j < events.size(); j++) {
                Event event = events.get(j);
                if (min.getJoinStartDate().after(event.getJoinStartDate())) { // "Date1 is after Date2"
                    min = event;
                    index = j;
                }
            }
            if (i != index) {
                events.set(index, events.get(i));
                events.set(i, min);
            }
        }
        return sortedEvents;
    }
}