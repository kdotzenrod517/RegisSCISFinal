package com.example.kdotz.regisscis.model.services;

import android.content.Context;
import android.util.Log;

import com.example.kdotz.regisscis.ProgramDetail;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdotz on 7/17/2016
 */
public class ProgramDetailSvcSioImpl {

    private final String filename = "ScheduleDetail.sio";
    private List<ProgramDetail> programDetails = new ArrayList<>();
    private Context appContext;

    public ProgramDetailSvcSioImpl  (Context context){
        programDetails = null;
        //store context passed in, needed to open files
        appContext = context;
        readFile();
    }

    private void readFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(appContext.openFileInput(filename));
            programDetails= (List<ProgramDetail>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            String TAG = "ContactSvcSioImpl";
            Log.e(TAG, e.getMessage());
        }
    }

    private void writeFile() {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(appContext.openFileOutput(filename, Context.MODE_PRIVATE));
            oos.writeObject(programDetails);
            oos.flush();
            oos.close();
        } catch (Exception e){
            String TAG = "ScheduleSvcSioImpl";
            Log.e(TAG, e.getMessage());
        }
    }

    public ProgramDetail create(ProgramDetail scheduleDetail){
        programDetails.add(scheduleDetail);
        writeFile();
        return scheduleDetail;
    }

    public List<ProgramDetail> retrieveAllScheduleDetails(){
        return programDetails;
    }

    public ProgramDetail update(ProgramDetail scheduleDetail, String find){

        for (int i = 0; i < programDetails.size(); i++){
            if (programDetails.get(i).equals(find)){
                programDetails.set(i, scheduleDetail);
                writeFile();
            }
        }
        return scheduleDetail;
    }

    public ProgramDetail delete(ProgramDetail programDetail, String find){
        for (int i = 0; i < programDetails.size(); i++){
            if (programDetails.get(i).equals(find)){
                programDetails.remove(i);
                writeFile();
            }
        }

        return programDetail;
    }

}
