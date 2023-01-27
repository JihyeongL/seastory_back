package com.jl.seastory.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class LicenseDto {
        private Long licenseCode;
        private String licenseName;
        private String licensePrice;
        private String licenseDescription;
        private String licenseOrderable;
        private Long categoryCode;
        private String categoryName;
        private MultipartFile licenseImage;
        private String licenseImageUrl;

    public Long getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(Long licenseCode) {
        this.licenseCode = licenseCode;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public String getLicensePrice() {
        return licensePrice;
    }

    public void setLicensePrice(String licensePrice) {
        this.licensePrice = licensePrice;
    }

    public String getLicenseDescription() {
        return licenseDescription;
    }

    public void setLicenseDescription(String licenseDescription) {
        this.licenseDescription = licenseDescription;
    }

    public String getLicenseOrderable() {
        return licenseOrderable;
    }

    public void setLicenseOrderable(String licenseOrderable) {
        this.licenseOrderable = licenseOrderable;
    }

    public Long getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(Long categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public MultipartFile getLicenseImage() {
        return licenseImage;
    }

    public void setLicenseImage(MultipartFile licenseImage) {
        this.licenseImage = licenseImage;
    }

    public String getLicenseImageUrl() {
        return licenseImageUrl;
    }

    public void setLicenseImageUrl(String licenseImageUrl) {
        this.licenseImageUrl = licenseImageUrl;
    }

    public LicenseDto(Long licenseCode, String licenseName, String licensePrice,
                      String licenseDescription, String licenseOrderable, Long categoryCode,
                      String categoryName, MultipartFile licenseImage, String licenseImageUrl) {
            this.licenseCode = licenseCode;
            this.licenseName = licenseName;
            this.licensePrice = licensePrice;
            this.licenseDescription = licenseDescription;
            this.licenseOrderable = licenseOrderable;
            this.categoryCode = categoryCode;
            this.categoryName = categoryName;
            this.licenseImage = licenseImage;
            this.licenseImageUrl = licenseImageUrl;
        }
}
