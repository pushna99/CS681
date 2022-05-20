package edu.umb.cs681.hw17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

            
            List michigan = matrix.stream().parallel().filter((i) -> i.get(4).contains("Michigan")).collect(Collectors.toList());
            
            List clinton = matrix.stream().parallel().filter((i) -> i.get(5).contains("Clinton")).collect(Collectors.toList()).get(0);

            List dickinson = matrix.stream().parallel().filter((i) -> i.get(5).contains("Dickinson")).collect(Collectors.toList()).get(0);

            float clintonIR = Float.parseFloat((String) clinton.get(8));
            float dickinsonIR = Float.parseFloat((String) dickinson.get(8));

            String caseTotal = matrix.stream().parallel().filter((i) -> i.get(4).contains("Michigan"))
                    .map((i) -> i.get(6)).reduce("0", (temp, result) -> String.valueOf(Integer.parseInt(temp) + Integer.parseInt(result)));


            System.out.println("Total no. of cases in Michigan: " + caseTotal);
            System.out.println("Infection rate in Clinton County: " + clinton.get(8));
            System.out.println("Infection rate in Dickinson County: " + dickinson.get(8));

            if(clintonIR > dickinsonIR){
                System.out.println("Infection Rate in Clinton is "+(clintonIR-dickinsonIR)+" times more than Dickinson");
            }else{
                System.out.println("Infection Rate in Dickinson is "+(dickinsonIR-clintonIR)+" times more than Clinton");
            }

        }

        catch(IOException e) {
            e.printStackTrace();
        }
    }

}