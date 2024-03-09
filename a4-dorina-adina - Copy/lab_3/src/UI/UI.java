import domain.Pacient;
import domain.Programare;

import repository.DuplicateException;
import repository.RepoGeneric;
import repository.RepositoryException;

import service.PacientService;
import service.ProgramareService;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


public class UI {
//    protected RepoGeneric<Pacient> pacientRepository = new RepoGeneric<Pacient>();
//    protected RepoGeneric<Programare> programareRepository = new RepoGeneric<Programare>();

//    private RepoGeneric<Pacient> pacientRepository = new RepoGeneric<>();
//    private RepoGeneric<Programare> programareRepository = new RepoGeneric<>();

    private PacientService pacientService;
    private ProgramareService programareService;

    public UI(PacientService pacientService, ProgramareService programareService) {
        this.pacientService = pacientService;
        this.programareService = programareService;
    }

    public void adaugam5Instante() throws RepositoryException {
        pacientService.addPacient(new Pacient(1, "Pop", "Ana", 13));
        pacientService.addPacient(new Pacient(2, "Popescu", "Silvia", 25));
        pacientService.addPacient(new Pacient(3, "Rus", "Bogdan", 15));
        pacientService.addPacient(new Pacient(4, "Antonescu", "Emil", 30));
        pacientService.addPacient(new Pacient(5, "Capota", "Dorin", 50));

        Pacient pacient1 = pacientService.findPacient(1);

        programareService.addProgramare(new Programare(1, pacient1, "2023-12-11", "12:40", "Extractie dentara"));
        programareService.addProgramare(new Programare(2, pacient1, "2024-01-12", "13:10", "Consult"));

        Pacient pacient2 = pacientService.findPacient(2);
        programareService.addProgramare(new Programare(3, pacient2, "2023-12-11", "13:40","Albire dinti"));

        Pacient pacient3 = pacientService.findPacient(3);
        programareService.addProgramare(new Programare(4, pacient3, "2023-12-11", "11:35", "Dertartraj"));

        Pacient pacient4 = pacientService.findPacient(4);
        programareService.addProgramare(new Programare(5, pacient4, "2024-02-11", "15:00","Consult"));
    }

    public void UIaddPacient() throws RepositoryException {
        int ID, varsta;
        String str;
        String nume, prenume;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dati datele pacientului de introdus: ");
        try {
            System.out.print("ID(nr. intreg): ");
            str = scanner.next();
            ID = Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println("ID-ul trebuie sa fie un numar intreg");
            return;
        }
        scanner.nextLine(); // Consume newline

        System.out.print("Nume: ");
        nume = scanner.next();

        System.out.print("Prenume: ");
        prenume = scanner.next();

        System.out.print("Varsta: ");
        try{
            str = scanner.next();
            varsta = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            System.out.println("Varsta trebuie sa fie un numar intreg");
            return;
        }
        try{
            if(varsta < 2 || varsta > 110)
                throw new Exception("Varsta trebuie sa fie cuprinsa intre 2 si 110");
        } catch (Exception e) {
            System.out.println("Varsta trebuie sa fie cuprinsa intre 2 si 110");
            return;
        }
        scanner.nextLine(); // Consume newline

        Pacient pacient = new Pacient(ID, nume, prenume, varsta);
        pacientService.addPacient(pacient);
    }



    public void UIupdatePacient() throws RepositoryException {
        int ID, varsta;
        String str;
        String nume, prenume;
        System.out.println("Dati datele pacientului de modificat: ");
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("ID(nr. intreg): ");
            str = scanner.next();
            ID = Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println("ID-ul trebuie sa fie un numar intreg");
            return;
        }
        scanner.nextLine(); // Consume newline

        System.out.println("Nume: ");
        nume = scanner.next();

        System.out.println("Prenume: ");
        prenume = scanner.next();

        try{
            System.out.print("Varsta: ");
            str = scanner.next();
            varsta = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            System.out.println("Varsta trebuie sa fie un numar intreg");
            return;
        }
        try{
            if(varsta < 2 || varsta > 110)
                throw new Exception("Varsta trebuie sa fie cuprinsa intre 2 si 110");
        } catch (Exception e) {
            System.out.println("Varsta trebuie sa fie cuprinsa intre 2 si 110");
            return;
        }
        scanner.nextLine(); // Consume newline



        Pacient pacientNew = new Pacient(ID, nume, prenume, varsta);
        List <Programare> programari = (List<Programare>) programareService.getAll();
        int lungime = programari.size();
        int  i = 0;
        while(i < lungime)
        {
            Pacient pacient = programari.get(i).getPacient();
            if (pacient.getId() == ID) {
                Programare programare = programareService.findProgramare(programari.get(i).getId());
                programare.setPacient(pacientNew);
            }
            i++;
        }
        pacientService.updatePacient(pacientNew);
    }

    public void UIremovePacient() throws RepositoryException {
        int ID;
        String str;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Dati id-ul pacientului de sters(nr. intreg): ");
            str = scanner.next();
            ID = Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println("ID-ul trebuie sa fie un numar intreg");
            return;
        }
        scanner.nextLine(); // Consume newline



        List <Programare> programari = (List<Programare>) programareService.getAll();
        int lungime = programari.size();
        int  i = 0;
        while(i < lungime)
        {
            Pacient pacient = programari.get(i).getPacient();
            if (pacient.getId() == ID) {
                programareService.removeProgramare(programari.get(i).getId());
                programari = (List<Programare>) programareService.getAll();
                lungime = programari.size();
            }
            else i++;
        }
        pacientService.removePacient(ID);
    }

    public void UIfindPacient() throws RepositoryException {
        int ID;
        String str;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Dati id-ul pacientului de cautat(nr. intreg): ");
            str = scanner.next();
            ID = Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println("ID-ul trebuie sa fie un numar intreg");
            return;
        }
        scanner.nextLine(); // Consume newline

        if(pacientService.findPacient(ID) != null) {
            Pacient pacient = pacientService.findPacient(ID);
            System.out.println(pacient);
        }
        else System.out.println();
    }


    public void UIgetAllPacienti(){
        List pacienti;
        pacienti = pacientService.getAll();
        for(Object pacient : pacienti){
            System.out.println(pacient);
        }
    }





    public void UIaddProgramare() throws RepositoryException {
        int ID, IDpacient;
        String str;
        String scopulProgramarii;
        String data, ora;
        String[] oraSplit;
        String[] dataSplit;
        int h1,h2,m1;
        int a,l,z;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Dati datele programarii: ");

        try {
            System.out.print("ID programare(nr. intreg): ");
            str = scanner.next();
            ID = Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println("ID-ul trebuie sa fie un numar intreg");
            return;
        }
        scanner.nextLine(); // Consume newline


//        try {
//            System.out.print("ID-ul pacientului de programat: "); ///dam id-ul pacientului caruia dorim sa ii atribuim programarea
//            str = scanner.next();
//            IDpacient = Integer.parseInt(str);
//        }catch (NumberFormatException e){
//            System.out.println("ID-ul trebuie sa fie un numar intreg"); ///verificam ca id-ul pacientului sa fie nr intreg
//            return;
//        }
//        try{
//            if(pacientService.findPacient(IDpacient) == null)
//                throw new RepositoryException("Pacientul cu id-ul specificat nu exista"); ///verificam sa existe pacientul cu id-ul dat
//        }catch (RepositoryException e){
//            System.out.println();
//            return;
//        } scanner.nextLine(); // Consume newline

        int IDp, varsta;
        String nume, prenume;
        System.out.println("Dati datele pacientului de introdus: ");
        try {
            System.out.print("ID(nr. intreg): ");
            str = scanner.next();
            IDp = Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println("ID-ul trebuie sa fie un numar intreg");
            return;
        }
        scanner.nextLine(); // Consume newline

        System.out.print("Nume: ");
        nume = scanner.next();

        System.out.print("Prenume: ");
        prenume = scanner.next();

        System.out.print("Varsta: ");
        try{
            str = scanner.next();
            varsta = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            System.out.println("Varsta trebuie sa fie un numar intreg");
            return;
        }
        try{
            if(varsta < 2 || varsta > 110)
                throw new Exception("Varsta trebuie sa fie cuprinsa intre 2 si 110");
        } catch (Exception e) {
            System.out.println("Varsta trebuie sa fie cuprinsa intre 2 si 110");
            return;
        }
        scanner.nextLine(); // Consume newline

        Pacient pacient = new Pacient(ID, nume, prenume, varsta);

        System.out.print("Data(aaaa-ll-zz) la care se programeaza pacientul: ");
        //TODO EXCEPTII DATA
        data = scanner.next();
        dataSplit = data.split("-");

        try {
            a = Integer.parseInt(dataSplit[0]);
        }catch (NumberFormatException e){
            System.out.println("Anul trebuie sa fie un numar intreg");
            return;
        }
        try{
            if(a < 2023 || a > 2024)
                throw new Exception("Anul sa fie intre 2023 si 2024");
        } catch (Exception e) {
            System.out.println("Anul sa fie intre 2023 si 2024");
            return;
        }

        try {
            l = Integer.parseInt(dataSplit[1]);
        }catch (NumberFormatException e){
            System.out.println("Luna trebuie sa fie un numar intreg");
            return;
        }
        try{
            if(l < 1 || l > 12)
                throw new Exception("Atentie!");
        } catch (Exception e) {
            System.out.println("Atentie!");
            return;
        }
        try {
            z = Integer.parseInt(dataSplit[2]);
        }catch (NumberFormatException e){
            System.out.println("Ziua trebuie sa fie un numar intreg");
            return;
        }
        try{
            if((l == 1 || l == 3 || l == 5 || l == 7 || l == 8 || l == 10 || l == 12) && (z < 1 || z > 31))
                throw new Exception("Atentie!");
            else if((l == 4 || l == 6 || l == 9 || l == 11) && (z < 1 || z > 30))
                throw new Exception("Atentie!");
            else if((l == 2) && (z < 1 || z > 28))
                throw new Exception("Atentie!");
        } catch (Exception e) {
            System.out.println("Atentie!");
            return;
        }


        System.out.print("Ora(hh:mm) la care se programeaza pacientul(programul de functionare este intre 8 si 20): ");
        ora = scanner.next();
        oraSplit = ora.split(":");

        try {
            h1 = Integer.parseInt(oraSplit[0]);
        }catch (NumberFormatException e){
            System.out.println("Ora trebuie sa fie un numar intreg");
            return;
        }
        try{
            if(h1 < 8 || h1 > 19)
                throw new Exception("Ora trebuie sa fie intre 8 si 20, acesta e programul nostru de functionare");
        } catch (Exception e) {
            System.out.println("Ora trebuie sa fie intre 8 si 20, acesta e programul nostru de functionare");
            return;
        }

        try {
            m1 = Integer.parseInt(oraSplit[1]);
        }catch (NumberFormatException e){
            System.out.println("Minutele trebuie sa fie un numar intreg");
            return;
        }
        try{
            if(m1 < 00 || m1 > 59)
                throw new Exception("Atentie!");
        } catch (Exception e) {
            System.out.println("Atentie!");
            return;
        }

        try{
            List <Programare> programari = (List<Programare>) programareService.getAll();
            for(Programare programare: programari){
                if(programare.getData().equals(data))
                {
                    String[] ora2Split = programare.getOra().split(":");
                    h2 = Integer.parseInt(ora2Split[0]);

                    if(Objects.equals(oraSplit[0], ora2Split[0]))
                        throw new Exception("Nu e buna ora");
                    else if (h1+1 == h2 && oraSplit[1].compareTo(ora2Split[1])>0) {
                        throw new Exception("Nu e buna ora");
                    }
                    else if(h1-1 == h2 && oraSplit[1].compareTo(ora2Split[1])<0){
                        throw new Exception("Nu e buna ora");

                    }
                }
            }
        }catch (Exception e){
            System.out.println("Nu e buna ora");
            return;
        }

        System.out.print("Scopul programarii: ");
        scopulProgramarii = scanner.next();

        Programare programare = null;
        programare = new Programare(ID, pacient, data, ora, scopulProgramarii);

        programareService.addProgramare(programare);
    }

    public void UIupdateProgramare() throws RepositoryException {
        int ID, IDpacientNou;
        String str;
        String scopulProgramariiNou;
        String dataNoua;
        String oraNoua;
        String[] oraSplit;
        String[] dataSplit;
        int h1,m1,h2;
        int a,l,z;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Dati datele programarii de modificat: ");

        try {
            System.out.print("ID programare(nr. intreg): ");
            str = scanner.next();
            ID = Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println("ID-ul trebuie sa fie un numar intreg");
            return;
        }
        scanner.nextLine(); // Consume newline

//        try {
//            System.out.print("ID-ul pacientului de programat(nr. intreg): "); ///dam id-ul pacientului
//            str = scanner.next();
//            IDpacientNou = Integer.parseInt(str);
//        }catch (NumberFormatException e){
//            System.out.println("ID-ul trebuie sa fie un numar intreg"); ///verificam ca id-ul pacientului sa fie un nr intreg
//            return;
//        }
//        try{
//            if(pacientService.findPacient(IDpacientNou) == null)
//                throw new RepositoryException("Nu exista o entitate cu acest id"); ///verificam sa existe pacientul cu id-ul dat
//        }catch (RepositoryException e) {
//            System.out.println("Nu exista o entitate cu acest id");
//            return;
//        }
////         try{
////            if(pacientService.findPacient(IDpacientNou) == null)
////                throw new RepositoryException("Entitatea cu id-ul specificat nu exista");
////        }catch (RepositoryException e){
////            System.out.println();
////            return;
////        }
//            scanner.nextLine(); // Consume newline/
        int IDp, varsta;
        String nume, prenume;
        System.out.println("Dati datele pacientului de modificat: ");
        try {
            System.out.print("ID(nr. intreg): ");
            str = scanner.next();
            IDp = Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println("ID-ul trebuie sa fie un numar intreg");
            return;
        }
        scanner.nextLine(); // Consume newline

        System.out.println("Nume: ");
        nume = scanner.next();

        System.out.println("Prenume: ");
        prenume = scanner.next();

        try{
            System.out.print("Varsta: ");
            str = scanner.next();
            varsta = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            System.out.println("Varsta trebuie sa fie un numar intreg");
            return;
        }
        try{
            if(varsta < 2 || varsta > 110)
                throw new Exception("Varsta trebuie sa fie cuprinsa intre 2 si 110");
        } catch (Exception e) {
            System.out.println("Varsta trebuie sa fie cuprinsa intre 2 si 110");
            return;
        }
        scanner.nextLine(); // Consume newline

        Pacient pacientNew = new Pacient(ID, nume, prenume, varsta);

        System.out.print("Data(aaaa-ll-zz) la care se programeaza pacientul: ");
        //TODO data frumos facuta
        dataNoua = scanner.next();
        dataSplit = dataNoua.split("-");

        try {
            a = Integer.parseInt(dataSplit[0]);
        }catch (NumberFormatException e){
            System.out.println("Anul trebuie sa fie un numar intreg");
            return;
        }
        try{
            if(a < 2023 || a > 2024)
                throw new Exception("Anul sa fie intre 2023 si 2024");
        } catch (Exception e) {
            System.out.println("Anul sa fie intre 2023 si 2024");
            return;
        }

        try {
            l = Integer.parseInt(dataSplit[1]);
        }catch (NumberFormatException e){
            System.out.println("Luna trebuie sa fie un numar intreg");
            return;
        }
        try{
            if(l < 1 || l > 12)
                throw new Exception("Atentie!");
        } catch (Exception e) {
            System.out.println("Atentie!");
            return;
        }
        try {
            z = Integer.parseInt(dataSplit[2]);
        }catch (NumberFormatException e){
            System.out.println("Ziua trebuie sa fie un numar intreg");
            return;
        }
        try{
            if((l == 1 || l == 3 || l == 5 || l == 7 || l == 8 || l == 10 || l == 12) && (z < 1 || z > 31))
                throw new Exception("Atentie!");
            else if((l == 4 || l == 6 || l == 9 || l == 11) && (z < 1 || z > 30))
                throw new Exception("Atentie!");
            else if((l == 2) && (z < 1 || z > 28))
                throw new Exception("Atentie!");
        } catch (Exception e) {
            System.out.println("Atentie!");
            return;
        }


        System.out.print("Ora(hh:mm) la care se programeaza pacientul(programul de functionare este intre 8 si 20): ");
        ///TODO ora frumos facuta
        oraNoua = scanner.next();
        oraSplit = oraNoua.split(":");

        try {
            h1 = Integer.parseInt(oraSplit[0]);
        }catch (NumberFormatException e){
            System.out.println("Ora trebuie sa fie un numar intreg");
            return;
        }
        try{
            if(h1 < 8 || h1 > 19)
                throw new Exception("Ora trebuie sa fie intre 8 si 20, acesta e programul nostru de functionare");
        } catch (Exception e) {
            System.out.println("Ora trebuie sa fie intre 8 si 20, acesta e programul nostru de functionare");
            return;
        }

        try {
            m1 = Integer.parseInt(oraSplit[1]);
        }catch (NumberFormatException e){
            System.out.println("Minutele trebuie sa fie un numar intreg");
            return;
        }
        try{
            if(m1 < 00 || m1 > 59)
                throw new Exception("Atentie!");
        } catch (Exception e) {
            System.out.println("Atentie!");
            return;
        }

        try{
            List <Programare> programari = (List<Programare>) programareService.getAll();
            for(Programare programare: programari){
                if(programare.getData().equals(dataNoua))
                {
                    String[] ora2Split = programare.getOra().split(":");
                    h2 = Integer.parseInt(ora2Split[0]);

                    if(Objects.equals(oraSplit[0], ora2Split[0]))
                        throw new Exception("Nu e buna ora");
                    else if (h1+1 == h2 && oraSplit[1].compareTo(ora2Split[1])>0) {
                        throw new Exception("Nu e buna ora");
                    }
                    else if(h1-1 == h2 && oraSplit[1].compareTo(ora2Split[1])<0){
                        throw new Exception("Nu e buna ora");

                    }
                }
            }
        }catch (Exception e){
            System.out.println("Nu e buna ora");
            return;
        }

        System.out.print("Scopul programarii: ");
        scopulProgramariiNou = scanner.next();

        Programare programare = new Programare(ID, pacientNew, dataNoua, oraNoua, scopulProgramariiNou);
        programareService.updateProgramare(programare);
    }

    public void UIremoveProgramare() throws RepositoryException {
        int ID;
        String str;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Dati id-ul programarii de sters(nr. intreg): ");
            str = scanner.next();
            ID = Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println("ID-ul trebuie sa fie un numar intreg");
            return;
        }
        scanner.nextLine(); // Consume newline

        programareService.removeProgramare(ID);
    }

    public void UIfindProgramare() throws RepositoryException {
        int ID;
        String str;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Dati id-ul programarii de cautat(nr. intreg): ");
            str = scanner.next();
            ID = Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.out.println("ID-ul trebuie sa fie un numar intreg");
            return;
        }
        scanner.nextLine(); // Consume newline

        if(programareService.findProgramare(ID) != null) {
            Programare programare = programareService.findProgramare(ID);
            System.out.println(programare);
        }
        else System.out.println("Nu se gaseste programarea");
    }

    public void UIgetAllProgramari(){
        List programari;
        programari = programareService.getAll();
        for(Object programare : programari){
            System.out.println(programare);
        }
    }




    public void meniu() throws RepositoryException { ///meniu cu optiuni
//        adaugam5Instante();
        Scanner scanner = new Scanner(System.in);
        int choice; String str;
        do {
            try{
                System.out.println("BUNA ZIUA! Va rugam sa alegeti o optiune: ");

                System.out.println("1. Adauga pacient");
                System.out.println("2. Afiseaza toti pacientii");
                System.out.println("3. Afiseaza pacient dupa ID");
                System.out.println("4. Actualizeaza pacient");
                System.out.println("5. Sterge pacient");
                System.out.println("6. Adauga programare");
                System.out.println("7. Afiseaza toate programarile");
                System.out.println("8. Afiseaza programare dupa ID");
                System.out.println("9. Actualizeaza programare");
                System.out.println("10. Sterge programare");
                System.out.println("11. Numar programari per pacient");
                System.out.println("12. Numar programari per luna");
                System.out.println("13. Raport zile trecute de la ultima programare");
                System.out.println("14. Cele mai aglomerate luni");
                System.out.println("0. Iesire");

                System.out.print("Optiune: ");

                str = scanner.next();
                choice = Integer.parseInt(str);}
            catch (NumberFormatException e){
                System.out.println("Optiunea trebuie sa fie un numar intreg");
                return;
            }
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    UIaddPacient();
                    break;
                case 2:
                    UIgetAllPacienti();
                    break;
                case 3:
                    UIfindPacient();
                    break;
                case 4:
                    UIupdatePacient();
                    break;
                case 5:
                    UIremovePacient();
                    break;
                case 6:
                    UIaddProgramare();
                    break;
                case 7:
                    UIgetAllProgramari();
                    break;
                case 8:
                    UIfindProgramare();
                    break;
                case 9:
                    UIupdateProgramare();
                    break;
                case 10:
                    UIremoveProgramare();
                    break;
                case 11:
                    Map<Pacient, Long> rezultat = programareService.numarProgramariPerPacient();
                    rezultat.forEach((pacient, numarProgramari) -> System.out.println(pacient + ": " + numarProgramari + " programari"));
                    break;
                case 12:
                    List<Programare> programari = programareService.getAll();
//                    String rezultat2 = programareService.numarProgramariPerLuna();
                    String programariReport = programareService.numarProgramariPerLuna();
                    System.out.println(programariReport);
                    break;
                case 13:
//                    Map<Pacient, Long> rezultat3 = zileTrecuteDeLaUltimaProgramare(pacienti);
//                    rezultat3.forEach((pacient, numarZile) -> System.out.println(pacient + ": " + numarZile + " zile trecute de la ultima programare"));
                    programari = programareService.getAll();
//                    int raport = programareService.raportZileTrecuteDeLaUltimaProgramare(programari).size();
//                    System.out.println("Raport Zile Trecute de la Ultima Programare:\n" + raport);
//                    Map<String, Long> raport = programareService.raportZileTrecuteDeLaUltimaProgramare(programari);
//                    raport.forEach((pacient, zile) -> System.out.println(pacient + ": " + zile + " zile trecute de la ultima programare"));
                    Map<String, Map<String, ?>> raport = programareService.raportZileTrecuteDeLaUltimaProgramare(programari);
                    raport.forEach((pacient, rezultate) ->
                            System.out.println(pacient + ": " +
                                    "Zile trecute: " + rezultate.get("zileTrecute") +
                                    ", Data ultimei programari: " + rezultate.get("dataUltimaProgramare")));
                    break;
                case 14:
                    programari = programareService.getAll();

                    Map<Month, Long> rezultat4 = programareService.celeMaiAglomerateLuni(programari);
//                    rezultat4.forEach((luna, numarProgramari) -> System.out.println(luna + ": " + numarProgramari + " programari"));
                    rezultat4.forEach((luna, numarProgramari) -> System.out.println(luna + ": " + numarProgramari + " programari"));

                    break;
                case 0:
                    System.out.println("Sfarsit");
                    break;
                default:
                    System.out.println("Optiune invalida. Va rugam sa incercati din nou.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
