package com.example.kdotz.regisscis;

/**
 * Created by kdotz on 6/30/2016.
 */
public class ProgramDetail {

    private long id;
    private String programName;
    private String programDescrip;

    public ProgramDetail(){

    }

    public ProgramDetail(String programName, String programDescrip, long id) {
        this.programName = programName;
        this.programDescrip = programDescrip;
        this.id = id;

    }

    public ProgramDetail(String string, String string1) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramDescrip() {
        return programDescrip;
    }

    public void setProgramDescrip(String programDescrip) {
        this.programDescrip = programDescrip;
    }
}
