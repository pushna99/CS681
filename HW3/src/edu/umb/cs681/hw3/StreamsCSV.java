package edu.umb.cs681.hw3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsCSV {

    public static void main(String[] args) {

        Path path = Path.of("Data", "PVIData.csv");

        try( Stream<String> lines = Files.lines(path) ){
            List<List<String>> matrix = lines.map(line -> {
                        return Stream.of( line.split(",") )
                                .map(value->value.substring(0, value.length()))
                                .collect( Collectors.toList() ); })
                    .collect( Collectors.toList() );


            List massachusetts = matrix.stream().filter((i) -> i.get(4).contains("Michigan")).collect(Collectors.toList());
            String totalCases = matrix.stream().filter((i) -> i.get(4).contains("Michigan"))
                    .map((i) -> i.get(6)).reduce("0", (temp, result) -> String.valueOf(Integer.parseInt(temp) + Integer.parseInt(result)));

            List delta = matrix.stream().filter((i) -> i.get(5).contains("Delta")).collect(Collectors.toList()).get(0);

            List dickinson = matrix.stream().filter((i) -> i.get(5).contains("Dickinson")).collect(Collectors.toList()).get(0);

            float delIR = Float.parseFloat((String) delta.get(8));
            float dicIR = Float.parseFloat((String) dickinson.get(8));

            System.out.println("Total no. of cases in Michigan: " + totalCases);
            System.out.println("\nInfection rate in Delta County: " + delta.get(8));
            System.out.println("\nInfection rate in Dickinson County: " + dickinson.get(8));

            if(delIR > dicIR){
                System.out.println("\nInfection Rate in Delta is "+(delIR-dicIR)+" times more than Dickinson");
            }else{
                System.out.println("\nInfection Rate in Dickinson is "+(dicIR-delIR)+" times more than Delta");
            }

        }

        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

}