package com.example.sportevent.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.sportevent.R;
import com.example.sportevent.ui.MainActivity;

import java.util.Deque;

public class BackButtonFragment extends Fragment {


//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_participant, container, false);
//
//        return getActivity().getCurrentFocus();
//    }
//

    @SuppressLint("RestrictedApi")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //getActivity().getSupportFragmentManager().popBackStack();
        //getChildFragmentManager().popBackStack();
        NavGraph navGraph = NavHostFragment.findNavController(getParentFragment()).getGraph();
       Deque<NavBackStackEntry> deque =  NavHostFragment.findNavController(requireParentFragment()).getBackStack();
        NavBackStackEntry navBackStackEntry =  NavHostFragment.findNavController(requireParentFragment()).getCurrentBackStackEntry();
        NavDestination NavDestination = NavHostFragment.findNavController(requireParentFragment()).getCurrentDestination();
        NavigatorProvider NavigatorProvider = NavHostFragment.findNavController(requireParentFragment()).getNavigatorProvider();
        NavInflater NavInflater = NavHostFragment.findNavController(requireParentFragment()).getNavInflater();
        NavBackStackEntry navBackStackEntry1 = NavHostFragment.findNavController(requireParentFragment()).getPreviousBackStackEntry();


        //NavHostFragment.findNavController(requireParentFragment()).getViewModelStoreOwner();
        int count = NavHostFragment.findNavController(requireParentFragment()).hashCode();
        //FragmentManager fm = getChildFragmentManager();
         //Log.i("######", "Found fragment: " + fm.getBackStackEntryAt(count).getName());

        // todo it's working but it's come back to head fragment NavHostFragment.findNavController(requireParentFragment()).popBackStack();

        //Navigation.findNavController(requireActivity().getCurrentFocus()).popBackStack();

        //View c = getActivity().getSupportFragmentManager().findNavController()
        //c.popBackStack() // current fragment will be pop up from the stack
        //c.navigate(DestinationFragmentID)
     //   Log.i("######", "Found fragment: " + fm.getBackStackEntryAt(count).getName());


        //requireActivity().getSupportFragmentManager().popBackStackImmediate();
        //getActivity().getSupportFragmentManager().

        //if (requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
         //   requireActivity().getSupportFragmentManager().popBackStackImmediate();
        //}

        //getFragmentManager().popBackStack();
        //requireActivity().getOnBackPressedDispatcher().onBackPressed();
        // This callback will only be called when MyFragment is at least Started.
//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//                // Handle the back button event
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);



        //getActivity().addOnBackPressedCallback(getViewLifecycleOwner(),this);
    }



}
