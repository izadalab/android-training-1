package dev.dhyto.movieapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerResponse {


    /**
     * id : 287947
     * results : [{"id":"5b5383370e0a26231c016fc5","iso_639_1":"en","iso_3166_1":"US","key":"go6GEIrcvFY","name":"SHAZAM! - Official Teaser Trailer [HD]","site":"YouTube","size":1080,"type":"Trailer"},{"id":"5c44e2c2c3a368478281a112","iso_639_1":"en","iso_3166_1":"US","key":"yJCbtNZ18O8","name":"SHAZAM! - In Theaters April 5","site":"YouTube","size":1080,"type":"Teaser"},{"id":"5c85df70c3a36839d4d5b557","iso_639_1":"en","iso_3166_1":"US","key":"uilJZZ_iVwY","name":"SHAZAM! - Official Trailer 2 - Only In Theaters April 5","site":"YouTube","size":1080,"type":"Trailer"}]
     */

    @SerializedName("id")
    private int id;
    @SerializedName("results")
    private List<ResultsTrailer> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ResultsTrailer> getResults() {
        return results;
    }

    public void setResults(List<ResultsTrailer> results) {
        this.results = results;
    }

    public static class ResultsTrailer {
        /**
         * id : 5b5383370e0a26231c016fc5
         * iso_639_1 : en
         * iso_3166_1 : US
         * key : go6GEIrcvFY
         * name : SHAZAM! - Official Teaser Trailer [HD]
         * site : YouTube
         * size : 1080
         * type : Trailer
         */

        @SerializedName("id")
        private String id;
        @SerializedName("iso_639_1")
        private String iso6391;
        @SerializedName("iso_3166_1")
        private String iso31661;
        @SerializedName("key")
        private String key;
        @SerializedName("name")
        private String name;
        @SerializedName("site")
        private String site;
        @SerializedName("size")
        private int size;
        @SerializedName("type")
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIso6391() {
            return iso6391;
        }

        public void setIso6391(String iso6391) {
            this.iso6391 = iso6391;
        }

        public String getIso31661() {
            return iso31661;
        }

        public void setIso31661(String iso31661) {
            this.iso31661 = iso31661;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
