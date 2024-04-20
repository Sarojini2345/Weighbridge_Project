package com.weighbridge.services.impls;

import com.weighbridge.entities.TransporterMaster;
import com.weighbridge.payloads.TransporterRequest;
import com.weighbridge.repsitories.TransporterMasterRepository;
import com.weighbridge.services.TransporterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransporterServiceImpl implements TransporterService {
    @Autowired
    private TransporterMasterRepository transporterMasterRepository;
    @Autowired
    private  ModelMapper modelMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public String addTransporter(TransporterRequest transporterRequest) {
//        HttpSession session=request.getSession();
        Boolean BytransporterMaster = transporterMasterRepository.existsByTransporterName(transporterRequest.getTransporterName());
        if (BytransporterMaster){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Transporter already exist");
        }
        else {
//            Object userId = session.getAttribute("userId");
            TransporterMaster transporterMaster=new TransporterMaster();
            transporterMaster.setTransporterAddress(transporterRequest.getTransporterAddress());
            transporterMaster.setTransporterContactNo(transporterRequest.getTransporterContactNo());
            transporterMaster.setTransporterEmailId(transporterRequest.getTransporterEmailId());
            transporterMaster.setTransporterName(transporterRequest.getTransporterName());
//            transporterMaster.setCreatedBy(String.valueOf(userId));
//            transporterMaster.setModifiedBy( String.valueOf(userId));
            LocalDateTime currentDateTime = LocalDateTime.now();
            transporterMaster.setTransporterCreatedDate(currentDateTime);
            transporterMaster.setTransporterModifiedDate(   currentDateTime);


            transporterMasterRepository.save(transporterMaster);
            return "transporter added successfully";
        }

    }

    @Override
    public List<String> getAllTransporter() {
        List<TransporterMaster> all = transporterMasterRepository.findAll();
        List<String> str=new ArrayList<>();
        for(TransporterMaster transporterMaster:all){
           str.add(transporterMaster.getTransporterName());
        }
        return str;
    }
}
