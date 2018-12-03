package com.jsonparser.jsonparser.ParserTest;

import com.JsonParser.JsonParser.Models.NPacket;
import com.JsonParser.JsonParser.Parser.Parser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;

@RunWith(JUnit4.class)
public class ParserTest {

    @Test
    public void givenJsonArray_whenDeserializingAsListWithTypeReferenceHelp_thenCorrect() throws IOException {
        Parser parser = new Parser();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<NPacket> nPacketList = new ArrayList<>();
        nPacketList.add(new NPacket("a", -1));
        nPacketList.add(new NPacket("b", -2));

        String JsonArray = parser.ParseObjectsToString(nPacketList);

        List<NPacket> asList = mapper.readValue(JsonArray, new TypeReference<List<NPacket>>(){ });
        Assert.assertEquals(asList.get(0), nPacketList.get(0));
        Assert.assertEquals(asList.get(1), nPacketList.get(1));
    }

    @Test
    public void givenObjectList_whenSerializingAsJsonString() throws IOException {
        Parser parser = new Parser();
        ObjectMapper mapper = new ObjectMapper();
        String string = "["+
        "{"+
            "\"stationName\":\"MySSID\","+
                "\"power\":-10.0"+
        "},"+
                "{"+
            "\"stationName\":\"Appolica\","+
                "\"power\":-15.0"+
        "},"+
        "{"+
            "\"stationName\":\"MySSID\","+
                "\"power\":-1.0"+
        "},"+
            "{"+
            "\"stationName\":\"Appolica\","+
                "\"power\":-5.0"+
        "},"+
        "{"+
            "\"stationName\":\"Appolica\","+
                "\"power\":-50.0"+
        "}"+
        "]";


        parser.setJsonString(string);
        List<NPacket> nPacketList = parser.ParseJsonStringToList();
        String result = parser.ParseObjectsToString(nPacketList);

        Assert.assertEquals(string, result);
    }
}
