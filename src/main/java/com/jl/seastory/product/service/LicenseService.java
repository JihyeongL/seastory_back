package com.jl.seastory.product.service;

import com.jl.seastory.common.paging.SelectCriteria;
import com.jl.seastory.product.dao.LicenseMapper;
import com.jl.seastory.product.dto.LicenseDto;
import com.jl.seastory.util.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class LicenseService {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;
    private final LicenseMapper licenseMapper;

    public LicenseService(LicenseMapper licenseMapper) {
        this.licenseMapper = licenseMapper;
    }

    public int selectLicenseTotal() {
        int result = licenseMapper.selectLicenseTotal();
        return result;
    }

    public int selectLicenseTotalForAdmin() {
        int result = licenseMapper.selectLicenseTotalForAdmin();
        return result;
    }

    public Object selectLicenseServiceListWithPaging(SelectCriteria selectCriteria) {
        List<LicenseDto> licenseList = licenseMapper.selectLicenseListWithPaging(selectCriteria);
        for(int i = 0; i < licenseList.size(); i++){
            licenseList.get(i).setLicenseImageUrl(IMAGE_URL + licenseList.get(i).getLicenseImageUrl());
        }
        return licenseList;
    }



    public Object selectLicenseListWithPagingForAdmin(SelectCriteria selectCriteria) {
        List<LicenseDto> licenseList = licenseMapper.selectLicenseListWithPagingForAdmin(selectCriteria);
        for (int i = 0; i < licenseList.size(); i++) {
            licenseList.get(i).setLicenseImageUrl(IMAGE_URL + licenseList.get(i).getLicenseImageUrl());
        }
        return licenseList;
    }

    public Object selectLicenseForAdmin(String licenseCode) {
        LicenseDto licenseDto = licenseMapper.selectLicenseForAdmin(licenseCode);
        licenseDto.setLicenseImageUrl(IMAGE_URL + licenseDto.getLicenseImageUrl());
        return licenseDto;
    }

    public Object selectLicense(String licenseCode) {
        LicenseDto licenseDto = licenseMapper.selectLicense(licenseCode);
        licenseDto.setLicenseImageUrl(IMAGE_URL + licenseDto.getLicenseImageUrl());
        return licenseDto;
    }

    @Transactional
    public Object insertLicense(LicenseDto licenseDto) {
        String imageName = UUID.randomUUID().toString().replace("-","");
        String replaceFileName = null;
        int result = 0;
        try{
            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, licenseDto.getLicenseImage());
            licenseDto.setLicenseImageUrl(replaceFileName);
            result = licenseMapper.insertLicense(licenseDto);
        }catch (IOException e){
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        log.info("[LicenseService] result > 0 성공: "+ result);
        return (result > 0)? "상품 등록 성공" : "상품 등록 실패";
    }

    @Transactional
    public Object updateLicense(LicenseDto licenseDto) {
        String replaceFileName = null;
        int result = 0;
        try{
            String oriImage = licenseMapper.selectLicense(String.valueOf(licenseDto.getLicenseCode())).getLicenseImageUrl();
            if(licenseDto.getLicenseImage() != null){
                String imageName = UUID.randomUUID().toString().replace("-", "");
                replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, licenseDto.getLicenseImage());
                licenseDto.setLicenseImageUrl(replaceFileName);
                boolean isDelete = FileUploadUtils.deleteFile(IMAGE_DIR, oriImage);
                log.info("[update] isDelete : " + isDelete);
            }else{
                licenseDto.setLicenseImageUrl(oriImage);
            }
            result = licenseMapper.updateLicense(licenseDto);
        }catch (IOException e){
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        return (result > 0)? "상품 수정 성공" : "상품 수정 실패";

    }

    @Transactional
    public String deleteLicense(String licenseCode) {

        int result = licenseMapper.deleteLicense(licenseCode);

        return (result > 0) ? "상품 삭제 성공":"상품 삭제 실패";
    }

    public Object selectLicenseList() {
        List<LicenseDto> licenseList = licenseMapper.selectLicenseList();
        for (int i = 0; i < licenseList.size(); i++) {
            licenseList.get(i).setLicenseImageUrl(IMAGE_URL + licenseList.get(i).getLicenseImageUrl());
        }
        return licenseList;
    }
}
