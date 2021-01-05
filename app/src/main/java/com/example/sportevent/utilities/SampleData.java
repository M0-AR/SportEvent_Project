package com.example.sportevent.utilities;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.entities.Participant;

import java.util.ArrayList;
import java.util.Date;

public class SampleData {
    public static ArrayList<Participant> participants;
    public static ArrayList<Event> signUpEventList;
    public static ArrayList<Event> joinedEventList;
    public static ArrayList<Event> finishedEventList;


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
        if (participants == null) {
            participants = new ArrayList<>();
        }

        for (int i = 0; i < 20; i++) {
            participants.add(new Participant(i, "MD: "+i, "@gmail.com: "+i, "DTU: "+i, "00 00 00 0"+i));
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
                "Software Engineer job qualifications and requirements\n" +
                "A degree in Software Engineering, Computer Science, Mathematics or related fields is essential. Some companies may require expertise in particular high-level programming languages such as C++, Java or Scala.\n" +
                "\n" +
                "As technology develops at an ever increasing pace, it is critical for Software Engineers to stay up to date with the latest developments in hardware, systems and coding.\n" +
                "\n" +
                "As well as formal qualifications, a Software Engineer job description should emphasise an importance towards:\n" +
                "\n" +
                " - A passion for solving problems and providing workable solutions\n" +
                " - Knowledge of algorithms and data structures\n" +
                " - Strong analytical and reasoning skills with an ability to visualise processes and outcomes\n" +
                " - Proficiency in troubleshooting software issues and debugging a large codebase\n" +
                " - Outstanding all-round communication skills and ability to work collaboratively\n" +
                "Robert Half is one of the leading technology recruitment agencies in Australia. Are you looking for an Software Engineer role? View our latest Software Engineer jobs here.\n" +
                "\n" +
                "Looking for an Software Engineer job or IT and Technology specific salary information? Head over to our Software Engineer Salary Guide for insights and trends.";

        for (int i = 0; i < 8; i++) {
            signUpEventList.add(new Event(images.get(i), i +" : "+ name, description, new Date(), new Date(), new Date(), new Date() ));
            // joinedEventList.add(new Event(images.get(i), i +" : "+ name, description, new Date(), new Date(), new Date(), new Date() ));
            // finishedEventList.add(new Event(images.get(i), i +" : "+ name, description, new Date(), new Date(), new Date(), new Date() ));
        }
    }


    public static ArrayList<Participant> getParticipants() {
        return participants;
    }

    public static ArrayList<Event> getSignUpEventList() {
        return signUpEventList;
    }

    public static ArrayList<Event> getJoinedEventList() {
        return joinedEventList;
    }

    public static ArrayList<Event> getFinishedEventList() {
        return finishedEventList;
    }


    public static boolean removeParticipants(Participant participant) {
        return participants.remove(participant);
    }

    public static boolean removeSignUpEventList(Event event) {
        return signUpEventList.remove(event);
    }

    public static boolean removeJoinedEventList(Event event) {
        return joinedEventList.remove(event);
    }

    public static boolean removeFinishedEventList(Event event) {
        return finishedEventList.remove(event);
    }

    public static boolean addJoinedEventList(Event event) {
        return joinedEventList.remove(event);
    }

    public static boolean addFinishedEventList(Event event) {
        return finishedEventList.remove(event);
    }
}
