//
//public class PacientFactory implements IEntityFactory<Pacient> {
//    @Override
//    public Pacient createEntity(String line) {
//        String[] parts = line.split(",");
//
//        int id = Integer.parseInt(parts[0]);
//        String nume = parts[1].trim();
//        String prenume = parts[2].trim();
//        int varsta = Integer.parseInt(parts[3]);
//
//        Pacient pacient = new Pacient(id, nume, prenume, varsta);
//        return pacient;
//
//    }
//}