package com.example.kdotz.regisscis.model.services;

import com.example.kdotz.regisscis.ProgramDetail;

import java.util.List;

/**
 * Created by kdotz on 7/17/2016.
 */
public interface IProgramDetailSvc {

    public ProgramDetail create (ProgramDetail  programDetail);
    public List<ProgramDetail > retrieveAllProgramDetails();
    public int update(ProgramDetail programDetail);
    public ProgramDetail  delete(ProgramDetail  programDetail);

}
