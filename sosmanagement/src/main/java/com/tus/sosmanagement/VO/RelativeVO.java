package com.tus.sosmanagement.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelativeVO{
    public Response response;
    public Links _links;

    @Data
    public static class Relative{
        public Long id;
        public String name;
        public String email;
        public String phoneNumber;
    }

    @Data
    public static class Response{
        public int id;
        public String name;
        public String email;
        public String phoneNumber;
        public ArrayList<Relative> relatives;
    }

    @Data
    public static class Links{
        @JsonProperty("DELETE")
        public ArrayList<String> dELETE;
        @JsonProperty("PUT")
        public ArrayList<String> pUT;
    }
}


