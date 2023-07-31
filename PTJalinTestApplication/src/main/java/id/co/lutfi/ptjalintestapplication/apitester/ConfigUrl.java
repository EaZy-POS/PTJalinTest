/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.lutfi.ptjalintestapplication.apitester;

/**
 *
 * @author RCS
 */
public class ConfigUrl {
    private String baseUrl;
    private String inquiryPath;
    private String paymentPath;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getInquiryPath() {
        return inquiryPath;
    }

    public void setInquiryPath(String inquiryPath) {
        this.inquiryPath = inquiryPath;
    }

    public String getPaymentPath() {
        return paymentPath;
    }

    public void setPaymentPath(String paymentPath) {
        this.paymentPath = paymentPath;
    }
    
    public String getUrl(int type){
        if (type == 1) {
            return getBaseUrl().concat(getInquiryPath());
        }
        
        if (type == 2) {
            return getBaseUrl().concat(getPaymentPath());
        }
        
        return null;
    }
    
}
