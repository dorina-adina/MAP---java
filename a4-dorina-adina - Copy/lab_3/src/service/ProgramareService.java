import domain.Pacient;
import domain.Programare;

import repository.DuplicateException;
import repository.RepoGeneric;
import repository.RepositoryException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.time.Month;


import java.time.Month;



public class ProgramareService {
    protected static RepoGeneric<Programare> programareRepository = new RepoGeneric<Programare>();

    public ProgramareService(RepoGeneric<Programare> programareRepository) {
        this.programareRepository = programareRepository;
    }


    public void addProgramare(Programare programare) throws RepositoryException {
        programareRepository.add(programare);
    }

    public void updateProgramare(Programare programareNew) throws RepositoryException {
        programareRepository.update(programareNew);
    }

    public void removeProgramare(int id) throws RepositoryException {
        programareRepository.remove(id);
    }

    public Programare findProgramare(int id) throws RepositoryException {
        return programareRepository.find(id);
    }


    public List<Programare> getAll() {
        return programareRepository.getAll();
    }

    public Map<Pacient, Long> numarProgramariPerPacient() {
        return programareRepository.getAll().stream()
                .collect(Collectors.groupingBy(Programare::getPacient, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Pacient, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }


    public String numarProgramariPerLuna() {
        Collection<Programare> programari = programareRepository.getAll();
        Map<String, Long> programariPerMonth = programari.stream()
                .filter(p -> p.getData() != null)
                .collect(Collectors.groupingBy(
                        p -> LocalDate.parse(p.getData()).getMonth().toString(),
                        Collectors.counting()
                ));

        EnumMap<Month, Long> result = new EnumMap<>(Month.class);
        for (Month month : Month.values()) {
            result.put(month, programariPerMonth.getOrDefault(month.toString(), 0L));
        }

        StringBuilder output = new StringBuilder();
        result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> output.append(entry.getKey()).append(": ").append(entry.getValue()).append(" programari\n"));

        return output.toString();
    }


    public Map<Month, Long> celeMaiAglomerateLuni(List<Programare> programari) {
        return programari.stream()
                .map(p -> LocalDate.parse(p.getData()))
                .collect(Collectors.groupingBy(
                        LocalDate::getMonth,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

    }

    List<Programare> programari = programareRepository.getAll();


    public Map<String, Map<String, ?>> raportZileTrecuteDeLaUltimaProgramare(List<Programare> programari) {
        return programari.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getPacient().getNume() + " " + p.getPacient().getPrenume(),
                        Collectors.collectingAndThen(
                                Collectors.reducing((Programare p1, Programare p2) ->
                                        p1.getData().compareTo(p2.getData()) >= 0 ? p1 : p2),
                                (Optional<Programare> lastAppointment) ->
                                        lastAppointment.map(appointment -> {
                                            long zileTrecute = ChronoUnit.DAYS.between(LocalDate.parse(appointment.getData()), LocalDate.now());
                                            Map<String, Object> resultMap = new HashMap<>();
                                            resultMap.put("zileTrecute", zileTrecute);
                                            resultMap.put("dataUltimaProgramare", appointment.getData());
                                            return resultMap;
                                        }).orElseGet(() -> {
                                    Map<String, Object> resultMap = new HashMap<>();
                                    resultMap.put("zileTrecute", 0L);
                                    resultMap.put("dataUltimaProgramare", null);  // You can modify this to a default value if needed
                                    return resultMap;})
                )));
    }
}

