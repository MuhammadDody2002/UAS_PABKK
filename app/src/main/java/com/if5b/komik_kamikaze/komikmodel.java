package com.if5b.komik_kamikaze;

import java.util.List;

public class komikmodel {
    private List<Result> result;

    public List<Result> getResult() {
        return result;
    }

    public class Result {
        private String id_komik;
        private String nm_komik;
        private String gbr_komik;
        private String genre;

        public String getId_komik() {
            return id_komik;
        }

        public String getNm_komik() {
            return nm_komik;
        }

        public String getGbr_komik() {
            return gbr_komik;
        }

        public String getGenre() {
            return genre;
        }
    }

}
