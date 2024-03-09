package repository;

public class DuplicateException extends RepositoryException{
    public DuplicateException(String message) {
        super(message);
    }
}
