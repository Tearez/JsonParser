package com.JsonParser.JsonParser;

import com.JsonParser.JsonParser.Models.NPacket;
import com.JsonParser.JsonParser.Parser.Parser;

import java.io.*;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        Parser parser = new Parser();
        File f = new File(args[1]);
        String jsonString = ReadInputFile(f);
        System.out.println(jsonString);
        parser.setJsonString(jsonString);

        List<NPacket> nPacketList = new ArrayList<>();
        HashMap<String, List<NPacket>> map = new HashMap<>();

        try {
            nPacketList = parser.ParseJsonStringToList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        nPacketList.forEach(x->{
            if (map.containsKey(x.getStationName())){
                map.get(x.getStationName()).add(x);
            } else {
                map.put(x.getStationName(), new ArrayList<>());
                map.get(x.getStationName()).add(x);
            }
        });

        nPacketList = AvarageSignalPower(map);
        nPacketList.forEach(System.out::println);
        try {
            WriteJsonToOutput(args[3], nPacketList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String ReadInputFile(File file){
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try (InputStream inputStream = new FileInputStream(file)){
            if (inputStream.available() > 0){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                    stringBuilder.append('\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static List<NPacket> AvarageSignalPower(Map<String, List<NPacket>> map) {
        double average = 0;
        List<NPacket> avgList = new ArrayList<>();
        for (String string: map.keySet()) {
            average = map.get(string).stream().mapToDouble(NPacket::getPower).sum();
            average = average/map.get(string).size();
            avgList.add(new NPacket(string, average));
        }
        return avgList;
    }

    public static void WriteJsonToOutput(String output, List<NPacket> average) throws IOException {
        PrintStream out = new PrintStream(new FileOutputStream(output));
        Parser parser = new Parser();
        String json = parser.ParseObjectsToString(average);
        out.print(json);
    }
}
