package com.JsonParser.JsonParser.Parser;

import com.JsonParser.JsonParser.Models.NPacket;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class Parser {
    private String JsonString;

    public Parser(String jsonString) {
        this.JsonString = jsonString;
    }

    public Parser() {
    }

    public void setJsonString(String jsonString) {
        this.JsonString = jsonString;
    }

    public List<NPacket> ParseJsonStringToList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<NPacket> nPackets = mapper.readValue(this.JsonString, new TypeReference<List<NPacket>>(){});

        return nPackets;
    }

    public String ParseObjectsToString(List<NPacket> nPackets) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonList = mapper.writeValueAsString(nPackets);
        return jsonList;
    }
}
