package com.jl.seastory.product.dao;

import com.jl.seastory.common.paging.SelectCriteria;
import com.jl.seastory.product.dto.LicenseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LicenseMapper {

    int selectLicenseTotal();

    List<LicenseDto> selectLicenseListWithPaging(SelectCriteria selectCriteria);

    int selectLicenseTotalForAdmin();

    List<LicenseDto> selectLicenseListWithPagingForAdmin(SelectCriteria selectCriteria);

    LicenseDto selectLicenseForAdmin(String licenseCode);

    LicenseDto selectLicense(String licenseCode);

    int insertLicense(LicenseDto licenseDto);

    int updateLicense(LicenseDto licenseDto);

    int deleteLicense(String licenseCode);
}
