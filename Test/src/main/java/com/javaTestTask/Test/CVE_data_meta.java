package com.javaTestTask.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CVE_data_meta {
        @JsonProperty("ID")
        private String id;
        @JsonProperty("ASSIGNER")
        private String assigner;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getAssigner() {
                return assigner;
        }

        public void setAssigner(String assigner) {
                this.assigner = assigner;
        }

        @Override
        public String toString() {
                return "CVE_data_meta{" +
                        "id='" + id + '\'' +
                        ", assigner='" + assigner + '\'' +
                        '}';
        }

}
