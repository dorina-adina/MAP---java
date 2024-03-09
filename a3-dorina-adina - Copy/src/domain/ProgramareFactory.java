//
//import repository.RepositoryException;
//import service.PacientService;
//import service.ProgramareService;
//
//public class ProgramareFactory implements IEntityFactory<Programare> {
//    PacientService pacientService = new PacientService();
//    @Override
//    public Programare createEntity(String line) throws RepositoryException {
//        int id = Integer.parseInt(line.split(",")[0]);
//        int idPacient = Integer.parseInt(line.split(",")[1]);
//        String nume = line.split(",")[2];
//        String prenume = line.split(",")[3];
//        int varsta = Integer.parseInt(line.split(",")[4]);
//        Pacient pacient = new Pacient(idPacient, nume, prenume, varsta);
//        String data = line.split(",")[5];
//        String ora = line.split(",")[6];
//        String scopProgramare = line.split(",")[7];
//
//        return new Programare(id, pacient, data, ora, scopProgramare);
//    }
//}
