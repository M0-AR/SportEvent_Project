package com.example.sportevent.utilities;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.entities.Participant;
import com.example.sportevent.data.model.entities.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SampleData {
    public static ArrayList<Participant> participants;
    public static HashMap<String, Result> participantResults;

    public static List<Participant> joinedParticipantList;
    public static ArrayList<Participant> finishedParticipantList;
    public static String currentUserEmail;

    public static ArrayList<Event> signUpEventList;
    public static ArrayList<Event> joinedEventList;
    public static ArrayList<Event> finishedEventList;


    public static int countOneByOneSecond = 0;

    public static ArrayList<String> images;
    private static final String image_test_0 = "https://dur-duweb.newscyclecloud.com/storyimage/DU/20200313/SPORTS01/200319747/AR/0/AR-200319747.jpg";
    private static final String image_test_1 = "https://s3-eu-west-1.amazonaws.com/rb-cms/rbv5/production/uploads/cover_images/c87e85c695ec584acbf8c9665a5727021854/i1080x475.jpg";
    private static final String image_test_2 = "https://www.mongoliabikechallenge.com/img_contenido/slider/1046_02.jpg";
    private static final String image_test_3 = "https://i.pinimg.com/564x/80/7e/1d/807e1d7aec10955860c35487035827ea.jpg";
    private static final String image_test_4 = "https://d3laewezlz9ul2.cloudfront.net/wp-content/uploads/2017/07/20155909/07184-how-to-recover-from-endurance-mountain-bike-races-700x394.jpg";
    private static final String image_test_5 = "https://www.thermofisher.com/blog/polymers2plastics/wp-content/uploads/sites/7/2016/07/istock_88862703_bicycletire-4.jpg";
    private static final String image_test_6 = "https://content.bikeroar.com/system/content/000/092/964/original/CriteriumRace.jpg";
    private static final String image_test_7 = "https://www.outsideonline.com/sites/default/files/styles/img_600x600/public/2017/08/08/amateur-cyclists-mt-evans-colorado_s.jpg";

    public static void initData() {
        if (participants == null && joinedParticipantList == null && finishedParticipantList == null) {
            participants = new ArrayList<>();
            joinedParticipantList = new ArrayList<>();
            finishedParticipantList = new ArrayList<>();
        }

        // TODO: 14/01/2021 Clean up
        for (int i = 0; i < 20; i++) {
            //participants.add(new Participant(i, "MD: "+i, "@gmail.com: "+i, "DTU: "+i, "00 00 00 0"+i,));
        }

        for (int i = 0; i < 8; i++) {
            //joinedParticipantList.add(new Participant(i, "MD: "+i, "@gmail.com: "+i, "DTU: "+i, "00 00 00 0"+i));
            //finishedParticipantList.add(new Participant(i, "MD: "+i, "@gmail.com: "+i, "DTU: "+i, "00 00 00 0"+i));
        }

        if (images == null) {
            images = new ArrayList<>();
        }

        images.add(image_test_0);
        images.add(image_test_1);
        images.add(image_test_2);
        images.add(image_test_3);
        images.add(image_test_4);
        images.add(image_test_5);
        images.add(image_test_6);
        images.add(image_test_7);


        if (signUpEventList == null && joinedEventList == null && finishedEventList == null) {
            signUpEventList = new ArrayList<>();
            joinedEventList = new ArrayList<>();
            finishedEventList = new ArrayList<>();
        }


        String name = "Software Engineer job description guide";
        String description = "The business environment relies heavily on software for many functions - from automated traffic control systems to complex manufacturing processes, and Software Engineers are pivotal in the development of software that provides real solutions. A Software Engineer needs to address the entire software development lifecycle - to analyse the needs, and then design, test and develop software in order to meet those needs.\n" +
                "\n" +
                "Software Engineer duties and responsibilities of the job\n" +
                "Software Engineers document this process through the use of diagrams and flowcharts, developing computer instructions through the use of algorithms. Given the breadth of the role, a Software Engineer must have a thorough understanding of computer systems, in order to recognise any hardware limitations that could impact software design. A typical Software Engineer job description includes:\n" +
                "\n" +
                "Improving system quality by identifying issues and common patterns, and developing standard operating procedures\n" +
                "Enhancing applications by identifying opportunities for improvement, making recommendations and designing and implementing systems\n" +
                "Maintaining and improving existing codebases and peer review code changes\n" +
                "Liaising with colleagues to implement technical designs\n" +
                "Investigating and using new technologies where relevant\n" +
                "Providing written knowledge transfer material\n" +
                "Software Engineer job qualifications and requirements\n";

        for (int i = 0; i < 8; i++) {
/*
            signUpEventList.add(new Event(images.get(i), i +" : "+ name, description,"https://www.google.com/maps/dir/Copenhagen/Spain/@47.7297451,-4.5837011,5z/data=!3m1!4b1!4m14!4m13!1m5!1m1!1s0x4652533c5c803d23:0x4dd7edde69467b8!2m2!1d12.5683372!2d55.6760968!1m5!1m1!1s0xc42e3783261bc8b:0xa6ec2c940768a3ec!2m2!1d-3.74922!2d40.463667!3e1"
                    ,joinedParticipantList,finishedParticipantList, new Date(), new Date(), new Date(), new Date() ));
*/
            signUpEventList.add(new Event(i,1000 ,25.0, images.get(i), i +" : "+ name, description,"https://www.google.com/maps/dir/Copenhagen/Spain/@47.7297451,-4.5837011,5z/data=!3m1!4b1!4m14!4m13!1m5!1m1!1s0x4652533c5c803d23:0x4dd7edde69467b8!2m2!1d12.5683372!2d55.6760968!1m5!1m1!1s0xc42e3783261bc8b:0xa6ec2c940768a3ec!2m2!1d-3.74922!2d40.463667!3e1"
                    , Arrays.asList("@gmail.com:"+i, "@gmail.com:"+i+1, "@gmail.com:"+i+2),Arrays.asList("@gmail.com:"+i, "@gmail.com:"+i+1, "@gmail.com:"+i+2), new Date(), new Date(), new Date(), new Date() ));

        }


//        for (int i = 0; i < 8; i++) {
//            joinedEventList.add(new Event(images.get(i), i +" : "+ name, description,"",joinedParticipantList,finishedParticipantList, new Date(), new Date(), new Date(), new Date() ));
//        }
//        for (int i = 0; i < 8; i++) {
//           finishedEventList.add(new Event(images.get(i), i +" : "+ name, description,"",joinedParticipantList,finishedParticipantList, new Date(), new Date(), new Date(), new Date() ));
//        }
    }
}
