package com.jl.seastory.product.controller;

import com.jl.seastory.common.ResponseDto;
import com.jl.seastory.common.paging.Pagenation;
import com.jl.seastory.common.paging.ResponseDtoWithPaging;
import com.jl.seastory.common.paging.SelectCriteria;
import com.jl.seastory.product.dto.LicenseDto;
import com.jl.seastory.product.service.LicenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LicenseController {
    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping("/licenses")
    public ResponseEntity<ResponseDto> selectProductListWithPaging(@RequestParam(name="offset", defaultValue="1") String offset) {

        int totalCount = licenseService.selectLicenseTotal();
        int limit = 6;
        int buttonAmount = 6;
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(Integer.parseInt(offset), totalCount, limit, buttonAmount);

        ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
        responseDtoWithPaging.setPageInfo(selectCriteria);
        responseDtoWithPaging.setData(licenseService.selectLicenseServiceListWithPaging(selectCriteria));

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
    }

    @GetMapping("/licenses-management")
    public ResponseEntity<ResponseDto> selectProductListWithPagingForAdmin(@RequestParam(name="offset", defaultValue="1") String offset) {


        int totalCount = licenseService.selectLicenseTotalForAdmin();
        int limit = 6;
        int buttonAmount = 10;
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(Integer.parseInt(offset), totalCount, limit, buttonAmount);;


        ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
        responseDtoWithPaging.setPageInfo(selectCriteria);
        responseDtoWithPaging.setData(licenseService.selectLicenseListWithPagingForAdmin(selectCriteria));

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
    }

    @GetMapping("/licenses-management/{licenseCode}")
    public ResponseEntity<ResponseDto> selectLicenseDetailForAdmin(@PathVariable String licenseCode){
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상품 상세정보 조회 성공",  licenseService.selectLicenseForAdmin(licenseCode)));
    }

    @GetMapping("/licenses/{licenseCode}")
    public ResponseEntity<ResponseDto> selectLicenseDetail(@PathVariable String licenseCode){
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", licenseService.selectLicense(licenseCode)));

    }

    @PostMapping("/licenses-management")
    public ResponseEntity<ResponseDto> insertLicense(@ModelAttribute LicenseDto licenseDto){

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상품 등록 성공", licenseService.insertLicense(licenseDto)));
    }

    @PutMapping("/licenses-management/{licenseCode}")
    public ResponseEntity<ResponseDto> updateLicense(@ModelAttribute LicenseDto licenseDto){
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상품 수정 완료", licenseService.updateLicense(licenseDto)));
    }

    @DeleteMapping("/licenses-management/{licenseCode}")
    public ResponseEntity<ResponseDto> deleteLicense(@PathVariable String licenseCode){
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상품 삭제 완료", licenseService.deleteLicense(licenseCode)));
    }
}
