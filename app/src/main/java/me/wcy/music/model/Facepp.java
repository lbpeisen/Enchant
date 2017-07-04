package me.wcy.music.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by oreo on 2017/5/30.
 */

public class Facepp {


    /**
     * request_id : 1496154292,0f539a2f-ec13-4953-8d05-e367bbd7dac6
     * time_used : 480
     * thresholds : {"1e-3":65.3,"1e-5":76.5,"1e-4":71.8}
     * results : [{"confidence":91.637,"user_id":"","face_token":"cb1975bb94db8b29f89f82669e9d3d04"}]
     */

    private String request_id;
    private int time_used;
    private ThresholdsBean thresholds;
    private List<ResultsBean> results;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public ThresholdsBean getThresholds() {
        return thresholds;
    }

    public void setThresholds(ThresholdsBean thresholds) {
        this.thresholds = thresholds;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ThresholdsBean {
        /**
         * 1e-3 : 65.3
         * 1e-5 : 76.5
         * 1e-4 : 71.8
         */

        @SerializedName("1e-3")
        private double _$1e3;
        @SerializedName("1e-5")
        private double _$1e5;
        @SerializedName("1e-4")
        private double _$1e4;

        public double get_$1e3() {
            return _$1e3;
        }

        public void set_$1e3(double _$1e3) {
            this._$1e3 = _$1e3;
        }

        public double get_$1e5() {
            return _$1e5;
        }

        public void set_$1e5(double _$1e5) {
            this._$1e5 = _$1e5;
        }

        public double get_$1e4() {
            return _$1e4;
        }

        public void set_$1e4(double _$1e4) {
            this._$1e4 = _$1e4;
        }
    }

    public class ResultsBean {
        /**
         * confidence : 91.637
         * user_id :
         * face_token : cb1975bb94db8b29f89f82669e9d3d04
         */

        private double confidence;
        private String user_id;
        private String face_token;

        public double getConfidence() {
            return confidence;
        }

        public void setConfidence(double confidence) {
            this.confidence = confidence;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getFace_token() {
            return face_token;
        }

        public void setFace_token(String face_token) {
            this.face_token = face_token;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Facepp facepp = (Facepp) o;

        if (time_used != facepp.time_used) return false;
        if (request_id != null ? !request_id.equals(facepp.request_id) : facepp.request_id != null)
            return false;
        if (thresholds != null ? !thresholds.equals(facepp.thresholds) : facepp.thresholds != null)
            return false;
        return results != null ? results.equals(facepp.results) : facepp.results == null;

    }

    @Override
    public int hashCode() {
        int result = request_id != null ? request_id.hashCode() : 0;
        result = 31 * result + time_used;
        result = 31 * result + (thresholds != null ? thresholds.hashCode() : 0);
        result = 31 * result + (results != null ? results.hashCode() : 0);
        return result;
    }
}
