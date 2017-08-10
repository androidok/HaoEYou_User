package com.haoeyou.user.bean;

public class DoctorBanner {
        private String doctor_id;
        private String open_htmlurl;
        private String open_image_url;

        public DoctorBanner() {
        }

        public DoctorBanner(String doctor_id, String open_htmlurl, String open_image_url) {
            this.doctor_id = doctor_id;
            this.open_htmlurl = open_htmlurl;
            this.open_image_url = open_image_url;
        }

        public String getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(String doctor_id) {
            this.doctor_id = doctor_id;
        }

        public String getOpen_htmlurl() {
            return open_htmlurl;
        }

        public void setOpen_htmlurl(String open_htmlurl) {
            this.open_htmlurl = open_htmlurl;
        }

        public String getOpen_image_url() {
            return open_image_url;
        }

        public void setOpen_image_url(String open_image_url) {
            this.open_image_url = open_image_url;
        }
    }